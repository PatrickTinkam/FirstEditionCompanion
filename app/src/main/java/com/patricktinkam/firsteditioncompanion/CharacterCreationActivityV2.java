package com.patricktinkam.firsteditioncompanion;

import android.text.*;
import android.view.*;
import android.widget.*;
import java.util.*;

/** v0.9.4 presentation/validation layer for the guided creator. */
public class CharacterCreationActivityV2 extends CharacterCreationActivity {
  @Override void buildShell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("CREATE CHARACTER  •  v0.9.4",19,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    progress=t("",13,MUT,true);progress.setGravity(Gravity.CENTER);root.addView(progress,new LinearLayout.LayoutParams(-1,dp(38)));
    ScrollView scroll=new ScrollView(this);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(dp(6),dp(4),dp(6),dp(22));scroll.addView(content);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setPadding(dp(7),dp(6),dp(7),dp(7));
    Button cancel=b("Cancel");cancel.setOnClickListener(v->cancelWizard());nav.addView(cancel,new LinearLayout.LayoutParams(0,dp(52),1));
    backButton=b("Back");backButton.setOnClickListener(v->{readCurrentStep();if(draft.step>0){draft.step--;saveDraft();render();}});nav.addView(backButton,new LinearLayout.LayoutParams(0,dp(52),1));
    continueButton=b("Continue");continueButton.setOnClickListener(v->advance());nav.addView(continueButton,new LinearLayout.LayoutParams(0,dp(52),1));
    root.addView(nav,new LinearLayout.LayoutParams(-1,-2));
  }

