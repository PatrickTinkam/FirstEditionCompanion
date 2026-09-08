package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/** Machine-readable rules used by the guided character-creation wizard. */
public final class CharacterRules {
  public static final String[] ALIGNMENTS={
    "Lawful Good","Neutral Good","Chaotic Good",
    "Lawful Neutral","True Neutral","Chaotic Neutral",
    "Lawful Evil","Neutral Evil","Chaotic Evil"
  };

  public static final class Availability {
    public final boolean allowed;
    public final String reason;
    Availability(boolean allowed,String reason){this.allowed=allowed;this.reason=reason;}
  }

  public static final class AgeFormula {
    public final boolean supported;
    public final int base,dice,sides;
    public final String note;
    AgeFormula(boolean supported,int base,int dice,int sides,String note){
      this.supported=supported;this.base=base;this.dice=dice;this.sides=sides;this.note=note;
    }
    public String expression(){
      if(!supported)return "No direct DMG formula";
      if(dice<=0||sides<=0)return String.valueOf(base);
      return base+" + "+dice+"d"+sides+" years";
    }
  }

  private static final String[] AB={"STR","INT","WIS","DEX","CON","CHA"};

  public static Availability availability(RaceData.Race race,ClassData.Entry cls){
    if(race==null||cls==null)return no("Choose a race and class first.");
    String r=race.name,c=cls.name,f=race.family;
    if("Paladin".equals(c)&&!"Human".equals(f))return no("Paladin is human-only under the PHB.");
    if("Monk".equals(c)&&!"Human".equals(f))return no("Monk is a human class under the PHB.");
    if("Bard".equals(c)&&!("Human".equals(f)||"Half-Elf".equals(f)))return no("The PHB Appendix II Bard is limited to humans and half-elves.");
    if("Human".equals(f))return yes("Humans may enter any player-character class permitted by the campaign.");
    if("Half-Ogre".equals(f)){
      return in(c,"Cleric","Druid","Fighter","Ranger","Thief","Assassin","Anti-Paladin")
        ?yes("Listed for half-ogres in the supplied UA variant.")
        :no("Not listed for half-ogres in the supplied UA variant.");
    }
    if(r.startsWith("Elf — Wild")){
      return in(c,"Druid","Fighter","Thief","Assassin","Bard","Psionicist")
        ?yes("Compatible with the narrower wild-elf class framework encoded from the supplied UA variant.")
        :no("The supplied UA variant gives wild elves a narrower class selection.");
    }
    if("Dwarf".equals(f))return in(c,"Fighter","Thief","Assassin")?yes("Available under the current dwarven player-class framework."):no("Not available to dwarven player characters under the current core class framework.");
    if("Elf".equals(f))return in(c,"Fighter","Magic-User","Thief","Assassin")?yes("Available under the current elven player-class framework."):no("Not available to elves under the current core class framework.");
    if("Gnome".equals(f))return in(c,"Fighter","Illusionist","Thief","Assassin")?yes("Available under the current gnomish player-class framework."):no("Not available to gnomes under the current core class framework.");
    if("Half-Elf".equals(f))return in(c,"Cleric","Druid","Fighter","Ranger","Magic-User","Thief","Assassin","Bard")?yes("Available under the broad PHB half-elf class framework."):no("Not available to half-elves under the current core class framework.");
    if("Halfling".equals(f))return in(c,"Fighter","Thief")?yes("Available under the PHB halfling class framework."):no("PHB halfling player characters are limited to fighter, thief, or their multiclass combination.");
    if("Half-Orc".equals(f))return in(c,"Cleric","Fighter","Thief","Assassin")?yes("Available under the PHB half-orc player-class framework."):no("Not available to half-orcs under the PHB player-class framework.");
    return no("This race/class combination is not yet encoded as a supported starting path.");
  }

  static boolean in(String x,String... values){for(String v:values)if(v.equals(x))return true;return false;}
  static Availability yes(String s){return new Availability(true,s);}
  static Availability no(String s){return new Availability(false,s);}

