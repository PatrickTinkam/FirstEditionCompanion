package com.patricktinkam.firsteditioncompanion;

import java.util.*;

public final class GearCatalog {
  public static final class Entry {
    public final String name,category,source,cost,weight,summary;
    public final boolean magic;
    Entry(String n,String c,String s,String cost,String weight,boolean magic){
      this.name=n;this.category=c;this.source=s;this.cost=cost;this.weight=weight;this.magic=magic;
      this.summary=magic?MagicItemRules.describe(this):EquipmentRules.describe(this);
    }
    public String label(){String x=name+"  •  "+category+"  ["+source+"]";if(!cost.isEmpty())x+="  •  "+cost;if(!weight.isEmpty())x+="  •  "+weight;return x;}
  }

  private static final ArrayList<Entry> ALL=new ArrayList<>();
  static { GearDataEquipment.fill(ALL); GearDataMagic.fill(ALL); }

  public static ArrayList<Entry> all(){return new ArrayList<>(ALL);}
  public static ArrayList<Entry> normal(){ArrayList<Entry> r=new ArrayList<>();for(Entry e:ALL)if(!e.magic)r.add(e);return r;}
  public static ArrayList<Entry> magic(){ArrayList<Entry> r=new ArrayList<>();for(Entry e:ALL)if(e.magic)r.add(e);return r;}
  public static ArrayList<Entry> category(String category){ArrayList<Entry> r=new ArrayList<>();for(Entry e:ALL)if(e.category.equalsIgnoreCase(category))r.add(e);return r;}
  public static ArrayList<Entry> categories(String... categories){HashSet<String> wanted=new HashSet<>();for(String c:categories)wanted.add(c.toLowerCase(Locale.US));ArrayList<Entry> r=new ArrayList<>();for(Entry e:ALL)if(wanted.contains(e.category.toLowerCase(Locale.US)))r.add(e);return r;}
  public static ArrayList<Entry> combine(List<Entry> a,List<Entry> b){LinkedHashMap<String,Entry> m=new LinkedHashMap<>();for(Entry e:a)m.put((e.magic?"m":"n")+"|"+e.category+"|"+e.name,e);for(Entry e:b)m.put((e.magic?"m":"n")+"|"+e.category+"|"+e.name,e);return new ArrayList<>(m.values());}
  public static void sort(List<Entry> list){Collections.sort(list,(a,b)->{int c=a.category.compareToIgnoreCase(b.category);return c!=0?c:a.name.compareToIgnoreCase(b.name);});}
  private GearCatalog(){}
}
