package com.patricktinkam.firsteditioncompanion;

import java.util.*;

public final class RulesReference {
  public static final class Topic {
    public final String title, section, text, source;
    public final int page;
    Topic(String title,String section,String source,int page,String text){this.title=title;this.section=section;this.source=source;this.page=page;this.text=text;}
  }

  private static final ArrayList<Topic> ALL=new ArrayList<>();
  static {
    add("Spell Preparation & Memorization","Spells","PHB",40,
      "Magic-users consult their spell books to memorize selected spells before adventuring. A rested, nourished mind is required; memorizing one spell level takes about 15 minutes as a working guideline. Casting expends that prepared copy. The same spell may be memorized more than once, but every copy is a separate use. Clerics pray for their available divine spells rather than learning them from a spellbook.");
    add("Spell Reference Fields","Spells","PHB",43,
      "Spell entries are defined by type/school, level, range, duration, area of effect, components, casting time, saving throw, and effect. V means verbal, S somatic, and M material. Casting-time segments matter during initiative because a melee round contains 10 segments.");
    add("Cleric Spell Access","Spells","DMG",38,
      "Clerics have access to the divine spell list appropriate to their class and level rather than collecting a personal spellbook. The DMG adds referee guidance on acquisition of higher divine spells and the role of faith/deity in granting them. The Companion therefore presents the Cleric list as an always-available prayer list and tracks only what is prepared.");
    add("Magic-User Initial & Acquired Spells","Spells","DMG",39,
      "Magic-users begin with a limited personal repertoire and add spells through study, captured spellbooks, scrolls, research, or other campaign discovery. Learning is not automatic merely because a spell appears on the class list. Intelligence and the DMG acquisition procedures govern whether new spells can be understood and added.");
    add("Spellcasting During Melee","Combat","DMG",65,
      "A spell announced for the round is vulnerable while being cast. If the caster is struck or otherwise disrupted before completion, the spell can be spoiled and the prepared copy is lost. Casting time in segments is therefore tactically important when initiative is close. Required verbal, somatic, and material components must remain possible throughout the casting.");
    add("Effect of Cover on Spell Powers","Combat","DMG",65,
      "Cover and line of effect can protect creatures from spells whose energy must pass through intervening space. Apply the spell's own area/ray/cone rules first, then the DMG cover handling. A creature completely outside an area is unaffected; partial exposure may alter saves or whether the effect can reach the target at all.");
    add("Counter-Affecting Spells","Spells","DMG",66,
      "Some spells explicitly counter or reverse other magic: haste/slow, light/darkness, bless/curse, and similar pairs. When the source entry says two effects counter one another, applying the opposing effect can cancel the existing magic rather than simply stacking another modifier.");

    add("Currency & Coinage","Gear","PHB",35,
      "The gold piece is the base unit. 10 cp = 1 sp; 20 sp = 1 gp; 2 ep = 1 gp; 1 pp = 5 gp. Thus 200 cp = 20 sp = 2 ep = 1 gp = one-fifth pp. Coin weight matters for encumbrance.");
    add("Player Character Expenses","Gear","DMG",25,
      "The DMG assumes an adventurer automatically spends at least 100 gp per experience level per month on upkeep, equipment, entertainment, and ordinary living. Henchmen and strongholds add further recurring expense; stronghold maintenance is about 1% of construction cost per month. These costs are campaign-economy tools and can be adjusted by the DM for circumstances.");
    add("Gems & Jewelry","Treasure","DMG",25,
      "Gem base values are generated in broad classes from 10 gp ornamental stones through 5,000 gp jewels, with actual value depending on type, quality, and size. Jewelry is valued separately. Appraising, gemcutting, and market discounts can change what a character actually realizes when selling treasure.");
    add("Money Changers & Merchants","Gear","PHB",35,
      "Changing large quantities of coin commonly costs a fee around 3%. Jewelers and gem merchants often buy below full appraised value, so sale price and nominal treasure value are not always identical. Campaign scarcity and reputation can move prices further.");

    add("Armor & Shields","Combat","PHB",36,
      "Armor, shields, Dexterity, and magic contribute to Armor Class. A shield improves AC by 1 only when it can be brought to bear. A small shield applies against one attack per round, a normal shield two, and a large shield three; attacks from the right flank or rear can negate the shield. Magical pluses improve AC further.");
    add("Magic Armor & Saving Throws","Combat","DMG",80,
      "Magical armor and shields improve Armor Class and can also matter for item/character saving throws when the rules call for protective equipment to resist destructive effects. Apply the item's magical plus only where the relevant DMG saving-throw rule allows it; a magical plus is not a universal bonus to every character save.");
    add("Weapon Proficiency","Combat","PHB",37,
      "Characters begin proficient with a class-limited number of weapons. Using a weapon without proficiency imposes the class's non-proficiency penalty to attack rolls. Additional proficiencies are gained with level. Proficiency with a mundane weapon also covers magical versions of the same weapon type.");
    add("Weapon Factors","Combat","PHB",37,
      "Weapon choice matters beyond the damage die. PHB tables include damage versus small/medium and large opponents, length, space required, speed factor, and adjustments against Armor Class. Missile weapons also have rate of fire and range bands.");
    add("Weapon Speed Factor","Combat","DMG",66,
      "Speed factor is not a flat initiative bonus every round. It becomes important in the DMG's tied/close initiative and multiple-attack interactions, where a quicker weapon can gain extra opportunities before a much slower weapon completes its attack sequence.");
    add("Charge Attacks","Combat","DMG",66,
      "A charge trades defense and flexibility for rapid closing and impact. The attacker must have room and a viable line to charge; certain set weapons such as spears gain special damage when receiving a charge, and lances gain their own mounted charge benefits.");
    add("Two-Weapon Fighting","Combat","DMG",70,
      "Fighting with a weapon in each hand grants an additional attack but imposes attack penalties modified by Dexterity and weapon size. The off-hand weapon is normally limited in size, and this does not override class weapon restrictions or proficiency requirements.");
    add("Striking to Subdue","Combat","DMG",67,
      "Subdual attacks intentionally convert normal weapon pressure into nonlethal defeat rather than ordinary killing damage. The DMG uses special handling for subdual, including the chance that accumulated subdual results cause surrender or unconsciousness instead of death.");

    add("Combat Round & Attack Rolls","Combat","DMG",61,
      "A melee round represents roughly one minute of feints, movement, parries, and multiple attempted blows; one attack roll represents the meaningful chance to score damaging hits during that interval. Hit points likewise represent endurance, luck, skill, and actual injury rather than only literal wounds.");
    add("Surprise","Combat","DMG",61,
      "Normal surprise is commonly 2 chances in 6 unless race, class, monster, or circumstance changes it. The number shown on the surprise die determines how many 6-second segments the surprised side is unable to react, creating real time for movement, attacks, or spell completion before normal initiative begins.");
    add("Initiative","Combat","DMG",61,
      "After surprise is resolved, each side rolls initiative. Lower/faster segment timing interacts with spell casting times, charging, missile fire, weapon speed, and multiple attacks. Initiative is a sequencing tool for the round rather than a complete simulation of every feint and swing.");
    add("Breaking Off & Fleeing","Combat","DMG",71,
      "Leaving melee can expose a character to attacks because opponents can exploit the opening. A withdrawal/break-off is different from uncontrolled flight; exact consequences depend on whether opponents remain engaged and able to pursue.");
    add("Pursuit & Evasion","Adventure","DMG",67,
      "Pursuit uses movement rates, terrain, visibility, obstacles, and relative speed. Outdoor pursuit has its own evasion probabilities. The DM can end pursuit when separation, concealment, or terrain makes continued tracking unreasonable.");
    add("Morale","Combat","DMG",67,
      "Morale is primarily for NPCs and monsters, not player characters. Casualties, leadership, overwhelming threats, and other circumstances modify morale; failure can cause retreat, surrender, rout, or other loss of fighting will.");

    add("Saving Throw Categories","Combat","DMG",79,
      "AD&D saving throws are divided into categories such as poison/death, petrification/polymorph, rods/staves/wands, breath weapon, and spells. Use the most specific applicable category first when an effect could fit more than one; class and level determine the base target number.");
    add("Item Saving Throws","Gear","DMG",80,
      "Objects exposed to destructive magic or extraordinary hazards may make item saving throws based on material and attack type. Carried items are not automatically checked for every spell; the effect normally has to expose or endanger them, and some rules call for checks only after particularly poor character saves.");
    add("Magic Resistance","Combat","DMG",79,
      "Magic resistance is checked separately from an ordinary saving throw when a creature possesses it. If resistance negates the spell, the magic fails against that creature before the normal saving-throw result is applied. Area magic can still affect other creatures in the same area.");

    add("Turning Undead","Class Ability","DMG",65,
      "Clerics present a holy/unholy symbol and attempt to turn undead using the class matrix. Higher-level clerics can automatically turn or destroy weaker undead; evil clerics can instead command appropriate undead. Turning occupies the cleric's action and requires the ability to confront and present the symbol.");
    add("Holy & Unholy Water","Gear","DMG",65,
      "Holy or unholy water harms creatures opposed to its spiritual nature and is often used as a thrown/splashed weapon or spell component. Containers, creation, and handling follow the DMG rules; a direct hit and splash exposure can produce different effects.");

    add("Potion Use","Magic Items","DMG",119,
      "A potion is normally one dose and takes effect when consumed. Duration and effect depend on the potion. Drinking multiple potions while another remains active can cause dangerous interaction, so the DMG potion-miscibility procedure should be used instead of assuming all potion effects stack safely.");
    add("Potion Miscibility","Magic Items","DMG",119,
      "Mixing active potions can produce anything from cancellation to increased potency, poison, or an explosive result. When two potion effects overlap in one creature, use the DMG miscibility procedure unless the specific items clearly say they combine safely.");
    add("Scroll Use","Magic Items","DMG",125,
      "Spell scrolls are one-use magic and must be read by an eligible character. Reading magic above the user's safe capability can require a failure check. Protection scrolls create their named ward and are consumed when activated; cursed scrolls trigger their curse when read/handled as specified.");
    add("Rings","Magic Items","DMG",129,
      "Rings are usually continuous or command/mental-activation items. AD&D limits how many rings a character can effectively use at once, and cursed rings can resist removal. Similar protection effects may not stack freely.");
    add("Rods, Staves & Wands","Magic Items","DMG",132,
      "These devices commonly contain charges. Each power lists its charge cost and sometimes a class restriction. When the final charge is spent, a device may become inert, retain only a basic weapon property, or risk destruction depending on the individual entry.");
    add("Magic Item Identification","Magic Items","DMG",116,
      "Characters do not automatically know every property of a newly found magic item. Experimentation, command words, identify-type magic, sages, and careful observation reveal functions. Cursed items can deliberately masquerade as beneficial objects until the triggering condition occurs.");
    add("Command Words","Magic Items","DMG",118,
      "Many items require a specific command word. Possession of the item is not enough if the word is unknown. Command words can be discovered by research, experimentation, divination, inscriptions, previous owners, or other campaign clues.");
    add("Charges & Limited Uses","Magic Items","DMG",118,
      "Charged items track a finite reserve. Different powers can cost different numbers of charges, and most devices are not casually rechargeable. Record remaining charges separately from daily-use limits because spending charges and waiting for a daily reset are different mechanics.");
    add("Cursed Magic Items","Magic Items","DMG",120,
      "Cursed items often identify as ordinary or beneficial until used. Many bind themselves to the owner or compel continued use, so simply dropping the item is not enough. Remove curse or stronger magic is commonly required, but the exact remedy is item-specific.");
    add("Artifacts & Relics","Magic Items","DMG",155,
      "Artifacts and relics use extraordinary major/minor powers, side effects, and drawbacks beyond ordinary magic items. Their full abilities are often deliberately hidden, normal identification can fail, and destruction usually requires a unique method rather than simple damage or dispel magic.");

    add("Encumbrance","Gear","PHB",101,
      "Weight and bulk both matter. Before Strength adjustments, roughly 35 lb with little bulk allows 12-inch movement, around 70 lb/fair bulk gives 9-inch movement, around 105 lb/bulky gives 6-inch movement, and heavier/very bulky loads reduce movement further. Ten gold pieces of weight equal about one pound.");
    add("Movement Scale","Adventure","PHB",102,
      "In dungeon exploration, each inch of movement rate represents 10 feet per 10-minute turn. Following a known route is much faster. Combat/fleeing movement is handled at the melee-round/segment scale instead of exploration-turn scale.");
    add("Light Sources","Adventure","PHB",102,
      "A torch illuminates about a 40-foot radius for 6 turns. A hooded lantern lights about a 30-foot radius and burns about 24 turns per pint of oil. A bullseye lantern projects a longer narrow beam, about 80 feet, for the same fuel duration.");
    add("Infravision & Ultravision","Adventure","DMG",59,
      "Infravision is heat-sensitive sight and is not ordinary vision in total darkness. Nearby light sources and mixed temperatures can interfere with useful contrast. Ultravision has its own enhanced low-light/energy perception and should not be treated as simple unlimited darkvision.");
    add("Invisibility","Adventure","DMG",59,
      "Invisible creatures are difficult but not impossible to locate. Noise, tracks, disturbed dust, attacks, and special detection can reveal position. Attacking normally ends spell-based invisibility, while some magic items or improved invisibility follow different rules.");
    add("Listening at Doors","Adventure","DMG",60,
      "Listening requires time and silence and normally produces only a chance to hear useful noise. Race/class and the nature of the barrier matter; repeated attempts are not guaranteed to reveal a silent or distant creature.");

    add("Damage, Falling & Healing","Combat","PHB",105,
      "Damage reduces hit points. Falling uses the campaign's 1e falling rule and surface adjudication; natural rest restores hit points slowly, while spells and magic can accelerate healing. Healing never raises a character above normal maximum hit points.");
    add("Experience Awards","Character","DMG",84,
      "Experience comes from successful adventuring: treasure recovered, opponents overcome, professional objectives, and special awards. The DM adjusts awards for circumstance and divides group awards among participating characters. Class prime-requisite bonuses apply after the appropriate earned XP is determined.");
    add("Training for New Levels","Character","DMG",86,
      "Gaining enough XP does not necessarily grant the next level instantly. The DMG includes training time and cost based on performance and instruction. Campaigns often adjust or omit this procedure, but it is part of the published advancement framework.");
  }

  private static void add(String t,String s,String src,int p,String x){ALL.add(new Topic(t,s,src,p,x));}
  public static ArrayList<Topic> all(){return new ArrayList<>(ALL);}
  private RulesReference(){}
}
