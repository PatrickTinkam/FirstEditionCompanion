package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;
import java.util.*;

/** v0.9.1 guided-character-creation workflow through languages. */
public class CharacterCreationActivity extends Activity {
  static final int BG=Color.rgb(17,17,15),PANEL=Color.rgb(31,30,26),GOLD=Color.rgb(201,168,91),
    TXT=Color.rgb(238,232,215),MUT=Color.rgb(180,172,151),WARN=Color.rgb(230,180,90),GOOD=Color.rgb(153,205,139);
  static final String PREF="fec",DRAFT_KEY="creation_draft_v1";
  static final String[] STEP_TITLES={"Race / Subrace","Class / Subclass","Ability Scores","Age","Alignment","Languages","Draft Review"};
  static final String[] ROLL_METHODS={
    "DMG Method I — 4d6, drop lowest",
    "DMG Method II — 12×3d6, keep best six",
    "DMG Method III — best of six 3d6 per ability",
    "DMG Method IV — choose 1 of 12 complete sets",
    "Manual entry"
  };

  SharedPreferences prefs;
  CharacterDraft draft;
  LinearLayout content;
  TextView progress;
  Button backButton,continueButton;
  Random rng=new Random();

  int dp(int n){return (int)(n*getResources().getDisplayMetrics().density+.5f);}
  GradientDrawable bg(int c){GradientDrawable g=new GradientDrawable();g.setColor(c);g.setCornerRadius(dp(12));g.setStroke(dp(1),Color.rgb(77,69,51));return g;}
  TextView t(String s,int z,int c,boolean bold){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(c);v.setPadding(dp(8),dp(7),dp(8),dp(7));if(bold)v.setTypeface(null,1);return v;}
  Button b(String s){Button v=new Button(this);v.setText(s);v.setTextColor(TXT);v.setAllCaps(false);v.setBackground(bg(PANEL));return v;}
  EditText e(String s,String hint,boolean num){
    EditText v=new EditText(this);v.setText(s);v.setHint(hint);v.setTextColor(TXT);v.setHintTextColor(MUT);v.setSingleLine(true);v.setPadding(dp(10),dp(8),dp(10),dp(8));v.setBackground(bg(Color.rgb(42,40,34)));
    if(num)v.setInputType(android.text.InputType.TYPE_CLASS_NUMBER|android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);return v;
  }
  LinearLayout card(String title){LinearLayout x=new LinearLayout(this);x.setOrientation(LinearLayout.VERTICAL);x.setPadding(dp(12),dp(10),dp(12),dp(10));x.setBackground(bg(PANEL));x.addView(t(title,18,GOLD,true));return x;}
  void add(LinearLayout col,View v){LinearLayout.LayoutParams q=new LinearLayout.LayoutParams(-1,-2);q.setMargins(dp(7),dp(6),dp(7),dp(6));col.addView(v,q);}

  @Override public void onCreate(Bundle state){
    super.onCreate(state);prefs=getSharedPreferences(PREF,0);draft=CharacterDraft.fromJson(prefs.getString(DRAFT_KEY,""));
    if(draft.rollMethod==null||draft.rollMethod.isEmpty()||"Manual".equals(draft.rollMethod))draft.rollMethod=ROLL_METHODS[0];
    buildShell();if(state==null&&draft.hasMeaningfulProgress())showResumeDialog();else render();
  }

  void buildShell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("CREATE CHARACTER  •  v0.9.1",19,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    progress=t("",13,MUT,true);progress.setGravity(Gravity.CENTER);root.addView(progress,new LinearLayout.LayoutParams(-1,dp(38)));
    ScrollView scroll=new ScrollView(this);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(dp(6),dp(4),dp(6),dp(22));scroll.addView(content);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setPadding(dp(7),dp(6),dp(7),dp(7));
    Button cancel=b("Cancel");cancel.setOnClickListener(v->cancelWizard());nav.addView(cancel,new LinearLayout.LayoutParams(0,dp(52),1));
    backButton=b("Back");backButton.setOnClickListener(v->{readCurrentStep();if(draft.step>0){draft.step--;saveDraft();render();}});nav.addView(backButton,new LinearLayout.LayoutParams(0,dp(52),1));
    continueButton=b("Continue");continueButton.setOnClickListener(v->advance());nav.addView(continueButton,new LinearLayout.LayoutParams(0,dp(52),1));
    root.addView(nav,new LinearLayout.LayoutParams(-1,-2));
  }