  /** Ability modifier array ordered STR, INT, WIS, DEX, CON, CHA. */
  public static int[] racialModifiers(String raceName){
    int[] m={0,0,0,0,0,0};if(raceName==null)return m;
    switch(raceName){
      case "Dwarf — Hill / Standard":
      case "Dwarf — Mountain":
      case "Dwarf — Gray / Duergar": m[4]=1;m[5]=-1;break;
      case "Elf — High": m[3]=1;m[4]=-1;break;
      case "Elf — Gray": m[0]=-1;m[1]=1;m[3]=1;m[4]=-1;break;
      case "Elf — Wood / Sylvan": m[0]=1;m[1]=-1;m[3]=1;m[4]=-1;break;
      case "Elf — Wild / Grugach": m[0]=1;m[3]=1;m[4]=-1;m[5]=-1;break;
      case "Elf — Dark / Drow (Male)": m[1]=1;m[2]=-1;m[3]=1;m[4]=-1;break;
      case "Elf — Dark / Drow (Female)": m[0]=-1;m[1]=1;m[3]=1;m[4]=-1;m[5]=1;break;
      case "Gnome — Deep / Svirfneblin": m[4]=1;m[5]=-1;break;
      case "Halfling — Unspecified / Mixed":
      case "Halfling — Hairfoot":
      case "Halfling — Stout":
      case "Halfling — Tallfellow": m[0]=-1;m[3]=1;break;
      case "Half-Orc": m[0]=1;m[4]=1;m[5]=-2;break;
      case "Half-Ogre": m[0]=2;m[1]=-1;m[3]=-1;m[4]=2;m[5]=-2;break;
      default: break;
    }
    return m;
  }

  public static int[] racialScores(CharacterDraft d){
    int[] out=new int[6],m=racialModifiers(d.raceName);
    for(int i=0;i<6;i++)out[i]=d.rawScores[i]<=0?0:d.rawScores[i]+m[i];
    return out;
  }

  public static int[] finalScores(CharacterDraft d){
    int[] out=racialScores(d),age=ageModifiers(ageCategory(d.raceName,d.age));
    for(int i=0;i<6;i++)if(out[i]>0)out[i]+=age[i];
    return out;
  }

  public static ArrayList<String> validateAbilityScores(CharacterDraft d){return validateScoreSet(d,racialScores(d));}
  public static ArrayList<String> validateFinalScores(CharacterDraft d){return validateScoreSet(d,finalScores(d));}

  static ArrayList<String> validateScoreSet(CharacterDraft d,int[] s){
    ArrayList<String> problems=new ArrayList<>();
    ClassData.Entry cls=ClassData.find(d.className);RaceData.Race race=RaceData.find(d.raceName);
    if(cls==null||race==null){problems.add("Choose a valid race and class.");return problems;}
    Availability a=availability(race,cls);if(!a.allowed)problems.add(a.reason);
    for(int v:s)if(v<=0){problems.add("Enter or roll all six ability scores.");return problems;}
    req(problems,s,0,minimum(cls.name,"STR"),"STR",cls.name);req(problems,s,1,minimum(cls.name,"INT"),"INT",cls.name);
    req(problems,s,2,minimum(cls.name,"WIS"),"WIS",cls.name);req(problems,s,3,minimum(cls.name,"DEX"),"DEX",cls.name);
    req(problems,s,4,minimum(cls.name,"CON"),"CON",cls.name);req(problems,s,5,minimum(cls.name,"CHA"),"CHA",cls.name);
    if("Bard".equals(cls.name))problems.add("PHB Bard uses the special Fighter → Thief → Bard progression and is not an ordinary level-1 starting class.");
    return problems;
  }

  static void req(ArrayList<String> p,int[] scores,int index,int min,String ability,String cls){if(min>0&&scores[index]>0&&scores[index]<min)p.add(cls+" requires "+ability+" "+min+"; final "+ability+" is "+scores[index]+".");}

