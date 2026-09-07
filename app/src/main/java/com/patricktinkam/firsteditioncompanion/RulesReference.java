package com.patricktinkam.firsteditioncompanion;

import java.util.*;

public final class RulesReference {
  public static final class Topic {
    public final String title, section, text;
    public final int page;
    Topic(String title,String section,int page,String text){this.title=title;this.section=section;this.page=page;this.text=text;}
  }

  private static final ArrayList<Topic> ALL=new ArrayList<>();
  static {
    add("Spell Preparation & Memorization","Spells",40,
      "Magic-users consult their spell books to memorize selected spells before adventuring. As a rule of thumb, memorizing one spell level takes about 15 minutes and requires a rested, nourished mind. Casting expends that memorized copy. The same spell may be memorized more than once, but each prepared copy is a separate use.");
    add("Spell Reference Fields","Spells",43,
      "Every spell is described by its magic type, level, range, duration, area of effect, components, casting time, saving throw, and explanation/effect. V means verbal, S somatic, and M material. A melee round has 10 segments; 10 melee rounds make one turn.");
    add("Spellcasting in Combat","Spells",100,
      "A caster chooses the spell being cast when the melee round begins. Verbal spells require speech; somatic spells require free, exact movement; material components must be ready. If required casting actions are interrupted, the spell fails and is lost from memory. Each cast spell and normally expended material component is crossed off.");
    add("Currency & Coinage","Gear",35,
      "The gold piece is the base unit. 10 cp = 1 sp; 20 sp = 1 gp; 2 ep = 1 gp; 1 pp = 5 gp. Therefore 200 cp = 20 sp = 2 ep = 1 gp = 1/5 pp. Coins are treated as approximately equal in size and weight.");
    add("Money Changers, Banks & Gems","Gear",35,
      "Changing large quantities of coins typically costs about 3% of the transaction. Bankers generally do not pay interest. Loan rates vary sharply with status and security. Jewelers and gem merchants commonly buy at about 20% below a piece's actual value, so shopping around can matter.");
    add("Armor & Shields","Combat",36,
      "Armor, shields, Dexterity, and magic contribute to Armor Class. A shield improves AC by 1 when it can be interposed against the attack. A small shield applies against one attack per round, a normal shield against two, and a large shield against three; attacks from the right flank or rear negate the shield. Each +1 of magical armor or shield improves AC by another 1.");
    add("Weapon Proficiency","Combat",37,
      "Characters begin proficient with a limited number of weapons based on class. Using a weapon without proficiency imposes a class-based penalty to the attack roll. Additional proficiencies are gained with level according to class. Proficiency with a normal weapon also covers a magical weapon of the same type.");
    add("Weapon Factors","Combat",37,
      "Weapon choice can matter beyond damage: the PHB tables give weight, damage versus small/medium and large opponents, weapon length, space required, speed factor, and adjustments against Armor Class. Missile tables also specify rate of fire and short/medium/long range adjustments.");
    add("Encumbrance","Gear",101,
      "Weight and bulk both matter. As a base guideline before Strength adjustments: about 35 lb and little bulk allows 12-inch movement; about 70 lb or fairly bulky gear gives 9-inch movement; about 105 lb and bulky gear gives 6-inch movement; more than about 105 lb and/or very bulky gear reduces movement to roughly 3-4 inches. Ten gold pieces of weight equal one pound.");
    add("Movement Scale","Adventure",102,
      "In dungeon exploration, each 1 inch of movement rate represents 10 feet over a 10-minute turn. Following a known route is five times faster. Fleeing and combat movement are ten times faster than exploration: a 12-inch movement rate becomes 120 feet per melee round, or 12 feet per segment.");
    add("Light Sources","Adventure",102,
      "A torch illuminates about a 40-foot radius for 6 turns (1 hour). A hooded lantern illuminates about 30 feet for 24 turns per pint of oil. A bullseye lantern reaches about 80 feet in a narrow beam and also burns about 24 turns per pint.");
    add("Surprise","Combat",102,
      "The normal surprise chance is commonly 2 in 6: a roll of 1 or 2 indicates surprise unless a creature or circumstance changes the probability. Each point of surprise represents one 6-second segment.");
    add("Turning Undead","Class Ability",104,
      "Clerics can turn undead through their profession and holy or unholy symbol. Success depends on cleric level and the undead faced. At higher levels, turning can become destruction; evil clerics may instead command suitable undead. The cleric must be able to confront the undead, speak, and present the symbol, which precludes simultaneous spell activity.");
    add("Spell Combat & Interruption","Combat",104,
      "Spells often resolve late in the round because they require casting time. A caster who is struck, grabbed, or magically attacked during casting can have the spell spoiled; the PHB notes that a required saving throw may apply depending on the attack. Curative spells follow the same casting considerations.");
    add("Saving Throws & Armor Class","Combat",105,
      "Many magical and breath attacks use a saving throw to avoid or reduce the effect rather than a normal attack roll. Weapon attacks normally require a to-hit roll, and a successful hit deals damage. Armor Class combines armor, shield, magic, Dexterity, and situational modifiers; some bonuses can be lost against attacks from unsuitable directions or circumstances.");
    add("Damage, Falling & Healing","Combat",105,
      "Damage is recorded in hit points. The PHB's base falling guideline is 1d6 damage per 10 feet fallen, up to 20d6, with the DM adjusting for the landing surface. Natural rest restores 1 hit point per day; after 30 game days, the stated recovery rate becomes 5 hit points per day. Healing cannot raise hit points above the character's normal maximum.");
    add("Experience","Character",106,
      "Experience measures advancement in a character's class. The PHB awards experience after adventures for treasure gained, opponents overcome, and professional accomplishments, with the DM applying circumstances and class-related considerations. High prime-requisite scores can grant an experience bonus where the class rules specify one.");
  }

  private static void add(String t,String s,int p,String x){ALL.add(new Topic(t,s,p,x));}
  public static ArrayList<Topic> all(){return new ArrayList<>(ALL);}
  private RulesReference(){}
}
