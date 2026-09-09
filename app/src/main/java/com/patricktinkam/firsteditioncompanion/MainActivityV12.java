package com.patricktinkam.firsteditioncompanion;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import org.json.*;

/** v0.9.7: profile-first sheet, source details, and centralized SecureRandom dice. */
public class MainActivityV12 extends MainActivityV11 {
  @Override public void onCreate(android.os.Bundle state){
    rng=DiceRng.adapter();
    super.onCreate(state);
  }

  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.9.7");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.9.7",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  @Override void newCharacter(){
    startActivity(new Intent(this,CharacterCreationActivityV3.class));
  }

  @Override void dice(LinearLayout c){
    LinearLayout audit=card("Randomness / RNG");
    audit.addView(t("All attack rolls, saving throws, damage dice, quick dice, character-creation rolls, age rolls, and other built-in dice use one shared OS-seeded SecureRandom source. The app does not reseed the generator on each roll.",12,MUT,false));
    TextView result=t("RNG source active. Tap the self-check to sample every standard die used by the app.",13,TXT,false);audit.addView(result);
    Button check=b("Run RNG Self-Check");
    check.setOnClickListener(v->{
      DiceRng.Audit a=DiceRng.auditStandardDice();
      result.setText(a.summary+"\n\nSamples: "+a.totalSamples);
      result.setTextColor(a.passed?Color.rgb(153,205,139):Color.rgb(230,180,90));
    });
    audit.addView(check,new LinearLayout.LayoutParams(-1,dp(48)));add(c,audit);
    super.dice(c);
  }

  boolean hasActiveCharacter(){
    String name=S("name","").trim();
    return !name.isEmpty()&&!name.equalsIgnoreCase("New Character");
  }

  @Override void sheet(LinearLayout c){
    boolean active=hasActiveCharacter();
    String name=S("name","").trim();

    LinearLayout profiles=card("Character Profiles");
    profiles.addView(t("App Version: v0.9.7",14,GOLD,true));
    profiles.addView(t(active?"Active character: "+name:"No active character",14,MUT,false));
    profiles.addView(t(active
      ?"Create New Character opens a separate guided draft. Your current character remains untouched until a new character is finished."
      :"Create a new character with the guided creator, or load one of your saved characters. The blank legacy character sheet is hidden until a real character is active.",12,MUT,false));

    Button create=b("Create New Character");
    create.setOnClickListener(v->newCharacter());
    profiles.addView(create,new LinearLayout.LayoutParams(-1,dp(54)));

    LinearLayout row=new LinearLayout(this);
    Button save=b("Save Character");save.setEnabled(active);save.setOnClickListener(v->saveCharacterDialog());row.addView(save,new LinearLayout.LayoutParams(0,dp(48),1));
    Button load=b("Load Character");load.setOnClickListener(v->loadCharacterDialog());row.addView(load,new LinearLayout.LayoutParams(0,dp(48),1));
    profiles.addView(row);

    Button del=b("Delete Saved Character");del.setOnClickListener(v->deleteCharacterDialog());profiles.addView(del,new LinearLayout.LayoutParams(-1,dp(46)));
    add(c,profiles);

    if(!active){
      LinearLayout empty=card("Ready to Begin");
      empty.addView(t("There is no character loaded into the play sheet yet. Use Create New Character for the guided AD&D 1e creation flow, or Load Character to continue an existing adventurer.",14,TXT,false));
      add(c,empty);
      return;
    }

    super.sheet(c);
  }
}
