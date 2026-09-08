package com.patricktinkam.firsteditioncompanion;
import java.util.*;
public final class SpellCatalog {
  public static final class Entry {
    public final String caster,name,source,summary; public final int level;
    Entry(String c,int l,String n,String s){
      caster=c;level=l;name=n;source=s;
      SpellRulesPHB.Rule rule=s.contains("PHB")?SpellRulesPHB.lookup(c,n):null;
      summary=(rule!=null&&!rule.digest.isEmpty())?rule.digest:NativeSpellDescriptions.summary(n,s);
    }
    public String label(){return (level==0?"Cantrip":"L"+level)+"  •  "+name+"  ["+source+"]";}
  }
  private static final ArrayList<Entry> ALL=new ArrayList<>();
  static { SpellDataCleric.fill(ALL); SpellDataDruid.fill(ALL); SpellDataIllusionist.fill(ALL); SpellDataMagicUser.fill(ALL); }
  public static String normalizeClass(String raw){String x=raw==null?"":raw.toLowerCase(Locale.US);if(x.contains("illusion"))return "Illusionist";if(x.contains("druid"))return "Druid";if(x.contains("cleric")||x.contains("priest"))return "Cleric";if(x.contains("magic")||x.contains("wizard")||x.contains("mage"))return "Magic-User";return raw==null?"":raw.trim();}
  public static ArrayList<Entry> forClass(String raw){String c=normalizeClass(raw);LinkedHashMap<String,Entry> m=new LinkedHashMap<>();for(Entry e:ALL)if(e.caster.equalsIgnoreCase(c)){String k=e.level+"|"+e.name.toLowerCase(Locale.US);Entry old=m.get(k);if(old==null)m.put(k,e);else if(!old.source.contains(e.source))m.put(k,new Entry(c,e.level,e.name,old.source+"/"+e.source));}ArrayList<Entry> out=new ArrayList<>(m.values());Collections.sort(out,(a,b)->a.level!=b.level?a.level-b.level:a.name.compareToIgnoreCase(b.name));return out;}
  static String nativeSummary(String name,String source){return NativeSpellDescriptions.summary(name,source);}
  private SpellCatalog(){}
}
