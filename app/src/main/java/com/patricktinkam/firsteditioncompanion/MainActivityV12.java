package com.patricktinkam.firsteditioncompanion;

import android.content.*;
import android.view.*;
import android.widget.*;
import org.json.*;

/** v0.9.2: restores the visible character-profile controls and guided-creator entry point. */
public class MainActivityV12 extends MainActivityV11 {
  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.9.2");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.9.2",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  @Override void newCharacter(){
    startActivity(new Intent(this,CharacterCreationActivityV2.class));
  }

  @Override void sheet(LinearLayout c){
    LinearLayout profiles=card("Character Profiles");
    profiles.addView(t("App Version: v0.9.2",14,GOLD,true));
    profiles.addView(t("Current working sheet: "+S("name","New Character"),14,MUT,false));
    profiles.addView(t("Create New Character opens the separate guided, multi-step creator. Your current character remains untouched while you work through the draft.",12,MUT,false));

    Button create=b("Create New Character");
    create.setOnClickListener(v->newCharacter());
    profiles.addView(create,new LinearLayout.LayoutParams(-1,dp(54)));

    LinearLayout row=new LinearLayout(this);
    Button save=b("Save Character");save.setOnClickListener(v->saveCharacterDialog());row.addView(save,new LinearLayout.LayoutParams(0,dp(48),1));
    Button load=b("Load Character");load.setOnClickListener(v->loadCharacterDialog());row.addView(load,new LinearLayout.LayoutParams(0,dp(48),1));
    profiles.addView(row);

    Button del=b("Delete Saved Character");del.setOnClickListener(v->deleteCharacterDialog());profiles.addView(del,new LinearLayout.LayoutParams(-1,dp(46)));
    add(c,profiles);

    super.sheet(c);
  }
}
