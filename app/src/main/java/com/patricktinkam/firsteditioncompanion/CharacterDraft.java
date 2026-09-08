package com.patricktinkam.firsteditioncompanion;

import org.json.*;

/** Mutable, JSON-friendly state for the guided character creator. */
public final class CharacterDraft {
  public static final String[] ABILITIES={"STR","INT","WIS","DEX","CON","CHA"};

  public int step=0;
  public String raceName="";
  public String className="";
  public String rollMethod="Manual";
  public final int[] rawScores={0,0,0,0,0,0};

  public boolean hasMeaningfulProgress(){
    if(!raceName.isEmpty()||!className.isEmpty())return true;
    for(int v:rawScores)if(v>0)return true;
    return false;
  }

  public JSONObject toJson(){
    JSONObject o=new JSONObject();
    try{
      o.put("schema",1);
      o.put("step",step);
      o.put("raceName",raceName);
      o.put("className",className);
      o.put("rollMethod",rollMethod);
      JSONArray a=new JSONArray();
      for(int v:rawScores)a.put(v);
      o.put("rawScores",a);
    }catch(Exception ignored){}
    return o;
  }

  public static CharacterDraft fromJson(String raw){
    CharacterDraft d=new CharacterDraft();
    if(raw==null||raw.trim().isEmpty())return d;
    try{
      JSONObject o=new JSONObject(raw);
      d.step=Math.max(0,Math.min(3,o.optInt("step",0)));
      d.raceName=o.optString("raceName","");
      d.className=o.optString("className","");
      d.rollMethod=o.optString("rollMethod","Manual");
      JSONArray a=o.optJSONArray("rawScores");
      if(a!=null)for(int i=0;i<Math.min(6,a.length());i++)d.rawScores[i]=a.optInt(i,0);
    }catch(Exception ignored){}
    return d;
  }
}
