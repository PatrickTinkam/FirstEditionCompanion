package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/**
 * Concise, original-reference summaries for the built-in spell catalog.
 * These are intentionally paraphrased quick-reference descriptions rather
 * than reproductions of rulebook text.
 */
final class NativeSpellDescriptions {
  private static final HashMap<String,String> D=new HashMap<>();
  static {
    // Unearthed Arcana and cantrip entries that previously had little/no useful text.
    put("Ceremony","Performs a formal religious rite whose exact benefit depends on the ceremony chosen, such as dedication, blessing, investiture, or another faith-specific observance.");
    put("Combine","Allows several clerics to join their spiritual power through a single acting cleric, strengthening the lead caster for a limited period.");
    put("Endure Cold/Heat","Lets the recipient tolerate unusually hot or cold natural conditions with much less risk from environmental exposure.");
    put("Invisibility to Undead","Makes the protected creature difficult for undead to perceive unless the protection is broken by hostile action or other circumstances.");
    put("Magic Stone","Enchants small stones so they can be thrown or slung as magical missiles with improved effectiveness.");
    put("Penetrate Disguise","Helps the caster see through mundane or magical attempts to conceal a creature's true identity or appearance.");
    put("Portent","Provides a brief supernatural warning or omen about danger, success, or the likely outcome of an immediate course of action.");
    put("Precipitation","Creates a small area of light rain or similar moisture, useful for dampening, watering, cleaning, or affecting exposed flames and surfaces.");
    put("Aid","Bolsters an ally with courage and temporary vitality, improving its ability to survive immediate danger.");
    put("Detect Life","Reveals whether living creatures are present within the spell's effective area, subject to barriers and the spell's limits.");
    put("Dust Devil","Conjures a small whirlwind-like servant that moves about at the caster's direction and can buffet creatures or disturb loose material.");
    put("Enthrall","Captivates listeners with the caster's speech, making them more attentive and less aware of events around them for a time.");
    put("Holy Symbol","Temporarily creates or empowers a usable sacred symbol for clerical purposes.");
    put("Messenger","Compels or persuades a small creature to carry a simple message toward a named place or recipient.");
    put("Withdraw","Greatly accelerates the caster's subjective thinking and prayer time, allowing quiet preparation while little time passes outside the effect.");
    put("Wyvern Watch","Creates a magical guard that watches a protected area and can strike or paralyze an intruder who triggers it.");
    put("Cloudburst","Produces a sudden heavy downpour over a limited area, soaking creatures and terrain and potentially suppressing exposed normal fire.");
    put("Death's Door","Keeps a creature at the brink of death from slipping away immediately, buying time for healing or rescue.");
    put("Flame Walk","Protects the recipient against intense heat and fire, allowing safer movement through fiery hazards.");
    put("Magical Vestment","Temporarily enhances a cleric's vestment or clothing so it functions as stronger supernatural protection.");
    put("Meld Into Stone","Lets the caster merge bodily into a sufficiently large mass of stone and remain hidden within it for a time.");
    put("Negative Plane Protection","Provides a temporary defense against life-draining or energy-draining effects associated with negative energy.");
    put("Remove Paralysis","Ends or reduces magical or supernatural paralysis affecting one or more creatures.");
    put("Water Walk","Allows recipients to move across the surface of water or similar liquids as though standing on firm ground.");
    put("Abjure","Forces or attempts to force an extraplanar or summoned being away from the caster's plane or immediate presence.");
    put("Cloak of Fear","Surrounds the caster with a terrifying aura that can cause nearby enemies to panic or flee.");
    put("Giant Insect","Transforms ordinary insects into enlarged, dangerous versions that can be directed by the caster.");
    put("Imbue With Spell Ability","Places a limited selection of clerical spell power into another creature so it can later invoke those spells itself.");
    put("Spell Immunity","Protects a creature against one or more specifically chosen spells for the duration.");
    put("Spike Growth","Causes vegetation or ground cover to harden into concealed spikes that injure creatures moving through the area.");
    put("Air Walk","Lets the subject tread on air as though climbing an invisible slope or solid path.");
    put("Animate Dead Monster","Animates the corpse of a non-humanoid monster as an undead servant under the caster's control.");
    put("Golem","Creates or empowers a temporary magical construct or golem-like servant from suitable material.");
    put("Magic Font","Creates a sacred scrying focus that can reveal distant events or information through a consecrated vessel or basin.");
    put("Rainbow","Produces a powerful multicolored magical effect whose exact result depends on the caster class and version of the spell.");
    put("Spike Stones","Makes stone or rocky ground sprout painful, often hard-to-see points that damage creatures crossing it.");
    put("Forbiddance","Wards a large area against unwanted planar travel and can harm or repel creatures that do not meet the ward's conditions.");
    put("Heroes' Feast","Creates a supernatural banquet that nourishes a group and grants temporary protective and morale-related benefits.");
    put("Exaction","Binds a powerful creature to perform a service through a magically enforced agreement or demand.");
    put("Succor","Creates a magical means of emergency aid or return, allowing the bearer to summon help or be transported according to the spell's form.");

    put("Detect Balance","Reveals strong imbalances in alignment, elemental influence, or natural harmony within the spell's area.");
    put("Detect Poison","Reveals the presence of poison in a creature, object, food, drink, or nearby substance.");
    put("Flame Blade","Creates a blade-shaped flame in the caster's hand that can be used as a magical melee weapon.");
    put("Goodberry","Enchants a handful of berries so they become nourishing and restore a small amount of lost vitality when eaten.");
    put("Reflecting Pool","Turns a natural pool or similar body of water into a temporary scrying surface for viewing a distant place or subject.");
    put("Starshine","Creates soft starlike illumination over an area, providing useful light without the intensity of daylight.");
    put("Moonbeam","Calls down a narrow shaft of moonlike light that can illuminate, reveal, or affect creatures within its path.");
    put("Liveoak","Transforms a large healthy oak into a powerful guardian that protects a designated place or serves the druid for a time.");
    put("Transmute Water to Dust","Transforms a quantity of water into dry dust, with potentially dangerous consequences for creatures or environments made largely of water.");
    put("Changestaff","Transforms a specially prepared staff into a powerful treant-like guardian under the druid's command.");
    put("Sunray","Projects an intense beam of sunlight that can blind or injure targets and is especially dangerous to creatures vulnerable to sunlight.");

    put("Colored Lights","Creates small, harmless colored lights for illumination, signals, decoration, or distraction.");
    put("Dim","Reduces the brightness of a small light source or darkens a limited area slightly.");
    put("Haze","Creates a faint visual haze that lightly obscures vision in a small area.");
    put("Mask","Makes a minor temporary alteration to facial appearance or superficial features.");
    put("Mirage","Creates a small, simple visual illusion intended to mislead or decorate rather than cause direct harm.");
    put("Noise","Creates a brief harmless sound at a chosen nearby point.");
    put("Two-D'lusion","Creates a simple flat illusion resembling a painted or two-dimensional image.");
    put("Chromatic Orb","Creates a colored magical orb whose damage and additional effect depend on the caster's level and the color selected.");
    put("Phantom Armor","Surrounds the recipient with illusory protective armor that absorbs or reduces a limited amount of harm before disappearing.");
    put("Read Illusionist Magic","Allows the caster to read and understand magical writings used by illusionists.");
    put("Spook","Makes a chosen creature perceive the caster as terrifying, potentially causing it to recoil or flee.");
    put("Alter Self","Changes the caster's outward physical form within broadly similar limits, allowing disguise and limited physical adaptation.");
    put("Fascinate","Captures a creature's attention with an illusion or compelling presentation, making it focus on the effect and neglect other concerns.");
    put("Ultravision","Grants vision beyond normal visible light, improving sight in certain darkness or unusual lighting conditions.");
    put("Whispering Wind","Sends a short spoken message on a magical breeze to a chosen location within range.");
    put("Delude","Masks or falsifies the caster's apparent alignment, aura, or other divinable quality.");
    put("Phantom Steed","Creates a supernatural mount whose speed and special movement abilities improve as the caster becomes more powerful.");
    put("Phantom Wind","Creates an illusory or magical wind effect used to mislead, buffet, or simulate environmental force.");
    put("Wraithform","Makes the caster partially insubstantial, allowing movement through narrow gaps and providing protection from many ordinary attacks.");
    put("Rainbow Pattern","Creates a shifting multicolored display that can mesmerize creatures that see it.");
    put("Solid Fog","Creates extremely thick fog that heavily obscures vision and greatly impedes movement through the affected area.");
    put("Vacancy","Makes an occupied place appear abandoned, unused, or empty to casual inspection and certain forms of detection.");
    put("Advanced Illusion","Creates a sophisticated illusion with broader sensory detail and flexibility than basic phantasmal effects.");
    put("Dream","Sends a dream or dreamlike message to a sleeping creature, potentially carrying information or influence across distance.");
    put("Magic Mirror","Creates or enchants a reflective surface for magical viewing, detection, or scrying.");
    put("Tempus Fugit","Alters the perceived or effective flow of time for creatures in a limited area, making events seem to pass at an unusual rate.");
    put("Death Fog","Creates a deadly, clinging fog that obscures vision and is harmful to creatures caught within it.");
    put("Mirage Arcane","Transforms the apparent terrain of a large area with a highly convincing illusion that can include structures and landscape features.");
    put("Mislead","Creates an illusory double while concealing the caster, causing observers to track the false image instead of the real caster.");
    put("Phantasmagoria","Overwhelms targets with a powerful sequence of frightening or bewildering illusions.");
    put("Shadow Walk","Lets the caster and companions travel through a shadowy border realm to cover great distances quickly.");
    put("Weird","Confronts multiple creatures with terrifying phantasmal manifestations capable of causing extreme fear and potentially lethal shock.");

    // Magic-user UA spells.
    put("Alarm","Places a magical warning on an area so the caster is alerted when a creature enters or crosses it.");
    put("Armor","Surrounds the recipient with an invisible magical force that improves protection until the effect is exhausted or ends.");
    put("Firewater","Creates or alters a liquid into a volatile magical substance associated with heat or flame.");
    put("Grease","Coats an area or object with magical slipperiness, making footing or grasping difficult.");
    put("Melt","Causes ice, snow, or similar frozen material to thaw rapidly in a limited area.");
    put("Mount","Summons a normal riding animal to serve the caster for the spell's duration.");
    put("Run","Greatly increases a creature's running speed for a limited period, generally at the cost of fatigue or exertion afterward.");
    put("Taunt","Provokes selected creatures into anger or reckless action by magically intensifying insults or irritation.");
    put("Wizard Mark","Places a durable personal magical inscription or sigil on an object or surface.");
    put("Bind","Magically secures or ties a creature or object with constricting force or material.");
    put("Deeppockets","Creates extradimensional storage in specially prepared pockets so they can carry far more than their apparent size suggests.");
    put("Flaming Sphere","Creates a rolling ball of fire that the caster can direct to burn creatures and objects it contacts.");
    put("Irritation","Afflicts a creature with maddening itching or a distracting rash that interferes with effective action.");
    put("Preserve","Slows decay or spoilage in an object, corpse, food, or other perishable material.");
    put("Protection from Cantrips","Wards the recipient against minor cantrip effects for a limited time.");
    put("Tasha's Hideous Laughter","Overcomes a creature with uncontrollable laughter, leaving it unable to act normally while the effect lasts.");
    put("Vocalize","Lets the caster speak or perform verbal spell components despite magical silence or similar interference.");
    put("Whip","Creates or animates a whip-like magical force that can strike, entangle, or harry a target.");
    put("Zephyr","Creates a brief controlled breeze useful for moving air, dispersing vapors, or affecting light objects.");
    put("Item","Temporarily reduces an object to a tiny, portable form and later restores it.");
    put("Material","Conjures or manipulates a modest quantity of simple nonliving material for a short period.");
    put("Melf's Minute Meteors","Creates several small fiery missiles that orbit the caster and can be launched one at a time as ranged attacks.");
    put("Secret Page","Changes the apparent contents of a written page while preserving the true writing beneath the magical disguise.");
    put("Sepia Snake Sigil","Creates a trapped magical symbol in writing that can spring into serpentlike energy and immobilize a reader.");
    put("Wind Wall","Raises a strong vertical curtain of wind that deflects many missiles, gases, small flying creatures, and similar hazards.");
    put("Evard's Black Tentacles","Fills an area with writhing dark tentacles that seize, crush, and restrain creatures caught among them.");
    put("Leomund's Secure Shelter","Creates a sturdy temporary shelter with basic security and comfort for the caster and companions.");
    put("Otiluke's Resilient Sphere","Encloses a creature or object in a mobile globe of force that blocks most physical and magical interaction across its surface.");
    put("Shout","Releases a devastating cone of sound that can injure creatures and damage fragile objects.");
    put("Stoneskin","Protects the recipient with magically hardened skin that negates a limited number of physical attacks before the protection is used up.");
    put("Avoidance","Makes an object or place magically repel a chosen creature or class of creatures.");
    put("Dismissal","Forces an extraplanar creature back toward its native plane if the spell overcomes its resistance.");
    put("Dolor","Inflicts supernatural pain or misery that can seriously impair the target's ability to act.");
    put("Fabricate","Converts raw materials into finished nonmagical goods, with the quality limited by the caster's craftsmanship and available materials.");
    put("Leomund's Lamentable Belaborment","Confuses and distracts intelligent creatures with magically compelling argument, discussion, or indecision.");
    put("Sending","Delivers a short message across great distance and allows the recipient a brief reply.");
    put("Chain Lightning","Unleashes a powerful lightning strike that jumps from the primary target to additional nearby creatures.");
    put("Contingency","Stores another spell to trigger automatically when a specific condition involving the caster occurs.");
    put("Ensnarement","Uses a carefully prepared magical lure or trap to call and bind a powerful extraplanar being.");
    put("Eyebite","Turns the caster's gaze into a supernatural attack capable of afflicting creatures with fear, sickness, sleep, or similar debilitating effects.");
    put("Mordenkainen's Lucubration","Restores a previously cast spell to the caster's memory under the spell's level and timing limits.");
    put("Banishment","Expels one or more extraplanar creatures from the caster's plane, often strengthened by objects or substances they oppose.");
    put("Forcecage","Creates an exceptionally strong invisible prison of force that traps creatures inside a barred or solid enclosure.");
    put("Sequester","Hides a creature or object from normal observation and many forms of magical detection, often placing a creature into suspended sleep.");
    put("Teleport without Error","Teleports the caster and companions to a known destination with far less risk of arriving off target than ordinary teleportation.");
    put("Torment","Subjects a magically bound creature to severe supernatural coercion intended to force compliance or information.");
    put("Truename","Uses knowledge of a being's secret true name to gain extraordinary magical leverage over it.");
    put("Volley","Turns hostile magic back toward its source or redirects certain incoming spell effects.");
    put("Binding","Uses one of several powerful magical methods to imprison, restrain, chain, hedge, or otherwise confine a creature for a long duration.");
    put("Demand","Sends a long-distance message backed by a compelling magical command or suggestion.");
    put("Otiluke's Telekinetic Sphere","Creates a resilient sphere of force that the caster can move telekinetically, carrying whatever is trapped inside.");
    put("Sink","Causes a creature or object to descend into the ground or another solid surface and become magically imprisoned below it.");
    put("Crystalbrittle","Makes a hard substance such as metal temporarily fragile and glasslike so it can be broken much more easily.");
    put("Energy Drain","Strikes a target with severe negative energy that drains life force, experience levels, or equivalent vitality.");

    // Common PHB entries that benefit from useful native summaries when no detailed digest exists.
    put("Detect Magic","Reveals the presence of magical auras within the spell's area and may provide clues about their location or nature.");
    put("Light","Creates magical illumination on a chosen point or object; some versions can also be used offensively against a creature's vision.");
    put("Dispel Magic","Attempts to end ongoing magical effects or suppress magic in the affected area, with success depending on the competing caster levels and effect involved.");
    put("Feign Death","Places a willing creature into a deathlike suspended state in which it appears dead and requires little or no normal activity.");
    put("Locate Object","Provides a directional sense toward a known or described object while it remains within range.");
    put("Tongues","Allows the recipient to understand and speak unfamiliar languages for the duration.");
    put("Animate Dead","Turns suitable corpses or remains into undead servants under the caster's control, subject to the spell's limits.");
    put("Plane Shift","Transports creatures between planes of existence, usually requiring a specially attuned focus and allowing some uncertainty in arrival.");
    put("Raise Dead","Restores life to a recently dead creature if its body and circumstances meet the spell's requirements.");
    put("Heal","Restores a very large amount of lost vitality and removes several serious afflictions in a single touch.");
    put("Gate","Opens a planar portal and can also call a powerful extraplanar being through it, with major consequences determined by the situation.");
    put("Resurrection","Returns a dead creature to life even after a longer period or more serious bodily damage than lesser revival magic normally permits.");
    put("Symbol","Places a powerful magical rune that triggers a selected harmful or controlling effect when activated.");
    put("Entangle","Causes plants in an area to twist around creatures, slowing or immobilizing those caught within the growth.");
    put("Faerie Fire","Outlines creatures or objects in harmless glowing light, making them easier to see and target and negating some concealment.");
    put("Shillelagh","Empowers a wooden club or staff so it strikes with enhanced magical force for a limited time.");
    put("Barkskin","Hardens a creature's skin like bark, improving its natural protection while the spell lasts.");
    put("Heat Metal","Heats metal equipment carried or worn by enemies, potentially causing pain, injury, or forcing them to drop it.");
    put("Call Lightning","Calls repeated lightning strikes from an existing storm or suitable weather against targets chosen by the druid.");
    put("Plant Growth","Causes vegetation to become dramatically thicker and more overgrown, hindering movement or improving long-term plant productivity depending on use.");
    put("Stone Shape","Reshapes a quantity of stone into a chosen rough form such as an opening, seal, tool, or structural feature.");
    put("Call Woodland Beings","Summons or calls intelligent woodland creatures or fey beings to the druid's location, with their cooperation depending on the situation.");
    put("Commune With Nature","Gives the druid broad supernatural awareness of terrain, creatures, water, and important natural features across a large surrounding area.");
    put("Wall of Thorns","Creates a dense barrier of magically hardened thorny vegetation that is difficult and painful to cross.");
    put("Creeping Doom","Calls a massive crawling swarm that moves at the druid's direction and can overwhelm creatures caught in its path.");
    put("Reincarnate","Returns a dead creature to life in a newly determined body rather than restoring its original form.");
    put("Audible Glamour","Creates an illusory sound that seems to come from a chosen point and can mimic voices or other noises within the spell's limits.");
    put("Change Self","Alters the caster's apparent appearance and clothing with illusion, allowing convincing disguise without truly changing the body.");
    put("Colour Spray","Projects a vivid fan of clashing color that can overwhelm, stun, blind, or render creatures unconscious depending on their strength.");
    put("Phantasmal Force","Creates a convincing visual illusion that can mislead creatures and may cause apparent harm if they believe the effect is real.");
    put("Mirror Image","Creates several illusory duplicates of the caster, causing attacks to strike images instead of the real caster until the images are destroyed.");
    put("Fear","Projects supernatural terror that can cause creatures to panic, flee, or drop what they are holding.");
    put("Suggestion","Plants a magically persuasive course of action in a creature's mind if the wording sounds reasonable enough to accept.");
    put("Confusion","Scrambles the behavior of affected creatures so they may wander, attack randomly, stand idle, or act unpredictably.");
    put("Project Image","Creates a distant illusory duplicate through which the caster can appear and, in many versions, cast spells.");
    put("Maze","Banishes a creature into an extradimensional labyrinth until it finds its way out, with smarter creatures generally escaping sooner.");
    put("Prismatic Spray","Fires multiple rays of different colors, each carrying a distinct powerful magical effect.");
    put("Prismatic Wall","Creates a layered multicolored barrier whose separate colors each block, damage, or repel different threats and require specific means to overcome.");
    put("Burning Hands","Projects a short fan of flame from the caster's hands, burning creatures and flammable objects in front of the caster.");
    put("Charm Person","Makes a humanoid regard the caster as a trusted friend or ally if it fails its resistance to the spell.");
    put("Feather Fall","Slows falling creatures or objects so they descend gently instead of taking normal falling damage.");
    put("Find Familiar","Calls a small supernatural companion whose presence benefits the caster and creates a special bond between them.");
    put("Shield","Creates an invisible magical barrier that improves the caster's protection, especially against missiles and certain magical attacks.");
    put("Shocking Grasp","Charges the caster's hand with electricity so a successful touch delivers a damaging shock.");
    put("Spider Climb","Lets the recipient cling to walls and ceilings like a spider for the duration.");
    put("Unseen Servant","Creates an invisible, mindless force that performs simple household or carrying tasks at the caster's direction.");
    put("Knock","Opens or releases many mundane and magically secured doors, locks, lids, and similar closures.");
    put("Levitate","Raises or lowers a creature or object vertically through the air while horizontal movement remains limited.");
    put("Ray of Enfeeblement","Weakens a creature with a magical ray, reducing its physical strength and combat effectiveness.");
    put("Stinking Cloud","Creates a nauseating cloud that obscures vision and can leave creatures retching and unable to act effectively.");
    put("Blink","Causes the caster to shift rapidly between the material world and another plane, making attacks and positioning unpredictable.");
    put("Clairaudience","Lets the caster hear sounds at a distant location as though present there.");
    put("Clairvoyance","Lets the caster see a distant location as though viewing it from a magical sensor.");
    put("Explosive Runes","Places dangerous magical writing that detonates when read by an unauthorized creature.");
    put("Gust of Wind","Creates a powerful blast of air that can push creatures, disperse vapors, and interfere with missiles or small flying beings.");
    put("Protection from Normal Missiles","Makes the recipient highly resistant or immune to ordinary nonmagical missile weapons for the duration.");
    put("Dimension Door","Instantly transports the caster a short distance to a chosen destination without crossing the intervening space.");
    put("Ice Storm","Creates a violent burst of hail, sleet, or magical ice that damages creatures across an area.");
    put("Polymorph Self","Lets the caster assume other physical forms while retaining more of the caster's own mind and identity than polymorphing another creature.");
    put("Cloudkill","Creates a moving bank of poisonous vapor that can kill weaker creatures outright and harm stronger creatures.");
    put("Cone of Cold","Projects a cone of intense cold that deals heavy damage to creatures and objects in its path.");
    put("Conjure Elemental","Summons a powerful elemental being and binds it to the caster's service while concentration and control are maintained.");
    put("Contact Other Plane","Allows the caster to ask questions of distant extraplanar intelligences, risking confusion or mental strain for deeper knowledge.");
    put("Magic Jar","Transfers the caster's life force into a vessel and from there allows attempts to possess other creatures.");
    put("Passwall","Opens a temporary passage through wood, plaster, or stone without permanently destroying the surrounding structure.");
    put("Telekinesis","Moves creatures or objects at a distance by force of will, with the amount of weight depending on caster power.");
    put("Teleport","Instantly transports the caster and companions to a distant destination, with accuracy depending on how well the place is known.");
    put("Wall of Force","Creates an invisible barrier of force that is extremely difficult to damage or cross by ordinary means.");
    put("Anti-Magic Shell","Creates an area around the caster where most magic and magical effects are suppressed or unable to function normally.");
    put("Death Spell","Kills multiple weaker creatures within a broad area without requiring ordinary weapon damage.");
    put("Disintegrate","Reduces a creature or nonmagical object struck by the spell to dust if it fails the spell's resistance.");
    put("Globe of Invulnerability","Creates a protective sphere that excludes many lower-level spells from affecting creatures inside it.");
    put("Reincarnation","Returns a dead creature to life in a newly determined body, similar in concept to druidic reincarnation.");
    put("Repulsion","Creates a field that forces creatures away from the caster and prevents them from approaching normally.");
    put("Tenser's Transformation","Temporarily turns the magic-user into a far more formidable physical combatant at the cost of normal spellcasting capability.");
    put("Delayed Blast Fireball","Creates a fireball whose detonation can be delayed for a short chosen interval before exploding.");
    put("Reverse Gravity","Reverses the direction of gravity in an area, causing creatures and loose objects to fall upward until the effect ends.");
    put("Simulacrum","Creates a partially real duplicate of a creature from snow or ice, possessing a limited portion of the original's abilities.");
    put("Clone","Grows a duplicate body from a piece of a creature, creating dangerous magical consequences if both original and clone coexist.");
    put("Mind Blank","Protects the recipient from many forms of mental detection, influence, scrying, and mind-affecting magic.");
    put("Permanency","Makes certain otherwise temporary magical effects permanent, often at a significant personal cost to the caster.");
    put("Trap the Soul","Imprisons a creature's life force within a specially prepared gem or similar magical receptacle.");
    put("Imprisonment","Buries a creature in magical suspended confinement far beneath the earth with no normal means of escape.");
    put("Meteor Swarm","Calls down multiple fiery meteors that explode across a large area for devastating fire and impact damage.");
    put("Shape Change","Lets the caster repeatedly assume a wide range of creature forms and gain many of their physical capabilities for the duration.");
    put("Temporal Stasis","Places a creature into suspended time so it neither ages nor acts until the magic is ended.");
    put("Time Stop","Briefly freezes the passage of time for everyone except the caster, allowing the caster several moments of unhindered action.");
  }

