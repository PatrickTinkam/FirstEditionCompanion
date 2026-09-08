package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/**
 * Native, paraphrased race/subrace reference used by character creation.
 * PHB entries follow the supplied 1e Players Handbook. UA-only entries follow
 * the supplied UA variant document and are labelled as such in the UI.
 */
public final class RaceData {
  public static final class Race {
    public final String name, family, source, adjustments, details;
    Race(String name,String family,String source,String adjustments,String details){
      this.name=name;this.family=family;this.source=source;this.adjustments=adjustments;this.details=details;
    }
    public String label(){return name+"  ["+source+"]";}
    public String fullDetails(){
      return "Race: "+name+"\nFamily: "+family+"\nSource: "+source+"\n\nAbility adjustments: "+adjustments+"\n\n"+details;
    }
  }

  private static final ArrayList<Race> ALL=new ArrayList<>();
  static {
    add("Human","Human","PHB","None",
      "Humans are the baseline against which the other player races are measured. They receive no racial ability-score bonuses or penalties. Humans may enter any player-character class permitted by the campaign and have no racial maximum level beyond limits intrinsic to the chosen class. They receive no special racial vision, saving-throw bonus, weapon bonus, or detection ability from race alone.");

    add("Dwarf — Hill / Standard","Dwarf","PHB","CON +1; CHA -1",
      "The standard PHB dwarf is the normal player-character dwarf. Dwarves have 60-foot infravision. Their exceptional constitution grants a Constitution-based bonus, normally +1 through +5, on saving throws against poison and against magic from wands, staves, rods, and spells. They speak Dwarven, Gnome, Goblin, Kobold, Orcish, Common, and their alignment language; regardless of Intelligence they normally learn no more than two additional languages.\n\nDwarves are expert miners and stoneworkers. When actively examining their surroundings they have strong chances to notice grades/slopes, new stone construction, shifting walls or rooms, stonework traps, and approximate depth underground. In melee they gain +1 to hit half-orcs, goblins, hobgoblins, and orcs. Ogres, trolls, ogre magi, giants, and titans suffer a -4 adjustment on attacks against dwarves because of dwarven training against larger foes.\n\nClass access is restricted compared with humans. Fighter advancement depends on Strength and can reach 9th level at the top end; thief advancement is unrestricted by race; assassin advancement is limited to 9th. Fighter/thief is a standard multiclass option. The dwarf's reduced Charisma applies chiefly when dealing with non-dwarves; the unmodified score remains relevant when dealing with dwarves.");

    add("Dwarf — Mountain","Dwarf","PHB + supplied UA variant","CON +1; CHA -1",
      "The PHB explicitly allows mountain dwarves as player characters and treats them as close cousins of standard dwarves. Use the normal dwarven package for magic and poison resistance, infravision, languages, underground/stonework expertise, combat bonuses against traditional humanoid enemies, and defensive expertise against giant-class opponents.\n\nThe supplied UA variant describes mountain dwarves as somewhat taller than their hill-dwarf cousins and retains the same +1 Constitution and -1 Charisma adjustment. Unless the campaign adopts additional UA-variant distinctions, the core PHB dwarven class and multiclass restrictions remain the safest baseline.");

    add("Dwarf — Gray / Duergar","Dwarf","Supplied UA variant","CON +1; CHA -1",
      "This entry comes from the supplied UA variant file, whose mechanics differ from clean original 1985 AD&D 1e presentation. Duergar are subterranean gray dwarves. The file grants automatic underground direction/depth sense, strong stonecraft and metalcraft benefits, +4 Armor Class against giantkind, a +4 bonus on saves versus arcane magic, immunity to poison, immunity to illusion/phantasm effects, and immunity to paralysis. It also grants once-per-day psionic enlarge, invisibility, and reduce effects.\n\nThe supplied file gives daylight penalties: effective Dexterity -2 while exposed, -2 on attacks, and a casting/concentration penalty. It lists stealth bonuses, Undercommon and Dwarven as automatic languages, and a 45-foot (9-inch) movement rate. It also applies a +2 level-equivalency cost for advancement.\n\nSource caution: the same entry is internally inconsistent about darkvision, labelling it '120 feet' but describing 60 feet in the accompanying sentence. The Companion flags that conflict rather than silently choosing one value.");

    add("Elf — High","Elf","PHB","DEX +1; CON -1",
      "High elves are the default PHB player-character elves. They have 60-foot infravision, a 90% resistance to sleep and charm magic before any normal saving throw applies, and +1 to attack rolls when using non-crossbow bows, short swords, or long swords.\n\nElves automatically speak Elvish, Gnome, Halfling, Goblin, Hobgoblin, Orcish, Gnoll, Common, and their alignment language. Intelligence above 15 allows additional languages. They are unusually good at finding hidden portals: merely passing within 10 feet gives a 1-in-6 chance to notice a concealed door; active searching gives 2-in-6 for a secret door and 3-in-6 for a concealed door.\n\nWhen alone and not wearing metal armor, or when at least 90 feet ahead of a party made entirely of suitable elves/halflings, an elf can move so quietly that surprise improves to 4-in-6 when no door or similar barrier must be opened; opening such a barrier reduces the chance to 2-in-6.\n\nPHB class choices include fighter, magic-user, thief, and assassin, with fighter and magic-user level limits based partly on ability scores. Standard multiclass combinations include fighter/magic-user, fighter/thief, magic-user/thief, and fighter/magic-user/thief.");

    add("Elf — Gray","Elf","Supplied UA variant","STR -1; INT +1; DEX +1; CON -1",
      "The supplied UA variant says gray elves share the normal high-elf racial package: resistance to sleep/charm magic, elven weapon training, enhanced vision/senses, secret-door detection, stealth, and the same language family. Their distinguishing mechanical change is an additional +1 Intelligence and -1 Strength compared with the high-elf baseline, producing the total adjustments shown above. The supplied variant raises initial Intelligence and Dexterity limits to 19 while limiting initial Strength and Constitution to 17.");

    add("Elf — Wood / Sylvan","Elf","Supplied UA variant","STR +1; DEX +1; INT -1; CON -1",
      "Wood elves retain the normal elven package for sleep/charm resistance, bow/sword training, vision, stealth, and secret/concealed-door detection. The supplied UA variant gives them animal-empathy capability, allows Treant as a bonus language option, adds +1 Strength, and imposes -1 Intelligence in addition to the normal elven Dexterity/Constitution changes. They are described as more reserved and less likely to mix routinely with other races than high elves.");

    add("Elf — Wild / Grugach","Elf","Supplied UA variant","STR +1; DEX +1; CON -1; CHA -1",
      "Wild elves retain most standard elven traits, including resistance to sleep/charm, elven vision and stealth, but replace normal sword training with spear training while retaining the bow bonus. The supplied UA variant also grants animal empathy and requires a trapping-oriented background skill.\n\nGrugach automatically speak Elven; Common is not automatic in this variant and must be learned separately. They are treated as Small despite a 60-foot (12-inch) movement rate. Their class choices are narrower than those of most elves: bard, druid, fighter and certain fighter subclasses, psionicist, thief, and assassin are listed, while magic-user and cleric multiclass paths are specifically restricted.");

    add("Elf — Dark / Drow (Male)","Elf","Supplied UA variant","INT +1; DEX +1; WIS -1; CON -1",
      "This is the male drow package from the supplied UA variant file. Player-character drow are treated as outcasts and do not begin with the full NPC drow equipment or NPC magic resistance. The variant grants subterranean direction/depth sense, enhanced senses, stonecraft, stealth, +4 on saves versus arcane magic, very strong sleep/charm resistance, immunity to ghoul paralysis, and once-per-day spell-like powers including dancing lights, faerie fire, and darkness; additional powers arrive at higher level.\n\nDrow suffer significant daylight sensitivity, including effective Dexterity -2, -2 on attacks, and casting/concentration penalties while exposed to bright daylight or equivalent effects. They receive improved two-weapon training, automatically know Undercommon and Elven, and move 60 feet (12 inches) per round. Male drow carry a +1 level-equivalency cost in this variant. The file lists darkvision and explicitly treats the UA-only mechanics as different from the verified PHB core rules.");

    add("Elf — Dark / Drow (Female)","Elf","Supplied UA variant","STR -1; INT +1; DEX +1; CON -1; CHA +1",
      "Female drow use the same supplied-UA racial package as male drow for subterranean senses, stonecraft, stealth, light sensitivity, arcane-magic resistance, sleep/charm resistance, ghoul-paralysis immunity, two-weapon training, languages, and basic spell-like powers. The supplied variant additionally grants higher-level female drow several extra once-per-day spell-like abilities.\n\nFemale drow receive different ability adjustments from males, as shown above, and carry a +2 level-equivalency cost in this variant. Drow player characters are not automatically bound to the NPC social restriction that only females may be clerics unless the campaign specifically uses Lolth's priesthood rules.");

    add("Gnome — Surface","Gnome","PHB","None",
      "PHB gnomes have 60-foot infravision and a Constitution-based +1 through +5 bonus on saving throws against wands, staves, rods, and spells. They speak Gnomish, Dwarvish, Halfling, Goblin, Kobold, Common, and their alignment language, and can communicate with burrowing mammals. They normally learn no more than two additional languages regardless of Intelligence.\n\nGnomes are excellent underground observers. When actively concentrating they have strong chances to notice grade/slope, unsafe stonework, approximate depth, and direction of travel. They gain +1 to hit kobolds and goblins. Gnolls, bugbears, ogres, trolls, ogre magi, giants, and titans suffer -4 on attacks against gnomes.\n\nPHB class choices include fighter (maximum 6th), illusionist (maximum 7th), thief, and assassin (maximum 8th), plus combinations such as fighter/illusionist, fighter/thief, and illusionist/thief. Multiclass use of thief abilities is still constrained by thief armor and weapon rules.");

    add("Gnome — Deep / Svirfneblin","Gnome","Supplied UA variant","CON +1; CHA -1",
      "The supplied UA variant describes deep gnomes as subterranean gnomes with 120-foot darkvision, enhanced senses, light sensitivity, automatic underground direction/depth sense, strong stealth, stonecraft/metalcraft expertise, +1 to hit drow and kuo-toa, +4 Armor Class against giantkind, a universal +2 saving-throw bonus, and immunity to illusion/phantasm effects.\n\nIt also grants once-per-day blindness, blur, and change self, continuous personal nondetection, and at 6th level the ability to attempt a daily earth-elemental or xorn summoning. Automatic languages are Undercommon and Svirfneblin; several underground and surface languages are available as bonuses. Movement is 45 feet (9 inches). The variant applies a +2 level-equivalency cost. It also states that player-character deep gnomes do not retain all of the stronger NPC svirfneblin defenses.");

    add("Half-Elf — High-Elf Ancestry","Half-Elf","PHB","None",
      "The standard PHB half-elf has 60-foot infravision and 30% resistance to sleep and charm magic before normal saving throws. Half-elves speak Common, Elvish, Gnome, Halfling, Goblin, Hobgoblin, Orcish, Gnoll, and their alignment language; Intelligence above 16 allows extra languages.\n\nThey share elven talent for hidden portals: passing within 10 feet gives a 1-in-6 chance to notice a concealed door; active searching gives 2-in-6 for secret doors and 3-in-6 for concealed doors.\n\nHalf-elves have broad class access: cleric, druid, fighter, ranger, magic-user, thief, and assassin, with racial level caps on several classes. Their multiclass combinations are much broader than most nonhumans and include cleric/fighter, cleric/ranger, cleric/magic-user, fighter/magic-user, fighter/thief, magic-user/thief, cleric/fighter/magic-user, and fighter/magic-user/thief.");

    add("Half-Elf — Gray-Elf Ancestry","Half-Elf","Supplied UA variant","No additional modifier stated beyond the half-elf baseline",
      "The supplied UA variant states that half-elves descended from gray elves have abilities and restrictions similar to the standard high-elf-descended half-elf. It does not state a separate set of inherited gray-elf ability-score adjustments for the half-elf, so the Companion does not invent them. Use the normal half-elf sleep/charm resistance, portal detection, languages, and class framework unless the campaign applies a specific house rule.");

    add("Half-Elf — Wood-Elf Ancestry","Half-Elf","Supplied UA variant","No additional modifier stated beyond the half-elf baseline",
      "The supplied UA variant states that half-elves descended from wood elves have abilities and restrictions similar to standard half-elves. It does not explicitly transfer the full wood-elf Strength/Intelligence adjustment package to the half-elf, so no extra ability modifier is applied here. Use the normal half-elf racial package unless the campaign specifies otherwise.");

    add("Half-Elf — Drow Ancestry","Half-Elf","Supplied UA variant","No additional modifier stated beyond the half-elf baseline",
      "The supplied UA variant explicitly says half-elves of drow descent retain their elven parent's disadvantages involving bright light and replace normal half-elf twilight/infravision with 120-foot darkvision. They are regarded as outcasts by drow society. The source does not provide a separate ability-score adjustment line for drow-descended half-elves, so the Companion does not assume the full drow modifiers.");

    add("Halfling — Unspecified / Mixed","Halfling","PHB","STR -1; DEX +1",
      "This baseline entry is useful when the exact PHB halfling subrace has not been chosen. All PHB halflings receive a Constitution-based +1 through +5 bonus on saves against wands, staves, rods, spells, and poison. They speak Halfling, Dwarven, Elven, Gnome, Goblin, Orcish, Common, and their alignment language; Intelligence above 16 grants extra languages.\n\nHalflings are naturally stealthy. When alone and not in metal armor, or well ahead of an all-elf/halfling group under the PHB conditions, they surprise on 4-in-6 when no door must be opened and 2-in-6 when a barrier must be opened. Infravision and underground directional sense depend on subrace/mixed ancestry, so select Hairfoot, Stout, or Tallfellow when that distinction matters. PHB halflings are fighters, thieves, or fighter/thieves, with fighter level caps depending on subrace and Strength.");

    add("Halfling — Hairfoot","Halfling","PHB","STR -1; DEX +1",
      "Hairfeet use the standard PHB halfling package: Constitution-based bonuses against magic and poison, broad demihuman languages, and exceptional stealth/surprise capability. The PHB fighter-level footnote treats Hairfeet as the most restricted halfling fighter stock: under normal ability ranges they are limited to 4th level. The PHB does not grant all Hairfeet the stronger Stout infravision/underground-sense package.");

    add("Halfling — Stout","Halfling","PHB","STR -1; DEX +1",
      "Stouts use all normal PHB halfling saving-throw, poison-resistance, language, and stealth abilities. Pure Stoutish characters have 60-foot infravision. Stouts and mixed halflings can also detect whether a passage slopes up or down 75% of the time and determine direction 50% of the time when concentrating. The PHB fighter-level footnote allows a Stout with Strength 18 to reach 5th fighter level.");

    add("Halfling — Tallfellow","Halfling","PHB","STR -1; DEX +1",
      "Tallfellows use the normal PHB halfling package for magic/poison resistance, languages, and stealth. The PHB racial-preference notes show unusually good relations with elves, and the fighter-level footnote makes Tallfellows slightly less restricted than Hairfeet: Strength 17 allows 5th fighter level and an exceptional Tallfellow who somehow reaches Strength 18 may reach 6th. Infravision is not granted universally to Tallfellows by the PHB; mixed ancestry can provide 30-foot infravision.");

    add("Half-Orc","Half-Orc","PHB","STR +1; CON +1; CHA -2",
      "PHB half-orcs have 60-foot infravision. They automatically speak Common, Orcish, and their alignment language and may learn no more than two additional languages. Their Charisma penalty primarily affects dealings with non-orcs/non-half-orcs, so the unmodified score can still matter within their own racial community.\n\nPHB class choices are cleric (maximum 4th), fighter (maximum 10th), thief (maximum 8th), and assassin, with multiclass combinations including cleric/fighter, cleric/thief, cleric/assassin, fighter/thief, and fighter/assassin. Multiclass armor use follows the least favorable class restriction when class abilities require it.");

    add("Half-Ogre","Half-Ogre","Supplied UA variant","STR +2; CON +2; INT -1; DEX -1; CHA -2",
      "The supplied UA variant presents the half-ogre as a large-framed human/ogre crossbreed. It can use a bastard sword one-handed, but specially sized armor and clothing cost double and armor/clothing weight is increased by 50%. The variant says half-ogres require unusually large mounts, are affected by weapons keyed to slay either humans or ogres, and suffer -4 on attacks against dwarves and gnomes because of those races' anti-giant combat training.\n\nThe variant grants 60-foot darkvision, imposes -2 on Dexterity checks to hide or move silently, and allows the player to choose a third ability score that receives +2 on ability checks. Automatic languages are Common and Giant, with several humanoid languages available as bonuses. Movement is 60 feet (12 inches). Class options are limited to anti-paladin, barbarian, cleric, druid, fighter, ranger, thief, and assassin. Initial score limits listed by the supplied file are Strength 19, Constitution 19, Intelligence 15, Dexterity 15, and Charisma 13.");
  }

