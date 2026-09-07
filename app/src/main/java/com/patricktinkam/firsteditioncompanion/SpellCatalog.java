package com.patricktinkam.firsteditioncompanion;
import java.util.*;
public final class SpellCatalog {
  public static final class Entry {
    public final String caster,name,source,summary; public final int level;
    Entry(String c,int l,String n,String s){
      caster=c;level=l;name=n;source=s;
      SpellRulesPHB.Rule rule=s.contains("PHB")?SpellRulesPHB.lookup(c,n):null;
      summary=(rule!=null&&!rule.digest.isEmpty())?rule.digest:summary(n,s);
    }
    public String label(){return (level==0?"Cantrip":"L"+level)+"  •  "+name+"  ["+source+"]";}
  }
  private static final ArrayList<Entry> ALL=new ArrayList<>();
  static { SpellDataCleric.fill(ALL); SpellDataDruid.fill(ALL); SpellDataIllusionist.fill(ALL); SpellDataMagicUser.fill(ALL); }
  public static String normalizeClass(String raw){String x=raw==null?"":raw.toLowerCase(Locale.US);if(x.contains("illusion"))return "Illusionist";if(x.contains("druid"))return "Druid";if(x.contains("cleric")||x.contains("priest"))return "Cleric";if(x.contains("magic")||x.contains("wizard")||x.contains("mage"))return "Magic-User";return raw==null?"":raw.trim();}
  public static ArrayList<Entry> forClass(String raw){String c=normalizeClass(raw);LinkedHashMap<String,Entry> m=new LinkedHashMap<>();for(Entry e:ALL)if(e.caster.equalsIgnoreCase(c)){String k=e.level+"|"+e.name.toLowerCase(Locale.US);Entry old=m.get(k);if(old==null)m.put(k,e);else if(!old.source.contains(e.source))m.put(k,new Entry(c,e.level,e.name,old.source+"/"+e.source));}ArrayList<Entry> out=new ArrayList<>(m.values());Collections.sort(out,(a,b)->a.level!=b.level?a.level-b.level:a.name.compareToIgnoreCase(b.name));return out;}
  private static String summary(String n,String s){
    String k=n.toLowerCase(Locale.US);
    if(n.equalsIgnoreCase("Chromatic Orb"))return "Creates a colored orb whose damage and special effect vary with caster level and hue.";
    if(n.equalsIgnoreCase("Goodberry"))return "Enchants berries so they provide nourishment and minor healing.";
    if(n.equalsIgnoreCase("Stoneskin"))return "Protective magic from the Unearthed Arcana catalog. Detailed mechanics remain source-dependent until a verified original UA source is linked.";
    if(n.equalsIgnoreCase("Combine"))return "Several clerics combine religious power through one acting cleric; detailed UA mechanics remain source-dependent.";
    if(k.startsWith("detect "))return "Reveals the named presence, direction, nature, or condition; consult the cited source for exact limits.";
    if(k.startsWith("cure "))return "Removes or repairs the named injury or affliction; consult the cited source for exact limits.";
    if(k.startsWith("protection ")||k.startsWith("resist ")||k.contains("immunity"))return "Provides magical protection or resistance against the named threat.";
    if(k.startsWith("summon ")||k.startsWith("conjure ")||k.contains("summoning"))return "Calls, creates, or brings forth the named creature or force.";
    if(k.startsWith("control "))return "Lets the caster influence or manipulate the named creature, force, element, or condition.";
    if(k.startsWith("hold "))return "Restrains or immobilizes the named target type, subject to the source spell's saving-throw rules.";
    if(k.contains("invisibility"))return "Creates or counters invisibility as indicated by the spell name.";
    if(k.startsWith("wall of "))return "Creates a magical barrier of the named material or energy.";
    return "Source-tagged spell reference. Exact mechanics are shown when a verified rule entry is available.";
  }
  private SpellCatalog(){}
}
