package com.patricktinkam.firsteditioncompanion;

import android.view.*;
import android.widget.*;

/** v0.9.3 presentation layer for the guided creator. */
public class CharacterCreationActivityV2 extends CharacterCreationActivity {
  @Override void buildShell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("CREATE CHARACTER  •  v0.9.3",19,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    progress=t("",13,MUT,true);progress.setGravity(Gravity.CENTER);root.addView(progress,new LinearLayout.LayoutParams(-1,dp(38)));
    ScrollView scroll=new ScrollView(this);content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(dp(6),dp(4),dp(6),dp(22));scroll.addView(content);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setPadding(dp(7),dp(6),dp(7),dp(7));
    Button cancel=b("Cancel");cancel.setOnClickListener(v->cancelWizard());nav.addView(cancel,new LinearLayout.LayoutParams(0,dp(52),1));
    backButton=b("Back");backButton.setOnClickListener(v->{readCurrentStep();if(draft.step>0){draft.step--;saveDraft();render();}});nav.addView(backButton,new LinearLayout.LayoutParams(0,dp(52),1));
    continueButton=b("Continue");continueButton.setOnClickListener(v->advance());nav.addView(continueButton,new LinearLayout.LayoutParams(0,dp(52),1));
    root.addView(nav,new LinearLayout.LayoutParams(-1,-2));
  }
}
