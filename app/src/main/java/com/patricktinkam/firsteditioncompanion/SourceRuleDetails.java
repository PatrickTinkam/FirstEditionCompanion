package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/**
 * Source-grounded explanations for rules warnings in the guided creator.
 * Mechanical facts are preserved from the supplied PHB/DMG/UA sources while
 * prose is kept concise and mostly paraphrased for native offline use.
 */
public final class SourceRuleDetails {
  public static final class Detail {
    public final String title,why,source,location,excerpt;
    Detail(String title,String why,String source,String location,String excerpt){
      this.title=title;this.why=why;this.source=source;this.location=location;this.excerpt=excerpt==null?"":excerpt;
    }
    public String body(){
      StringBuilder b=new StringBuilder();
      b.append("WHY THIS RULE APPEARS\n\n").append(why);
      b.append("\n\nSOURCE\n").append(source);
      if(location!=null&&!location.trim().isEmpty())b.append("\n").append(location);
      if(!excerpt.isEmpty())b.append("\n\nBRIEF SOURCE EXCERPT\n“").append(excerpt).append("”");
      b.append("\n\nThe Companion keeps the mechanical rule and source location native/offline. Longer source wording is paraphrased rather than reproduced verbatim.");
      return b.toString();
    }
  }

  public static Detail raceClass(CharacterDraft d){
    RaceData.Race race=RaceData.find(d.raceName);ClassData.Entry cls=ClassData.find(d.className);
    CharacterRules.Availability a=CharacterRules.availability(race,cls);
    String r=race==null?d.raceName:race.name,c=cls==null?d.className:cls.name;
    String family=race==null?"":race.family;
    boolean variant=race!=null&&race.source.toLowerCase(Locale.US).contains("supplied ua")&&
      (r.startsWith("Elf — Wild")||"Half-Ogre".equals(family));
    String source=variant?"Supplied Unearthed Arcana variant":"AD&D 1e Players Handbook";
    String location=variant?"Character Class Limitations By Race / permitted-class entry for "+r:
      "CHARACTER RACES — "+(family.isEmpty()?r:family)+pageSuffix(racePage(family));
    String why="Current selection: "+r+" → "+c+".\n\n"+a.reason+" The source's race entry determines which classes are available before ability scores are considered. Ability rolls can satisfy class minimums, but they do not override a race/class prohibition under the active core rules.";
    String excerpt="";
    if(!variant&&"Elf".equals(family))excerpt="A character of elven stock can opt to be a fighter, a magic-user, a thief, or an assassin.";
    else if(!variant&&"Half-Elf".equals(family))excerpt="A character of half-elven race can play as a cleric, druid, fighter, ranger, magic-user, thief, or assassin.";
    return new Detail("Race / Class Rule Source",why,source,location,excerpt);
  }

  public static Detail abilityQualification(CharacterDraft d){
    ArrayList<ClassData.Entry> classes=AbilityDistributionRules.selectedClasses(d.className);
    int[] adjusted=CharacterRules.racialScores(d);
    ArrayList<String> problems=CharacterRules.validateAbilityScores(d);
    StringBuilder why=new StringBuilder();
    why.append("The class qualification check happens after the six ability scores are rolled/distributed and after racial adjustments are applied.\n\n");
    if(classes.isEmpty())why.append("No recognized built-in class requirements are available for this selection.");
    else{
      for(ClassData.Entry cls:classes){
        why.append(cls.name).append(": ").append(cls.requirements).append("\n");
      }
      why.append("\nCurrent adjusted scores: ");
      for(int i=0;i<6;i++){if(i>0)why.append("  •  ");why.append(CharacterDraft.ABILITIES[i]).append(" ").append(adjusted[i]>0?adjusted[i]:0);}
      if(!problems.isEmpty())why.append("\n\nCurrent rule check:\n").append(joinProblems(problems));
      else why.append("\n\nThe current distribution satisfies the encoded minimums.");
    }
    String source=classes.size()==1?sourceName(classes.get(0)):"AD&D 1e Players Handbook / selected class sources";
    String location=classes.size()==1?classLocation(classes.get(0)):"CHARACTER CLASSES — requirements for each selected class";
    return new Detail("Class Ability Requirement Source",why.toString().trim(),source,location,"");
  }

