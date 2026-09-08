package com.patricktinkam.firsteditioncompanion;

import android.view.*;
import android.widget.*;
import org.json.*;

/** v0.7.1: every built-in gear/treasure entry uses an offline native description path. */
public class MainActivityV9 extends MainActivityV8 {

  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.7.1");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.7.1",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  @Override String gearRules(GearCatalog.Entry en){
    if(en==null)return "";
    String base=en.category+" • "+en.source+(en.cost.isEmpty()?"":" • Cost: "+en.cost)+(en.weight.isEmpty()?"":" • Weight: "+en.weight);
    String body=en.magic?MagicItemRules.describe(en):EquipmentRules.describe(en);
    String sourceNote="";
    if(en.source.contains("UA")&&!en.source.contains("PHB"))sourceNote="\n\nSource note: UA-only handling follows the supplied UA variant material where applicable and is kept distinct from verified PHB/DMG 1e rules.";
    else if(en.source.contains("DMG"))sourceNote="\n\nSource: Dungeon Masters Guide. The description is stored natively; linking a PDF is optional.";
    else if(en.source.contains("PHB"))sourceNote="\n\nSource: Players Handbook. The description is stored natively; linking a PDF is optional.";
    return base+"\n\n"+body+sourceNote;
  }
}