  private static void put(String name,String text){D.put(norm(name),text);}
  private static String norm(String s){return s==null?"":s.toLowerCase(Locale.US).replaceAll("[^a-z0-9]","");}

  static String summary(String name,String source){
    String hit=D.get(norm(name));
    if(hit!=null)return hit;
    String n=name==null?"":name.trim();
    String k=n.toLowerCase(Locale.US);

    // Cantrips are deliberately described by their practical minor effect.
    if(k.equals("chill"))return "Makes a small object or tiny amount of material noticeably cooler without producing dangerous cold.";
    if(k.equals("clean"))return "Cleans light dirt, grime, or stains from a small object or surface.";
    if(k.equals("color"))return "Changes the color of a small object or limited surface area for a minor magical effect.";
    if(k.equals("cut"))return "Makes a small, simple cut or slice in an appropriate ordinary material.";
    if(k.equals("dampen"))return "Makes a small object or patch of material damp or slightly wet.";
    if(k.equals("dry"))return "Removes ordinary moisture from a small object or patch of material.";
    if(k.equals("dust"))return "Removes a light coating of dust from a small area or object.";
    if(k.equals("exterminate"))return "Kills tiny mundane pests such as insects or vermin in a very small area.";
    if(k.equals("flavor"))return "Adds or changes a mild flavor in a small amount of food or drink.";
    if(k.equals("freshen"))return "Makes a small amount of stale food, air, or material seem fresher for ordinary use.";
    if(k.equals("gather"))return "Draws a handful of nearby small loose objects together into one place.";
    if(k.equals("polish")||k.equals("shine"))return "Cleans and brightens a small surface so it appears freshly polished.";
    if(k.equals("salt")||k.equals("spice")||k.equals("sweeten"))return "Adds a mild culinary quality suggested by the spell name to a small amount of food or drink.";
    if(k.equals("sprout"))return "Encourages a viable seed or small plant to begin sprouting or show fresh growth.";
    if(k.equals("stitch"))return "Closes a small tear in cloth or similar material with simple magical stitching.";
    if(k.equals("tie")||k.equals("knot"))return "Ties a small cord, string, ribbon, or similar object into a simple knot.";
    if(k.equals("untie"))return "Loosens or unties a simple ordinary knot in a small cord or similar object.";
    if(k.equals("warm"))return "Makes a small object or amount of material pleasantly warm without creating dangerous heat.";
    if(k.equals("wrap"))return "Wraps or folds a small flexible object or covering around another small item.";
    if(k.equals("curdle")||k.equals("sour")||k.equals("tarnish")||k.equals("wilt"))return "Causes the minor undesirable change suggested by the spell name in a small suitable target.";
    if(k.equals("dirty")||k.equals("dusty")||k.equals("hairy"))return "Creates the harmless cosmetic condition suggested by the spell name on a small target.";
    if(k.equals("ravel")||k.equals("tangle"))return "Tangling or disordering magic affects a small amount of thread, hair, cord, or similar material.";
    if(k.equals("scorch"))return "Leaves a small harmless scorch mark or singed patch on a suitable nonliving surface.";
    if(k.equals("spill"))return "Causes a small open container or loose quantity of material to spill in a minor mishap.";
    if(k.equals("change"))return "Produces a small harmless cosmetic or situational alteration chosen within cantrip-scale limits.";
    if(k.equals("distract"))return "Creates a tiny sensory distraction that briefly draws a nearby creature's attention.";
    if(k.equals("hide")||k.equals("palm"))return "Helps conceal a very small object from casual observation for a brief time.";
    if(k.equals("mute"))return "Suppresses a small or quiet sound for a short time.";
    if(k.equals("present"))return "Makes a small object appear neatly or theatrically presented at hand.";
    if(Arrays.asList("belch","blink","cough","giggle","nod","scratch","sneeze","twitch","wink","yawn").contains(k))return "Causes a harmless involuntary action matching the spell name in a nearby creature if the cantrip takes effect.";
    if(k.equals("listen"))return "Enhances attention to a faint nearby sound for a brief moment.";
    if(k.equals("snatch")||k.equals("tweak"))return "Produces a tiny telekinetic tug or quick manipulation of a very small nearby object.";
    if(k.equals("sting"))return "Creates a brief harmless or mildly painful stinging sensation on a nearby creature.";
    if(Arrays.asList("bee","bug","gnats","mouse","spider").contains(k))return "Creates a tiny harmless illusory or cantrip-scale nuisance matching the spell name to distract or startle someone.";
    if(k.equals("bluelight")||k.equals("firefinger")||k.equals("spark"))return "Creates a tiny visible magical light or spark useful for signaling, illumination, or harmless tricks.";
    if(k.equals("catfeet"))return "Muffles the caster's next few steps, making movement slightly quieter for a brief time.";
    if(k.equals("smokepuff"))return "Creates a small puff of harmless smoke at a nearby point.";
    if(k.equals("unlock"))return "Manipulates a very simple nonmagical fastening or latch at cantrip scale; it is not a replacement for the full knock spell.";
    if(Arrays.asList("creak","footfall","groan","horn","moan","rattle","tap","thump","whistle").contains(k))return "Creates the brief harmless sound suggested by the spell name at a chosen nearby point.";

    // Generic but still useful fallbacks; no catalog spell should display a missing-description placeholder.
    if(k.startsWith("detect "))return "Reveals the presence, direction, nature, or condition named by the spell within its limited range and duration.";
    if(k.startsWith("cure "))return "Repairs or removes the injury or affliction named by the spell, subject to the target and resistance rules for that version.";
    if(k.startsWith("protection ")||k.startsWith("protection from ")||k.startsWith("resist ")||k.contains("immunity"))return "Provides magical protection or resistance against the threat named by the spell for a limited duration.";
    if(k.startsWith("summon ")||k.startsWith("conjure ")||k.contains("summoning"))return "Calls or creates the creature, force, or elemental power named by the spell and places it under limited magical control or direction.";
    if(k.startsWith("control "))return "Allows the caster to manipulate or influence the named creature, force, element, weather, or condition within the spell's limits.";
    if(k.startsWith("hold "))return "Restrains or immobilizes the named type of target if it fails the spell's resistance or saving throw.";
    if(k.contains("invisibility"))return "Creates, improves, reveals, or counters invisibility as indicated by the spell name.";
    if(k.startsWith("wall of "))return "Creates a substantial magical barrier made from or resembling the named material or energy.";
    if(k.startsWith("speak with "))return "Allows meaningful communication with the named type of creature, spirit, plant, or entity for the duration.";
    if(k.startsWith("monster summoning")||k.startsWith("animal summoning"))return "Summons one or more creatures appropriate to the spell's level to aid the caster for a limited time.";
    if(k.startsWith("power word "))return "Utters a potent magical word that immediately imposes the named effect on creatures that meet the spell's power limit.";
    if(k.startsWith("bigby's "))return "Creates a powerful disembodied magical hand whose specific combat or control function is described by the spell name.";
    if(k.startsWith("extension"))return "Extends the duration of another eligible spell beyond its normal limit.";

    return "Produces the magical effect indicated by the spell name. This built-in entry is a concise offline quick reference; exact table adjudication may still depend on the specific 1e printing and campaign rulings.";
  }

  private NativeSpellDescriptions(){}
}
