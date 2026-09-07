package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.content.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import org.json.*;
import java.util.*;

public class MainActivityV5 extends MainActivityV4 {
  static final String KEY_ITEMS="gear_items",KEY_CURRENCY="gear_currency";
  static final String[] SLOTS={"Other / Worn","Main Hand","Off Hand / Shield","Armor","Head","Neck","Cloak / Shoulders","Hands","Waist","Feet","Ring - Left","Ring - Right","Missile Weapon","Ammunition"};

  @Override JSONObject snapshot(){
    JSONObject o=super.snapshot();
    try{o.put(KEY_ITEMS,S(KEY_ITEMS,"[]"));o.put(KEY_CURRENCY,S(KEY_CURRENCY,"{}"));}catch(Exception ignored){}
    return o;
  }

  @Override void applySnapshot(JSONObject o){
    super.applySnapshot(o);
    ss(KEY_ITEMS,o==null?"[]":o.optString(KEY_ITEMS,"[]"));
    ss(KEY_CURRENCY,o==null?"{}":o.optString(KEY_CURRENCY,"{}"));
  }

  @Override JSONObject makeBackup(){JSONObject o=super.makeBackup();try{o.put("appVersion","0.5.0");}catch(Exception ignored){}return o;}

  JSONArray inventory(){try{return new JSONArray(S(KEY_ITEMS,"[]"));}catch(Exception e){return new JSONArray();}}
  void saveInventory(JSONArray a){ss(KEY_ITEMS,a.toString());}
  JSONObject currency(){try{return new JSONObject(S(KEY_CURRENCY,"{}"));}catch(Exception e){return new JSONObject();}}
  void saveCurrency(JSONObject c){ss(KEY_CURRENCY,c.toString());}

  JSONObject itemFrom(GearCatalog.Entry en,String bucket,boolean equipped,int qty,String slot,int charges,String notes)throws Exception{
    JSONObject o=new JSONObject();o.put("name",en.name);o.put("category",en.category);o.put("source",en.source);o.put("cost",en.cost);o.put("weight",en.weight);o.put("summary",en.summary);o.put("magic",en.magic);o.put("qty",Math.max(1,qty));o.put("bucket",bucket);o.put("equipped",equipped);o.put("slot",slot==null?"":slot);o.put("charges",charges);o.put("notes",notes==null?"":notes);return o;
  }

  String bucketFor(GearCatalog.Entry en,String requested){
    if(requested!=null&&!requested.isEmpty())return requested;
    if(en.magic)return "Magic";
    if(en.category.equals("Animal")||en.category.equals("Tack & Harness")||en.category.equals("Transport"))return "Property";
    if(en.category.equals("Valuable"))return "Valuable";
    return "Carried";
  }