  void showResumeDialog(){
    new AlertDialog.Builder(this).setTitle("Resume character draft?").setMessage("A guided character-creation draft is already in progress. Your active character has not been changed.")
      .setPositiveButton("Resume",(d,w)->render()).setNeutralButton("Start Over",(d,w)->{draft=new CharacterDraft();draft.rollMethod=ROLL_METHODS[0];saveDraft();render();})
      .setNegativeButton("Cancel",(d,w)->finish()).setOnCancelListener(d->finish()).show();
  }
  void saveDraft(){prefs.edit().putString(DRAFT_KEY,draft.toJson().toString()).apply();}
  void discardDraft(){prefs.edit().remove(DRAFT_KEY).apply();}

  void render(){
    content.removeAllViews();progress.setText((draft.step+1)+" of "+STEP_TITLES.length+"  •  "+STEP_TITLES[draft.step]);backButton.setEnabled(draft.step>0);
    continueButton.setText(draft.step==STEP_TITLES.length-1?"Save Draft & Return":"Continue");
    if(draft.step==0)raceStep();else if(draft.step==1)classStep();else if(draft.step==2)abilityStep();else if(draft.step==3)ageStep();else if(draft.step==4)alignmentStep();else if(draft.step==5)languagesStep();else reviewStep();
    updateContinueState();
  }

