package com.patricktinkam.firsteditioncompanion;

import android.view.*;
import android.widget.*;
import org.json.*;

/** Major PHB + DMG + supplied-UA mechanics pass. */
public class MainActivityV8 extends MainActivityV7 {

  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.7.0");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.7.0",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
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
    if(en.magic){
      String base=en.category+" • "+en.source+(en.cost.isEmpty()?"":" • Cost: "+en.cost)+(en.weight.isEmpty()?"":" • Weight: "+en.weight);
      return base+"\n\n"+MagicItemRules.describe(en);
    }
    return super.gearRules(en);
  }

  Button sourceBookButton(RulesReference.Topic r){
    String src=r.source==null?"":r.source;
    if(src.equals("PHB")){
      Button q=b("Optional source page • PHB p. "+r.page);q.setOnClickListener(v->openPHB(r.page));return q;
    }
    if(src.equals("DMG")){
      Button q=b("Optional source page • DMG p. "+r.page);q.setOnClickListener(v->openPdfPage(KEY_DMG_URI,r.page,0,"Dungeon Masters Guide • p. "+r.page));return q;
    }
    Button q=b("Source: "+src);q.setEnabled(false);return q;
  }

  @Override void rules(LinearLayout c){
    LinearLayout intro=card("Native 1e Rules Library");
    intro.addView(t("v0.7 moves the Companion toward being usable as a self-contained table reference. The descriptions below are paraphrased native summaries of the supplied AD&D Players Handbook and Dungeon Masters Guide. Linked PDFs are optional source-verification conveniences only; they are not required to use the rules reference.",13,TXT,false));
    intro.addView(t("Unearthed Arcana note: the UA file supplied to this project contains later/conversion-style terminology and mechanics. UA-only entries remain available, but they are explicitly treated as supplied-variant material rather than silently presented as clean original 1985 1e rules.",12,MUT,false));
    add(c,intro);

    LinearLayout books=card("Optional Source PDFs");
    books.addView(t("These links are optional. Native mechanics remain in the app even when no PDF is linked.",13,MUT,false));
    books.addView(t("Players Handbook: "+bookStatus(KEY_PHB_URI),13,TXT,true));
    Button phb=b(S(KEY_PHB_URI,"").isEmpty()?"Link PHB PDF (optional)":"Relink PHB PDF");phb.setOnClickListener(v->linkBook(REQ_LINK_PHB,"Players Handbook"));books.addView(phb);
    books.addView(t("Dungeon Masters Guide: "+bookStatus(KEY_DMG_URI),13,TXT,true));
    Button dmg=b(S(KEY_DMG_URI,"").isEmpty()?"Link DMG PDF (optional)":"Relink DMG PDF");dmg.setOnClickListener(v->linkBook(REQ_LINK_DMG,"Dungeon Masters Guide"));books.addView(dmg);
    books.addView(t("Unearthed Arcana variant: "+bookStatus(KEY_UA_URI),13,TXT,true));
    Button ua=b(S(KEY_UA_URI,"").isEmpty()?"Link UA PDF (optional)":"Relink UA PDF");ua.setOnClickListener(v->linkBook(REQ_LINK_UA,"Unearthed Arcana"));books.addView(ua);
    add(c,books);

    String lastSection="";
    for(RulesReference.Topic r:RulesReference.all()){
      if(!r.section.equals(lastSection)){
        c.addView(t(r.section,20,GOLD,true));lastSection=r.section;
      }
      LinearLayout k=card(r.title+"  •  "+r.source+" p. "+r.page);
      k.addView(t(r.text,14,TXT,false));
      k.addView(sourceBookButton(r));
      add(c,k);
    }
  }

  @Override String spellSourceNote(String source){
    if(source==null)return "";
    if(source.contains("UA")&&!source.contains("PHB"))return "\n\nSource integrity: UA-only content is based on the supplied UA variant file and is kept distinct from verified PHB/DMG 1e mechanics.";
    return "";
  }

  @Override void spells(LinearLayout c){
    LinearLayout ref=card("Sourcebook Spell Mechanics");
    ref.addView(t("PHB spell entries show their native school/type, range, duration, area, components, casting time, saving throw, reversibility, and an offline effect digest. Clerics prepare directly from their full divine list; Magic-Users and Illusionists retain learned spellbooks.",13,TXT,false));
    ref.addView(t("DMG spell commentary is reflected in the Rules tab for acquisition, interruption, melee casting, cover, and counter-affecting magic. UA-only spells remain tagged as supplied-variant material.",12,MUT,false));
    add(c,ref);
    super.spells(c);
  }
}