  Spinner spinner(String[] values,String selected){Spinner s=new Spinner(this);ArrayAdapter<String> a=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,values);s.setAdapter(a);for(int i=0;i<values.length;i++)if(values[i].equalsIgnoreCase(selected)){s.setSelection(i);break;}return s;}

  void addCatalogEntry(JSONArray items,GearCatalog.Entry en,String requestedBucket,boolean equipDefault){
    String bucket=bucketFor(en,requestedBucket);
    LinearLayout box=card("Add "+en.name);EditText qty=e("1","Quantity",true);box.addView(t(en.category+" • "+en.source+(en.cost.isEmpty()?"":" • "+en.cost)+(en.weight.isEmpty()?"":" • "+en.weight),13,MUT,false));box.addView(t("Quantity",12,MUT,true));box.addView(qty);
    CheckBox equipped=new CheckBox(this);equipped.setText("Equipped / worn now");equipped.setTextColor(TXT);equipped.setChecked(equipDefault);box.addView(equipped);
    box.addView(t("Equipment slot",12,MUT,true));Spinner slot=spinner(SLOTS,"Other / Worn");box.addView(slot);
    EditText charges=e("","Charges / uses (optional)",true);if(en.magic){box.addView(t("Charges / uses",12,MUT,true));box.addView(charges);}
    EditText notes=e("","Item notes",false);box.addView(t("Notes",12,MUT,true));box.addView(notes);
    new AlertDialog.Builder(this).setTitle(en.name).setView(box).setPositiveButton("Add",(d,w)->{try{int q=Math.max(1,Integer.parseInt(qty.getText().toString().trim()));int ch=-1;if(en.magic&&!charges.getText().toString().trim().isEmpty())ch=Math.max(0,Integer.parseInt(charges.getText().toString().trim()));items.put(itemFrom(en,bucket,equipped.isChecked(),q,(String)slot.getSelectedItem(),ch,notes.getText().toString()));saveInventory(items);show("Gear");toast("Added "+en.name);}catch(Exception ex){toast("Check quantity / charges");}}).setNegativeButton("Cancel",null).show();
  }

  void catalogDialog(String title,ArrayList<GearCatalog.Entry> source,String bucket,boolean equipDefault){
    GearCatalog.sort(source);LinearLayout box=new LinearLayout(this);box.setOrientation(LinearLayout.VERTICAL);box.setPadding(dp(10),dp(6),dp(10),dp(6));box.addView(t("Search by item name, category, source book, cost, or weight.",13,MUT,false));EditText search=e("","Search catalog",false);box.addView(search);ListView list=new ListView(this);ArrayList<GearCatalog.Entry> shown=new ArrayList<>();ArrayList<String> labels=new ArrayList<>();ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,labels);list.setAdapter(adapter);box.addView(list,new LinearLayout.LayoutParams(-1,dp(520)));
    Runnable filter=()->{String q=search.getText().toString().trim().toLowerCase(Locale.US);shown.clear();labels.clear();for(GearCatalog.Entry en:source){String label=en.label();if(q.isEmpty()||label.toLowerCase(Locale.US).contains(q)){shown.add(en);labels.add(label);}}adapter.notifyDataSetChanged();};search.addTextChangedListener(new W(){public void afterTextChanged(Editable z){filter.run();}});filter.run();
    AlertDialog dlg=new AlertDialog.Builder(this).setTitle(title).setView(box).setNegativeButton("Close",null).create();list.setOnItemClickListener((p,v,pos,id)->{if(pos>=0&&pos<shown.size()){GearCatalog.Entry en=shown.get(pos);dlg.dismiss();addCatalogEntry(inventory(),en,bucket,equipDefault);}});dlg.show();
  }

  void customItemDialog(String bucket,boolean magic,boolean equipDefault){
    LinearLayout x=card("Custom Item");EditText name=e("","Item name",false),cat=e(magic?"Magic Item":"Custom","Category",false),qty=e("1","Quantity",true),cost=e("","Cost / value",false),weight=e("","Weight",false),notes=e("","Notes",false);x.addView(name);x.addView(cat);x.addView(qty);x.addView(cost);x.addView(weight);x.addView(notes);
    new AlertDialog.Builder(this).setTitle("Add Custom Item").setView(x).setPositiveButton("Add",(d,w)->{try{String n=name.getText().toString().trim();if(n.isEmpty()){toast("Enter an item name");return;}int q=Math.max(1,Integer.parseInt(qty.getText().toString().trim()));GearCatalog.Entry en=new GearCatalog.Entry(n,cat.getText().toString().trim().isEmpty()?"Custom":cat.getText().toString().trim(),"Custom / campaign",cost.getText().toString(),weight.getText().toString(),magic);JSONArray a=inventory();JSONObject o=itemFrom(en,bucketFor(en,bucket),equipDefault,q,"Other / Worn",-1,notes.getText().toString());a.put(o);saveInventory(a);show("Gear");}catch(Exception ex){toast("Check item details");}}).setNegativeButton("Cancel",null).show();
  }

  String itemLine(JSONObject o){int qty=Math.max(1,o.optInt("qty",1));String line=(qty>1?qty+"×  ":"")+o.optString("name","Item");if(o.optBoolean("equipped"))line+="  ["+o.optString("slot","Equipped")+"]";int ch=o.optInt("charges",-1);if(ch>=0)line+="  •  "+ch+" charge"+(ch==1?"":"s");return line;}

  void editItem(JSONArray a,int index){
    JSONObject o=a.optJSONObject(index);if(o==null)return;LinearLayout box=card(o.optString("name","Item"));box.addView(t(o.optString("category","Item")+" • "+o.optString("source","Custom"),13,MUT,false));String cost=o.optString("cost","");String weight=o.optString("weight","");if(!cost.isEmpty()||!weight.isEmpty())box.addView(t((cost.isEmpty()?"":cost)+(cost.isEmpty()||weight.isEmpty()?"":" • ")+(weight.isEmpty()?"":weight),13,MUT,false));box.addView(t(o.optString("summary",""),12,MUT,false));
    EditText qty=e(String.valueOf(Math.max(1,o.optInt("qty",1))),"Quantity",true);box.addView(t("Quantity",12,MUT,true));box.addView(qty);CheckBox equipped=new CheckBox(this);equipped.setText("Equipped / worn");equipped.setTextColor(TXT);equipped.setChecked(o.optBoolean("equipped"));box.addView(equipped);box.addView(t("Equipment slot",12,MUT,true));Spinner slot=spinner(SLOTS,o.optString("slot","Other / Worn"));box.addView(slot);
    EditText charges=e(o.optInt("charges",-1)>=0?String.valueOf(o.optInt("charges",0)):"","Charges / uses",true);if(o.optBoolean("magic")){box.addView(t("Charges / uses (blank = not tracked)",12,MUT,true));box.addView(charges);}EditText notes=e(o.optString("notes",""),"Notes",false);box.addView(t("Notes",12,MUT,true));box.addView(notes);
    new AlertDialog.Builder(this).setTitle(o.optString("name","Item")).setView(box).setPositiveButton("Save",(d,w)->{try{o.put("qty",Math.max(1,Integer.parseInt(qty.getText().toString().trim())));o.put("equipped",equipped.isChecked());o.put("slot",String.valueOf(slot.getSelectedItem()));if(o.optBoolean("magic"))o.put("charges",charges.getText().toString().trim().isEmpty()?-1:Math.max(0,Integer.parseInt(charges.getText().toString().trim())));o.put("notes",notes.getText().toString());saveInventory(a);show("Gear");}catch(Exception ex){toast("Check item details");}}).setNeutralButton("Remove",(d,w)->new AlertDialog.Builder(this).setTitle("Remove Item").setMessage("Remove "+o.optString("name","this item")+" from this character?").setPositiveButton("Remove",(dd,ww)->{a.remove(index);saveInventory(a);show("Gear");}).setNegativeButton("Cancel",null).show()).setNegativeButton("Cancel",null).show();
  }

  void itemRow(LinearLayout card,JSONArray a,int index){JSONObject o=a.optJSONObject(index);if(o==null)return;LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);TextView n=t(itemLine(o),14,o.optBoolean("magic")?GOLD:TXT,true);n.setOnClickListener(v->editItem(a,index));LinearLayout text=new LinearLayout(this);text.setOrientation(LinearLayout.VERTICAL);text.addView(n);text.addView(t(o.optString("category","Item")+" • "+o.optString("source","Custom"),11,MUT,false));row.addView(text,new LinearLayout.LayoutParams(0,-2,1));Button edit=b("Edit");edit.setOnClickListener(v->editItem(a,index));row.addView(edit);card.addView(row);}

  int count(JSONArray a,String section){int n=0;for(int i=0;i<a.length();i++){JSONObject o=a.optJSONObject(i);if(matches(o,section))n++;}return n;}
  boolean matches(JSONObject o,String section){if(o==null)return false;String bucket=o.optString("bucket",o.optBoolean("magic")?"Magic":"Carried");if(section.equals("Equipped"))return o.optBoolean("equipped");if(section.equals("Magic"))return o.optBoolean("magic");if(section.equals("Valuable"))return bucket.equals("Valuable");if(section.equals("Property"))return bucket.equals("Property");return bucket.equals("Carried")&&!o.optBoolean("magic")&&!o.optBoolean("equipped");}

  LinearLayout inventorySection(JSONArray a,String title,String section,String empty){LinearLayout c=card(title+"  •  "+count(a,section));boolean any=false;for(int i=0;i<a.length();i++)if(matches(a.optJSONObject(i),section)){itemRow(c,a,i);any=true;}if(!any)c.addView(t(empty,13,MUT,false));return c;}

  void currencyField(LinearLayout c,JSONObject money,String label,String key){LinearLayout r=new LinearLayout(this);r.setGravity(Gravity.CENTER_VERTICAL);r.addView(t(label,14,MUT,true),new LinearLayout.LayoutParams(dp(120),-2));EditText q=e(String.valueOf(Math.max(0,money.optLong(key,0))),label,true);q.addTextChangedListener(new W(){public void afterTextChanged(Editable z){try{String s=z.toString().trim();money.put(key,s.isEmpty()?0:Math.max(0,Long.parseLong(s)));saveCurrency(money);}catch(Exception ignored){}}});r.addView(q,new LinearLayout.LayoutParams(0,-2,1));c.addView(r);}
  String gpEquivalent(JSONObject m){double gp=m.optLong("cp",0)/200.0+m.optLong("sp",0)/20.0+m.optLong("ep",0)/2.0+m.optLong("gp",0)+m.optLong("pp",0)*5.0;return String.format(Locale.US,"%.2f gp",gp);}

  @Override void gear(LinearLayout c){
    JSONArray a=inventory();c.addView(t("Structured inventory for AD&D 1e. Tap any recorded item to edit quantity, equipment state, slot, charges, or notes. Catalog entries carry PHB, DMG, or Unearthed Arcana source labels.",13,MUT,false));

    LinearLayout equipped=inventorySection(a,"Equipped","Equipped","Nothing equipped yet.");LinearLayout eqBtns=new LinearLayout(this);Button addEq=b("Search Equipment");addEq.setOnClickListener(v->catalogDialog("Equip from 1e Catalog",GearCatalog.all(),"",true));eqBtns.addView(addEq,new LinearLayout.LayoutParams(0,dp(48),1));Button customEq=b("+ Custom");customEq.setOnClickListener(v->customItemDialog("Carried",false,true));eqBtns.addView(customEq,new LinearLayout.LayoutParams(0,dp(48),1));equipped.addView(eqBtns);add(c,equipped);

    LinearLayout carried=inventorySection(a,"Carried Gear","Carried","No unequipped carried gear recorded.");LinearLayout carryBtns=new LinearLayout(this);Button addGear=b("Search Gear Catalog");addGear.setOnClickListener(v->catalogDialog("Core Equipment Catalog",GearCatalog.normal(),"Carried",false));carryBtns.addView(addGear,new LinearLayout.LayoutParams(0,dp(48),1));Button custom=b("+ Custom");custom.setOnClickListener(v->customItemDialog("Carried",false,false));carryBtns.addView(custom,new LinearLayout.LayoutParams(0,dp(48),1));carried.addView(carryBtns);add(c,carried);

    JSONObject money=currency();LinearLayout cur=card("Currency");cur.addView(t("PHB monetary system",13,GOLD,true));currencyField(cur,money,"Copper Pieces (cp)","cp");currencyField(cur,money,"Silver Pieces (sp)","sp");currencyField(cur,money,"Electrum Pieces (ep)","ep");currencyField(cur,money,"Gold Pieces (gp)","gp");currencyField(cur,money,"Platinum Pieces (pp)","pp");cur.addView(t("200 cp = 20 sp = 2 ep = 1 gp = 1/5 pp",13,MUT,false));TextView total=t("Total value: "+gpEquivalent(money),17,GOLD,true);cur.addView(total);Button refresh=b("Refresh Total");refresh.setOnClickListener(v->show("Gear"));cur.addView(refresh);add(c,cur);

    LinearLayout magic=inventorySection(a,"Magic Items","Magic","No magic items recorded.");LinearLayout magicBtns=new LinearLayout(this);Button addMagic=b("Search Magic Catalog");addMagic.setOnClickListener(v->catalogDialog("DMG + UA Magic Items",GearCatalog.magic(),"Magic",false));magicBtns.addView(addMagic,new LinearLayout.LayoutParams(0,dp(48),1));Button customMagic=b("+ Custom Magic");customMagic.setOnClickListener(v->customItemDialog("Magic",true,false));magicBtns.addView(customMagic,new LinearLayout.LayoutParams(0,dp(48),1));magic.addView(magicBtns);add(c,magic);

    LinearLayout valuables=inventorySection(a,"Valuables & Treasure","Valuable","No gems, jewelry, art objects, or other valuables recorded.");LinearLayout valBtns=new LinearLayout(this);Button addVal=b("Search Valuables");addVal.setOnClickListener(v->catalogDialog("Valuables",GearCatalog.category("Valuable"),"Valuable",false));valBtns.addView(addVal,new LinearLayout.LayoutParams(0,dp(48),1));Button customVal=b("+ Custom Valuable");customVal.setOnClickListener(v->customItemDialog("Valuable",false,false));valBtns.addView(customVal,new LinearLayout.LayoutParams(0,dp(48),1));valuables.addView(valBtns);add(c,valuables);

    LinearLayout property=inventorySection(a,"Mounts, Tack & Transport","Property","No mounts, tack, carts, ships, or other transport recorded.");LinearLayout propBtns=new LinearLayout(this);Button addProp=b("Search Property");addProp.setOnClickListener(v->catalogDialog("Mounts, Tack & Transport",GearCatalog.categories("Animal","Tack & Harness","Transport"),"Property",false));propBtns.addView(addProp,new LinearLayout.LayoutParams(0,dp(48),1));Button customProp=b("+ Custom");customProp.setOnClickListener(v->customItemDialog("Property",false,false));propBtns.addView(customProp,new LinearLayout.LayoutParams(0,dp(48),1));property.addView(propBtns);add(c,property);

    LinearLayout legacy=card("Legacy Gear Notes");legacy.addView(t("Your original free-form Gear field is preserved here so upgrading does not discard anything you previously recorded. Move items into the structured sections whenever convenient.",12,MUT,false));legacy.addView(multi("gear","Legacy equipment, treasure, coins, charges, encumbrance..."),new LinearLayout.LayoutParams(-1,dp(180)));add(c,legacy);
    LinearLayout prof=card("Proficiencies & Languages");prof.addView(multi("prof","Weapon/nonweapon proficiencies, languages, class abilities..."),new LinearLayout.LayoutParams(-1,dp(220)));add(c,prof);
  }
}
