package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/**
 * Offline, paraphrased table-use mechanics for the built-in magic-item catalog.
 * This deliberately summarizes source rules instead of reproducing sourcebook prose.
 */
public final class MagicItemRules {
  private static final HashMap<String,String> D=new HashMap<>();
  static {
    put("Arrow of Slaying","A +3 magical arrow keyed to one creature type. If it hits a creature of the designated type, that creature is slain outright; the arrow is expended on a hit. Its unusual construction normally gives clues that it is specialized rather than an ordinary +3 arrow.");
    put("Dagger of Venom","Functions as a magical dagger and contains a concealed poison reservoir. On the appropriate activation/use, a successful hit can inject its stored poison; the reservoir must be refilled after its limited doses are spent.");
    put("Dwarven Thrower +3","In a dwarf's hands this +3 war hammer may be thrown and returns to the wielder. Against giants it gains greatly increased damage, making it substantially more dangerous than an ordinary +3 hammer.");
    put("Hammer of Thunderbolts","A powerful magical hammer whose full legendary effects require the appropriate giant-strength girdle and gauntlets of ogre power. Properly combined, it can be hurled with exceptional range and damage and can produce a thunderous stunning effect.");
    put("Mace of Disruption","A strongly enchanted mace especially dangerous to undead and certain evil extraplanar creatures. A successful hit can destroy qualifying creatures outright depending on their nature and the wielder's attack result.");
    put("Crossbow of Speed","Allows a crossbow to be cocked and fired far faster than a normal weapon of its type, effectively removing the ordinary slow reload limitation and greatly increasing rate of fire.");
    put("Crossbow of Accuracy +3","A +3 crossbow whose enchantment is primarily directed toward exceptional accuracy. Apply the magical attack bonus in addition to the crossbow's normal ammunition and range rules.");
    put("Crossbow of Distance","Greatly extends the effective ranges of the crossbow while retaining the normal weapon's damage and ammunition handling.");
    put("Sling of Seeking +2","A +2 sling whose missiles are magically guided toward their target, making it significantly more accurate than a mundane sling.");
    put("Sling Bullet of Impact","A magical sling bullet that strikes with unusually violent force, increasing damage beyond an ordinary enchanted bullet and being especially effective against hard or massive targets.");
    put("Cursed Backbiter Spear","Appears to be a beneficial enchanted spear, but when used in earnest it can reverse or twist back toward its wielder. Removing the curse is normally necessary before the owner can safely discard or cease using it.");
    put("Sword +1 Cursed","Appears to be an ordinary beneficial +1 sword until used in dangerous circumstances, at which point its curse imposes the listed detrimental behavior. Once claimed and used, the wielder is normally unable to voluntarily abandon it without curse removal.");
    put("Sword -2 Cursed","A cursed sword that imposes a -2 penalty rather than a bonus. The curse normally compels continued use and prevents simply discarding the weapon until a suitable curse-removal effect is applied.");
    put("Cursed Berserking Sword","Forces the wielder into a berserk state in combat, attacking nearby creatures without normal discrimination until no valid targets remain. The curse prevents normal abandonment of the weapon.");
    put("Flame Tongue","A +1 sword that can ignite on command and gains larger attack/damage bonuses against regenerative, cold-using, avian, or fire-vulnerable foes according to the DMG weapon entry. The flame also serves as a useful light/fire source.");
    put("Frost Brand +3","A +3 sword with strong cold associations. It grants protection against fire and can extinguish ordinary and some magical flame; against fire-using or fire-based opponents it is especially potent.");
    put("Dragon Slayer","A magical sword with its normal bonus against most foes and a much larger bonus against one dragon type. Determine or record the keyed dragon variety when the item is identified.");
    put("Giant Slayer","A magical sword that gains a substantially larger bonus and damage benefit against true giants and closely related giant-class opponents.");
    put("Sword +4 Defender","The wielder may shift some or all of the sword's +4 bonus from offense to defense each round, reducing attack/damage bonus by the amount assigned while improving Armor Class by the same amount.");
    put("Sword +5 Defender","Works as a defender sword with a +5 pool that can be divided between offense and Armor Class from round to round.");
    put("Sword +6 Defender","Works as a defender sword with a +6 pool that can be divided between offense and Armor Class from round to round.");
    put("Sword +5 Holy Avenger","In a paladin's hands this becomes one of the game's strongest holy weapons: it provides a major magical weapon bonus, strong bonuses against evil creatures, and anti-magic/protective benefits. In other hands it functions as a much less remarkable magical sword.");
    put("Sword +6 Holy Avenger","A stronger UA-variant holy avenger. It follows the same class-locked concept as the holy avenger but uses the supplied UA variant's enhanced bonus values.");
    put("Vorpal Sword","A highly enchanted sword capable of severing the head of a vulnerable opponent on especially high attack results. The exact qualifying roll depends on target size/form; creatures that do not rely on a head may be immune to the decapitation effect.");
    put("Sword of Sharpness","An enchanted blade that can sever limbs or extremities on high attack rolls. It is less absolute than a vorpal sword but can inflict crippling severing effects on appropriate creatures.");
    put("Sword of Wounding","Damage inflicted by this sword continues to bleed after the hit, and wounds from it resist ordinary magical healing until the weapon's special wound effect is properly treated or the rules' recovery conditions are met.");
    put("Sword of Life Stealing","On especially successful attack rolls this sword drains life energy/levels in addition to weapon damage and transfers a limited benefit to the wielder, subject to the item's usage limits.");
    put("Nine Lives Stealer +2","A +2 sword with a limited number of death-dealing charges. On qualifying high attack rolls, a charge can be spent to slay a living target outright if the target fails the required defense; when the charges are gone it remains a +2 sword.");
    put("Sword of Dancing","Can be released to fight on its own for a short sequence of rounds while its owner performs other actions. Its autonomous attack bonus changes through the dancing sequence before it must be grasped again.");
    put("Sun Blade","A sword-sized weapon with exceptional effectiveness against undead and creatures vulnerable to sunlight. It can produce bright sunlight-like radiance and is unusually easy to wield for characters permitted to use short swords.");
    put("Luck Blade","A magical sword that improves the possessor's luck/saving throws and may contain a small number of wishes. The number of wishes is determined when the weapon is generated and each wish is permanently expended when used.");

    put("Ring of Protection","Improves the wearer's Armor Class and saving throws by its magical plus. Multiple protection effects do not freely stack; use the strongest applicable protection where the 1e rules prohibit combining similar bonuses.");
    put("Ring of Regeneration","Restores lost hit points over time and can eventually regrow lost body parts if the wearer survives. Damage from certain causes may not regenerate, and death still ends the benefit unless the specific item rules say otherwise.");
    put("Ring of Invisibility","Allows the wearer to become invisible in the manner of the invisibility spell and to end/reuse the effect according to the ring's command/activation rules. Attacking normally breaks the current invisibility.");
    put("Ring of Free Action","Lets the wearer move and act despite many magical restraints and movement-impairing effects, including paralysis/holding and similar impediments covered by the item.");
    put("Ring of Feather Falling","Automatically protects the wearer from dangerous falling by reducing descent to a safe rate whenever a fall occurs.");
    put("Ring of Fire Resistance","Protects the wearer from normal fire and grants strong bonuses/reductions against magical fire. It also provides the DMG's protection from exceptionally hot environments and fiery attacks.");
    put("Ring of Warmth","Keeps the wearer comfortable in severe natural cold and improves resistance to cold-based attacks and effects.");
    put("Ring of Water Walking","Allows the wearer to stand and move across water and similar liquids as though they were firm ground.");
    put("Ring of Swimming","Greatly improves the wearer's ability to swim, including movement and endurance in water under the ring's stated limits.");
    put("Ring of Telekinesis","Allows telekinetic movement of objects up to the ring's weight limit. The wearer directs the force mentally and must remain within the item's effective range.");
    put("Ring of Spell Turning","Reflects a percentage of spell energy back toward the original caster. Determine the ring's turning percentage when generated; partial turning can cause unusual interactions when a spell is only partly reflected.");
    put("Ring of Spell Storing","Contains a number of pre-cast spells. The wearer can release stored spells without normal preparation, after which the ring can be recharged by casting suitable spells into it.");
    put("Ring of Three Wishes","Contains exactly three wishes when found unless some have already been expended. Each wish is consumed permanently when invoked.");
    put("Ring of Multiple Wishes","Contains a larger randomly determined number of wishes than a three-wish ring; each use permanently removes one wish.");
    put("Ring of Wizardry","Doubles the number of magic-user spells the wearer may prepare at one or more specified spell levels. Determine the affected level range when the ring is generated.");
    put("Ring of X-Ray Vision","Allows the wearer to see through many solid materials to a limited depth and range. Dense materials block the power, and excessive use causes severe fatigue or other harmful consequences under the DMG limits.");
    put("Ring of Mammal Control","Allows mental control of one or more mammals within the ring's limits, subject to intelligence/Hit Dice and saving-throw restrictions.");
    put("Ring of Human Influence","Improves Charisma-related reactions with humans/humanoids and provides limited charm-like influence a small number of times per day.");
    put("Ring of Djinni Summoning","Summons a specific djinni bound to the ring to serve the wearer. The djinni has its normal creature abilities and follows the item's service limits.");
    put("Ring of Elemental Command","A powerful ring keyed to air, earth, fire, or water. It grants protection and spell-like control over that element; its fullest powers are usually unlocked only after the wearer performs the condition associated with the ring's opposed elemental creature.");
    put("Ring of Contrariness","A cursed ring that causes the wearer to oppose suggestions and act contrary to reasonable requests. The wearer normally resists attempts to remove or identify the curse.");
    put("Ring of Delusion","A cursed ring that convinces its wearer it has a useful magical power even though the supposed power is false. The delusion can make identification attempts misleading.");
    put("Ring of Weakness","A cursed ring that reduces the wearer's Strength after it has been worn for the triggering period. Curse removal is normally required to remove it safely.");

    put("Rod of Absorption","Absorbs incoming spell energy directed at the wielder and stores a finite number of spell levels. Stored energy can later power the wielder's own spells; the rod becomes inert after its lifetime absorption capacity is exhausted.");
    put("Rod of Cancellation","A single-purpose anti-magic rod. Touching a magic item with it can permanently drain the item's enchantment after the required attack/contact; successful cancellation also exhausts the rod.");
    put("Rod of Lordly Might","A multifunction weapon/tool that can change form into several weapons and utility forms. It also has limited powerful command functions such as fear, paralysis, or life-draining effects, each constrained by daily/charge limits.");
    put("Rod of Resurrection","Holds resurrection power measured in charges. Restoring a creature to life consumes a number of charges based on the subject's race; the rod cannot be recharged normally once its reserve is depleted.");
    put("Rod of Rulership","Temporarily causes large numbers of creatures within range to regard the wielder as their ruler, subject to saving throws and total Hit Dice limits. The effect is influence/obedience rather than direct puppet-like control.");
    put("Rod of Smiting","Functions as a strong magical mace/club and deals extra damage to golems and certain extraplanar or construct-like foes. Particularly powerful strikes may expend charges for amplified effects.");
    put("Staff of Power","A high-level spellcaster staff with a large suite of charged spell effects plus defensive and melee benefits. It can be deliberately broken for a retributive strike, causing a dangerous explosion whose result depends on remaining charges and distance.");
    put("Staff of the Magi","One of the most powerful magic-user staves: it grants spell absorption, many spell-like effects, strong defensive properties, and melee enchantment. Like a staff of power, it can be broken in a retributive strike with catastrophic results based on remaining charges.");
    put("Staff of Curing","Expends charges to cure wounds, disease, blindness, and related afflictions according to the staff's listed functions. Some powers have per-day limits in addition to charge cost.");
    put("Staff of Striking","A magical quarterstaff whose wielder may spend charges on a successful hit to add large extra damage. More charges can be committed to a single blow for greater effect, up to the item's limit.");
    put("Staff of the Serpent","A clerical staff that can transform its head or entire form into a serpent for attack, restraint, or utility depending on the staff variety. It retains magical weapon properties when used as a staff.");
    put("Staff of Withering","A charged staff that can inflict aging, ability loss, or withering harm in addition to normal staff attacks, subject to the victim's saving throws and the charge cost of each special effect.");
    put("Staff of Swarming Insects","Expends charges to create a large cloud/swarm of insects around a target area, reproducing the disruptive and damaging qualities described by the item's spell-like effect.");
    put("Staff of Thunder & Lightning","A powerful staff with several electrical and thunderous functions, including shock, lightning, thunderclap, and combined effects. The stronger functions consume more charges and have limited use rates.");
    put("Staff of Woodlands","A druid-oriented staff that functions as an enchanted weapon and grants several nature powers such as plant growth, pass without trace, and tree/woodland effects. Some abilities consume charges while others are passive or limited-use.");

    put("Wand of Magic Missiles","Expends charges to fire one or more unerring magic missiles. Spending more charges in one activation increases the number of missiles up to the wand's stated maximum.");
    put("Wand of Fireballs","Expends a charge to produce a fireball at the wand's fixed caster level. Targets save for half damage under the normal fireball rules.");
    put("Wand of Lightning Bolts","Expends a charge to produce a lightning bolt at the wand's fixed caster level, including the normal line/rebound handling of the 1e spell.");
    put("Wand of Fire","Combines several fire effects—such as burning hands, pyrotechnics, fireball, or wall of fire—at different charge costs. Stronger functions consume more charges.");
    put("Wand of Frost","Produces cold-based effects such as an ice storm, wall of ice, or cone of cold, with charge cost depending on the selected function.");
    put("Wand of Fear","Projects a cone or ray of supernatural fear. Creatures caught by it must make the required saving throw or flee/panic for the effect's duration.");
    put("Wand of Paralyzation","Projects a paralyzing ray/beam. A target struck by the effect must save or become unable to move for the wand's stated duration.");
    put("Wand of Polymorphing","Uses charges to polymorph creatures or the wielder in a manner derived from the corresponding polymorph spells, including normal saving throws for unwilling targets.");
    put("Wand of Illusion","Allows the wielder to create and maintain illusion effects comparable to phantasmal-force magic. Continued concentration is normally required to keep complex effects operating.");
    put("Wand of Negation","Temporarily suppresses or negates the operation of another wand, staff, rod, or similar device targeted by the wielder, subject to the item's activation and duration rules.");
    put("Wand of Enemy Detection","Reveals hostile creatures within range and indicates their direction; it does not require the wielder to see the enemy normally.");
    put("Wand of Magic Detection","Detects magical auras in the wand's scanning area and can help locate enchanted objects or active spell effects.");
    put("Wand of Metal & Mineral Detection","Points toward significant masses of metal or valuable mineral material within range and provides progressively clearer information as the wielder closes on the source.");
    put("Wand of Secret Door & Trap Location","Detects secret doors and traps within its directional search area, normally revealing location rather than automatically explaining how to bypass the feature.");
    put("Wand of Wonder","Each activation produces a randomly determined magical effect. Results range from useful to bizarre or dangerous; roll on the wand's effect table every time it is used.");

    put("Bag of Holding","An extradimensional container whose inside capacity greatly exceeds its outside size. Four standard capacities exist; overloading or piercing can rupture it, and dangerous interactions occur if it is placed inside another extradimensional storage space.");
    put("Portable Hole","A cloth-like magical opening that unfolds into an extradimensional cylindrical space. It can be folded with contents inside, but combining it with certain other extradimensional containers can tear open a planar rift and destroy the containers.");
    put("Bag of Devouring","Looks like a bag of holding but is a hazardous extradimensional maw. Objects placed inside are consumed/lost, and a creature reaching into it can be seized and dragged in unless rescued under the item's rules.");
    put("Bag of Tricks","A small bag containing fuzzy objects that become real animals when thrown out. The bag variety determines the possible creatures; only a limited number can be drawn in a given period.");
    put("Alchemy Jug","Produces one selected liquid at a time from a fixed list. Each liquid has a maximum quantity per day, and the jug cannot produce another type until the current daily production limit/reset condition is satisfied.");
    put("Decanter of Endless Water","Produces water on command in several flow rates, from a stream to a forceful geyser. The geyser can knock creatures down or cause structural/environmental effects and continues until commanded to stop.");
    put("Deck of Many Things","A high-risk artifact-like deck. The user declares how many cards will be drawn, then each card immediately produces a major beneficial or harmful result; drawn cards vanish and failing to complete the declared draws has consequences under the deck rules.");
    put("Robe of Useful Items","A robe covered with patches representing mundane objects. Removing a patch transforms it into the depicted real item; each patch is one-use and permanently disappears from the robe.");
    put("Robe of Eyes","Grants extraordinary all-around vision, including detection of invisible/hidden things, but also makes the wearer especially vulnerable to gaze attacks and intense light effects that strike the robe's many eyes.");
    put("Robe of Archmagi","A powerful robe restricted by alignment. It improves Armor Class, saving throws, and magic resistance/spell effectiveness for a wearer of the matching alignment; an incompatible wearer suffers severe consequences.");
    put("Cloak of Displacement","Makes the wearer appear slightly displaced from the true position, causing attacks to miss more often and improving defense until opponents or circumstances overcome the displacement advantage.");
    put("Cloak of Elvenkind","Makes the wearer extremely difficult to see when motionless or moving carefully in suitable surroundings. The chance of detection changes with movement and proximity.");
    put("Boots of Elvenkind","Suppress the wearer's footfalls, allowing extremely quiet movement and improving the chance to move without being heard.");
    put("Boots of Speed","Greatly increase the wearer's movement and combat speed for a limited daily duration. Prolonged use causes fatigue and requires recovery before the boots can be safely used again.");
    put("Boots of Levitation","Allow the wearer to levitate vertically on command in the manner of the spell, subject to the boots' weight and movement limits.");
    put("Boots of Striding and Springing","Improve walking/running speed and allow unusually long or high jumps. The jumping benefit is constrained by available space and landing conditions.");
    put("Bracers of Defense","Provide an Armor Class value to a wearer who is not using conventional body armor. They can combine with some other defensive bonuses but do not stack with worn armor in the normal way.");
    put("Gauntlets of Ogre Power","Set the wearer's effective Strength to ogre-like level, improving melee attack/damage and many strength-based tasks. They are also one of the required components for unlocking the hammer of thunderbolts' full power.");
    put("Girdle of Giant Strength","Sets the wearer's effective Strength to that of a particular giant type. Stronger girdles grant higher attack/damage and strength performance; one is required for the hammer of thunderbolts' greatest effects.");
    put("Gauntlets of Dexterity","Increase the wearer's Dexterity to a high fixed value, improving applicable Armor Class, reaction, missile, and class abilities derived from Dexterity.");
    put("Gauntlets of Fumbling","Cursed gauntlets that appear beneficial but cause severe clumsiness, dropped objects, and penalties when precision or weapon handling matters. Curse removal is normally required to remove them safely.");
    put("Helm of Telepathy","Allows the wearer to read surface thoughts within range and communicate mentally. It can also help implant suggestions or influence targets under the item's saving-throw limits.");
    put("Helm of Teleportation","Allows a qualified wearer to teleport repeatedly in the manner of the spell. Accuracy and mishap chance follow the teleport rules and the wearer's familiarity with the destination.");
    put("Helm of Brilliance","A gem-studded helm with multiple stored powers: light/fire effects, weapon enhancement, and strong anti-undead/fiend abilities. Its gems are expended as certain powers are used, and exposure to intense magical fire can trigger a catastrophic explosion.");
    put("Helm of Opposite Alignment","A cursed helm that reverses the wearer's alignment when donned unless the victim receives the allowed defense. The change is real rather than cosmetic and normally requires powerful magic to reverse.");
    put("Helm of Comprehending Languages & Reading Magic","Lets the wearer understand spoken/written languages and read magical writing, subject to the helm's normal limits and the special handling of dangerous inscriptions.");
    put("Necklace of Adaptation","Creates a protective envelope of breathable air around the wearer, allowing survival in smoke, poisonous gas, underwater, vacuum-like environments, and similar hazards where ordinary breathing would fail.");
    put("Necklace of Missiles","Carries detachable beads/spheres that can be thrown as fireballs of different strengths. Removing and throwing a bead consumes it; exposure of the necklace to magical fire can cause all remaining missiles to detonate.");
    put("Necklace of Prayer Beads","A clerical necklace whose special beads each grant a specific divine power such as blessing, curing, smiting, summoning, or major aid. Determine which beads are present; some powers are limited to once per day or once per use period.");
    put("Necklace of Strangulation","A cursed necklace that tightens around the wearer's throat once donned and inflicts continuing damage. Ordinary removal is impossible; powerful curse-removal or wish-level magic is required, and the necklace can remain attached even after death.");
    put("Pearl of Power","Lets a spellcaster recall one previously cast spell of the pearl's designated spell level. It can normally be used once per day and cannot restore spells outside the pearl's level/class restrictions.");
    put("Pearl of Wisdom","Raises a cleric's Wisdom while possessed/worn, improving Wisdom-dependent benefits and potentially bonus spell access under the class rules.");
    put("Periapt of Health","Protects the wearer from disease, including many magical diseases, while worn.");
    put("Periapt of Proof Against Poison","Provides a strong or absolute defense against poison according to the periapt's type, improving or replacing normal saving throws against toxic effects.");
    put("Periapt of Wound Closure","Automatically stabilizes/halts bleeding and causes wounds to close, greatly improving natural healing and protecting against effects that continue to bleed.");
    put("Scarab of Protection","Provides a finite number of defenses against deadly magical attacks such as death magic, level drain, or similar effects. Each successful protection consumes a charge; when all charges are spent the scarab crumbles or becomes inert.");
    put("Scarab of Death","A cursed scarab that appears valuable but transforms into a deadly burrowing creature/effect after being carried for the trigger period, attacking the possessor from within unless detected and removed in time.");
    put("Stone of Good Luck (Luckstone)","Grants a general +1 luck benefit to saving throws and many other checks/rolls while carried, subject to the 1e limits on which rolls receive luck modifiers.");
    put("Stone of Weight (Loadstone)","A cursed stone that burdens its possessor and effectively increases encumbrance. Once acquired it repeatedly returns if discarded until remove curse or equivalent magic breaks the attachment.");
    put("Stone of Controlling Earth Elementals","Summons a powerful earth elemental from a suitable mass of earth or stone after the command procedure. Only one can be controlled at a time; the elemental follows conjure-elemental style control rules.");
    put("Sphere of Annihilation","A small black sphere that utterly destroys matter entering it. Mental control moves it slowly; competing control, planar effects, or contact with certain powerful magic can produce dangerous or catastrophic results.");
    put("Talisman of the Sphere","Greatly improves a character's ability to control a sphere of annihilation and can help contest another controller. It is primarily useful only in conjunction with such a sphere.");
    put("Well of Many Worlds","A portable cloth-like portal. When spread on a surface it opens a two-way passage to another plane or world; the destination changes when the well is moved and reopened.");
    put("Cubic Gate","A cube keyed to six planes. Pressing a face can open a gate to the plane associated with that side or transport the holder and nearby creatures, depending on the activation method.");
    put("Amulet of the Planes","Allows planar travel by concentrating on a destination plane. Use requires strong mental control; failure can send the user to an unintended plane or location.");
    put("Amulet of Proof Against Detection and Location","Blocks or strongly resists divination, scrying, ESP, crystal-ball observation, and similar attempts to locate or read the wearer.");
    put("Amulet of Life Protection","Protects the wearer's life force/soul and can preserve it under conditions that would otherwise destroy or trap it, according to the amulet's specific death/possession rules.");
    put("Crystal Ball","Allows remote viewing of a distant person or place. Clarity, duration, and chance of success depend on familiarity and distance; some crystal balls add clairaudience, ESP, or other powers.");
    put("Crystal Hypnosis Ball","A cursed crystal ball that appears normal but exposes the user to influence by the item's controlling intelligence or remote master, allowing false visions and hypnotic commands.");
    put("Mirror of Life Trapping","Can imprison creatures bodily inside extradimensional cells when they look into the mirror and fail the required defense. Breaking the mirror releases trapped occupants and may have dangerous consequences.");
    put("Mirror of Mental Prowess","Provides several major divination/travel functions, including remote viewing and using the mirror as a portal to a viewed location. Activation and use are subject to the mirror's daily limits.");
    put("Mirror of Opposition","Creates an exact hostile duplicate of a creature that looks into it. The duplicate fights its original and normally disappears only when one of the pair is defeated or the mirror's condition is resolved.");
    put("Figurines of Wondrous Power","Small statuettes that transform into living magical creatures on command. Each figurine type has its own creature statistics, duration, frequency of use, and special abilities; when the duration ends it returns to figurine form.");
    put("Ioun Stones","Orbit the user's head and each grant a specific passive benefit such as ability improvement, regeneration, spell absorption, protection, or sustenance. A stone can be attacked or captured and stops functioning if removed from orbit.");
    put("Iron Flask","Can imprison certain extraplanar creatures. Opening/releasing a captured being may allow the owner to command it for a limited service, but control depends on the flask's rules and the creature's status.");
    put("Efreeti Bottle","Contains an efreet. Opening it releases the creature, whose reaction and service can range from attack to limited aid depending on the bottle's generation result and the efreet's nature.");
    put("Broom of Flying","Carries one or more riders through the air on command, with speed reduced by heavier loads. It can be directed by its owner and may be called from a short distance.");
    put("Carpet of Flying","A flying carpet with capacity and speed determined by its size. It moves by command and slows as carried weight approaches its maximum.");
    put("Daern's Instant Fortress","A small metal cube that expands on command into a full-sized fortified tower. It can crush creatures/objects in its expansion area and later collapse back to portable form if not too badly damaged.");
    put("Apparatus of Kwalish","A large iron, lobster-like submersible vehicle. Internal controls operate movement, claws, viewing ports, and other functions; it protects occupants while underwater but can be damaged like a heavy construct.");
    put("Folding Boat","A small box that unfolds on command into one of two seaworthy boat sizes complete with basic fittings. A second command folds it back into portable form.");
    put("Horn of Blasting","Produces a devastating cone of sound that damages creatures and objects and can deafen targets. Repeated use risks destroying the horn in an explosion.");
    put("Horn of Valhalla","Summons berserk warriors to fight for the user for a limited time. Horn material determines how many warriors appear and which character classes can safely use it; improper use can cause the summoned warriors to attack the user.");
    put("Horn of the Tritons","A sea-oriented horn that can calm or summon marine creatures, panic hostile sea beings, and produce other aquatic effects. Its strongest powers are limited in frequency.");
    put("Lyre of Building","Can negate structural attacks while played and, when used for construction, performs an enormous amount of building labor in a short time. Extended playing requires skill checks/limits to avoid mistakes or exhaustion.");
    put("Manual of Bodily Health","Studying the manual for the required uninterrupted period permanently increases Constitution by 1. Once successfully used, the magic leaves the book for that reader.");
    put("Manual of Gainful Exercise","Studying the manual correctly permanently increases Strength by 1; misuse by an unsuitable reader can have no effect or harmful consequences depending on the book.");
    put("Manual of Quickness of Action","Proper study permanently increases Dexterity by 1. The book's magic is consumed for that reader after completion.");
    put("Tome of Clear Thought","Proper study permanently increases Intelligence by 1; the book must be studied for the full required period and its magic is then exhausted for that reader.");
    put("Tome of Leadership and Influence","Proper study permanently increases Charisma by 1 after the required study period.");
    put("Tome of Understanding","Proper study permanently increases Wisdom by 1 after the required study period.");
    put("Book of Exalted Deeds","A powerful good-aligned holy text. A qualified good divine reader gains major permanent benefits after full study; evil readers suffer severe harm, and neutral readers gain little or no benefit.");
    put("Book of Vile Darkness","The evil counterpart to the Book of Exalted Deeds. Qualified evil readers gain major benefits after study, while good readers suffer severe consequences.");
    put("Book of Infinite Spells","Contains a sequence of magical pages, each with a spell-like power. Only one page is active at a time; turning to a new page is risky and normally prevents returning to the previous one.");
    put("Vacuous Grimoire","A cursed book that appears valuable but drains mental abilities from readers who study it. The loss persists until restored by appropriate powerful magic.");
  }

