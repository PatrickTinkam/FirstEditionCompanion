package com.patricktinkam.firsteditioncompanion;

import java.util.*;

/** Offline paraphrased rules summaries for non-magical built-in gear/treasure entries. */
public final class EquipmentRules {
  private static final HashMap<String,String> D=new HashMap<>();
  static {
    put("Gem, Ornamental Stone","DMG gem category with a base value of 10 gp per stone before individual value variation. The DM may adjust a particular stone upward or downward for quality, size, and exceptional results when appraised.");
    put("Gem, Semi-Precious Stone","DMG gem category with a base value of 50 gp per stone before individual value variation. Actual worth can shift with quality, size, and the DMG's exceptional-value procedure.");
    put("Gem, Fancy Stone","DMG gem category with a base value of 100 gp per stone before individual value variation. Appraisal can reveal a higher or lower actual value depending on quality and exceptional results.");
    put("Gem, Precious Stone","DMG gem category with a base value of 500 gp per stone before individual value variation. Large or unusually fine stones can increase substantially in value under the DMG procedure.");
    put("Gem, Gem Stone","DMG gem category with a base value of 1,000 gp per stone before individual value variation. Exceptional quality can push the stone into still higher value ranges.");
    put("Gem, Jewel","DMG gem category with a base value of 5,000 gp per stone before individual value variation. These are the highest ordinary gem category and can rise further if exceptional.");
    put("Jewelry, Typical Piece","Jewelry value is generated separately from loose gems. Base worth depends on material and construction, and workmanship/design can increase a piece to the top of its value class or into a higher class. Gem-set jewelry must also account for exceptional stones in the setting.");
    put("Jewelry, Exceptional Piece","An unusually fine piece of jewelry. Under the DMG valuation method, exceptional workmanship can push jewelry to the top of its class or into a higher value class, with any set gems evaluated separately for exceptional quality.");
    put("Art Object","A nonmagical treasure-tracking category rather than a single fixed-stat DMG item. Assign or record the DM/appraised gp value based on material, workmanship, rarity, provenance, size, and portability. It is treasure for valuation/encumbrance purposes, not a magic item, and has no universal combat power or fixed activation mechanic.");
    put("Rare Book or Manuscript","A nonmagical valuable whose worth depends on subject, rarity, age, authorship, condition, language, and who wants it. Record the DM-assigned/appraised value and any campaign information it contains. Unless separately identified as magical, it has no inherent spell effect or charges.");
    put("Precious Metal Ingot","Treat as bulk treasure valued primarily by metal type, purity, and weight. Record its actual weight for encumbrance and its DM/appraised gp value; unlike coin, it may require an assayer, merchant, money changer, or jeweler before full value is realized.");
    put("Elfin Chain Mail","Exceptionally light chain armor of elven manufacture. Its main practical advantage is reduced weight and superior flexibility compared with ordinary chain; class/use restrictions and exact Armor Class handling follow the campaign's 1e source ruling for elfin chain.");
    put("Great Helm","A heavy enclosing helmet providing substantial head protection but with greater weight, restricted hearing/vision concerns, and the usual practical drawbacks of a fully enclosed helm where the DM applies them.");
    put("Small Helm","A lighter protective helmet. It offers head protection with less weight and obstruction than a great helm; any special head-hit or visibility adjudication remains part of the DM's combat ruling rather than a separate universal AC bonus.");
    put("Standard Spell Book","A magic-user's normal spell-book format. Base book cost is 1,000 gp plus 100 gp per spell level entered, and the catalog records 45 lb for a standard volume/set. It is required for normal study and memorization of the spells written within it.");
    put("Travelling Spell Book","A compact spell-book intended for adventuring. Base cost is 500 gp plus 100 gp per spell level entered and the catalog records 6 lb. It trades capacity/durability for portability and is used for memorization like a normal spell book for the spells copied into it.");
  }