  void raceStep(){
    LinearLayout intro=card("Choose Race / Subrace");intro.addView(t("Race is the first decision in this creator. It sets racial adjustments and determines which classes are available. You can come back and change it at any time.",14,TXT,false));add(content,intro);
    ArrayList<RaceData.Race> races=RaceData.all();ArrayList<String> labels=new ArrayList<>();int selected=0;
    for(int i=0;i<races.size();i++){RaceData.Race r=races.get(i);labels.add(r.label());if(r.name.equals(draft.raceName))selected=i;}
    Spinner spin=new Spinner(this);spin.setAdapter(spinnerAdapter(labels));spin.setSelection(selected);LinearLayout choose=card("Race");choose.addView(spin,new LinearLayout.LayoutParams(-1,dp(58)));
    TextView adjustments=t("",14,GOLD,true),source=t("",12,MUT,false),note=t("",13,TXT,false);choose.addView(adjustments);choose.addView(source);choose.addView(note);Button details=b("Race Details");choose.addView(details,new LinearLayout.LayoutParams(-1,dp(48)));add(content,choose);
    Runnable refresh=()->{RaceData.Race r=RaceData.find(draft.raceName);if(r==null)return;adjustments.setText("Ability adjustments: "+r.adjustments);source.setText("Source: "+r.source+(r.source.toLowerCase(Locale.US).contains("ua")?"  •  Variant source":""));note.setText(CharacterRules.modifierSummary(r.name));details.setOnClickListener(v->raceDetails(r));};
    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){public void onItemSelected(AdapterView<?> p,View v,int pos,long id){String old=draft.raceName;draft.raceName=races.get(pos).name;if(!Objects.equals(old,draft.raceName))saveDraft();refresh.run();updateContinueState();}public void onNothingSelected(AdapterView<?> p){}});
    if(draft.raceName.isEmpty()){draft.raceName=races.get(selected).name;saveDraft();}refresh.run();
  }

  void classStep(){
    RaceData.Race race=RaceData.find(draft.raceName);LinearLayout intro=card("Choose Class / Subclass");intro.addView(t("All built-in classes stay visible so you can learn what they do. A class that your selected race cannot use is marked unavailable; you can still open its details, then go Back and change race if you want it.",14,TXT,false));add(content,intro);
    ArrayList<ClassData.Entry> classes=ClassData.all();ArrayList<String> labels=new ArrayList<>();int selected=0;
    for(int i=0;i<classes.size();i++){ClassData.Entry c=classes.get(i);CharacterRules.Availability a=CharacterRules.availability(race,c);labels.add((a.allowed?"✓ ":"⛔ ")+c.label());if(c.name.equals(draft.className))selected=i;}
    Spinner spin=new Spinner(this);spin.setAdapter(spinnerAdapter(labels));spin.setSelection(selected);LinearLayout choose=card("Class");choose.addView(spin,new LinearLayout.LayoutParams(-1,dp(58)));
    TextView status=t("",14,TXT,true),req=t("",13,GOLD,true),quick=t("",12,TXT,false),source=t("",12,MUT,false);choose.addView(status);choose.addView(req);choose.addView(quick);choose.addView(source);Button details=b("Class Details");choose.addView(details,new LinearLayout.LayoutParams(-1,dp(48)));add(content,choose);
    Runnable refresh=()->{ClassData.Entry c=ClassData.find(draft.className);if(c==null)return;CharacterRules.Availability a=CharacterRules.availability(race,c);status.setText((a.allowed?"AVAILABLE":"UNAVAILABLE FOR "+(race==null?"SELECTED RACE":race.name.toUpperCase(Locale.US)))+"\n"+a.reason);status.setTextColor(a.allowed?GOOD:WARN);req.setText("Requirements: "+c.requirements);quick.setText("Hit Die: "+c.hitDie+"  •  Alignment: "+c.alignment+"\nArmor & weapons: "+c.armorWeapons);source.setText("Source: "+c.source);details.setOnClickListener(v->classDetails(c));};
    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){public void onItemSelected(AdapterView<?> p,View v,int pos,long id){draft.className=classes.get(pos).name;saveDraft();refresh.run();updateContinueState();}public void onNothingSelected(AdapterView<?> p){}});
    if(draft.className.isEmpty()){draft.className=classes.get(selected).name;saveDraft();}refresh.run();
  }

  void abilityStep(){
    LinearLayout intro=card("Establish Ability Scores");intro.addView(t("Rolling is the default. Choose a DMG generation method and press ROLL. Manual entry is always available. Raw scores are stored separately from final scores so changing race never applies racial modifiers twice.",14,TXT,false));add(content,intro);
    LinearLayout roll=card("Roll Ability Scores");Spinner method=new Spinner(this);ArrayList<String> methods=new ArrayList<>(Arrays.asList(ROLL_METHODS));method.setAdapter(spinnerAdapter(methods));int mi=Math.max(0,methods.indexOf(draft.rollMethod));method.setSelection(mi);roll.addView(method,new LinearLayout.LayoutParams(-1,dp(58)));Button rollButton=b("ROLL");roll.addView(rollButton,new LinearLayout.LayoutParams(-1,dp(56)));TextView rollNote=t("",12,MUT,false);roll.addView(rollNote);add(content,roll);
    method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){public void onItemSelected(AdapterView<?> p,View v,int pos,long id){draft.rollMethod=ROLL_METHODS[pos];saveDraft();boolean manual="Manual entry".equals(draft.rollMethod);rollButton.setEnabled(!manual);rollButton.setText(manual?"Manual Entry Selected":"ROLL");rollNote.setText(manual?"Enter scores below.":"Rolled values populate the fields below; methods that allow arrangement can be rearranged by editing the raw-score fields.");}public void onNothingSelected(AdapterView<?> p){}});rollButton.setOnClickListener(v->rollScores());
    LinearLayout scores=card("Raw → Racially Adjusted Scores");scores.addView(t("Racial modifiers: "+CharacterRules.modifierSummary(draft.raceName),13,GOLD,true));TextView finalPreview=t("",13,TXT,false);scores.addView(finalPreview);
    for(int i=0;i<6;i++){final int ix=i;LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);row.addView(t(CharacterDraft.ABILITIES[i],14,MUT,true),new LinearLayout.LayoutParams(dp(80),-2));EditText q=e(draft.rawScores[i]>0?String.valueOf(draft.rawScores[i]):"",CharacterDraft.ABILITIES[i],true);row.addView(q,new LinearLayout.LayoutParams(0,-2,1));scores.addView(row);q.addTextChangedListener(new SimpleWatcher(){public void afterTextChanged(Editable z){try{draft.rawScores[ix]=Integer.parseInt(z.toString());}catch(Exception ex){draft.rawScores[ix]=0;}saveDraft();finalPreview.setText(scoreText(false));updateContinueState();}});}finalPreview.setText(scoreText(false));add(content,scores);
    ArrayList<String> problems=CharacterRules.validateAbilityScores(draft);LinearLayout validate=card("Rules Check");validate.addView(t(problems.isEmpty()?"✓ Ability scores meet the currently encoded starting requirements.":joinProblems(problems),13,problems.isEmpty()?GOOD:WARN,false));add(content,validate);
  }

  void ageStep(){
    LinearLayout intro=card("Establish Character Age");intro.addView(t("The DMG establishes age at character creation. Use the source-table roll when available, or enter a campaign age manually. Aging adjustments are applied after racial adjustments and are recalculated from the stored raw scores whenever you change an earlier choice.",14,TXT,false));add(content,intro);
    CharacterRules.AgeFormula f=CharacterRules.startingAgeFormula(draft);
    LinearLayout setup=card("Starting Age");ArrayList<String> modes=new ArrayList<>(Arrays.asList("DMG roll","Manual / campaign override"));Spinner mode=new Spinner(this);mode.setAdapter(spinnerAdapter(modes));mode.setSelection(draft.ageMode.startsWith("Manual")?1:0);setup.addView(mode,new LinearLayout.LayoutParams(-1,dp(58)));
    TextView formula=t("",13,GOLD,true),formulaNote=t("",12,MUT,false);setup.addView(formula);setup.addView(formulaNote);Button roll=b("ROLL AGE");setup.addView(roll,new LinearLayout.LayoutParams(-1,dp(54)));
    LinearLayout ageRow=new LinearLayout(this);ageRow.setGravity(Gravity.CENTER_VERTICAL);ageRow.addView(t("Age",14,MUT,true),new LinearLayout.LayoutParams(dp(90),-2));EditText ageField=e(draft.age>0?String.valueOf(draft.age):"","Years",true);ageRow.addView(ageField,new LinearLayout.LayoutParams(0,-2,1));setup.addView(ageRow);add(content,setup);
    LinearLayout effects=card("Age Category & Ability Effects");TextView category=t("",15,TXT,true),mod=t("",13,GOLD,true),finals=t("",13,TXT,false),source=t("",12,MUT,false),check=t("",13,TXT,false);effects.addView(category);effects.addView(mod);effects.addView(finals);effects.addView(source);effects.addView(check);add(content,effects);
    Runnable refresh=()->{CharacterRules.AgeFormula af=CharacterRules.startingAgeFormula(draft);formula.setText(af.supported?"DMG formula: "+af.expression():"DMG formula: not directly available");formulaNote.setText(af.note);boolean manual=draft.ageMode.startsWith("Manual");roll.setEnabled(!manual&&af.supported);roll.setText(manual?"Manual Age Selected":(af.supported?"ROLL AGE":"Use Manual Age"));String cat=CharacterRules.ageCategory(draft.raceName,draft.age);category.setText("Age "+(draft.age>0?draft.age:"—")+"  •  "+cat);mod.setText("Age adjustments: "+CharacterRules.ageModifierSummary(draft));finals.setText(scoreText(true));source.setText(CharacterRules.ageSourceNote(draft.raceName));ArrayList<String> p=CharacterRules.validateFinalScores(draft);String msg=draft.age<=0?"Enter or roll an age.":(p.isEmpty()?"✓ Final scores still meet class minimums after age adjustments.":joinProblems(p));check.setText(msg);check.setTextColor(draft.age>0&&p.isEmpty()?GOOD:WARN);updateContinueState();};
    mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){public void onItemSelected(AdapterView<?> p,View v,int pos,long id){draft.ageMode=pos==1?"Manual / campaign override":"DMG roll";saveDraft();refresh.run();}public void onNothingSelected(AdapterView<?> p){}});
    roll.setOnClickListener(v->{CharacterRules.AgeFormula af=CharacterRules.startingAgeFormula(draft);if(!af.supported){toast("Use manual age for this race/class.");return;}int n=af.base;for(int i=0;i<af.dice;i++)n+=die(af.sides);draft.age=n;draft.ageMode="DMG roll";saveDraft();render();});
    ageField.addTextChangedListener(new SimpleWatcher(){public void afterTextChanged(Editable z){try{draft.age=Math.max(0,Integer.parseInt(z.toString()));}catch(Exception ex){draft.age=0;}saveDraft();refresh.run();}});refresh.run();
  }

  void alignmentStep(){
    ClassData.Entry cls=ClassData.find(draft.className);LinearLayout intro=card("Choose Alignment");intro.addView(t("All nine alignments stay visible. The selected class determines which choices are legal; unavailable choices remain visible so the restriction is understandable rather than hidden.",14,TXT,false));add(content,intro);
    ArrayList<String> labels=new ArrayList<>();int selected=-1,firstAllowed=0;
    for(int i=0;i<CharacterRules.ALIGNMENTS.length;i++){String a=CharacterRules.ALIGNMENTS[i];CharacterRules.Availability av=CharacterRules.alignmentAvailability(draft.className,a);labels.add((av.allowed?"✓ ":"⛔ ")+a);if(av.allowed&&selected<0)firstAllowed=i;if(a.equals(draft.alignment))selected=i;}
    if(selected<0)selected=firstAllowed;Spinner spin=new Spinner(this);spin.setAdapter(spinnerAdapter(labels));spin.setSelection(selected);
    LinearLayout choose=card("Alignment");choose.addView(spin,new LinearLayout.LayoutParams(-1,dp(58)));TextView status=t("",14,TXT,true),classRule=t("",12,GOLD,true);choose.addView(status);choose.addView(classRule);
    CheckBox lang=new CheckBox(this);lang.setText("Use original 1e alignment language");lang.setTextColor(TXT);lang.setChecked(draft.useAlignmentLanguage);choose.addView(lang);choose.addView(t("PHB core characters normally know the special language of their alignment. Turn this off only if your campaign does not use alignment languages.",12,MUT,false));
    EditText deity=null;if(CharacterRules.isDivineClass(draft.className)){choose.addView(t("Deity / Patron (optional)",13,MUT,true));deity=e(draft.deity,"Deity or patron",false);choose.addView(deity);}add(content,choose);
    Runnable refresh=()->{CharacterRules.Availability av=CharacterRules.alignmentAvailability(draft.className,draft.alignment);status.setText((av.allowed?"AVAILABLE":"UNAVAILABLE FOR "+draft.className.toUpperCase(Locale.US))+"\n"+av.reason);status.setTextColor(av.allowed?GOOD:WARN);classRule.setText("Class alignment rule: "+(cls==null?"Unknown":cls.alignment));updateContinueState();};
    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){public void onItemSelected(AdapterView<?> p,View v,int pos,long id){draft.alignment=CharacterRules.ALIGNMENTS[pos];saveDraft();refresh.run();}public void onNothingSelected(AdapterView<?> p){}});
    lang.setOnCheckedChangeListener((buttonView,isChecked)->{draft.useAlignmentLanguage=isChecked;saveDraft();});
    if(deity!=null){EditText de=deity;de.addTextChangedListener(new SimpleWatcher(){public void afterTextChanged(Editable z){draft.deity=z.toString();saveDraft();}});}if(draft.alignment.isEmpty()){draft.alignment=CharacterRules.ALIGNMENTS[selected];saveDraft();}refresh.run();
  }

  void languagesStep(){
    LinearLayout intro=card("Languages");intro.addView(t("Automatic languages come from race, class, and the alignment-language setting. Additional-language capacity is driven by final Intelligence and the race's own PHB limits. Supplied-UA races keep their source-specific language lists visibly separate when that source uses background-skill language choices.",14,TXT,false));add(content,intro);
    LinearLayout auto=card("Automatic Languages");ArrayList<String> automatic=CharacterRules.automaticLanguages(draft);auto.addView(t(automatic.isEmpty()?"None encoded":join(automatic,", "),14,TXT,false));add(content,auto);
    LinearLayout bonus=card("Additional Languages");TextView rule=t(CharacterRules.languageRuleNote(draft),13,GOLD,true),selected=t("",13,TXT,false),check=t("",12,TXT,false);bonus.addView(rule);bonus.addView(selected);Button choose=b("Choose Additional Languages");bonus.addView(choose,new LinearLayout.LayoutParams(-1,dp(52)));Button clear=b("Clear Additional Languages");bonus.addView(clear,new LinearLayout.LayoutParams(-1,dp(46)));bonus.addView(check);add(content,bonus);
    Runnable refresh=()->{selected.setText(draft.chosenLanguages.isEmpty()?"Chosen: none":"Chosen: "+join(draft.chosenLanguages,", "));ArrayList<String> p=CharacterRules.validateLanguages(draft);check.setText(p.isEmpty()?"✓ Language selection fits the currently encoded capacity.":joinProblems(p));check.setTextColor(p.isEmpty()?GOOD:WARN);updateContinueState();};
    choose.setOnClickListener(v->chooseLanguagesDialog());clear.setOnClickListener(v->{draft.chosenLanguages.clear();saveDraft();render();});refresh.run();
  }

  void chooseLanguagesDialog(){
    ArrayList<String> automatic=CharacterRules.automaticLanguages(draft),options=CharacterRules.languageCandidates(draft.raceName);
    options.removeAll(automatic);for(String s:draft.chosenLanguages)if(!options.contains(s))options.add(s);
    String[] names=options.toArray(new String[0]);boolean[] checked=new boolean[names.length];for(int i=0;i<names.length;i++)checked[i]=draft.chosenLanguages.contains(names[i]);
    AlertDialog dlg=new AlertDialog.Builder(this).setTitle("Choose Additional Languages").setMultiChoiceItems(names,checked,(d,which,isChecked)->checked[which]=isChecked).setPositiveButton("Use Selection",null).setNegativeButton("Cancel",null).create();
    dlg.setOnShowListener(x->dlg.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v->{ArrayList<String> picked=new ArrayList<>();for(int i=0;i<names.length;i++)if(checked[i])picked.add(names[i]);int slots=CharacterRules.bonusLanguageSlots(draft);if(slots>=0&&picked.size()>slots){toast("Choose no more than "+slots+" additional language"+(slots==1?"":"s")+".");return;}draft.chosenLanguages.clear();draft.chosenLanguages.addAll(picked);saveDraft();dlg.dismiss();render();}));dlg.show();
  }

  String scoreText(boolean includeAge){
    int[] finals=includeAge?CharacterRules.finalScores(draft):CharacterRules.racialScores(draft),race=CharacterRules.racialModifiers(draft.raceName),age=includeAge?CharacterRules.ageModifiers(CharacterRules.ageCategory(draft.raceName,draft.age)):new int[6];StringBuilder s=new StringBuilder();
    for(int i=0;i<6;i++){if(i>0)s.append("\n");s.append(CharacterDraft.ABILITIES[i]).append(": ");if(draft.rawScores[i]<=0)s.append("—");else{s.append(draft.rawScores[i]);if(race[i]!=0)s.append(race[i]>0?" + ":" - ").append(Math.abs(race[i])).append(" racial");if(age[i]!=0)s.append(age[i]>0?" + ":" - ").append(Math.abs(age[i])).append(" age");s.append(" = ").append(finals[i]);}}
    return s.toString();
  }

  void reviewStep(){
    LinearLayout summary=card("Draft Review");RaceData.Race race=RaceData.find(draft.raceName);ClassData.Entry cls=ClassData.find(draft.className);int[] f=CharacterRules.finalScores(draft);StringBuilder s=new StringBuilder();
    s.append("Race: ").append(race==null?draft.raceName:race.name).append("\nClass: ").append(cls==null?draft.className:cls.name).append("\nAge: ").append(draft.age>0?draft.age:"—").append(" (").append(CharacterRules.ageCategory(draft.raceName,draft.age)).append(")\nAlignment: ").append(draft.alignment.isEmpty()?"—":draft.alignment);
    if(!draft.deity.trim().isEmpty())s.append("\nDeity / Patron: ").append(draft.deity.trim());s.append("\n\n");for(int i=0;i<6;i++)s.append(CharacterDraft.ABILITIES[i]).append(" ").append(f[i]).append(i==5?"":"   ");
    s.append("\n\nAutomatic languages: ").append(join(CharacterRules.automaticLanguages(draft),", "));s.append("\nAdditional languages: ").append(draft.chosenLanguages.isEmpty()?"None":join(draft.chosenLanguages,", "));
    summary.addView(t(s.toString(),15,TXT,true));add(content,summary);
    LinearLayout next=card("What Happens Next");next.addView(t("This v0.9.1 slice still keeps the character isolated as a draft. The remaining creation work will add weapon proficiencies, secondary skills, health, class-specific skills, spell setup, starting money and equipment, optional personality/background, derived combat values, and the final Finish Character commit into the normal sheet.",14,TXT,false));next.addView(t("Use Back to change any earlier decision. Later choices are retained and revalidated instead of being silently deleted.",13,MUT,false));add(content,next);
  }

  void advance(){
    readCurrentStep();
    if(draft.step==0&&RaceData.find(draft.raceName)==null){toast("Choose a race first.");return;}
    if(draft.step==1){CharacterRules.Availability a=CharacterRules.availability(RaceData.find(draft.raceName),ClassData.find(draft.className));if(!a.allowed){toast("That class is unavailable for the selected race.");return;}}
    if(draft.step==2){ArrayList<String> p=CharacterRules.validateAbilityScores(draft);if(!p.isEmpty()){dialog("Resolve Rules Check",joinProblems(p));return;}}
    if(draft.step==3){if(draft.age<=0){toast("Enter or roll a character age.");return;}ArrayList<String> p=CharacterRules.validateFinalScores(draft);if(!p.isEmpty()){dialog("Resolve Age / Ability Check",joinProblems(p));return;}}
    if(draft.step==4){CharacterRules.Availability a=CharacterRules.alignmentAvailability(draft.className,draft.alignment);if(!a.allowed){dialog("Choose a Compatible Alignment",a.reason);return;}}
    if(draft.step==5){ArrayList<String> p=CharacterRules.validateLanguages(draft);if(!p.isEmpty()){dialog("Resolve Language Selection",joinProblems(p));return;}}
    if(draft.step==STEP_TITLES.length-1){saveDraft();toast("Character draft saved");finish();return;}
    draft.step++;saveDraft();render();
  }

  void readCurrentStep(){saveDraft();}
  void updateContinueState(){
    if(continueButton==null)return;boolean ok=true;
    if(draft.step==0)ok=RaceData.find(draft.raceName)!=null;
    else if(draft.step==1)ok=CharacterRules.availability(RaceData.find(draft.raceName),ClassData.find(draft.className)).allowed;
    else if(draft.step==2)ok=CharacterRules.validateAbilityScores(draft).isEmpty();
    else if(draft.step==3)ok=draft.age>0&&CharacterRules.validateFinalScores(draft).isEmpty();
    else if(draft.step==4)ok=CharacterRules.alignmentAvailability(draft.className,draft.alignment).allowed;
    else if(draft.step==5)ok=CharacterRules.validateLanguages(draft).isEmpty();
    continueButton.setEnabled(ok);
  }

  void cancelWizard(){
    if(!draft.hasMeaningfulProgress()){finish();return;}
    new AlertDialog.Builder(this).setTitle("Leave character creation?").setMessage("Your active character has not been changed. Keep this draft so you can resume later, or discard it.")
      .setPositiveButton("Keep Draft",(d,w)->{saveDraft();finish();}).setNegativeButton("Stay",null).setNeutralButton("Discard Draft",(d,w)->{discardDraft();finish();}).show();
  }
  @Override public void onBackPressed(){if(draft.step>0){draft.step--;saveDraft();render();}else cancelWizard();}

  void rollScores(){
    String m=draft.rollMethod;if(m.startsWith("DMG Method I")){for(int i=0;i<6;i++)draft.rawScores[i]=roll4d6DropLowest();saveDraft();render();}
    else if(m.startsWith("DMG Method II")){ArrayList<Integer> rolls=new ArrayList<>();for(int i=0;i<12;i++)rolls.add(roll3d6());rolls.sort(Collections.reverseOrder());for(int i=0;i<6;i++)draft.rawScores[i]=rolls.get(i);saveDraft();render();toast("Best six rolled values inserted; rearrange them as desired.");}
    else if(m.startsWith("DMG Method III")){for(int a=0;a<6;a++){int best=0;for(int j=0;j<6;j++)best=Math.max(best,roll3d6());draft.rawScores[a]=best;}saveDraft();render();}
    else if(m.startsWith("DMG Method IV"))showMethodIVSets();
  }
  int die(int sides){return 1+rng.nextInt(sides);}int roll3d6(){return die(6)+die(6)+die(6);}int roll4d6DropLowest(){int total=0,low=7;for(int i=0;i<4;i++){int d=die(6);total+=d;low=Math.min(low,d);}return total-low;}
  void showMethodIVSets(){final int[][] sets=new int[12][6];String[] labels=new String[12];for(int n=0;n<12;n++){int total=0;StringBuilder s=new StringBuilder("Set "+(n+1)+"  •  ");for(int i=0;i<6;i++){sets[n][i]=roll3d6();total+=sets[n][i];s.append(CharacterDraft.ABILITIES[i]).append(" ").append(sets[n][i]).append(i==5?"":"  ");}s.append("  •  Total ").append(total);labels[n]=s.toString();}new AlertDialog.Builder(this).setTitle("DMG Method IV — choose a set").setItems(labels,(d,which)->{System.arraycopy(sets[which],0,draft.rawScores,0,6);saveDraft();render();}).setNegativeButton("Cancel",null).show();}

  ArrayAdapter<String> spinnerAdapter(ArrayList<String> labels){return new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,labels){@Override public View getView(int position,View convertView,ViewGroup parent){TextView v=(TextView)super.getView(position,convertView,parent);v.setTextColor(TXT);v.setTextSize(15);v.setPadding(dp(10),dp(10),dp(10),dp(10));return v;}@Override public View getDropDownView(int position,View convertView,ViewGroup parent){TextView v=(TextView)super.getDropDownView(position,convertView,parent);v.setTextColor(TXT);v.setBackgroundColor(PANEL);v.setTextSize(14);v.setPadding(dp(12),dp(14),dp(12),dp(14));return v;}};}
  void raceDetails(RaceData.Race r){detailDialog(r.name,r.fullDetails());}void classDetails(ClassData.Entry c){detailDialog(c.name,c.fullDetails());}
  void detailDialog(String title,String message){ScrollView sc=new ScrollView(this);TextView body=t(message,14,TXT,false);body.setPadding(dp(18),dp(10),dp(18),dp(18));sc.addView(body);new AlertDialog.Builder(this).setTitle(title).setView(sc).setPositiveButton("Close",null).show();}
  void dialog(String title,String message){new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("OK",null).show();}void toast(String s){Toast.makeText(this,s,Toast.LENGTH_SHORT).show();}
  String joinProblems(ArrayList<String> p){StringBuilder s=new StringBuilder();for(String q:p)s.append("• ").append(q).append("\n");return s.toString().trim();}
  String join(Collection<String> values,String sep){StringBuilder s=new StringBuilder();for(String v:values){if(s.length()>0)s.append(sep);s.append(v);}return s.toString();}
  abstract class SimpleWatcher implements TextWatcher {public void beforeTextChanged(CharSequence s,int st,int c,int a){}public void onTextChanged(CharSequence s,int st,int b,int c){}}
}
