package com.patricktinkam.firsteditioncompanion;

import android.text.*;
import android.view.*;
import android.widget.*;
import java.util.*;

/** v0.9.5 presentation/validation layer for the guided creator. */
public class CharacterCreationActivityV2 extends CharacterCreationActivity {
  @Override void buildShell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("CREATE CHARACTER  •  v0.9.5",19,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
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

  boolean poolMethod(){return draft.rollMethod.startsWith("DMG Method I —")||draft.rollMethod.startsWith("DMG Method II");}
  boolean manualMethod(){return "Manual entry".equals(draft.rollMethod);}
  boolean fixedMethod(){return draft.rollMethod.startsWith("DMG Method III")||draft.rollMethod.startsWith("DMG Method IV");}

  void clearScoresAndPool(){
    Arrays.fill(draft.rawScores,0);
    draft.rolledPool.clear();
  }

  boolean allRawScoresPresent(){for(int v:draft.rawScores)if(v<=0)return false;return true;}

  void migrateLegacyPoolIfNeeded(){
    if(!poolMethod()||!draft.rolledPool.isEmpty()||!allRawScoresPresent())return;
    for(int v:draft.rawScores)draft.rolledPool.add(v);
    saveDraft();
  }

  void sanitizePoolAssignments(){
    if(!poolMethod()||draft.rolledPool.size()!=6)return;
    HashMap<Integer,Integer> counts=new HashMap<>();
    for(int v:draft.rolledPool)counts.put(v,counts.getOrDefault(v,0)+1);
    boolean changed=false;
    for(int i=0;i<6;i++){
      int v=draft.rawScores[i];if(v<=0)continue;
      int left=counts.getOrDefault(v,0);
      if(left<=0){draft.rawScores[i]=0;changed=true;}
      else counts.put(v,left-1);
    }
    if(changed)saveDraft();
  }

  TreeMap<Integer,Integer> poolCountsExcludingSlot(int slot){
    TreeMap<Integer,Integer> counts=new TreeMap<>(Collections.reverseOrder());
    for(int v:draft.rolledPool)counts.put(v,counts.getOrDefault(v,0)+1);
    for(int i=0;i<6;i++)if(i!=slot&&draft.rawScores[i]>0){
      int v=draft.rawScores[i],left=counts.getOrDefault(v,0);if(left>0)counts.put(v,left-1);
    }
    counts.entrySet().removeIf(e->e.getValue()<=0);
    return counts;
  }

  TreeMap<Integer,Integer> remainingPoolCounts(){
    TreeMap<Integer,Integer> counts=new TreeMap<>(Collections.reverseOrder());
    for(int v:draft.rolledPool)counts.put(v,counts.getOrDefault(v,0)+1);
    for(int v:draft.rawScores)if(v>0){int left=counts.getOrDefault(v,0);if(left>0)counts.put(v,left-1);}
    counts.entrySet().removeIf(e->e.getValue()<=0);
    return counts;
  }

  String countsText(Map<Integer,Integer> counts){
    if(counts.isEmpty())return "None";
    StringBuilder s=new StringBuilder();
    for(Map.Entry<Integer,Integer> e:counts.entrySet()){
      if(s.length()>0)s.append("  •  ");
      s.append(e.getKey());if(e.getValue()>1)s.append(" ×").append(e.getValue());
    }
    return s.toString();
  }

  String fullPoolText(){
    TreeMap<Integer,Integer> counts=new TreeMap<>(Collections.reverseOrder());
    for(int v:draft.rolledPool)counts.put(v,counts.getOrDefault(v,0)+1);
    return countsText(counts);
  }

  void addPoolAssignmentRow(LinearLayout scores,int ix){
    LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
    row.addView(t(CharacterDraft.ABILITIES[ix],14,MUT,true),new LinearLayout.LayoutParams(dp(80),-2));

    TreeMap<Integer,Integer> available=poolCountsExcludingSlot(ix);
    ArrayList<String> labels=new ArrayList<>();ArrayList<Integer> values=new ArrayList<>();
    labels.add("— Unassigned —");values.add(0);int selected=0;
    for(Map.Entry<Integer,Integer> e:available.entrySet()){
      String label=String.valueOf(e.getKey());if(e.getValue()>1)label+="  ("+e.getValue()+" copies available)";
      labels.add(label);values.add(e.getKey());if(e.getKey()==draft.rawScores[ix])selected=values.size()-1;
    }
    Spinner pick=new Spinner(this);pick.setAdapter(spinnerAdapter(labels));pick.setSelection(selected);
    row.addView(pick,new LinearLayout.LayoutParams(0,dp(58),1));scores.addView(row);
    pick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      public void onItemSelected(AdapterView<?> p,View v,int pos,long id){
        int chosen=values.get(pos);if(chosen==draft.rawScores[ix])return;
        draft.rawScores[ix]=chosen;saveDraft();render();
      }
      public void onNothingSelected(AdapterView<?> p){}
    });
  }

  @Override void abilityStep(){
    migrateLegacyPoolIfNeeded();sanitizePoolAssignments();

    LinearLayout intro=card("Establish Ability Scores");
    intro.addView(t("Rolling is the default. Methods I and II create a pool of six scores that you assign to STR, INT, WIS, DEX, CON, and CHA. Method III rolls each ability separately, Method IV chooses a complete in-order set, and Manual Entry lets you type scores directly. Class minimums are enforced only after the appropriate distribution is complete.",14,TXT,false));
    add(content,intro);

    LinearLayout roll=card("Roll Ability Scores");
    Spinner method=new Spinner(this);ArrayList<String> methods=new ArrayList<>(Arrays.asList(ROLL_METHODS));method.setAdapter(spinnerAdapter(methods));int mi=Math.max(0,methods.indexOf(draft.rollMethod));method.setSelection(mi);roll.addView(method,new LinearLayout.LayoutParams(-1,dp(58)));
    Button rollButton=b("ROLL");roll.addView(rollButton,new LinearLayout.LayoutParams(-1,dp(56)));
    TextView rollNote=t("",12,MUT,false);roll.addView(rollNote);add(content,roll);

    LinearLayout scores=card(poolMethod()?"Distribute Rolled Scores":"Raw → Racially Adjusted Scores");
    scores.addView(t("Racial modifiers: "+CharacterRules.modifierSummary(draft.raceName),13,GOLD,true));

    if(poolMethod()){
      if(draft.rolledPool.size()==6){
        scores.addView(t("Rolled pool: "+fullPoolText(),14,TXT,true));
        scores.addView(t("Remaining unassigned: "+countsText(remainingPoolCounts()),13,GOLD,true));
        scores.addView(t("Choose a value for each ability. Assigning a value consumes one copy from the shared pool. Changing or clearing that ability returns its previous value to the pool.",12,MUT,false));
        for(int i=0;i<6;i++)addPoolAssignmentRow(scores,i);
      }else{
        scores.addView(t("Press ROLL to generate the six-score pool, then assign those values to the abilities below.",14,WARN,false));
        for(int i=0;i<6;i++){
          LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
          row.addView(t(CharacterDraft.ABILITIES[i],14,MUT,true),new LinearLayout.LayoutParams(dp(80),-2));
          row.addView(t("— waiting for roll —",14,TXT,false),new LinearLayout.LayoutParams(0,-2,1));scores.addView(row);
        }
      }
    }else if(manualMethod()){
      scores.addView(t("Enter the six raw scores manually. Racial adjustments are shown below but stored separately from the raw values.",12,MUT,false));
      for(int i=0;i<6;i++){
        final int ix=i;LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
        row.addView(t(CharacterDraft.ABILITIES[i],14,MUT,true),new LinearLayout.LayoutParams(dp(80),-2));
        EditText q=e(draft.rawScores[i]>0?String.valueOf(draft.rawScores[i]):"",CharacterDraft.ABILITIES[i],true);
        row.addView(q,new LinearLayout.LayoutParams(0,-2,1));scores.addView(row);
        q.addTextChangedListener(new SimpleWatcher(){public void afterTextChanged(Editable z){
          try{draft.rawScores[ix]=Integer.parseInt(z.toString());}catch(Exception ex){draft.rawScores[ix]=0;}
          saveDraft();updateContinueState();
        }});
      }
    }else{
      String rule=draft.rollMethod.startsWith("DMG Method III")
        ?"Method III rolls each ability independently; these results stay attached to their listed abilities."
        :"Method IV selects one complete 3d6-in-order set; the selected set stays in its rolled ability order.";
      scores.addView(t(rule,12,MUT,false));
      for(int i=0;i<6;i++){
        LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
        row.addView(t(CharacterDraft.ABILITIES[i],14,MUT,true),new LinearLayout.LayoutParams(dp(80),-2));
        row.addView(t(draft.rawScores[i]>0?String.valueOf(draft.rawScores[i]):"— not rolled —",16,TXT,true),new LinearLayout.LayoutParams(0,-2,1));scores.addView(row);
      }
    }

    TextView finalPreview=t(scoreText(false),13,TXT,false);scores.addView(finalPreview);add(content,scores);

    ClassData.Entry cls=ClassData.find(draft.className);
    LinearLayout validate=card("Class Qualification Check");
    validate.addView(t("Selected class: "+(cls==null?draft.className:cls.name),14,TXT,true));
    validate.addView(t("Requirements: "+(cls==null?"Unknown":cls.requirements),13,GOLD,true));
    ArrayList<String> problems=CharacterRules.validateAbilityScores(draft);
    TextView check=t(problems.isEmpty()?"✓ Ability distribution qualifies for the selected class.":joinProblems(problems),13,problems.isEmpty()?GOOD:WARN,false);validate.addView(check);
    validate.addView(t("Passing here means the race/class combination is legal and the racially adjusted scores meet the current starting requirements. Age adjustments are applied next and the app will recheck the final scores there as well.",12,MUT,false));
    add(content,validate);

    method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      public void onItemSelected(AdapterView<?> p,View v,int pos,long id){
        String next=ROLL_METHODS[pos];
        if(!next.equals(draft.rollMethod)){
          draft.rollMethod=next;clearScoresAndPool();saveDraft();render();return;
        }
        if(manualMethod()){
          rollButton.setEnabled(false);rollButton.setText("Manual Entry Selected");
          rollNote.setText("Type the six raw scores directly below. Switching generation methods starts a fresh score set.");
        }else if(poolMethod()){
          rollButton.setEnabled(true);rollButton.setText(draft.rolledPool.size()==6?"ROLL NEW POOL":"ROLL SCORE POOL");
          rollNote.setText("This method creates six freely assignable scores. A new roll replaces the current pool and clears its assignments.");
        }else if(draft.rollMethod.startsWith("DMG Method III")){
          rollButton.setEnabled(true);rollButton.setText("ROLL METHOD III");
          rollNote.setText("Each ability keeps the best of six 3d6 rolls made specifically for that ability.");
        }else{
          rollButton.setEnabled(true);rollButton.setText("ROLL / CHOOSE SET");
          rollNote.setText("Generate twelve complete in-order sets, then choose one whole set.");
        }
      }
      public void onNothingSelected(AdapterView<?> p){}
    });
    rollButton.setOnClickListener(v->rollScores());
    updateContinueState();
  }

  @Override void rollScores(){
    String m=draft.rollMethod;
    if(m.startsWith("DMG Method I —")){
      clearScoresAndPool();for(int i=0;i<6;i++)draft.rolledPool.add(roll4d6DropLowest());saveDraft();render();toast("Six scores rolled. Assign each value to an ability.");
    }else if(m.startsWith("DMG Method II")){
      clearScoresAndPool();ArrayList<Integer> rolls=new ArrayList<>();for(int i=0;i<12;i++)rolls.add(roll3d6());rolls.sort(Collections.reverseOrder());for(int i=0;i<6;i++)draft.rolledPool.add(rolls.get(i));saveDraft();render();toast("Best six scores kept. Assign each value to an ability.");
    }else if(m.startsWith("DMG Method III")){
      clearScoresAndPool();for(int a=0;a<6;a++){int best=0;for(int j=0;j<6;j++)best=Math.max(best,roll3d6());draft.rawScores[a]=best;}saveDraft();render();
    }else if(m.startsWith("DMG Method IV")){
      clearScoresAndPool();saveDraft();showMethodIVSets();
    }
  }
}