  private static void put(String n,String s){D.put(norm(n),s);}
  private static String norm(String s){return s==null?"":s.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+","");}

  public static String describe(GearCatalog.Entry en){
    if(en==null)return "";
    String hit=D.get(norm(en.name));if(hit!=null)return hit;
    String n=en.name.toLowerCase(Locale.US),cat=en.category;
    if(cat.equals("Armor"))return "Nonmagical armor. Use its listed weight and purchase cost together with the applicable 1e Armor Class table, class restrictions, Dexterity/shield modifiers, and encumbrance rules. Armor does not provide a separate damage-reduction roll; it changes the Armor Class target attackers must hit.";
    if(cat.equals("Shield"))return "A shield improves Armor Class when it can be brought to bear. In the PHB handling, shield size limits how many attacks in a round it can protect against, and attacks from directions the shield cannot cover do not receive its benefit.";
    if(cat.equals("Weapon")){
      String x="Use the 1e weapon tables for damage versus small/medium and large targets, length, space required, speed factor, rate of fire/range when applicable, and Armor Class adjustments. A character using a weapon without proficiency takes the class-based non-proficiency attack penalty.";
      if(n.contains("spear"))x+=" A spear set to receive a charge can deal double damage under the charge rules.";
      if(n.contains("lance"))x+=" A lance used from a charging mount can deal double damage under the mounted-charge rules.";
      return x;
    }
    if(cat.equals("Adventuring Gear")){
      if(n.contains("torch"))return "Provides ordinary illumination in roughly a 40-foot radius and burns for about 6 turns (1 hour). It can ignite suitable combustibles and is consumed as it burns.";
      if(n.contains("bullseye"))return "Projects a narrow beam roughly 80 feet long. A pint of lamp oil fuels about 24 turns of use. Hooding/extinguishing and fire risk are handled normally.";
      if(n.contains("lantern"))return "Provides ordinary lantern illumination in roughly a 30-foot radius and burns about 24 turns on one pint of lamp oil.";
      if(n.contains("rope"))return "Ordinary rope used for climbing, hauling, tying, securing, and similar adventuring tasks. Track its length and encumbrance; breakage, knots, anchors, and load limits are adjudicated from circumstances rather than a universal attack statistic.";
      if(n.contains("oil"))return "A flask of ordinary oil can fuel lamps and may be used as an improvised fire hazard when the DM allows. Ignition, splash area, duration, and damage are handled under the campaign's oil/fire rules.";
      return "Standard adventuring equipment. Track its listed cost/weight and apply its real-world function in play; unusual loads, breakage, climbing, fire, leverage, concealment, and similar uses are resolved by the relevant 1e exploration/combat rule and DM adjudication.";
    }
    if(cat.equals("Religious Item"))return "A clerical/religious adjunct used for class abilities, turning, rituals, or spell components as appropriate. Holy/unholy symbols are required for many clerical functions; holy/unholy water is a consumable sacred substance with its own creature-interaction rules.";
    if(cat.equals("Provision"))return "Consumable food or drink used for daily upkeep and travel. Track quantity and days of supply; shortages, spoilage, intoxication, or unusual environmental needs are handled under the campaign's survival and DMG rules.";
    if(cat.equals("Animal"))return "A normal animal used as livestock, mount, messenger, companion, or trained creature as appropriate. Movement, carrying ability, morale, feeding, and combat statistics come from the creature's normal 1e entry rather than from the equipment list itself.";
    if(cat.equals("Tack & Harness"))return "Mount equipment used to ride, control, carry loads, or armor an animal. Apply its listed weight/cost and the mount's carrying/movement limits; barding modifies the mount's protection and encumbrance as appropriate.";
    if(cat.equals("Transport"))return "A mundane vehicle or vessel. Capacity, crew, movement, weather, damage, and combat handling use the appropriate land or waterborne movement rules; cargo counts toward carrying capacity and can materially affect speed.";
    if(cat.equals("Clothing"))return "Ordinary clothing or fabric. It normally has no inherent Armor Class bonus unless a specific class/item rule says otherwise; its main game effects are cost, encumbrance, disguise/social use, weather protection, and material availability.";
    if(cat.equals("Herb"))return "A mundane herb with traditional or campaign uses. It does not automatically reproduce a spell effect merely by being carried; any medicinal, monster-repellent, ritual, or material-component use applies only where a specific 1e rule calls for it.";
    if(cat.equals("Writing & Spellbook"))return "Writing or spell-research equipment. Track sheets, ink, books, cost, and weight; magical writing still requires the appropriate spell/class procedures and copying/research rules rather than becoming usable simply because the physical materials are present.";
    if(cat.equals("Valuable"))return "Nonmagical treasure. Record its appraised/DM-assigned gp value, weight, and notes. Its worth depends on material, quality, rarity, workmanship, condition, and buyer; it has no magic powers unless separately identified as a magic item.";
    return "Built-in nonmagical equipment entry. Track its cost, weight, quantity, and ordinary physical use; any special interaction follows the applicable PHB/DMG/UA rule rather than requiring a linked PDF.";
  }
  private EquipmentRules(){}
}