  static int minimum(String cls,String ability){
    if("Cleric".equals(cls))return "WIS".equals(ability)?9:0;
    if("Druid".equals(cls)){if("WIS".equals(ability))return 12;if("CHA".equals(ability))return 15;return 0;}
    if("Fighter".equals(cls)){if("STR".equals(ability))return 9;if("CON".equals(ability))return 7;return 0;}
    if("Paladin".equals(cls)){if("STR".equals(ability))return 12;if("INT".equals(ability))return 9;if("WIS".equals(ability))return 13;if("CON".equals(ability))return 9;if("CHA".equals(ability))return 17;return 0;}
    if("Ranger".equals(cls)){if("STR".equals(ability)||"INT".equals(ability))return 13;if("WIS".equals(ability)||"CON".equals(ability))return 14;return 0;}
    if("Magic-User".equals(cls)){if("INT".equals(ability))return 9;if("DEX".equals(ability))return 6;return 0;}
    if("Illusionist".equals(cls)){if("INT".equals(ability))return 15;if("DEX".equals(ability))return 16;return 0;}
    if("Thief".equals(cls))return "DEX".equals(ability)?9:0;
    if("Assassin".equals(cls)){if("STR".equals(ability)||"DEX".equals(ability))return 12;if("INT".equals(ability))return 11;return 0;}
    if("Monk".equals(cls)){if("STR".equals(ability)||"WIS".equals(ability)||"DEX".equals(ability))return 15;if("CON".equals(ability))return 11;return 0;}
    if("Bard".equals(cls)){if("STR".equals(ability)||"WIS".equals(ability)||"DEX".equals(ability)||"CHA".equals(ability))return 15;if("INT".equals(ability))return 12;if("CON".equals(ability))return 10;return 0;}
    if("Psionicist".equals(cls))return "CHA".equals(ability)?9:0;
    return 0;
  }

  public static String modifierSummary(String raceName){
    int[] m=racialModifiers(raceName);StringBuilder b=new StringBuilder();
    for(int i=0;i<6;i++)if(m[i]!=0){if(b.length()>0)b.append("  •  ");b.append(AB[i]).append(m[i]>0?"+":"").append(m[i]);}
    return b.length()==0?"No racial ability adjustment":b.toString();
  }

