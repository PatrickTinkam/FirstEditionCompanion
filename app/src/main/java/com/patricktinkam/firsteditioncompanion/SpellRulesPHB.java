package com.patricktinkam.firsteditioncompanion;

import android.util.Base64;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.GZIPInputStream;
import org.json.*;

public final class SpellRulesPHB {
  public static final class Rule {
    public final String caster,name,school,level,range,duration,area,components,castingTime,savingThrow,digest;
    public final int page; public final boolean reversible;
    Rule(JSONObject o){caster=o.optString("c");name=o.optString("n");school=o.optString("s");page=o.optInt("p");reversible=o.optBoolean("v");level=o.optString("l");range=o.optString("r");duration=o.optString("d");area=o.optString("a");components=o.optString("co");castingTime=o.optString("ct");savingThrow=o.optString("st");digest=digestFor(name);}
    public String source(){return "PHB p. "+page;}
    public String details(){StringBuilder x=new StringBuilder();if(!school.isEmpty())x.append("School/Type: ").append(school).append("\n");if(reversible)x.append("Reversible: Yes\n");if(!range.isEmpty())x.append("Range: ").append(range).append("\n");if(!duration.isEmpty())x.append("Duration: ").append(duration).append("\n");if(!area.isEmpty())x.append("Area of Effect: ").append(area).append("\n");if(!components.isEmpty())x.append("Components: ").append(components).append("\n");if(!castingTime.isEmpty())x.append("Casting Time: ").append(castingTime).append("\n");if(!savingThrow.isEmpty())x.append("Saving Throw: ").append(savingThrow).append("\n");if(!digest.isEmpty())x.append("\nRules digest: ").append(digest);else x.append("\nRules digest: Open the cited PHB page for the complete effect; v0.6 avoids inventing a mechanic when the source scan could not be reduced confidently.");x.append("\n\nSource: ").append(source());return x.toString();}
  }
  private static final ArrayList<Rule> ALL=new ArrayList<>();
  private static final HashMap<String,String> D=new HashMap<>();
  static {
    D.put("bless","Friendly creatures in the area gain +1 morale and +1 on attack rolls, but only if they are not already engaged in melee when the spell is cast. The reverse imposes -1 morale and attack rolls on enemies.");
    D.put("command","Issues one clear one-word order to a creature that understands the language. Undead are unaffected. Creatures with Intelligence 13+ or 6+ HD/levels receive a save versus magic; a command to 'die' only causes a one-round faint.");
    D.put("createwater","Creates 4 gallons of clean water per caster level, or the reverse destroys the same amount. The water persists normally; it cannot be created inside a living creature.");
    D.put("curelightwounds","Touch heals 1d8 hit points on a living corporeal creature. The reverse deals 1d8 damage and requires a melee touch hit if the target is avoiding contact.");
    D.put("sanctuary","Anyone attempting to directly attack the protected cleric must first save versus magic; failure makes the attacker ignore the cleric and choose another target. Area attacks still work, and the cleric cannot take offensive action without breaking the protection.");
    D.put("resistcold","Protects the touched creature from cold: comfortable down to 0°F, +3 on saves against cold, and cold damage is reduced to half on a failed save or one-quarter on a successful save.");
    D.put("magicmissile","Creates missiles that strike unerringly for 1d4+1 damage each. One missile is gained initially, with another for every two caster levels beyond 1st; missiles may be split among targets.");
    D.put("fireball","Explodes in a 2-inch-radius sphere for 1d6 fire damage per caster level. A successful save takes half damage. The burst fills its volume, so confined spaces can make placement important.");
    D.put("lightningbolt","Releases a lightning stroke for 1d6 damage per caster level; a successful save takes half. The bolt can ignite combustibles, damage objects, and rebound/continue according to its path and the DM's adjudication.");
    D.put("sleep","Puts a variable number of lower-HD creatures into magical sleep with no saving throw. Undead and specifically excluded creatures are unaffected; all targets must fit within the spell's area. Sleeping creatures are extremely vulnerable.");
    D.put("haste","Affected creatures move and attack at double their normal rates; spellcasting is not accelerated. The number of creatures affected is limited by caster level, and haste negates slow.");
    D.put("slow","Reduces affected creatures' movement and attack rates; it counters haste. Consult the source entry for the exact targeting and saving-throw procedure.");
    D.put("web","Creates strong, sticky webs that obstruct and entangle creatures in the area. Strength, size, fire, and cutting can affect how quickly a trapped creature gets free.");
    D.put("fly","Grants controlled flight for the rolled duration. The caster controls direction and movement within the spell's movement limits; the variable duration is normally rolled secretly by the DM.");
    D.put("invisibility","Makes the recipient unseen until the spell ends or the recipient attacks. Carried gear becomes invisible with the creature; light produced by an invisible creature is still visible.");
    D.put("improvedinvisibility","Like invisibility, but the recipient can attack, cast spells, or fire missiles without becoming visible. Observant foes may attack the telltale shimmer at -4; the invisible creature receives +4 on saving throws.");
    D.put("polymorphother","Transforms another creature's form and abilities permanently unless reversed or dispelled. The target saves to negate the spell, and a major form change can also alter mentality/personality according to the PHB's percentage procedure.");
    D.put("wish","A very powerful reality-altering spell. The DM interprets wording and scope; uses beyond duplicating or safely producing lesser effects can exact severe strain, including -3 Strength and 2d4 days of bed rest under the PHB rule.");
    D.put("limitedwish","Partially or temporarily changes reality according to carefully worded intent. It cannot simply create major realities, wealth, or experience; it can produce bounded changes such as healing, modifying combat chances, extending effects, or revealing a minor clue.");
    D.put("phantasmalkiller","Creates the victim's worst fear as an illusion only the caster and target perceive. The killer attacks as a 4-HD monster; a hit kills from fright. The target can attempt the spell's special disbelief procedure based on Intelligence.");
    D.put("mending","Repairs small breaks in ordinary objects, including a single break in small metal objects, multiple breaks in wood/ceramic, or a hole in a leather container. It does not repair magic items.");
    D.put("message","Carries a whispered message along a straight, unobstructed path to the chosen recipient. If the duration allows, the recipient can whisper a reply back to the caster.");
    D.put("invisiblestalker","Summons and binds an 8-HD invisible stalker to perform a task. It obeys even over great distances but is unwilling, resents prolonged/complex missions, and may twist instructions.");
    D.put("ottosirresistibledance","A touched victim must dance for 2-5 rounds and can do nothing else. While dancing it loses shield benefit, cannot make saving throws, and its Armor Class is worsened by 4.");
    D.put("gazereflection","Creates a mirror-like area of air for one round that reflects gaze attacks back toward the creature using the gaze.");
    D.put("continualdarkness","Creates a permanent globe of impenetrable darkness with the listed radius, following the core darkness spell's handling except for the illusionist entry's stated differences.");
    D.put("truesight","Penetrates illusions and deceptive forms in a manner similar to cleric true seeing, but the illusionist version does not reveal alignment.");
    D.put("animalfriendship","Makes a suitable animal friendly if it fails its save. The druid can teach it up to three tricks per point of Intelligence; training takes one week per trick and must be completed within three months.");
    D.put("speakwithplants","Allows simple communication with living vegetation and limited commands such as reporting passage, opening a route, or entangling pursuers. It does not animate normally immobile plants.");
    D.put("stickstosnakes","Changes one suitable nonmagical stick or similar wooden object per caster level into a commanded snake. The cleric version gives each created snake a 5% chance per caster level to be venomous; the reverse turns affected snakes back to sticks.");
    try{byte[] gz=Base64.decode(SpellRulesPHBData.DATA,Base64.DEFAULT);ByteArrayOutputStream out=new ByteArrayOutputStream();try(GZIPInputStream in=new GZIPInputStream(new ByteArrayInputStream(gz))){byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);}JSONArray a=new JSONArray(out.toString(StandardCharsets.UTF_8.name()));for(int i=0;i<a.length();i++)ALL.add(new Rule(a.getJSONObject(i)));}catch(Exception ignored){}
  }
  static String norm(String s){String x=s==null?"":s.toLowerCase(Locale.US);x=x.replace("colour","color").replace("glamer","glamour").replace("neutralise","neutralize").replace("paralysation","paralyzation").replace("projected","project");x=x.replace(" feet "," ").replace(" foot "," ").replace(" ft "," ");return x.replaceAll("[^a-z0-9]","");}
  static String digestFor(String name){String d=D.get(norm(name));if(d!=null)return d;String k=norm(name);if(k.startsWith("detect"))return "Detects the named presence, direction, nature, or condition within the spell's listed range and duration; open the cited PHB page for exact limitations.";if(k.startsWith("cure"))return "Removes or repairs the named injury or affliction subject to the listed target, range, and saving-throw rules; open the cited PHB page for exact limits.";if(k.startsWith("protectionfrom")||k.startsWith("resist"))return "Provides the named magical protection or resistance under the listed duration and area; open the cited PHB page for exact modifiers and exclusions.";if(k.startsWith("monstersummoning")||k.startsWith("animalsummoning"))return "Summons creatures according to the spell level and listed arrival/duration rules; the cited PHB page gives the exact number and HD limits.";if(k.startsWith("wallof"))return "Creates the named magical barrier with the listed dimensions and duration; the cited PHB page gives its damage, passage, and destruction rules.";return "";}
  static int distance(String a,String b){int[] prev=new int[b.length()+1],cur=new int[b.length()+1];for(int j=0;j<=b.length();j++)prev[j]=j;for(int i=1;i<=a.length();i++){cur[0]=i;for(int j=1;j<=b.length();j++){int cost=a.charAt(i-1)==b.charAt(j-1)?0:1;cur[j]=Math.min(Math.min(cur[j-1]+1,prev[j]+1),prev[j-1]+cost);}int[] t=prev;prev=cur;cur=t;}return prev[b.length()];}
  public static Rule lookup(String caster,String name){String c=SpellCatalog.normalizeClass(caster),n=norm(name);Rule best=null;int bestD=Integer.MAX_VALUE;for(Rule r:ALL){if(!r.caster.equalsIgnoreCase(c))continue;String rn=norm(r.name);if(rn.equals(n))return r;int d=distance(rn,n);if(d<bestD){bestD=d;best=r;}}int tolerance=Math.max(2,Math.min(5,n.length()/8));return bestD<=tolerance?best:null;}
  public static Rule lookupAny(String name,int level){String n=norm(name);Rule best=null;int bestD=Integer.MAX_VALUE;for(Rule r:ALL){String rn=norm(r.name);int d=rn.equals(n)?0:distance(rn,n);if(d<bestD&&(level<=0||r.level.isEmpty()||r.level.equals(String.valueOf(level)))){bestD=d;best=r;if(d==0)return r;}}int tolerance=Math.max(2,Math.min(5,n.length()/8));return bestD<=tolerance?best:null;}
  public static ArrayList<Rule> all(){return new ArrayList<>(ALL);}
  private SpellRulesPHB(){}
}