  private static void put(String n,String d){D.put(norm(n),d);}
  static String norm(String s){return s==null?"":s.toLowerCase(Locale.US).replaceAll("[^a-z0-9]","");}

  static String categorySource(GearCatalog.Entry en){
    String src=en.source==null?"":en.source;
    if(src.contains("DMG")){
      if(en.category.contains("Potion"))return "DMG pp. 119-124";
      if(en.category.equals("Scroll"))return "DMG pp. 125-128";
      if(en.category.equals("Ring"))return "DMG pp. 129-131";
      if(en.category.equals("Rod / Staff")||en.category.equals("Wand"))return "DMG pp. 132-135";
      if(en.category.equals("Miscellaneous Magic"))return "DMG pp. 136-164";
      if(en.category.equals("Magic Sword"))return "DMG pp. 165-168";
      if(en.category.equals("Magic Weapon"))return "DMG pp. 168-169 and weapon entries";
      if(en.category.equals("Magic Armor / Shield"))return "DMG magic armor tables/entries";
      if(en.category.equals("Artifact / Relic"))return "DMG pp. 155-164 artifact/relic section";
      return "Dungeon Masters Guide";
    }
    if(src.contains("UA"))return "Supplied Unearthed Arcana variant";
    return src;
  }

  static String family(GearCatalog.Entry en){
    String n=en.name, k=norm(n), c=en.category;
    if(c.equals("Magic Armor / Shield")){
      java.util.regex.Matcher m=java.util.regex.Pattern.compile("[+]([1-9])").matcher(n);
      if(m.find())return "Magical armor/shield. Its +"+m.group(1)+" enchantment improves Armor Class by "+m.group(1)+" beyond the base item while retaining the underlying armor or shield's normal coverage, movement, and class restrictions. Special named variants add the property indicated by their name.";
      return "Magical armor/shield. Apply the base armor or shield rules first, then the item's magical modifier or curse. Named variants may alter missile defense, etherealness, vulnerability, size, or other properties.";
    }
    if(c.equals("Magic Weapon")||c.equals("Magic Sword")){
      java.util.regex.Matcher m=java.util.regex.Pattern.compile("[+]([1-9])").matcher(n);
      if(m.find())return "Magical weapon. Unless its entry states otherwise, the +"+m.group(1)+" bonus applies to attack rolls and damage. Special target bonuses, returning/throwing behavior, curses, speed, slaying, and other named effects are applied in addition to the base weapon rules.";
      return "Magical weapon. Use the normal weapon's damage, rate, range, speed, and proficiency rules, then apply the named enchantment. Cursed or specialized weapons can override the ordinary bonus behavior.";
    }
    if(c.equals("Potion / Oil / Elixir"))return "Consumable magic. Drinking a potion or applying an oil activates its effect for the listed duration; one container is normally one dose unless the item says otherwise. Potions can interact dangerously when mixed, and oils usually affect the coated creature/object rather than the drinker.";
    if(c.equals("Scroll"))return "One-use written magic. Spell scrolls require the appropriate class/reading ability and can fail or produce dangerous effects when used above the reader's safe capability. Protection scrolls create the named ward for their listed duration/area and are consumed when read.";
    if(c.equals("Ring"))return "Wearable magic that normally functions continuously or by mental/command activation. Only a limited number of rings can be worn effectively at once under 1e rules; cursed rings may resist removal.";
    if(c.equals("Rod / Staff"))return "Charged or command-activated device. Most special functions expend charges, while some passive weapon/defensive properties do not. When charges reach zero, many rods/staves become inert or retain only mundane/basic magical weapon value.";
    if(c.equals("Wand"))return "Usually a charged device with a specific spell-like effect. Activating a wand normally costs one or more charges; its effects use the wand's stated fixed power/caster level rather than the wielder's own spell level unless the item says otherwise.";
    if(c.equals("Artifact / Relic"))return "Artifact/relic. These unique or near-unique objects have major powers, side effects, and often hidden drawbacks determined by the artifact/relic rules and the individual item's power tables. Ordinary identification and destruction methods are often insufficient.";
    return "Magic item with a source-defined command, passive, charged, cursed, or limited-use effect. The app records the item natively; use its specific entry when available and the category rules shown here for handling charges, activation, and stacking.";
  }

  public static String describe(GearCatalog.Entry en){
    if(en==null)return "";
    String d=D.get(norm(en.name));
    if(d==null)d=family(en);
    String src=categorySource(en);
    String variant=(en.source!=null&&en.source.contains("UA"))?"\n\nSource integrity: the supplied UA file is a later/conversion-style variant, so UA-only mechanics are tagged as variant material rather than silently treated as original 1985 1e text.":"";
    return d+"\n\nSource: "+src+variant;
  }

  private MagicItemRules(){}
}