  public static AgeFormula startingAgeFormula(CharacterDraft d){
    RaceData.Race race=RaceData.find(d.raceName);ClassData.Entry cls=ClassData.find(d.className);
    if(race==null||cls==null)return unsupportedAge("Choose race and class first.");
    if("Half-Ogre".equals(race.family))return unsupportedAge("The DMG predates this supplied-UA race and gives no half-ogre starting-age formula. Enter a campaign age manually.");
    if("Bard".equals(cls.name))return unsupportedAge("The DMG states that a bard begins at the age of the class in which the special Fighter → Thief → Bard progression began.");
    if("Psionicist".equals(cls.name))return unsupportedAge("The supplied-UA Psionicist has no matching starting-age formula in the original DMG. Enter a campaign age manually.");

    if("Human".equals(race.family)){
      if("Cleric".equals(cls.name))return age(18,1,4,"DMG human Cleric starting age.");
      if("Druid".equals(cls.name))return age(18,1,4,"DMG human Druid starting age.");
      if("Fighter".equals(cls.name))return age(15,1,4,"DMG human Fighter starting age.");
      if("Paladin".equals(cls.name))return age(17,1,4,"DMG human Paladin starting age.");
      if("Ranger".equals(cls.name))return age(20,1,4,"DMG human Ranger starting age.");
      if("Magic-User".equals(cls.name))return age(24,2,8,"DMG human Magic-User starting age.");
      if("Illusionist".equals(cls.name))return age(30,1,6,"DMG human Illusionist starting age.");
      if("Thief".equals(cls.name))return age(18,1,4,"DMG human Thief starting age.");
      if("Assassin".equals(cls.name))return age(20,1,4,"DMG human Assassin starting age.");
      if("Monk".equals(cls.name))return age(21,1,4,"DMG human Monk starting age.");
      if("Cloistered Cleric".equals(cls.name))return age(18,1,4,"Supplied-UA subclass; using the DMG Cleric age formula as a labeled parent-class baseline.");
      if("Anti-Paladin".equals(cls.name)||"Duelist".equals(cls.name))return age(15,1,4,"Supplied-UA fighter subclass; using the DMG Fighter age formula as a labeled parent-class baseline.");
      if("Necromancer".equals(cls.name))return age(24,2,8,"Supplied-UA magic-user subclass; using the DMG Magic-User age formula as a labeled parent-class baseline.");
      return unsupportedAge("No original-DMG starting-age formula is encoded for this class.");
    }

    String group=ageClassGroup(cls.name);if(group.isEmpty())return unsupportedAge("No original-DMG nonhuman starting-age column matches this class.");
    String rf=race.family;
    if("Dwarf".equals(rf)){
      if("Cleric".equals(group))return age(250,2,20,"DMG nonhuman Dwarf/Cleric column.");
      if("Fighter".equals(group))return age(40,5,4,"DMG nonhuman Dwarf/Fighter column.");
      if("Thief".equals(group))return age(75,3,6,"DMG nonhuman Dwarf/Thief column.");
    }else if("Elf".equals(rf)){
      if("Fighter".equals(group))return age(130,5,6,"DMG nonhuman Elf/Fighter column.");
      if("Magic-User".equals(group))return age(150,5,6,"DMG nonhuman Elf/Magic-User column.");
      if("Thief".equals(group))return age(100,5,6,"DMG nonhuman Elf/Thief column.");
    }else if("Gnome".equals(rf)){
      if("Cleric".equals(group))return age(300,3,12,"DMG nonhuman Gnome/Cleric column.");
      if("Fighter".equals(group))return age(60,5,4,"DMG nonhuman Gnome/Fighter column.");
      if("Magic-User".equals(group))return age(100,2,12,"DMG nonhuman Gnome/Magic-User column.");
      if("Thief".equals(group))return age(80,5,4,"DMG nonhuman Gnome/Thief column.");
    }else if("Half-Elf".equals(rf)){
      if("Cleric".equals(group))return age(40,2,4,"DMG nonhuman Half-Elf/Cleric column.");
      if("Fighter".equals(group))return age(22,3,4,"DMG nonhuman Half-Elf/Fighter column.");
      if("Magic-User".equals(group))return age(30,2,8,"DMG nonhuman Half-Elf/Magic-User column.");
      if("Thief".equals(group))return age(22,3,8,"DMG nonhuman Half-Elf/Thief column.");
    }else if("Halfling".equals(rf)){
      if("Fighter".equals(group))return age(20,3,4,"DMG nonhuman Halfling/Fighter column.");
      if("Thief".equals(group))return age(40,2,4,"DMG nonhuman Halfling/Thief column.");
    }else if("Half-Orc".equals(rf)){
      if("Cleric".equals(group))return age(20,1,4,"DMG nonhuman Half-Orc/Cleric column.");
      if("Fighter".equals(group))return age(13,1,4,"DMG nonhuman Half-Orc/Fighter column.");
      if("Thief".equals(group))return age(20,2,4,"DMG nonhuman Half-Orc/Thief column.");
    }
    return unsupportedAge("The DMG table has no starting-age entry for this race/class column. Enter a campaign age manually.");
  }

  static String ageClassGroup(String c){
    if(in(c,"Cleric","Druid","Cloistered Cleric"))return "Cleric";
    if(in(c,"Fighter","Paladin","Ranger","Anti-Paladin","Duelist"))return "Fighter";
    if(in(c,"Magic-User","Illusionist","Necromancer"))return "Magic-User";
    if(in(c,"Thief","Assassin"))return "Thief";
    return "";
  }
  static AgeFormula age(int base,int dice,int sides,String note){return new AgeFormula(true,base,dice,sides,note);}
  static AgeFormula unsupportedAge(String note){return new AgeFormula(false,0,0,0,note);}

  public static String ageCategory(String raceName,int age){
    if(age<=0)return "Not set";int[] r=ageRanges(raceName);if(r==null)return "Campaign-defined";
    if(age<r[0])return "Below Young Adult range";
    if(age<=r[1])return "Young Adult";if(age<=r[2])return "Mature";if(age<=r[3])return "Middle Aged";if(age<=r[4])return "Old";if(age<=r[5])return "Venerable";
    return "Beyond Venerable range";
  }

