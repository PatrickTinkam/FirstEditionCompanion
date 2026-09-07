package com.patricktinkam.firsteditioncompanion;
import java.util.*;
final class SpellDataCleric {
  static void fill(ArrayList<SpellCatalog.Entry> out){
    add(out,"Cleric",1,"PHB","Bless|Command|Create Water|Cure Light Wounds|Detect Evil|Detect Magic|Light|Protection From Evil|Purify Food and Drink|Remove Fear|Resist Cold|Sanctuary");
    add(out,"Cleric",2,"PHB","Augury|Chant|Detect Charm|Find Traps|Hold Person|Know Alignment|Resist Fire|Silence 15 ft Radius|Slow Poison|Snake Charm|Speak With Animals|Spiritual Weapon");
    add(out,"Cleric",3,"PHB","Animate Dead|Continual Light|Create Food and Water|Cure Blindness|Cure Disease|Dispel Magic|Feign Death|Glyph of Warding|Locate Object|Prayer|Remove Curse|Speak with Dead");
    add(out,"Cleric",4,"PHB","Cure Serious Wounds|Detect Lie|Divination|Exorcise|Lower Water|Neutralise Poison|Protection from Evil 10 ft radius|Speak With Plants|Sticks to Snakes|Tongues");
    add(out,"Cleric",5,"PHB","Atonement|Commune|Cure Critical Wounds|Dispel Evil|Flame Strike|Insect Plague|Plane Shift|Quest|Raise Dead|True Seeing");
    add(out,"Cleric",6,"PHB","Aerial Servant|Animate Object|Blade Barrier|Conjure Animals|Find the Path|Heal|Part Water|Speak With Monsters|Stone Tell|Word of Recall");
    add(out,"Cleric",7,"PHB","Astral Spell|Control Weather|Earthquake|Gate|Holy Word|Regenerate|Restoration|Resurrection|Symbol|Wind Walk");
    add(out,"Cleric",1,"UA","Ceremony|Combine|Endure Cold/Heat|Invisibility to Undead|Magic Stone|Penetrate Disguise|Portent|Precipitation");
    add(out,"Cleric",2,"UA","Aid|Detect Life|Dust Devil|Enthrall|Holy Symbol|Messenger|Withdraw|Wyvern Watch");
    add(out,"Cleric",3,"UA","Cloudburst|Death's Door|Flame Walk|Magical Vestment|Meld Into Stone|Negative Plane Protection|Remove Paralysis|Water Walk");
    add(out,"Cleric",4,"UA","Abjure|Cloak of Fear|Giant Insect|Imbue With Spell Ability|Spell Immunity|Spike Growth");
    add(out,"Cleric",5,"UA","Air Walk|Animate Dead Monster|Golem|Magic Font|Rainbow|Spike Stones");
    add(out,"Cleric",6,"UA","Forbiddance|Heroes' Feast");
    add(out,"Cleric",7,"UA","Exaction|Succor");
  }
  private static void add(ArrayList<SpellCatalog.Entry> out,String c,int l,String s,String names){for(String n:names.split("\\|"))out.add(new SpellCatalog.Entry(c,l,n,s));}
  private SpellDataCleric(){}
}
