package com.patricktinkam.firsteditioncompanion;

import android.view.*;
import android.widget.*;
import org.json.*;
import java.util.*;

/**
 * v0.6.2 spell-flow refinement.
 * Clerics do not maintain a learned-spell list: the built-in Cleric catalog is
 * their always-available prayer list, grouped by spell level, and spells are
 * prepared directly from that list.
 */
public class MainActivityV7 extends MainActivityV6 {

  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.6.2");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.6.2",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  boolean isCleric(JSONObject tr){
    return tr!=null && "Cleric".equalsIgnoreCase(SpellCatalog.normalizeClass(tr.optString("class","")));
  }

  void clericSpellRow(LinearLayout parent,JSONArray tracks,int ti,SpellCatalog.Entry en){
    LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
    LinearLayout text=new LinearLayout(this);text.setOrientation(LinearLayout.VERTICAL);
    TextView name=t(en.name,15,TXT,true);name.setOnClickListener(v->clericCatalogDetail(tracks,ti,en));text.addView(name);
    text.addView(t(en.source,11,MUT,false));
    row.addView(text,new LinearLayout.LayoutParams(0,-2,1));
    Button details=b("Details");details.setOnClickListener(v->clericCatalogDetail(tracks,ti,en));row.addView(details,new LinearLayout.LayoutParams(dp(88),dp(44)));
    Button prepare=b("Prepare");prepare.setOnClickListener(v->prepareClericSpell(tracks,ti,en));row.addView(prepare,new LinearLayout.LayoutParams(dp(92),dp(44)));
    parent.addView(row);
  }

  void clericCatalogDetail(JSONArray tracks,int ti,SpellCatalog.Entry en){
    SpellRulesPHB.Rule rule=en.source.contains("PHB")?SpellRulesPHB.lookup(en.caster,en.name):null;
    android.app.AlertDialog.Builder d=new android.app.AlertDialog.Builder(this)
      .setTitle(en.name)
      .setMessage(spellDetails(en.caster,en.name,en.level,en.source,en.summary))
      .setPositiveButton("Prepare",(x,w)->prepareClericSpell(tracks,ti,en))
      .setNegativeButton("Close",null);
    if(rule!=null)d.setNeutralButton("Open PHB p. "+rule.page,(x,w)->openPHB(rule.page));
    d.show();
  }

  void prepareClericSpell(JSONArray tracks,int ti,SpellCatalog.Entry en){
    JSONObject temp=new JSONObject();
    try{
      temp.put("name",en.name);temp.put("level",en.level);temp.put("source",en.source);temp.put("summary",en.summary);
      prepareKnownDialog(tracks,ti,temp);
    }catch(Exception e){toast("Could not prepare spell");}
  }

  void clericAvailableList(LinearLayout card,JSONArray tracks,int ti){
    ArrayList<SpellCatalog.Entry> all=SpellCatalog.forClass("Cleric");
    card.addView(t("Divine Spell List  •  "+all.size()+" spells",16,GOLD,true));
    card.addView(t("Clerics automatically have access to the built-in Cleric spell list. There is nothing to learn or add first—choose a spell below and prepare the number of copies you want.",13,MUT,false));

    if(all.isEmpty()){
      card.addView(t("The built-in Cleric catalog could not be loaded.",13,MUT,false));
      return;
    }

    for(int level=1;level<=7;level++){
      ArrayList<SpellCatalog.Entry> atLevel=new ArrayList<>();
      for(SpellCatalog.Entry en:all)if(en.level==level)atLevel.add(en);
      if(atLevel.isEmpty())continue;
      Collections.sort(atLevel,(a,b)->a.name.compareToIgnoreCase(b.name));
      LinearLayout section=card("Level "+level+" Spells  •  "+atLevel.size());
      for(SpellCatalog.Entry en:atLevel)clericSpellRow(section,tracks,ti,en);
      card.addView(section);
    }

    LinearLayout customActions=new LinearLayout(this);
    Button custom=b("+ Custom / Campaign Spell");custom.setOnClickListener(v->customKnownDialog(tracks,ti));
    customActions.addView(custom,new LinearLayout.LayoutParams(0,dp(48),1));
    card.addView(customActions);
  }