  /** [youngMin, youngMax, matureMax, middleMax, oldMax, venerableMax]. */
  static int[] ageRanges(String raceName){
    if(raceName==null)return null;
    switch(raceName){
      case "Human": return rr(14,20,40,60,90,120);
      case "Dwarf — Hill / Standard": return rr(35,50,150,250,350,450);
      case "Dwarf — Mountain": return rr(40,60,175,275,400,525);
      case "Dwarf — Gray / Duergar": return rr(35,50,150,250,350,450);
      case "Elf — Dark / Drow (Male)":
      case "Elf — Dark / Drow (Female)": return rr(50,100,400,600,800,1000);
      case "Elf — Gray": return rr(150,250,650,1000,1500,2000);
      case "Elf — High": return rr(100,175,550,875,1200,1600);
      case "Elf — Wood / Sylvan":
      case "Elf — Wild / Grugach": return rr(75,150,500,800,1100,1350);
      case "Gnome — Surface":
      case "Gnome — Deep / Svirfneblin": return rr(50,90,300,450,600,750);
      case "Half-Elf — High-Elf Ancestry":
      case "Half-Elf — Gray-Elf Ancestry":
      case "Half-Elf — Wood-Elf Ancestry":
      case "Half-Elf — Drow Ancestry": return rr(24,40,100,175,250,325);
      case "Halfling — Unspecified / Mixed":
      case "Halfling — Hairfoot":
      case "Halfling — Stout":
      case "Halfling — Tallfellow": return rr(22,33,68,101,144,199);
      case "Half-Orc": return rr(12,15,30,45,60,80);
      default:return null;
    }
  }
  static int[] rr(int ymin,int ymax,int mature,int middle,int old,int venerable){return new int[]{ymin,ymax,mature,middle,old,venerable};}

  public static String ageSourceNote(String raceName){
    if("Dwarf — Gray / Duergar".equals(raceName))return "The original DMG has no separate duergar age bracket; the creator uses its generic dwarf bracket as a visibly labeled baseline.";
    if("Elf — Wild / Grugach".equals(raceName))return "The original DMG has no separate grugach bracket; the creator uses the DMG wood-elf bracket as a visibly labeled baseline.";
    if("Gnome — Deep / Svirfneblin".equals(raceName))return "The original DMG has no separate svirfneblin bracket; the creator uses its generic gnome bracket as a visibly labeled baseline.";
    if("Half-Ogre".equals(raceName))return "The original DMG gives no half-ogre age bracket. Age is campaign-defined here and no DMG aging modifier is invented.";
    return "Age category and aging adjustments use the original DMG table for this racial stock.";
  }

  public static int[] ageModifiers(String category){
    int[] m={0,0,0,0,0,0};
    if(category==null)return m;
    int stage=0;
    if("Young Adult".equals(category))stage=1;else if("Mature".equals(category))stage=2;else if("Middle Aged".equals(category))stage=3;else if("Old".equals(category))stage=4;else if("Venerable".equals(category)||"Beyond Venerable range".equals(category))stage=5;
    if(stage>=1){m[2]-=1;m[4]+=1;}
    if(stage>=2){m[0]+=1;m[2]+=1;}
    if(stage>=3){m[0]-=1;m[4]-=1;m[1]+=1;m[2]+=1;}
    if(stage>=4){m[0]-=2;m[3]-=2;m[4]-=1;m[2]+=1;}
    if(stage>=5){m[0]-=1;m[3]-=1;m[4]-=1;m[1]+=1;m[2]+=1;}
    return m;
  }

  public static String ageModifierSummary(CharacterDraft d){
    String cat=ageCategory(d.raceName,d.age);int[] m=ageModifiers(cat);StringBuilder b=new StringBuilder();
    for(int i=0;i<6;i++)if(m[i]!=0){if(b.length()>0)b.append("  •  ");b.append(AB[i]).append(m[i]>0?"+":"").append(m[i]);}
    return b.length()==0?"No DMG aging ability adjustment":b.toString();
  }

