package com.patricktinkam.firsteditioncompanion;

import org.json.*;
import java.util.*;

/** Mutable, JSON-friendly state for the guided character creator. */
public final class CharacterDraft {
  public static final String[] ABILITIES={"STR","INT","WIS","DEX","CON","CHA"};

  public int step=0;
  public String raceName="";
  public String className="";
  public String rollMethod="Manual";
  public final int[] rawScores={0,0,0,0,0,0};

  public String ageMode="DMG roll";
  public int age=0;
  public String alignment="";
  public String deity="";
  public boolean useAlignmentLanguage=true;
  public final ArrayList<String> chosenLanguages=new ArrayList<>();

  public boolean hasMeaningfulProgress(){
    if(!raceName.isEmpty()||!className.isEmpty()||age>0||!alignment.isEmpty()||!deity.isEmpty()||!chosenLanguages.isEmpty())return true;
    for(int v:rawScores)if(v>0)return true;
    return false;
  }

  public JSONObject toJson(){
    JSONObject o=new JSONObject();
    try{
      o.put("schema",2);
      o.put("step",step);
      o.put("raceName",raceName);
      o.put("className",className);
      o.put("rollMethod",rollMethod);
      JSONArray a=new JSONArray();
      for(int v:rawScores)a.put(v);
      o.put("rawScores",a);
      o.put("ageMode",ageMode);
      o.put("age",age);
      o.put("alignment",alignment);
      o.put("deity",deity);
      o.put("useAlignmentLanguage",useAlignmentLanguage);
      JSONArray langs=new JSONArray();
      for(String s:chosenLanguages)langs.put(s);
      o.put("chosenLanguages",langs);
    }catch(Exception ignored){}
    return o;
  }

  public static CharacterDraft fromJson(String raw){
    CharacterDraft d=new CharacterDraft();
    if(raw==null||raw.trim().isEmpty())return d;
    try{
      JSONObject o=new JSONObject(raw);
      d.step=Math.max(0,Math.min(6,o.optInt("step",0)));
      d.raceName=o.optString("raceName","");
      d.className=o.optString("className","");
      d.rollMethod=o.optString("rollMethod","Manual");
      JSONArray a=o.optJSONArray("rawScores");
      if(a!=null)for(int i=0;i<Math.min(6,a.length());i++)d.rawScores[i]=a.optInt(i,0);
      d.ageMode=o.optString("ageMode","DMG roll");
      d.age=Math.max(0,o.optInt("age",0));
      d.alignment=o.optString("alignment","");
      d.deity=o.optString("deity","");
      d.useAlignmentLanguage=o.optBoolean("useAlignmentLanguage",true);
      JSONArray langs=o.optJSONArray("chosenLanguages");
      if(langs!=null)for(int i=0;i<langs.length();i++){
        String s=langs.optString(i,"").trim();if(!s.isEmpty()&&!d.chosenLanguages.contains(s))d.chosenLanguages.add(s);
      }
    }catch(Exception ignored){}
    return d;
  }
}
