package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.text.*;
import android.widget.*;
import org.json.*;
import java.util.*;

public class MainActivityV3 extends MainActivityV2 {
  boolean sameSpell(JSONObject a,String name,int level){return a!=null&&a.optInt("level",1)==level&&a.optString("name","").equalsIgnoreCase(name);}

  boolean ensureKnown(JSONObject tr){
    if(tr==null||tr.optJSONArray("known")!=null)return false;
    JSONArray known=new JSONArray();
    JSONArray prepared=tr.optJSONArray("spells");
    if(prepared!=null){
      for(int i=0;i<prepared.length();i++){
        JSONObject s=prepared.optJSONObject(i);if(s==null)continue;
        String name=s.optString("name","Spell");int level=s.optInt("level",1);boolean exists=false;
        for(int j=0;j<known.length();j++)if(sameSpell(known.optJSONObject(j),name,level)){exists=true;break;}
        if(!exists){JSONObject k=new JSONObject();try{k.put("name",name);k.put("level",level);k.put("source",s.optString("source","Migrated from prepared spells"));k.put("summary",s.optString("summary","No catalog summary stored for this spell."));known.put(k);}catch(Exception ignored){}}
      }
    }
    try{tr.put("known",known);}catch(Exception ignored){}
    return true;
  }

  JSONArray known(JSONObject tr){ensureKnown(tr);JSONArray k=tr==null?null:tr.optJSONArray("known");return k==null?new JSONArray():k;}
  String libraryName(JSONObject tr){String cls=SpellCatalog.normalizeClass(tr==null?"":tr.optString("class",""));return (cls.equals("Magic-User")||cls.equals("Illusionist"))?"Spellbook":"Available Spells";}

  void addKnown(JSONArray tracks,int ti,String name,int level,String source,String summary){
    try{
      JSONObject tr=tracks.getJSONObject(ti);JSONArray k=known(tr);
      for(int i=0;i<k.length();i++)if(sameSpell(k.optJSONObject(i),name,level)){toast(name+" is already in "+libraryName(tr));return;}
      JSONObject o=new JSONObject();o.put("name",name);o.put("level",level);o.put("source",source);o.put("summary",summary);k.put(o);tr.put("known",k);save(tracks);show("Spells");toast("Added "+name+" to "+libraryName(tr));
    }catch(Exception e){toast("Could not add spell");}
  }

  void addKnown(JSONArray tracks,int ti,SpellCatalog.Entry en){addKnown(tracks,ti,en.name,en.level,en.source,en.summary);}

  void knownDetail(JSONArray tracks,int ti,JSONObject spell){
    JSONObject tr=tracks.optJSONObject(ti);if(tr==null||spell==null)return;
    String name=spell.optString("name","Spell"),src=spell.optString("source","Custom / campaign"),sum=spell.optString("summary","No catalog summary stored for this spell.");int level=spell.optInt("level",1);
    new AlertDialog.Builder(this).setTitle(name)
      .setMessage((level==0?"Cantrip":"Level "+level)+"\nSource: "+src+"\n\n"+sum)
      .setPositiveButton("Prepare",(d,w)->prepareKnownDialog(tracks,ti,spell))
      .setNegativeButton("Close",null).show();
  }

  void prepareKnownDialog(JSONArray tracks,int ti,JSONObject spell){
    EditText copies=e("1","Copies to prepare",true);
    String name=spell.optString("name","Spell");
    new AlertDialog.Builder(this).setTitle("Prepare "+name).setMessage("How many copies do you want memorized/prepared?").setView(copies)
      .setPositiveButton("Prepare",(d,w)->{try{int cp=Math.max(1,Math.min(20,Integer.parseInt(copies.getText().toString())));JSONObject tr=tracks.getJSONObject(ti);JSONArray prepared=tr.optJSONArray("spells");if(prepared==null){prepared=new JSONArray();tr.put("spells",prepared);}for(int i=0;i<cp;i++){JSONObject o=new JSONObject();o.put("name",name);o.put("level",spell.optInt("level",1));o.put("used",false);o.put("source",spell.optString("source","Custom / campaign"));o.put("summary",spell.optString("summary","No catalog summary stored for this spell."));prepared.put(o);}save(tracks);show("Spells");toast("Prepared "+cp+" × "+name);}catch(Exception ex){toast("Enter a number from 1 to 20");}})
      .setNegativeButton("Cancel",null).show();
  }

  void customKnownDialog(JSONArray tracks,int ti){
    JSONObject tr=tracks.optJSONObject(ti);if(tr==null)return;
    LinearLayout x=card("Add to "+libraryName(tr));EditText n=e("","Spell name",false),lv=e("1","Spell level",true);x.addView(n);x.addView(lv);
    new AlertDialog.Builder(this).setTitle("Custom / Campaign Spell").setView(x)
      .setPositiveButton("Add",(d,w)->{try{String name=n.getText().toString().trim();if(name.isEmpty()){toast("Enter a spell name");return;}int level=Math.max(0,Math.min(9,Integer.parseInt(lv.getText().toString())));addKnown(tracks,ti,name,level,"Custom / campaign","Personal spell entry. Add your own notes in the character notes section if needed.");}catch(Exception ex){toast("Check spell level");}})
      .setNegativeButton("Cancel",null).show();
  }

