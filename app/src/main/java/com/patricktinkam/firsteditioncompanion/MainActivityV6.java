package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.widget.*;
import org.json.*;
import java.util.*;

public class MainActivityV6 extends MainActivityV5 {
  static final int REQ_LINK_PHB=6101,REQ_LINK_UA=6102,REQ_LINK_DMG=6103;
  static final String KEY_PHB_URI="rulebook_phb_uri",KEY_UA_URI="rulebook_ua_uri",KEY_DMG_URI="rulebook_dmg_uri";

  @Override JSONObject makeBackup(){JSONObject o=super.makeBackup();try{o.put("appVersion","0.6.0");}catch(Exception ignored){}return o;}

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.6.0",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  @Override void show(String x){
    if(!"Rules".equals(x)){super.show(x);return;}
    page=x;host.removeAllViews();ScrollView s=new ScrollView(this);LinearLayout c=col();s.addView(c);host.addView(s,new LinearLayout.LayoutParams(-1,-1));rules(c);
  }

  String bookStatus(String key){return S(key,"").isEmpty()?"Not linked":"Linked on this device";}
  void linkBook(int request,String title){
    Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);i.addCategory(Intent.CATEGORY_OPENABLE);i.setType("application/pdf");
    i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
    try{startActivityForResult(Intent.createChooser(i,"Select "+title),request);}catch(Exception e){toast("No PDF picker is available");}
  }
  String bookKey(int request){return request==REQ_LINK_PHB?KEY_PHB_URI:request==REQ_LINK_UA?KEY_UA_URI:request==REQ_LINK_DMG?KEY_DMG_URI:"";}

  @Override protected void onActivityResult(int requestCode,int resultCode,Intent data){
    super.onActivityResult(requestCode,resultCode,data);
    String key=bookKey(requestCode);if(key.isEmpty()||resultCode!=RESULT_OK||data==null||data.getData()==null)return;
    Uri uri=data.getData();
    try{
      int flags=data.getFlags()&Intent.FLAG_GRANT_READ_URI_PERMISSION;
      getContentResolver().takePersistableUriPermission(uri,flags);
    }catch(Exception ignored){}
    ss(key,uri.toString());show("Rules");toast("Rulebook linked");
  }

  void openPdfPage(String key,int printedPage,int offset,String title){
    String raw=S(key,"");if(raw.isEmpty()){toast("Link this rulebook on the Rules tab first");return;}
    try(android.os.ParcelFileDescriptor fd=getContentResolver().openFileDescriptor(Uri.parse(raw),"r");PdfRenderer renderer=new PdfRenderer(fd)){
      int index=Math.max(0,Math.min(renderer.getPageCount()-1,printedPage+offset));
      try(PdfRenderer.Page pg=renderer.openPage(index)){
        int targetW=Math.min(1800,Math.max(900,getResources().getDisplayMetrics().widthPixels*2));
        int targetH=Math.max(1,(int)(targetW*((float)pg.getHeight()/Math.max(1,pg.getWidth()))));
        Bitmap bmp=Bitmap.createBitmap(targetW,targetH,Bitmap.Config.ARGB_8888);bmp.eraseColor(Color.WHITE);
        pg.render(bmp,null,null,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        ImageView image=new ImageView(this);image.setAdjustViewBounds(true);image.setImageBitmap(bmp);
        ScrollView sc=new ScrollView(this);sc.addView(image);
        new AlertDialog.Builder(this).setTitle(title).setView(sc).setPositiveButton("Close",null).show();
      }
    }catch(Exception e){toast("Could not open linked PDF page");}
  }
  void openPHB(int page){openPdfPage(KEY_PHB_URI,page,0,"Players Handbook • p. "+page);}

  Button sourceButton(String label,int page){Button q=b(label);q.setOnClickListener(v->openPHB(page));return q;}

  void rules(LinearLayout c){
    LinearLayout books=card("Your Rulebooks");
    books.addView(t("Link your own PDFs once and the Companion can open the exact cited page without bundling the books into the app.",13,MUT,false));
    books.addView(t("Players Handbook (1978): "+bookStatus(KEY_PHB_URI),13,TXT,true));
    Button phb=b(S(KEY_PHB_URI,"").isEmpty()?"Link Players Handbook PDF":"Relink Players Handbook PDF");phb.setOnClickListener(v->linkBook(REQ_LINK_PHB,"Players Handbook"));books.addView(phb);
    books.addView(t("Unearthed Arcana: "+bookStatus(KEY_UA_URI),13,TXT,true));
    Button ua=b(S(KEY_UA_URI,"").isEmpty()?"Link Unearthed Arcana PDF":"Relink Unearthed Arcana PDF");ua.setOnClickListener(v->linkBook(REQ_LINK_UA,"Unearthed Arcana"));books.addView(ua);
    books.addView(t("Dungeon Masters Guide: "+bookStatus(KEY_DMG_URI),13,TXT,true));
    Button dmg=b(S(KEY_DMG_URI,"").isEmpty()?"Link Dungeon Masters Guide PDF":"Relink Dungeon Masters Guide PDF");dmg.setOnClickListener(v->linkBook(REQ_LINK_DMG,"Dungeon Masters Guide"));books.addView(dmg);
    books.addView(t("Source note: the PHB supplied for this build is the 1978 1e Players Handbook. The supplied Unearthed Arcana file contains later revised/variant mechanics, so v0.6 does not silently replace original 1e rules with that variant. UA catalog entries remain available, but detailed UA mechanics are kept separate pending a verified original 1985 source.",12,MUT,false));
    add(c,books);

    c.addView(t("PHB Rules Reference",20,GOLD,true));
    for(RulesReference.Topic r:RulesReference.all()){
      LinearLayout k=card(r.title+"  •  PHB p. "+r.page);k.addView(t(r.text,14,TXT,false));k.addView(sourceButton("Open PHB p. "+r.page,r.page));add(c,k);
    }
  }

  String spellSourceNote(String source){
    if(source==null)return "";
    if(source.contains("UA")&&!source.contains("PHB"))return "\n\nUA source note: this entry remains source-tagged Unearthed Arcana, but detailed mechanics are not being inferred from the supplied later variant document.";
    if(source.contains("DMG"))return "\n\nDMG source note: detailed mechanics require the Dungeon Masters Guide source and are not guessed here.";
    return "";
  }
  String spellDetails(String caster,String name,int level,String source,String fallback){
    SpellRulesPHB.Rule r=(source!=null&&source.contains("PHB"))?SpellRulesPHB.lookup(caster,name):null;
    if(r==null && source!=null&&source.contains("PHB"))r=SpellRulesPHB.lookupAny(name,level);
    String head=(level==0?"Cantrip":"Level "+level)+" • "+caster+"\nSource: "+source;
    if(r!=null)return head+"\n\n"+r.details();
    return head+"\n\n"+fallback+spellSourceNote(source);
  }

  @Override void catalogDetail(JSONArray tracks,int ti,SpellCatalog.Entry en){
    JSONObject tr=tracks.optJSONObject(ti);String destination=libraryName(tr);
    SpellRulesPHB.Rule rule=en.source.contains("PHB")?SpellRulesPHB.lookup(en.caster,en.name):null;
    AlertDialog.Builder d=new AlertDialog.Builder(this).setTitle(en.name)
      .setMessage(spellDetails(en.caster,en.name,en.level,en.source,en.summary))
      .setPositiveButton("Add to "+destination,(x,w)->addKnown(tracks,ti,en))
      .setNegativeButton("Close",null);
    if(rule!=null)d.setNeutralButton("Open PHB p. "+rule.page,(x,w)->openPHB(rule.page));
    d.show();
  }

  @Override void knownDetail(JSONArray tracks,int ti,JSONObject spell){
    JSONObject tr=tracks.optJSONObject(ti);if(tr==null||spell==null)return;
    String caster=tr.optString("class","Caster"),name=spell.optString("name","Spell"),source=spell.optString("source","Custom / campaign");
    int level=spell.optInt("level",1);SpellRulesPHB.Rule rule=source.contains("PHB")?SpellRulesPHB.lookup(caster,name):null;
    AlertDialog.Builder d=new AlertDialog.Builder(this).setTitle(name)
      .setMessage(spellDetails(caster,name,level,source,spell.optString("summary","No stored summary.")))
      .setPositiveButton("Prepare",(x,w)->prepareKnownDialog(tracks,ti,spell))
      .setNegativeButton("Close",null);
    if(rule!=null)d.setNeutralButton("Open PHB p. "+rule.page,(x,w)->openPHB(rule.page));
    d.show();
  }

  @Override void preparedSpellDetail(JSONObject spell){
    if(spell==null)return;String name=spell.optString("name","Spell"),source=spell.optString("source","Custom / personal entry");int level=spell.optInt("level",1);
    SpellRulesPHB.Rule rule=source.contains("PHB")?SpellRulesPHB.lookupAny(name,level):null;
    String caster=rule==null?"Prepared spell":rule.caster;
    AlertDialog.Builder d=new AlertDialog.Builder(this).setTitle(name)
      .setMessage(spellDetails(caster,name,level,source,spell.optString("summary","No stored summary.")))
      .setPositiveButton("Close",null);
    if(rule!=null)d.setNeutralButton("Open PHB p. "+rule.page,(x,w)->openPHB(rule.page));
    d.show();
  }

  @Override void spells(LinearLayout c){
    LinearLayout ref=card("How 1e Preparation Works");
    ref.addView(t("The PHB treats each memorized copy as a separate use: casting erases that prepared copy from memory. Multiple copies of the same spell may be memorized. v0.6 spell details now show PHB range, duration, area, components, casting time, saving throw, a rules digest, and the cited page where available.",13,TXT,false));
    ref.addView(sourceButton("Open PHB Spell Preparation • p. 40",40));ref.addView(sourceButton("Open PHB Spell Format • p. 43",43));add(c,ref);
    super.spells(c);
  }

  int gearPage(GearCatalog.Entry en){
    if(en==null||!en.source.contains("PHB"))return -1;
    String cat=en.category;
    if(cat.equals("Armor")||cat.equals("Shield"))return 36;
    if(cat.equals("Weapon"))return 37;
    if(cat.equals("Writing & Spellbook"))return 40;
    return 35;
  }
  String gearRules(GearCatalog.Entry en){
    if(en==null)return "";
    String base=en.category+" • "+en.source+(en.cost.isEmpty()?"":" • Cost: "+en.cost)+(en.weight.isEmpty()?"":" • Weight: "+en.weight);
    if(en.source.contains("DMG"))return base+"\n\nDetailed powers/mechanics are not inserted without the Dungeon Masters Guide source PDF. The catalog entry remains usable for character tracking.";
    if(en.source.contains("UA")&&!en.source.contains("PHB"))return base+"\n\nThis is an Unearthed Arcana catalog entry. The supplied UA PDF is a later revised/variant document, so v0.6 does not treat its altered mechanics as canonical 1985 AD&D 1e rules.";
    if(!en.source.contains("PHB"))return base+"\n\nCustom or campaign-source item; use the item's Notes field for your table ruling.";
    String cat=en.category;
    String detail;
    if(cat.equals("Armor")||cat.equals("Shield")){
      detail="Armor and shields contribute directly to Armor Class. A shield improves AC by 1 when it can be brought to bear: small shields cover one attack per melee round, normal shields two, and large shields three. Attacks from the right flank or rear negate the shield. Magical armor/shield pluses each improve AC by 1; magical armor is ignored for movement encumbrance.";
    }else if(cat.equals("Weapon")){
      detail="PHB weapon use is governed by more than the damage die. The tables also give damage versus small/medium and large opponents, weapon length, space required, speed factor, and adjustments against Armor Class. A character without proficiency in the weapon suffers the class-specific non-proficiency attack penalty.";
      String n=en.name.toLowerCase(Locale.US);
      if(n.contains("spear"))detail+=" A spear set to receive a charge deals twice the indicated damage.";
      if(n.contains("lance"))detail+=" A lance used by an attacker on a charging mount deals twice the indicated damage.";
    }else if(en.name.equalsIgnoreCase("Torch")){
      detail="A torch normally illuminates a 40-foot radius and burns for 6 turns (1 hour).";
    }else if(en.name.toLowerCase(Locale.US).contains("lantern")&&en.name.toLowerCase(Locale.US).contains("bull")){
      detail="A bullseye lantern projects an approximately 80-foot narrow beam, about 1 inch wide in game scale, and burns for 24 turns on one pint of fine oil.";
    }else if(en.name.toLowerCase(Locale.US).contains("lantern")){
      detail="A normal/hooded lantern illuminates about a 30-foot radius and burns for 24 turns on one pint of fine oil.";
    }else if(cat.equals("Religious Item")){
      detail="Religious symbols and similar standard clerical adjuncts matter for class abilities and spell components. The PHB notes that standard religious items used as components are notable exceptions to the normal rule that material components are consumed.";
    }else{
      detail="This item appears in the PHB equipment lists. Its cost is a baseline adventuring-area price; availability and merchant pricing can vary by campaign. Weight and bulk both matter for encumbrance even when an item's weight alone seems modest.";
    }
    return base+"\n\n"+detail+"\n\nSource: PHB p. "+gearPage(en)+(cat.equals("Weapon")?" (weapon tables continue on p. 38)":"");
  }
  GearCatalog.Entry entryFrom(JSONObject o){
    if(o==null)return null;
    return new GearCatalog.Entry(o.optString("name","Item"),o.optString("category","Item"),o.optString("source","Custom"),o.optString("cost",""),o.optString("weight",""),o.optBoolean("magic"));
  }
  void gearRulesDialog(GearCatalog.Entry en){
    int pg=gearPage(en);AlertDialog.Builder d=new AlertDialog.Builder(this).setTitle(en.name).setMessage(gearRules(en)).setPositiveButton("Close",null);
    if(pg>0)d.setNeutralButton("Open PHB p. "+pg,(x,w)->openPHB(pg));d.show();
  }

  @Override void addCatalogEntry(JSONArray items,GearCatalog.Entry en,String requestedBucket,boolean equipDefault){
    int pg=gearPage(en);
    AlertDialog.Builder d=new AlertDialog.Builder(this).setTitle(en.name).setMessage(gearRules(en))
      .setPositiveButton("Add…",(x,w)->super.addCatalogEntry(items,en,requestedBucket,equipDefault))
      .setNegativeButton("Cancel",null);
    if(pg>0)d.setNeutralButton("Open PHB p. "+pg,(x,w)->openPHB(pg));d.show();
  }

  @Override void itemRow(LinearLayout card,JSONArray a,int index){
    JSONObject o=a.optJSONObject(index);if(o==null)return;LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);
    TextView n=t(itemLine(o),14,o.optBoolean("magic")?GOLD:TXT,true);n.setOnClickListener(v->editItem(a,index));
    LinearLayout text=new LinearLayout(this);text.setOrientation(LinearLayout.VERTICAL);text.addView(n);text.addView(t(o.optString("category","Item")+" • "+o.optString("source","Custom"),11,MUT,false));
    row.addView(text,new LinearLayout.LayoutParams(0,-2,1));Button rules=b("Rules");rules.setOnClickListener(v->gearRulesDialog(entryFrom(o)));row.addView(rules);Button edit=b("Edit");edit.setOnClickListener(v->editItem(a,index));row.addView(edit);card.addView(row);
  }

  @Override void gear(LinearLayout c){
    super.gear(c);
    LinearLayout ref=card("PHB Gear & Money Mechanics");
    ref.addView(t("The structured inventory now distinguishes the catalog record from its rules reference. PHB items expose source-grounded handling; DMG-only magic items remain explicitly pending a DMG source instead of receiving guessed powers.",13,TXT,false));
    ref.addView(t("Currency: 10 cp = 1 sp; 20 sp = 1 gp; 2 ep = 1 gp; 1 pp = 5 gp. Money changing commonly costs about 3%. Coins are treated as roughly equal in weight.",13,MUT,false));
    ref.addView(sourceButton("Open PHB Money • p. 35",35));ref.addView(sourceButton("Open PHB Armor • p. 36",36));ref.addView(sourceButton("Open PHB Weapons • p. 37",37));ref.addView(sourceButton("Open PHB Encumbrance • p. 101",101));add(c,ref);
  }

  void referenceCard(LinearLayout c,String title,String body,int page){
    LinearLayout k=card(title+"  •  PHB p. "+page);k.addView(t(body,13,TXT,false));k.addView(sourceButton("Open PHB p. "+page,page));add(c,k);
  }
  @Override void combat(LinearLayout c){
    super.combat(c);
    referenceCard(c,"Saving Throws & Armor Class","Many magical/breath attacks use saving throws; ordinary weapon attacks normally use a to-hit roll. AC is a composite of armor, shield, magic, Dexterity, and circumstances. Shield and Dexterity benefits can be lost when the attack direction or situation prevents their use.",105);
    referenceCard(c,"Spellcasting Under Attack","Casting takes time. If required verbal, somatic, or material actions are interrupted, the spell cannot be completed and is lost. Being struck, grabbed, restrained, silenced, or otherwise disrupted can therefore matter before the casting finishes.",100);
    referenceCard(c,"Damage & Healing","The PHB records damage in hit points. Its base falling guideline is 1d6 per 10 feet up to 20d6. Natural rest restores 1 hit point per day, later increasing under the PHB's long-resting rule; magical healing can restore damage but not raise the character over normal maximum HP.",105);
  }
}