  public static Detail age(CharacterDraft d){
    CharacterRules.AgeFormula f=CharacterRules.startingAgeFormula(d);
    ArrayList<String> problems=CharacterRules.validateFinalScores(d);
    StringBuilder why=new StringBuilder();
    why.append("The DMG establishes character age and then applies cumulative age-category ability adjustments. The creator therefore rechecks class minimums after those adjustments instead of assuming a previously valid score stays valid.\n\n");
    why.append("Current age: ").append(d.age>0?d.age:"not set").append("  •  Category: ").append(CharacterRules.ageCategory(d.raceName,d.age));
    why.append("\nStarting-age rule: ").append(f.supported?f.expression():f.note);
    if(!problems.isEmpty())why.append("\n\nCurrent rule check:\n").append(joinProblems(problems));
    String excerpt="Likewise, any adjustments cannot lower any ability below racial or class minimums.";
    return new Detail("Age / Ability Rule Source",why.toString(),"AD&D 1e Dungeon Masters Guide","CHARACTER AGE, AGING, DISEASE & DEATH — pp. 12–13",excerpt);
  }

  public static Detail alignment(CharacterDraft d){
    ClassData.Entry cls=ClassData.find(d.className);CharacterRules.Availability a=CharacterRules.alignmentAvailability(d.className,d.alignment);
    String classRule=cls==null?"No built-in class alignment rule found.":cls.alignment;
    String why="Current selection: "+d.alignment+" for "+d.className+".\n\nClass alignment rule: "+classRule+".\n"+a.reason+" The PHB notes that class selection can predetermine or restrict alignment, so the creator checks the chosen alignment against the selected class.";
    String source=cls==null?"AD&D 1e Players Handbook":sourceName(cls);
    String location=cls==null?"ALIGNMENT — p. 33":"ALIGNMENT — p. 33; "+classLocation(cls);
    return new Detail("Alignment Rule Source",why,source,location,"");
  }

  public static Detail languages(CharacterDraft d){
    int[] finalScores=CharacterRules.finalScores(d);int intel=finalScores[1];int slots=CharacterRules.bonusLanguageSlots(d);
    ArrayList<String> problems=CharacterRules.validateLanguages(d);RaceData.Race race=RaceData.find(d.raceName);
    StringBuilder why=new StringBuilder();
    why.append("Language capacity is based on the character's final Intelligence plus the selected race's own language rules. Automatic racial languages are separate from additional languages chosen by the player.");
    why.append("\n\nFinal INT: ").append(intel>0?intel:"not established");
    why.append("\nAdditional-language capacity currently encoded: ").append(slots<0?"source-specific / not numerically capped":slots);
    why.append("\nAdditional languages chosen: ").append(d.chosenLanguages.size());
    if(!problems.isEmpty())why.append("\n\nCurrent rule check:\n").append(joinProblems(problems));
    String family=race==null?"":race.family;
    String location="CHARACTER LANGUAGES — p. 34"+(family.isEmpty()?"":"; CHARACTER RACES — "+family+pageSuffix(racePage(family)));
    String source=race!=null&&race.source.toLowerCase(Locale.US).contains("supplied ua")?"AD&D 1e Players Handbook + supplied UA variant racial entry":"AD&D 1e Players Handbook";
    return new Detail("Language Rule Source",why.toString(),source,location,"");
  }

  static String sourceName(ClassData.Entry cls){
    return cls!=null&&cls.source.toLowerCase(Locale.US).contains("supplied ua")?"Supplied Unearthed Arcana variant":"AD&D 1e Players Handbook";
  }

  static String classLocation(ClassData.Entry cls){
    if(cls==null)return "CHARACTER CLASSES";
    if(cls.source.toLowerCase(Locale.US).contains("supplied ua"))return cls.name+" class entry in the supplied UA variant";
    int p=classPage(cls.name);return "CHARACTER CLASSES — "+cls.name+(p>0?" — p. "+p:"");
  }

  static int classPage(String name){
    if("Cleric".equals(name)||"Druid".equals(name))return 20;
    if("Fighter".equals(name)||"Paladin".equals(name))return 22;
    if("Ranger".equals(name))return 24;if("Magic-User".equals(name))return 25;
    if("Illusionist".equals(name)||"Thief".equals(name))return 26;
    if("Assassin".equals(name))return 28;if("Monk".equals(name))return 30;
    return 0;
  }

  static int racePage(String family){
    if("Dwarf".equals(family))return 15;if("Elf".equals(family)||"Gnome".equals(family)||"Half-Elf".equals(family))return 16;
    if("Halfling".equals(family)||"Half-Orc".equals(family)||"Human".equals(family))return 17;return 0;
  }
  static String pageSuffix(int p){return p>0?" — p. "+p:"";}
  static String joinProblems(ArrayList<String> p){StringBuilder s=new StringBuilder();for(String q:p){if(s.length()>0)s.append("\n");s.append("• ").append(q);}return s.toString();}
  private SourceRuleDetails(){}
}
