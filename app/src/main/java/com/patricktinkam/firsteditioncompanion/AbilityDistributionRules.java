package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/**
 * Assigns a six-score pool to abilities using the selected class or class combination.
 * The optimizer first tries to satisfy every encoded class minimum after racial
 * adjustments, then favors prime/principal abilities and stronger class requirements.
 */
public final class AbilityDistributionRules {
  private static final String[] AB=CharacterDraft.ABILITIES;
  private static final String[] FULL={"STRENGTH","INTELLIGENCE","WISDOM","DEXTERITY","CONSTITUTION","CHARISMA"};

  public static final class Result {
    public final boolean complete;
    public final boolean qualifies;
    public final String note;
    Result(boolean complete,boolean qualifies,String note){this.complete=complete;this.qualifies=qualifies;this.note=note;}
  }

  public static Result autoDistribute(CharacterDraft d){
    if(d==null||d.rolledPool.size()!=6)return new Result(false,false,"Roll a six-score pool first.");
    ArrayList<ClassData.Entry> classes=selectedClasses(d.className);
    if(classes.isEmpty())return new Result(false,false,"Choose a built-in class or class combination first.");

    int[] mins=new int[6],weights=new int[6];
    for(ClassData.Entry cls:classes){
      for(int i=0;i<6;i++){
        int m=CharacterRules.minimum(cls.name,AB[i]);
        mins[i]=Math.max(mins[i],m);
        if(m>0)weights[i]+=100+(m*12);
        if(isPrime(cls,i))weights[i]+=900;
      }
    }

    int[] values=new int[6];for(int i=0;i<6;i++)values[i]=d.rolledPool.get(i);
    int[] race=CharacterRules.racialModifiers(d.raceName);
    Search best=new Search();permute(values,0,mins,weights,race,best);
    if(best.assignment==null)return new Result(false,false,"No distribution could be generated.");
    System.arraycopy(best.assignment,0,d.rawScores,0,6);

    boolean qualifies=best.shortfall==0;
    String label=classLabel(classes);
    String note=qualifies
      ?"Auto-distributed for "+label+". All currently encoded class minimums are met after racial adjustments. You can still change any assignment manually."
      :"Auto-distributed for "+label+", but the rolled pool cannot satisfy every currently encoded class minimum. The closest class-focused arrangement was used; you can still rearrange it manually or roll again.";
    return new Result(true,qualifies,note);
  }

  static final class Search {
    int[] assignment;
    int shortfall=Integer.MAX_VALUE;
    long score=Long.MIN_VALUE;
  }

  static void permute(int[] a,int at,int[] mins,int[] weights,int[] race,Search best){
    if(at==a.length){evaluate(a,mins,weights,race,best);return;}
    HashSet<Integer> used=new HashSet<>();
    for(int i=at;i<a.length;i++){
      if(!used.add(a[i]))continue;
      int t=a[at];a[at]=a[i];a[i]=t;
      permute(a,at+1,mins,weights,race,best);
      t=a[at];a[at]=a[i];a[i]=t;
    }
  }

  static void evaluate(int[] raw,int[] mins,int[] weights,int[] race,Search best){
    int shortfall=0;long score=0;
    for(int i=0;i<6;i++){
      int adjusted=raw[i]+race[i];
      if(mins[i]>0&&adjusted<mins[i])shortfall+=mins[i]-adjusted;
      score+=(long)adjusted*weights[i];
      // Stable, mildly useful tie-breakers after class needs are already satisfied.
      if(i==4)score+=adjusted*4L; // CON
      else if(i==3)score+=adjusted*3L; // DEX
      else score+=adjusted;
    }
    if(shortfall<best.shortfall||(shortfall==best.shortfall&&score>best.score)){
      best.shortfall=shortfall;best.score=score;best.assignment=raw.clone();
    }
  }

  static boolean isPrime(ClassData.Entry cls,int ability){
    if(cls==null)return false;
    String req=cls.requirements==null?"":cls.requirements.toUpperCase(Locale.US);
    String full=FULL[ability],abbr=AB[ability];
    if(req.contains(full+" IS THE PRINCIPAL ATTRIBUTE")||req.contains(full+" IS THE MAJOR ABILITY")||req.contains(full+" IS THE PRIME REQUISITE"))return true;
    // PHB classes whose requirements list several important attributes rather than one explicit label.
    if("Paladin".equals(cls.name))return ability==0||ability==2||ability==5;
    if("Ranger".equals(cls.name))return ability==0||ability==1||ability==2;
    if("Assassin".equals(cls.name))return ability==0||ability==1||ability==3;
    if("Monk".equals(cls.name))return ability==0||ability==2||ability==3;
    if("Bard".equals(cls.name))return ability==0||ability==1||ability==2||ability==3||ability==5;
    return req.contains(abbr+" 16+")||req.contains(abbr+" 15+");
  }

  public static ArrayList<ClassData.Entry> selectedClasses(String stored){
    ArrayList<ClassData.Entry> out=new ArrayList<>();
    if(stored==null||stored.trim().isEmpty())return out;
    ClassData.Entry exact=ClassData.findLoose(stored);if(exact!=null){out.add(exact);return out;}
    String normalized=stored.replace("→","/");
    String[] parts=normalized.split("\\s*(?:/|\\+|&)\\s*");
    for(String part:parts){
      ClassData.Entry e=ClassData.findLoose(part.trim());
      if(e!=null&&!contains(out,e.name))out.add(e);
    }
    return out;
  }

  static boolean contains(ArrayList<ClassData.Entry> list,String name){for(ClassData.Entry e:list)if(e.name.equalsIgnoreCase(name))return true;return false;}

  public static String selectedClassLabel(String stored){
    ArrayList<ClassData.Entry> classes=selectedClasses(stored);
    return classes.isEmpty()?(stored==null?"selected class":stored):classLabel(classes);
  }

  static String classLabel(ArrayList<ClassData.Entry> classes){
    StringBuilder s=new StringBuilder();for(ClassData.Entry e:classes){if(s.length()>0)s.append(" / ");s.append(e.name);}return s.toString();
  }

  private AbilityDistributionRules(){}
}