  public static Availability alignmentAvailability(String className,String alignment){
    if(className==null||className.isEmpty()||alignment==null||alignment.isEmpty())return no("Choose a class and alignment first.");
    if(in(className,"Fighter","Magic-User","Illusionist","Cloistered Cleric","Duelist"))return yes("This class allows any alignment in its source.");
    if("Cleric".equals(className))return "True Neutral".equals(alignment)?no("A PHB cleric cannot be true neutral unless using the Druid subclass."):yes("Allowed for a PHB cleric, subject to deity/campaign requirements.");
    if("Druid".equals(className))return "True Neutral".equals(alignment)?yes("Druids are true neutral under the PHB."):no("Druids are true neutral under the PHB.");
    if("Paladin".equals(className))return "Lawful Good".equals(alignment)?yes("Paladins are lawful good under the PHB."):no("Paladins must be lawful good under the PHB.");
    if("Ranger".equals(className))return in(alignment,"Lawful Good","Neutral Good","Chaotic Good")?yes("Rangers may be any good alignment under the PHB."):no("Rangers must be good under the PHB.");
    if("Thief".equals(className))return in(alignment,"Lawful Neutral","True Neutral","Chaotic Neutral","Lawful Evil","Neutral Evil","Chaotic Evil")?yes("The PHB table allows thieves from any neutral to any evil alignment."):no("The PHB table restricts thieves to any neutral through any evil alignment.");
    if("Assassin".equals(className))return in(alignment,"Lawful Evil","Neutral Evil","Chaotic Evil")?yes("Assassins are evil under the PHB."):no("Assassins must be evil under the PHB.");
    if("Monk".equals(className))return in(alignment,"Lawful Good","Lawful Neutral","Lawful Evil")?yes("Monks must be lawful under the PHB."):no("Monks must be lawful under the PHB.");
    if("Bard".equals(className))return in(alignment,"Lawful Neutral","True Neutral","Chaotic Neutral","Neutral Good","Neutral Evil")?yes("PHB Bards must retain a neutral component in alignment."):no("PHB Bards must retain a neutral component in alignment.");
    if("Anti-Paladin".equals(className))return "Chaotic Evil".equals(alignment)?yes("The supplied UA variant lists Anti-Paladin as chaotic evil."):no("The supplied UA variant lists Anti-Paladin as chaotic evil.");
    if("Necromancer".equals(className))return in(alignment,"Lawful Neutral","True Neutral","Chaotic Neutral","Lawful Evil","Neutral Evil","Chaotic Evil")?yes("The supplied UA variant lists Necromancer as non-good."):no("The supplied UA variant lists Necromancer as non-good.");
    if("Psionicist".equals(className))return in(alignment,"Lawful Good","Neutral Good","Lawful Neutral","True Neutral","Lawful Evil","Neutral Evil")?yes("The supplied UA variant lists Psionicist as non-chaotic."):no("The supplied UA variant lists Psionicist as non-chaotic.");
    return yes("No additional alignment restriction is encoded for this class.");
  }

  public static boolean isDivineClass(String c){return in(c,"Cleric","Druid","Paladin","Anti-Paladin","Cloistered Cleric");}

  public static ArrayList<String> automaticLanguages(CharacterDraft d){
    ArrayList<String> out=new ArrayList<>();String r=d.raceName;
    if("Human".equals(r))addAll(out,"Common");
    else if(in(r,"Dwarf — Hill / Standard","Dwarf — Mountain"))addAll(out,"Dwarven","Gnomish","Goblin","Kobold","Orcish","Common");
    else if("Dwarf — Gray / Duergar".equals(r))addAll(out,"Undercommon","Dwarven");
    else if(in(r,"Elf — High","Elf — Gray","Elf — Wood / Sylvan"))addAll(out,"Elvish","Gnomish","Halfling","Goblin","Hobgoblin","Orcish","Gnoll","Common");
    else if("Elf — Wild / Grugach".equals(r))addAll(out,"Elvish");
    else if(r!=null&&r.startsWith("Elf — Dark / Drow"))addAll(out,"Undercommon","Elvish");
    else if("Gnome — Surface".equals(r))addAll(out,"Gnomish","Dwarven","Halfling","Goblin","Kobold","Common");
    else if("Gnome — Deep / Svirfneblin".equals(r))addAll(out,"Undercommon","Svirfneblin");
    else if(r!=null&&r.startsWith("Half-Elf"))addAll(out,"Common","Elvish","Gnomish","Halfling","Goblin","Hobgoblin","Orcish","Gnoll");
    else if(r!=null&&r.startsWith("Halfling"))addAll(out,"Common","Dwarven","Elvish","Gnomish","Goblin","Halfling","Orcish");
    else if("Half-Orc".equals(r))addAll(out,"Common","Orcish");
    else if("Half-Ogre".equals(r))addAll(out,"Common","Giant");

    if(d.useAlignmentLanguage&&!d.alignment.isEmpty())addUnique(out,d.alignment+" alignment language");
    if("Druid".equals(d.className))addUnique(out,"Druidic");
    if("Thief".equals(d.className))addUnique(out,"Thieves' Cant");
    return out;
  }

