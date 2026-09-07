package com.patricktinkam.firsteditioncompanion;
import java.util.*;
final class GearDataEquipment {
  static void fill(ArrayList<GearCatalog.Entry> out){
    add(out,"PHB","Armor",false,
      "Banded Mail~90 gp~35 lb|Chain Mail~75 gp~30 lb|Leather Armor~5 gp~15 lb|Padded Armor~4 gp~10 lb|Plate Mail~400 gp~45 lb|Ring Mail~30 gp~25 lb|Scale Mail~45 gp~40 lb|Splint Mail~80 gp~40 lb|Studded Leather~15 gp~20 lb");
    add(out,"UA","Armor",false,
      "Bronze Plate Mail~100 gp~45 lb|Field Plate~2,000 gp~55 lb|Full Plate~4,000 gp~65 lb");
    add(out,"DMG","Armor",false,"Elfin Chain Mail~~15 lb|Great Helm~15 gp~10 lb|Small Helm~10 gp~4.5 lb");
    add(out,"PHB","Shield",false,"Buckler~5 gp~3 lb|Large Shield~15 gp~10 lb|Medium Shield~12 gp~8 lb|Small Shield~10 gp~5 lb|Small Wooden Shield~1 gp~3 lb");

    add(out,"PHB","Weapon",false,
      "Battle Axe~5 gp~7.5 lb|Hand/Throwing Axe~1 gp~5 lb|Bardiche~7 gp~12.5 lb|Bec de Corbin~6 gp~10 lb|Bill-Guisarme~6 gp~15 lb|Club~~3 lb|Heavy Crossbow~20 gp~8 lb|Light Crossbow~12 gp~5 lb|Dagger and Scabbard~2 gp~1 lb|Dart~5 sp~0.5 lb|Fauchard~3 gp~6 lb|Fauchard-Fork~8 gp~8 lb|Footman's Flail~3 gp~15 lb|Horseman's Flail~8 gp~3.5 lb|Military Fork~4 gp~7.5 lb|Glaive~6 gp~7.5 lb|Glaive-Guisarme~10 gp~10 lb|Guisarme~5 gp~8 lb|Guisarme-Voulge~7 gp~15 lb|Halberd~9 gp~17.5 lb|Lucern Hammer~7 gp~15 lb|Hammer~1 gp~5 lb|Javelin~10 sp~2 lb|Light Horse Lance~6 gp~5 lb|Medium Horse Lance~6 gp~10 lb|Heavy Horse Lance~6 gp~15 lb|Footman's Mace~8 gp~10 lb|Horseman's Mace~4 gp~5 lb|Morning Star~5 gp~12.5 lb|Partisan~10 gp~8 lb|Footman's Military Pick~8 gp~6 lb|Horseman's Military Pick~5 gp~4 lb|Awl Pike~3 gp~8 lb|Ranseur~4 gp~5 lb|Scimitar~15 gp~4 lb|Spear~1 gp~4-6 lb|Spetum~3 gp~5 lb|Quarterstaff~~5 lb|Bastard Sword and Scabbard~25 gp~10 lb|Broad Sword and Scabbard~10 gp~7.5 lb|Long Sword and Scabbard~15 gp~6 lb|Short Sword and Scabbard~8 gp~3.5 lb|Two-Handed Sword~30 gp~25 lb|Trident~4 gp~5 lb|Voulge~2 gp~12.5 lb|Composite Short Bow~75 gp~5 lb|Composite Long Bow~100 gp~8 lb|Long Bow~60 gp~5 lb|Short Bow~15 gp~5 lb|Normal Arrow~2 sp~0.2 lb|Quiver (12 arrows)~8 sp~3 lb|Quiver (20 arrows)~12 sp~3 lb|Light Crossbow Bolt~1 sp~0.1 lb|Heavy Crossbow Bolts (20)~2 gp~4 lb|Sling and 12 Bullets~15 sp~2.4 lb|Sling Bullets (20)~10 sp~4 lb|Sling Stone~~0.1 lb");
    add(out,"UA","Weapon",false,
      "Aklys~2 gp~3.5 lb|Atlatl~1 gp~3 lb|Blowgun~20 gp~1.5 lb|Blowgun Needle~1 sp~|Caltrop~2 sp~0.2-0.4 lb|Hand Crossbow~300 gp~|Garrote~1 ep~0.1 lb|Harpoon~5 gp~5-6 lb|Hook Fauchard~6 gp~8 lb|Lasso~5 sp~2 lb|Man Catcher~25 gp~8 lb|Sap~1 gp~1 lb|Spiked Buckler~10 gp~3.5 lb|Staff Sling~2 gp~|Falchion and Scabbard~10 gp~6-8 lb|Khopesh~10 gp~7.5 lb|Whip~3 gp~2.5-3.5 lb|Bo Stick~~1.5 lb|Jo Stick~~4 lb");

    add(out,"PHB","Clothing",false,
      "Belt~~~|Boots, High Hard~~~|Boots, High Soft~~~|Boots, Low Hard~~~|Boots, Low Soft~~~|Cap~~~|Cape~~~|Cloak~~~|Dress~~~|Gloves, Cloth~~~|Gloves, Leather~~~|Girdle, Broad~~~|Girdle, Normal~~~|Hat~~~|Robe~~~|Shirt/Blouse~~~|Trousers/Skirt~~~|Cloth, Cotton~~~|Cloth, Linen~~~|Cloth, Satin~~~|Cloth, Silk~~~|Cloth, Velvet~~~|Cloth, Wool~~~|Sewing Needle~~~|Scissors~~~|Thread, Spool~~~");
    add(out,"PHB","Herb",false,"Belladonna, Sprig~~~|Garlic, Bud~~~|Wolvesbane, Sprig~~~");

    add(out,"PHB","Animal",false,
      "Chicken~~~|Cow~~~|Donkey~~~|Goat~~~|Hawk, Large~~~|Hawk, Small~~~|Horse, Draft~~~|Horse, Heavy War~~~|Horse, Light War~~~|Horse, Medium War~~~|Horse, Riding~~~|Mule~~~|Ox~~~|Pigeon~~~|Piglet~~~|Pig~~~|Pony~~~|Sheep~~~|Songbird~~~");

    add(out,"PHB","Adventuring Gear",false,
      "Backpack, Leather~~~|Box, Iron, Large~~~|Box, Iron, Small~~~|Candle, Tallow~~~|Candle, Wax~~~|Map/Scroll Case, Bone~~~|Map/Scroll Case, Leather~~~|Chest, Wooden, Large~~~|Chest, Wooden, Small~~~|Lantern, Bullseye~~~|Lantern, Hooded~~~|Mirror, Large Metal~~~|Mirror, Small Silver~~~|Oil, Flask~~~|Pole, 10-Foot~~~|Pouch, Belt, Large~~~|Pouch, Belt, Small~~~|Quiver, 20 Bolts~~~|Quiver, 40 Bolts~~~|Sack, Large~~~|Sack, Small~~~|Waterskin/Wineskin~~~|Spike, Iron~~~|Torch~~~|Air Bladder~~~|Basket~~~|Crowbar~~~|Grappling Hook~~~|Pickaxe~~~|Chalk~~~|Pulley~~~|Shovel~~~|Whistle~~~|Thieves' Picks and Tools~~~|Rope, 50 Feet~~~|Flint and Steel~~~|Tent~~~|Bedroll~~~");

    add(out,"PHB","Provision",false,
      "Ale, Pint~~~|Beer, Small Pint~~~|Meal, Merchant's~~~|Meal, Rich~~~|Horse Meal, One Day~~~|Mead, Pint~~~|Iron Rations, One Week~~~|Standard Rations, One Week~~~|Wine, Good, Pint~~~|Wine, Watered, Pint~~~|Water, Daily Ration~~~");

    add(out,"PHB","Religious Item",false,
      "Prayer Beads~~~|Incense~~~|Holy/Unholy Symbol, Iron~~~|Holy/Unholy Symbol, Silver~~~|Holy/Unholy Symbol, Wooden~~~|Holy/Unholy Water, Vial~~~");

    add(out,"PHB","Tack & Harness",false,
      "Bit and Bridle~~~|Harness~~~|Saddle~~~|Saddle Bags, Large~~~|Saddle Bags, Small~~~|Saddle Blanket~~~|Barding, Chain~~~|Barding, Leather~~~|Barding, Plate~~~");

    add(out,"PHB","Transport",false,
      "Cart~~~|Wagon~~~|Barge, Small~~~|Raft, Small~~~|Boat, Small~~~|Long Boat~~~|Galley, Large~~~|Galley, Small~~~|Merchant Ship, Large~~~|Merchant Ship, Small~~~|Warship~~~");

    add(out,"DMG","Valuable",false,
      "Gem, Ornamental Stone~~~|Gem, Semi-Precious Stone~~~|Gem, Fancy Stone~~~|Gem, Precious Stone~~~|Gem, Gem Stone~~~|Gem, Jewel~~~|Jewelry, Typical Piece~~~|Jewelry, Exceptional Piece~~~|Art Object~~~|Rare Book or Manuscript~~~|Precious Metal Ingot~~~");

    add(out,"PHB","Writing & Spellbook",false,
      "Papyrus, Sheet~2 gp and up~|Parchment, Sheet~4 gp and up~|Vellum, Sheet~8 gp and up~|Standard Spell Book~1,000 gp + 100 gp/spell level~45 lb|Travelling Spell Book~500 gp + 100 gp/spell level~6 lb|Ink~~~|Quill Pen~~~");
  }

  private static void add(ArrayList<GearCatalog.Entry> out,String source,String category,boolean magic,String rows){
    for(String row:rows.split("\\|")){
      String[] p=row.split("~",-1);String name=p.length>0?p[0].trim():"";if(name.isEmpty())continue;
      String cost=p.length>1?p[1].trim():"",weight=p.length>2?p[2].trim():"";
      out.add(new GearCatalog.Entry(name,category,source,cost,weight,magic));
    }
  }
  private GearDataEquipment(){}
}
