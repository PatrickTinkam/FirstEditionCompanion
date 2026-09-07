package com.patricktinkam.firsteditioncompanion;
import java.util.*;
final class SpellDataDruid {
  static void fill(ArrayList<SpellCatalog.Entry> out){
    add(out,"Druid",1,"PHB","Animal Friendship|Detect Magic|Detect Pits and Snares|Entangle|Faerie Fire|Invisibility to Animals|Locate Animals|Pass Without Trace|Predict Weather|Purify Water|Shillelagh|Speak with Animals");
    add(out,"Druid",2,"PHB","Barkskin|Charm Person or Mammal|Create Water|Cure Light Wounds|Feign Death|Fire Trap|Heat Metal|Locate Plants|Obscurement|Produce Flame|Trip|Warp Wood");
    add(out,"Druid",3,"PHB","Call Lightning|Cure Disease|Hold Animal|Neutralise Poison|Plant Growth|Protection From Fire|Pyrotechnics|Snare|Stone Shape|Summon Insects|Tree|Water Breathing");
    add(out,"Druid",4,"PHB","Animal Summoning I|Call Woodland Beings|Control Temperature 10 ft radius|Cure Serious Wounds|Dispel Magic|Hallucinatory Forest|Hold Plant|Plant Door|Produce Fire|Protection From Lightning|Repel Insects|Speak with Plants");
    add(out,"Druid",5,"PHB","Animal Growth|Animal Summoning II|Anti-Plant Shell|Commune With Nature|Control Winds|Insect Plague|Pass Plant|Sticks to Snakes|Transmute Rock to Mud|Wall of Fire");
    add(out,"Druid",6,"PHB","Animal Summoning III|Anti-Animal Shell|Conjure Fire Elemental|Cure Critical Wounds|Feeblemind|Fire Seeds|Transport via Plants|Turn Wood|Wall of Thorns|Weather Summoning");
    add(out,"Druid",7,"PHB","Animate Rock|Chariot of Fire|Confusion|Conjure Earth Elemental|Control Weather|Creeping Doom|Finger of Death|Fire Storm|Reincarnate|Transmute Metal to Wood");
    add(out,"Druid",1,"UA","Ceremony|Detect Balance|Detect Poison|Precipitation");
    add(out,"Druid",2,"UA","Flame Blade|Goodberry|Reflecting Pool|Slow Poison");
    add(out,"Druid",3,"UA","Cloudburst|Know Alignment|Spike Growth|Starshine");
    add(out,"Druid",5,"UA","Moonbeam|Spike Stones");
    add(out,"Druid",6,"UA","Liveoak|Transmute Water to Dust");
    add(out,"Druid",7,"UA","Changestaff|Sunray");
  }
  private static void add(ArrayList<SpellCatalog.Entry> out,String c,int l,String s,String names){for(String n:names.split("\\|"))out.add(new SpellCatalog.Entry(c,l,n,s));}
  private SpellDataDruid(){}
}
