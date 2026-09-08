package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/**
 * Machine-readable rules used by the character-creation wizard.
 *
 * Core race/class access and minimum class requirements follow the native PHB
 * reference already encoded in RaceData/ClassData. Supplied-UA-only entries
 * remain visibly marked as variant material.
 */
public final class CharacterRules {
  public static final class Availability {
    public final boolean allowed;
    public final String reason;
    Availability(boolean allowed,String reason){this.allowed=allowed;this.reason=reason;}
  }

  private static final String[] AB={"STR","INT","WIS","DEX","CON","CHA"};

  public static Availability availability(RaceData.Race race,ClassData.Entry cls){
    if(race==null||cls==null)return new Availability(false,"Choose a race and class first.");
    String r=race.name,c=cls.name,f=race.family;

    if("Paladin".equals(c)&&!"Human".equals(f))
      return no(c+" is human-only under the PHB.");
    if("Monk".equals(c)&&!"Human".equals(f))
      return no(c+" is a human class under the PHB.");
    if("Bard".equals(c)&&!("Human".equals(f)||"Half-Elf".equals(f)))
      return no("The PHB Appendix II Bard is limited to humans and half-elves.");

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

    if("Dwarf".equals(f)){
      return in(c,"Fighter","Thief","Assassin")
        ?yes("Available under the current dwarven player-class framework.")
        :no("Not available to dwarven player characters under the current core class framework.");
    }
    if("Elf".equals(f)){
      return in(c,"Fighter","Magic-User","Thief","Assassin")
        ?yes("Available under the current elven player-class framework.")
        :no("Not available to elves under the current core class framework.");
    }
    if("Gnome".equals(f)){
      return in(c,"Fighter","Illusionist","Thief","Assassin")
        ?yes("Available under the current gnomish player-class framework.")
        :no("Not available to gnomes under the current core class framework.");
    }
    if("Half-Elf".equals(f)){
      return in(c,"Cleric","Druid","Fighter","Ranger","Magic-User","Thief","Assassin","Bard")
        ?yes("Available under the broad PHB half-elf class framework.")
        :no("Not available to half-elves under the current core class framework.");
    }
    if("Halfling".equals(f)){
      return in(c,"Fighter","Thief")
        ?yes("Available under the PHB halfling class framework.")
        :no("PHB halfling player characters are limited to fighter, thief, or their multiclass combination.");
    }
    if("Half-Orc".equals(f)){
      return in(c,"Cleric","Fighter","Thief","Assassin")
        ?yes("Available under the PHB half-orc class framework.")
        :no("Not available to half-orcs under the PHB player-class framework.");
    }

    return no("This race/class combination is not yet encoded as a supported starting path.");
  }

  static boolean in(String x,String... values){
    for(String v:values)if(v.equals(x))return true;
    return false;
  }
  static Availability yes(String s){return new Availability(true,s);}
  static Availability no(String s){return new Availability(false,s);}

  /** Ability modifier array ordered STR, INT, WIS, DEX, CON, CHA. */
  public static int[] racialModifiers(String raceName){
    int[] m={0,0,0,0,0,0};
    if(raceName==null)return m;
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

  public static int[] finalScores(CharacterDraft d){
    int[] out=new int[6],m=racialModifiers(d.raceName);
    for(int i=0;i<6;i++)out[i]=d.rawScores[i]<=0?0:d.rawScores[i]+m[i];
    return out;
  }

  public static ArrayList<String> validateScores(CharacterDraft d){
    ArrayList<String> problems=new ArrayList<>();
    ClassData.Entry cls=ClassData.find(d.className);
    RaceData.Race race=RaceData.find(d.raceName);
    if(cls==null||race==null){problems.add("Choose a valid race and class.");return problems;}
    Availability a=availability(race,cls);
    if(!a.allowed)problems.add(a.reason);

    int[] s=finalScores(d);
    boolean missing=false;
    for(int i=0;i<6;i++)if(s[i]<=0)missing=true;
    if(missing){problems.add("Enter or roll all six ability scores.");return problems;}

    req(problems,s,0,minimum(cls.name,"STR"),"STR",cls.name);
    req(problems,s,1,minimum(cls.name,"INT"),"INT",cls.name);
    req(problems,s,2,minimum(cls.name,"WIS"),"WIS",cls.name);
    req(problems,s,3,minimum(cls.name,"DEX"),"DEX",cls.name);
    req(problems,s,4,minimum(cls.name,"CON"),"CON",cls.name);
    req(problems,s,5,minimum(cls.name,"CHA"),"CHA",cls.name);

    if("Bard".equals(cls.name))
      problems.add("PHB Bard uses the special Fighter → Thief → Bard progression and is not an ordinary level-1 starting class.");
    return problems;
  }

  static void req(ArrayList<String> p,int[] scores,int index,int min,String ability,String cls){
    if(min>0&&scores[index]>0&&scores[index]<min)
      p.add(cls+" requires "+ability+" "+min+"; final "+ability+" is "+scores[index]+".");
  }

  static int minimum(String cls,String ability){
    if("Cleric".equals(cls))return "WIS".equals(ability)?9:0;
    if("Druid".equals(cls)){if("WIS".equals(ability))return 12;if("CHA".equals(ability))return 15;return 0;}
    if("Fighter".equals(cls)){if("STR".equals(ability))return 9;if("CON".equals(ability))return 7;return 0;}
    if("Paladin".equals(cls)){
      if("STR".equals(ability))return 12;if("INT".equals(ability))return 9;if("WIS".equals(ability))return 13;
      if("CON".equals(ability))return 9;if("CHA".equals(ability))return 17;return 0;
    }
    if("Ranger".equals(cls)){
      if("STR".equals(ability)||"INT".equals(ability))return 13;
      if("WIS".equals(ability)||"CON".equals(ability))return 14;return 0;
    }
    if("Magic-User".equals(cls)){if("INT".equals(ability))return 9;if("DEX".equals(ability))return 6;return 0;}
    if("Illusionist".equals(cls)){if("INT".equals(ability))return 15;if("DEX".equals(ability))return 16;return 0;}
    if("Thief".equals(cls))return "DEX".equals(ability)?9:0;
    if("Assassin".equals(cls)){
      if("STR".equals(ability)||"DEX".equals(ability))return 12;if("INT".equals(ability))return 11;return 0;
    }
    if("Monk".equals(cls)){
      if("STR".equals(ability)||"WIS".equals(ability)||"DEX".equals(ability))return 15;
      if("CON".equals(ability))return 11;return 0;
    }
    if("Bard".equals(cls)){
      if("STR".equals(ability)||"WIS".equals(ability)||"DEX".equals(ability)||"CHA".equals(ability))return 15;
      if("INT".equals(ability))return 12;if("CON".equals(ability))return 10;return 0;
    }
    if("Psionicist".equals(cls))return "CHA".equals(ability)?9:0;
    return 0;
  }

  public static String modifierSummary(String raceName){
    int[] m=racialModifiers(raceName);
    StringBuilder b=new StringBuilder();
    for(int i=0;i<6;i++)if(m[i]!=0){
      if(b.length()>0)b.append("  •  ");
      b.append(AB[i]).append(m[i]>0?"+":"").append(m[i]);
    }
    return b.length()==0?"No racial ability adjustment":b.toString();
  }

  private CharacterRules(){}
}
