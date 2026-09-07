package com.patricktinkam.firsteditioncompanion;
import java.util.*;
final class SpellDataIllusionist {
  static void fill(ArrayList<SpellCatalog.Entry> out){
    add(out,"Illusionist",1,"PHB","Audible Glamour|Change Self|Colour Spray|Dancing Lights|Darkness|Detect Illusion|Detect Invisibility|Gaze Reflection|Hypnotism|Light|Phantasmal Force|Wall of Fog");
    add(out,"Illusionist",2,"PHB","Blindness|Blur|Deafness|Detect Magic|Fog Cloud|Hypnotic Pattern|Improved Phantasmal Force|Invisibility|Magic Mouth|Mirror Image|Misdirection|Ventriloquism");
    add(out,"Illusionist",3,"PHB","Continual Darkness|Continual Light|Dispel Illusion|Fear|Hallucinatory Terrain|Illusory Script|Invisibility 10 ft Radius|Non-Detection|Paralysation|Rope Trick|Spectral Force|Suggestion");
    add(out,"Illusionist",4,"PHB","Confusion|Dispel Exhaustion|Emotion|Improved Invisibility|Massmorph|Minor Creation|Phantasmal Killer|Shadow Monsters");
    add(out,"Illusionist",5,"PHB","Chaos|Demi-Shadow Monsters|Major Creation|Maze|Project Image|Shadow Door|Shadow Magic|Summon Shadow");
    add(out,"Illusionist",6,"PHB","Conjure Animals|Demi-Shadow Magic|Mass Suggestion|Permanent Illusion|Programmed Illusion|Shades|True Sight|Veil");
    add(out,"Illusionist",7,"PHB","Alter Reality|Astral Spell|Prismatic Spray|Prismatic Wall|Vision|First Level Magic-User Spells");
    add(out,"Illusionist",0,"UA","Colored Lights|Dim|Haze|Mask|Mirage|Noise|Rainbow|Two-D'lusion");
    add(out,"Illusionist",1,"UA","Chromatic Orb|Phantom Armor|Read Illusionist Magic|Spook");
    add(out,"Illusionist",2,"UA","Alter Self|Fascinate|Ultravision|Whispering Wind");
    add(out,"Illusionist",3,"UA","Delude|Phantom Steed|Phantom Wind|Wraithform");
    add(out,"Illusionist",4,"UA","Dispel Magic|Rainbow Pattern|Solid Fog|Vacancy");
    add(out,"Illusionist",5,"UA","Advanced Illusion|Dream|Magic Mirror|Tempus Fugit");
    add(out,"Illusionist",6,"UA","Death Fog|Mirage Arcane|Mislead|Permanent Illusion|Phantasmagoria");
    add(out,"Illusionist",7,"UA","Shadow Walk|Weird");
  }
  private static void add(ArrayList<SpellCatalog.Entry> out,String c,int l,String s,String names){for(String n:names.split("\\|"))out.add(new SpellCatalog.Entry(c,l,n,s));}
  private SpellDataIllusionist(){}
}