  @Override void classStep(){
    RaceData.Race race=RaceData.find(draft.raceName);
    LinearLayout intro=card("Choose Class / Subclass");
    intro.addView(t("This step checks only whether your selected race can use a class. Ability-score qualification happens after you roll and distribute scores on the next step. Every class stays visible so you can inspect its details before deciding whether to change race.",14,TXT,false));
    add(content,intro);

    ArrayList<ClassData.Entry> classes=ClassData.all();
    ArrayList<String> labels=new ArrayList<>();
    int selected=-1,firstAllowed=-1;
    for(int i=0;i<classes.size();i++){
      ClassData.Entry c=classes.get(i);
      CharacterRules.Availability a=CharacterRules.availability(race,c);
      labels.add((a.allowed?"✓ ":"⛔ ")+c.label()+(a.allowed?"  •  race-compatible":"  •  race restricted"));
      if(a.allowed&&firstAllowed<0)firstAllowed=i;
      if(c.name.equals(draft.className))selected=i;
    }
    if(selected<0)selected=firstAllowed>=0?firstAllowed:0;

    Spinner spin=new Spinner(this);spin.setAdapter(spinnerAdapter(labels));spin.setSelection(selected);
    LinearLayout choose=card("Class");choose.addView(spin,new LinearLayout.LayoutParams(-1,dp(58)));
    TextView status=t("",14,TXT,true),req=t("",13,GOLD,true),quick=t("",12,TXT,false),source=t("",12,MUT,false),timing=t("",12,MUT,false);
    choose.addView(status);choose.addView(req);choose.addView(quick);choose.addView(source);choose.addView(timing);
    Button details=b("Class Details");choose.addView(details,new LinearLayout.LayoutParams(-1,dp(48)));add(content,choose);

    Runnable refresh=()->{
      ClassData.Entry c=ClassData.find(draft.className);if(c==null)return;
      CharacterRules.Availability a=CharacterRules.availability(race,c);
      if(a.allowed){
        status.setText("RACE-COMPATIBLE\nAbility eligibility is still pending until scores are rolled/distributed.");
        status.setTextColor(GOOD);
        timing.setText("Continue is based only on race/class legality here. The Ability Scores screen will enforce the class minimums after racial adjustments, and Age will recheck the final scores again afterward.");
      }else{
        status.setText("RACE RESTRICTION\n"+a.reason);
        status.setTextColor(WARN);
        timing.setText("Ability rolls cannot override a core race/class restriction. You can still open Class Details, then go Back and change race if you want this class.");
      }
      req.setText("Ability requirements — checked after distribution: "+c.requirements);
      quick.setText("Hit Die: "+c.hitDie+"  •  Alignment: "+c.alignment+"\nArmor & weapons: "+c.armorWeapons);
      source.setText("Source: "+c.source);
      details.setOnClickListener(v->classDetails(c));
      updateContinueState();
    };

    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      public void onItemSelected(AdapterView<?> p,View v,int pos,long id){draft.className=classes.get(pos).name;saveDraft();refresh.run();}
      public void onNothingSelected(AdapterView<?> p){}
    });
    if(draft.className.isEmpty()){draft.className=classes.get(selected).name;saveDraft();}
    refresh.run();

    LinearLayout note=card("Why the checks are split");
    note.addView(t("In core 1e, race determines which player-character classes exist for that race. Ability scores separately determine whether this particular character qualifies for the chosen class. Some nonhuman class level limits also vary with ability scores, but those are advancement limits rather than a reason to reject an otherwise legal level-1 class choice here.",12,MUT,false));
    add(content,note);
  }

  @Override void abilityStep(){
    LinearLayout intro=card("Establish Ability Scores");
    intro.addView(t("Roll first, then arrange/edit scores as your chosen method allows. This is where the selected class's minimum ability requirements are actually enforced. Raw scores stay separate from adjusted scores so racial modifiers can never be applied twice.",14,TXT,false));
    add(content,intro);

    LinearLayout roll=card("Roll Ability Scores");
    Spinner method=new Spinner(this);ArrayList<String> methods=new ArrayList<>(Arrays.asList(ROLL_METHODS));method.setAdapter(spinnerAdapter(methods));int mi=Math.max(0,methods.indexOf(draft.rollMethod));method.setSelection(mi);roll.addView(method,new LinearLayout.LayoutParams(-1,dp(58)));
    Button rollButton=b("ROLL");roll.addView(rollButton,new LinearLayout.LayoutParams(-1,dp(56)));
    TextView rollNote=t("",12,MUT,false);roll.addView(rollNote);add(content,roll);

    LinearLayout scores=card("Raw → Racially Adjusted Scores");
    scores.addView(t("Racial modifiers: "+CharacterRules.modifierSummary(draft.raceName),13,GOLD,true));
    TextView finalPreview=t("",13,TXT,false);scores.addView(finalPreview);

    ClassData.Entry cls=ClassData.find(draft.className);
    LinearLayout validate=card("Class Qualification Check");
    validate.addView(t("Selected class: "+(cls==null?draft.className:cls.name),14,TXT,true));
    validate.addView(t("Requirements: "+(cls==null?"Unknown":cls.requirements),13,GOLD,true));
    TextView check=t("",13,TXT,false);validate.addView(check);
    validate.addView(t("Passing here means the race/class combination is legal and the racially adjusted scores meet the current starting requirements. Age adjustments are applied next and the app will recheck the final scores there as well.",12,MUT,false));

    Runnable refresh=()->{
      finalPreview.setText(scoreText(false));
      ArrayList<String> problems=CharacterRules.validateAbilityScores(draft);
      check.setText(problems.isEmpty()?"✓ Ability distribution qualifies for the selected class.":joinProblems(problems));
      check.setTextColor(problems.isEmpty()?GOOD:WARN);
      updateContinueState();
    };

    for(int i=0;i<6;i++){
      final int ix=i;
      LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
      row.addView(t(CharacterDraft.ABILITIES[i],14,MUT,true),new LinearLayout.LayoutParams(dp(80),-2));
      EditText q=e(draft.rawScores[i]>0?String.valueOf(draft.rawScores[i]):"",CharacterDraft.ABILITIES[i],true);
      row.addView(q,new LinearLayout.LayoutParams(0,-2,1));scores.addView(row);
      q.addTextChangedListener(new SimpleWatcher(){public void afterTextChanged(Editable z){
        try{draft.rawScores[ix]=Integer.parseInt(z.toString());}catch(Exception ex){draft.rawScores[ix]=0;}
        saveDraft();refresh.run();
      }});
    }
    add(content,scores);add(content,validate);

    method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      public void onItemSelected(AdapterView<?> p,View v,int pos,long id){
        draft.rollMethod=ROLL_METHODS[pos];saveDraft();boolean manual="Manual entry".equals(draft.rollMethod);
        rollButton.setEnabled(!manual);rollButton.setText(manual?"Manual Entry Selected":"ROLL");
        rollNote.setText(manual?"Enter and distribute scores below.":"Rolled values populate the fields below. Methods that allow arrangement can be rearranged by editing the raw-score fields before continuing.");
      }
      public void onNothingSelected(AdapterView<?> p){}
    });
    rollButton.setOnClickListener(v->rollScores());
    refresh.run();
  }
}
