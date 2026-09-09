package com.patricktinkam.firsteditioncompanion;

import android.view.*;
import android.widget.*;

/** v0.9.7: source-detail affordances plus shared SecureRandom-backed dice. */
public class CharacterCreationActivityV3 extends CharacterCreationActivityV2 {
  @Override void buildShell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("CREATE CHARACTER  •  v0.9.7",19,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    progress=t("",13,MUT,true);progress.setGravity(Gravity.CENTER);root.addView(progress,new LinearLayout.LayoutParams(-1,dp(38)));
    ScrollView scroll=new ScrollView(this);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(dp(6),dp(4),dp(6),dp(22));scroll.addView(content);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setPadding(dp(7),dp(6),dp(7),dp(7));
    Button cancel=b("Cancel");cancel.setOnClickListener(v->cancelWizard());nav.addView(cancel,new LinearLayout.LayoutParams(0,dp(52),1));
    backButton=b("Back");backButton.setOnClickListener(v->{readCurrentStep();if(draft.step>0){draft.step--;saveDraft();render();}});nav.addView(backButton,new LinearLayout.LayoutParams(0,dp(52),1));
    continueButton=b("Continue");continueButton.setOnClickListener(v->advance());nav.addView(continueButton,new LinearLayout.LayoutParams(0,dp(52),1));
    root.addView(nav,new LinearLayout.LayoutParams(-1,-2));
  }

  @Override int die(int sides){return DiceRng.die(sides);}

  LinearLayout cardAt(int index){
    if(content==null||index<0||index>=content.getChildCount())return null;
    View v=content.getChildAt(index);return v instanceof LinearLayout?(LinearLayout)v:null;
  }

  void addSourceButton(LinearLayout target,View.OnClickListener action){
    if(target==null)return;
    Button why=b("Why? • Source");
    why.setOnClickListener(action);
    LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(dp(190),dp(42));p.topMargin=dp(6);
    target.addView(why,p);
  }

  void showSource(SourceRuleDetails.Detail d){if(d!=null)detailDialog(d.title,d.body());}

  @Override void classStep(){
    super.classStep();
    addSourceButton(cardAt(1),v->showSource(SourceRuleDetails.raceClass(draft)));
  }

  @Override void abilityStep(){
    super.abilityStep();
    addSourceButton(cardAt(3),v->showSource(SourceRuleDetails.abilityQualification(draft)));
  }

  @Override void ageStep(){
    super.ageStep();
    addSourceButton(cardAt(1),v->showSource(SourceRuleDetails.age(draft)));
    addSourceButton(cardAt(2),v->showSource(SourceRuleDetails.age(draft)));
  }

  @Override void alignmentStep(){
    super.alignmentStep();
    addSourceButton(cardAt(1),v->showSource(SourceRuleDetails.alignment(draft)));
  }

  @Override void languagesStep(){
    super.languagesStep();
    addSourceButton(cardAt(2),v->showSource(SourceRuleDetails.languages(draft)));
  }
}