  private static void add(String n,String f,String s,String a,String d){ALL.add(new Race(n,f,s,a,d));}

  public static ArrayList<Race> all(){return new ArrayList<>(ALL);}

  public static Race find(String raw){
    if(raw==null)return null;String q=raw.trim();
    for(Race r:ALL)if(r.name.equalsIgnoreCase(q))return r;
    String k=q.toLowerCase(Locale.US).replace('_',' ').replace('-', ' ');
    if(k.equals("human"))return by("Human");
    if(k.equals("dwarf")||k.equals("dwarven")||k.equals("hill dwarf"))return by("Dwarf — Hill / Standard");
    if(k.equals("mountain dwarf"))return by("Dwarf — Mountain");
    if(k.equals("elf")||k.equals("elven")||k.equals("high elf"))return by("Elf — High");
    if(k.equals("gnome")||k.equals("surface gnome"))return by("Gnome — Surface");
    if(k.equals("half elf")||k.equals("half elven"))return by("Half-Elf — High-Elf Ancestry");
    if(k.equals("halfling"))return by("Halfling — Unspecified / Mixed");
    if(k.equals("half orc")||k.equals("half orcish"))return by("Half-Orc");
    return null;
  }

  private static Race by(String n){for(Race r:ALL)if(r.name.equals(n))return r;return null;}

  public static Race legacy(String name){
    String n=(name==null||name.trim().isEmpty())?"Custom / Unspecified":name.trim();
    return new Race(n,"Custom / legacy","Character save","Not available for a custom/legacy race",
      "This race value came from an older character save or a campaign-specific entry and is not being overwritten. Select a built-in race/subrace from the dropdown to use the native racial reference. Keep this value if your DM uses a custom race or house-ruled ancestry.");
  }

  private RaceData(){}
}