  void preparedSection(LinearLayout card,JSONArray tracks,JSONObject tr,int ti){
    JSONArray prepared=tr.optJSONArray("spells");if(prepared==null){prepared=new JSONArray();try{tr.put("spells",prepared);}catch(Exception ignored){}}
    card.addView(t("Prepared / Memorized  •  "+prepared.length()+" cop"+(prepared.length()==1?"y":"ies"),16,GOLD,true));
    if(prepared.length()==0)card.addView(t("Nothing is currently prepared.",13,MUT,false));
    for(int j=0;j<prepared.length();j++){
      JSONObject spell=prepared.optJSONObject(j);if(spell==null)continue;final int sj=j;boolean used=spell.optBoolean("used");
      LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
      TextView name=t((spell.optInt("level",1)==0?"Cantrip":"L"+spell.optInt("level",1))+"  "+spell.optString("name","Spell")+(used?"  [USED]":""),15,used?MUT:TXT,!used);
      name.setOnClickListener(v->preparedSpellDetail(spell));row.addView(name,new LinearLayout.LayoutParams(0,-2,1));
      Button use=b(used?"Restore":"Use");use.setOnClickListener(v->{try{spell.put("used",!spell.optBoolean("used"));save(tracks);show("Spells");}catch(Exception ignored){}});row.addView(use);
      Button remove=b("×");remove.setOnClickListener(v->{JSONArray q=tr.optJSONArray("spells");if(q!=null)q.remove(sj);save(tracks);show("Spells");});row.addView(remove);
      card.addView(row);
    }
    Button rest=b("Rest / Restore All Prepared Spells");rest.setOnClickListener(v->{JSONArray q=tr.optJSONArray("spells");if(q!=null)for(int j=0;j<q.length();j++)try{q.getJSONObject(j).put("used",false);}catch(Exception ignored){}save(tracks);show("Spells");});card.addView(rest);
  }

  void normalCasterLibrary(LinearLayout card,JSONArray tracks,JSONObject tr,int ti){
    String libName=libraryName(tr);JSONArray k=known(tr);
    card.addView(t(libName+"  •  "+k.length()+" spell"+(k.length()==1?"":"s"),16,GOLD,true));
    if(k.length()==0)card.addView(t("No spells recorded yet. Browse the class catalog or add a custom/campaign spell.",13,MUT,false));
    for(int j=0;j<k.length();j++){
      JSONObject spell=k.optJSONObject(j);if(spell==null)continue;final int kj=j;
      LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
      TextView name=t((spell.optInt("level",1)==0?"Cantrip":"L"+spell.optInt("level",1))+"  "+spell.optString("name","Spell"),15,TXT,true);name.setOnClickListener(v->knownDetail(tracks,ti,spell));row.addView(name,new LinearLayout.LayoutParams(0,-2,1));
      Button prep=b("Prepare");prep.setOnClickListener(v->prepareKnownDialog(tracks,ti,spell));row.addView(prep);
      Button remove=b("×");remove.setOnClickListener(v->new android.app.AlertDialog.Builder(this).setTitle("Remove from "+libName).setMessage("Remove "+spell.optString("name","this spell")+"? Any copies already prepared will remain prepared until you remove them separately.").setPositiveButton("Remove",(d,w)->{tr.optJSONArray("known").remove(kj);save(tracks);show("Spells");}).setNegativeButton("Cancel",null).show());row.addView(remove);
      card.addView(row);
    }
    LinearLayout actions=new LinearLayout(this);
    Button browse=b("Browse Class Spells");browse.setOnClickListener(v->catalogDialog(tracks,ti));actions.addView(browse,new LinearLayout.LayoutParams(0,dp(48),1));
    Button custom=b("+ Custom Spell");custom.setOnClickListener(v->customKnownDialog(tracks,ti));actions.addView(custom,new LinearLayout.LayoutParams(0,dp(48),1));
    card.addView(actions);
  }

  @Override void spells(LinearLayout c){
    c.addView(t("Spell access follows the class. Clerics automatically receive the full built-in divine spell list and prepare directly from it. Magic-Users and Illusionists still maintain learned spellbooks; other caster tracks keep their existing available-spell workflow.",14,MUT,false));
    JSONArray tracks=tracks();boolean migrated=false;for(int i=0;i<tracks.length();i++)migrated|=ensureKnown(tracks.optJSONObject(i));if(migrated)save(tracks);

    for(int i=0;i<tracks.length();i++){
      JSONObject tr=tracks.optJSONObject(i);if(tr==null)continue;final int ti=i;
      String cls=SpellCatalog.normalizeClass(tr.optString("class","Caster"));
      LinearLayout outer=card(cls+" • class level "+tr.optInt("level",1));

      if(isCleric(tr)) clericAvailableList(outer,tracks,ti);
      else normalCasterLibrary(outer,tracks,tr,ti);

      preparedSection(outer,tracks,tr,ti);
      Button del=b("Remove casting track");del.setOnClickListener(v->{tracks.remove(ti);save(tracks);show("Spells");});outer.addView(del);
      add(c,outer);
    }

    Button addTrack=b("+ Add Casting Track");addTrack.setOnClickListener(v->trackDialog());add(c,addTrack);
  }
}