  @Override void catalogDetail(JSONArray tracks,int ti,SpellCatalog.Entry en){
    JSONObject tr=tracks.optJSONObject(ti);String destination=libraryName(tr);
    new AlertDialog.Builder(this).setTitle(en.name)
      .setMessage((en.level==0?"Cantrip":"Level "+en.level)+" • "+en.caster+"\nSource: "+en.source+"\n\n"+en.summary+"\n\nAdd this spell to "+destination+" first; prepare it from there when your character memorizes it.")
      .setPositiveButton("Add to "+destination,(d,w)->addKnown(tracks,ti,en))
      .setNegativeButton("Close",null).show();
  }

  @Override void spells(LinearLayout c){
    c.addView(t("Spells you know/have access to are now separate from spells currently prepared. Magic-Users and Illusionists keep a Spellbook; Clerics and Druids keep an Available Spells list. Prepare copies from that list, then mark prepared copies used as you cast them.",14,MUT,false));
    JSONArray tracks=tracks();boolean migrated=false;for(int i=0;i<tracks.length();i++)migrated|=ensureKnown(tracks.optJSONObject(i));if(migrated)save(tracks);

    for(int i=0;i<tracks.length();i++){
      JSONObject tr=tracks.optJSONObject(i);if(tr==null)continue;final int ti=i;String libName=libraryName(tr);LinearLayout card=card(tr.optString("class","Caster")+" • class level "+tr.optInt("level",1));

      JSONArray k=known(tr);card.addView(t(libName+"  •  "+k.length()+" spell"+(k.length()==1?"":"s"),16,GOLD,true));
      if(k.length()==0)card.addView(t("No spells recorded yet. Browse the class catalog or add a custom/campaign spell.",13,MUT,false));
      for(int j=0;j<k.length();j++){
        JSONObject spell=k.optJSONObject(j);if(spell==null)continue;final int kj=j;LinearLayout row=new LinearLayout(this);TextView name=t((spell.optInt("level",1)==0?"Cantrip":"L"+spell.optInt("level",1))+"  "+spell.optString("name","Spell"),15,TXT,true);name.setOnClickListener(v->knownDetail(tracks,ti,spell));row.addView(name,new LinearLayout.LayoutParams(0,-2,1));Button prep=b("Prepare");prep.setOnClickListener(v->prepareKnownDialog(tracks,ti,spell));row.addView(prep);Button remove=b("×");remove.setOnClickListener(v->new AlertDialog.Builder(this).setTitle("Remove from "+libName).setMessage("Remove "+spell.optString("name","this spell")+"? Any copies already prepared will remain prepared until you remove them separately.").setPositiveButton("Remove",(d,w)->{tr.optJSONArray("known").remove(kj);save(tracks);show("Spells");}).setNegativeButton("Cancel",null).show());row.addView(remove);card.addView(row);
      }

      LinearLayout libActions=new LinearLayout(this);Button browse=b("Browse Class Spells");browse.setOnClickListener(v->catalogDialog(tracks,ti));libActions.addView(browse,new LinearLayout.LayoutParams(0,dp(48),1));Button custom=b("+ Custom Spell");custom.setOnClickListener(v->customKnownDialog(tracks,ti));libActions.addView(custom,new LinearLayout.LayoutParams(0,dp(48),1));card.addView(libActions);

      JSONArray prepared=tr.optJSONArray("spells");if(prepared==null)prepared=new JSONArray();card.addView(t("Prepared / Memorized  •  "+prepared.length()+" cop"+(prepared.length()==1?"y":"ies"),16,GOLD,true));
      if(prepared.length()==0)card.addView(t("Nothing is currently prepared.",13,MUT,false));
      for(int j=0;j<prepared.length();j++){
        JSONObject spell=prepared.optJSONObject(j);if(spell==null)continue;final int sj=j;boolean used=spell.optBoolean("used");LinearLayout row=new LinearLayout(this);TextView name=t((spell.optInt("level",1)==0?"Cantrip":"L"+spell.optInt("level",1))+"  "+spell.optString("name","Spell")+(used?"  [USED]":""),15,used?MUT:TXT,!used);name.setOnClickListener(v->preparedSpellDetail(spell));row.addView(name,new LinearLayout.LayoutParams(0,-2,1));Button use=b(used?"Restore":"Use");use.setOnClickListener(v->{try{spell.put("used",!spell.optBoolean("used"));save(tracks);show("Spells");}catch(Exception ignored){}});row.addView(use);Button remove=b("×");remove.setOnClickListener(v->{tr.optJSONArray("spells").remove(sj);save(tracks);show("Spells");});row.addView(remove);card.addView(row);
      }

      Button rest=b("Rest / Restore All Prepared Spells");rest.setOnClickListener(v->{JSONArray q=tr.optJSONArray("spells");if(q!=null)for(int j=0;j<q.length();j++)try{q.getJSONObject(j).put("used",false);}catch(Exception ignored){}save(tracks);show("Spells");});card.addView(rest);
      Button del=b("Remove casting track");del.setOnClickListener(v->{tracks.remove(ti);save(tracks);show("Spells");});card.addView(del);add(c,card);
    }

    Button addTrack=b("+ Add Casting Track");addTrack.setOnClickListener(v->trackDialog());add(c,addTrack);
  }
}