  static void addAll(ArrayList<String> a,String... values){for(String s:values)addUnique(a,s);}
  static void addUnique(ArrayList<String> a,String s){if(s!=null&&!s.isEmpty()&&!a.contains(s))a.add(s);}

  public static boolean usesVariantLanguageSkills(String raceName){
    return in(raceName,"Dwarf — Gray / Duergar","Elf — Wild / Grugach","Gnome — Deep / Svirfneblin","Half-Ogre")||(raceName!=null&&raceName.startsWith("Elf — Dark / Drow"));
  }

  public static int bonusLanguageSlots(CharacterDraft d){
    int intel=finalScores(d)[1];if(intel<=0)return 0;String r=d.raceName;
    if(usesVariantLanguageSkills(r))return -1;
    if(in(r,"Elf — High","Elf — Gray","Elf — Wood / Sylvan"))return Math.max(0,intel-15);
    if(r!=null&&r.startsWith("Half-Elf"))return Math.max(0,intel-16);
    if(r!=null&&r.startsWith("Halfling"))return Math.max(0,intel-16);
    int n=intelligenceAdditionalLanguages(intel);
    RaceData.Race race=RaceData.find(r);
    if(race!=null&&in(race.family,"Dwarf","Gnome","Half-Orc"))n=Math.min(2,n);
    return n;
  }

  static int intelligenceAdditionalLanguages(int score){
    if(score<=7)return 0;if(score<=9)return 1;if(score<=11)return 2;if(score<=13)return 3;if(score<=15)return 4;if(score==16)return 5;if(score==17)return 6;return 7;
  }

  public static ArrayList<String> languageCandidates(String raceName){
    ArrayList<String> a=new ArrayList<>();
    if("Dwarf — Gray / Duergar".equals(raceName))addAll(a,"Common","Elvish","Giant","Gnomish","Goblin","Kobold","Svirfneblin");
    else if(raceName!=null&&raceName.startsWith("Elf — Dark / Drow"))addAll(a,"Common","Dwarven","Gnomish","Goblin","Kobold","Kuo-Toa","Svirfneblin");
    else if("Elf — Wild / Grugach".equals(raceName))addAll(a,"Common","Gnomish","Goblin","Sylvan");
    else if("Gnome — Deep / Svirfneblin".equals(raceName))addAll(a,"Common","Elvish","Dwarven","Gnomish","Goblin","Kuo-Toa","Kobold","Terran");
    else if("Half-Ogre".equals(raceName))addAll(a,"Dwarven","Goblin","Kobold","Orcish");
    else addAll(a,"Dwarvish","Elvish","Goblin","Halfling","Hobgoblin","Kobold","Lizardman","Ogrish","Orcish");
    return a;
  }

  public static ArrayList<String> validateLanguages(CharacterDraft d){
    ArrayList<String> p=new ArrayList<>();int slots=bonusLanguageSlots(d);
    if(slots>=0&&d.chosenLanguages.size()>slots)p.add("You selected "+d.chosenLanguages.size()+" additional languages but currently have "+slots+" available slot"+(slots==1?"":"s")+".");
    return p;
  }

  public static String languageRuleNote(CharacterDraft d){
    if(usesVariantLanguageSkills(d.raceName))return "This supplied-UA race uses its own background-skill language choices. The source gives the available bonus-language list but not a PHB-style numeric language-slot count, so the creator does not invent one.";
    int slots=bonusLanguageSlots(d);
    return "Additional language capacity: "+slots+". PHB racial limits override the general Intelligence table where the race gives a tighter rule.";
  }

  private CharacterRules(){}
}
