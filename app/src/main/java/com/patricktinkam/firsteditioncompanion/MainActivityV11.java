package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import org.json.*;
import java.util.*;

/** v0.8.1: class/subclass selector and detailed class reference. */
public class MainActivityV11 extends MainActivityV10 {
  ClassData.Entry selectedClass;
  int selectedClassLevel=1;

  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.8.1");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.8.1",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  ArrayAdapter<String> classAdapter(ArrayList<String> labels){
    return new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,labels){
      @Override public View getView(int position,View convertView,android.view.ViewGroup parent){
        TextView v=(TextView)super.getView(position,convertView,parent);v.setTextColor(TXT);v.setTextSize(15);v.setPadding(dp(10),dp(10),dp(10),dp(10));return v;
      }
      @Override public View getDropDownView(int position,View convertView,android.view.ViewGroup parent){
        TextView v=(TextView)super.getDropDownView(position,convertView,parent);v.setTextColor(TXT);v.setBackgroundColor(PANEL);v.setTextSize(15);v.setPadding(dp(12),dp(14),dp(12),dp(14));return v;
      }
    };
  }

  void classDetails(ClassData.Entry e){
    if(e==null)return;
    ScrollView sc=new ScrollView(this);TextView body=t(e.fullDetails(),14,TXT,false);body.setPadding(dp(18),dp(10),dp(18),dp(18));sc.addView(body);
    new AlertDialog.Builder(this).setTitle(e.name).setView(sc).setPositiveButton("Close",null).show();
  }

  void multiclassRules(){
    String m="PHB MULTI-CLASSING\n\nNon-human and part-human characters may use only the class combinations permitted to their race. Experience is divided among the classes, and hit points gained at a new level are based on the class hit dice combined and divided by the number of classes. Thief functions remain subject to thief armor/weapon restrictions. The PHB specifically notes that cleric/fighter-type combinations may use edged weapons.\n\nExamples supported by the PHB include cleric/fighter, cleric/ranger, cleric/magic-user, cleric/thief, cleric/assassin, fighter/magic-user, fighter/illusionist, fighter/thief, fighter/assassin, fighter/magic-user/thief, magic-user/thief, and illusionist/thief, but race determines which combinations are legal.\n\nPHB DUAL-CLASSING\n\nHumans use the separate two-class/dual-class procedure rather than racial multiclassing. The original class's principal ability must be 15 or better, while the new class's principal ability requirement is exceptionally high (17 or 18 under the PHB procedure). Advancement in the old class stops. Until the new class exceeds the former class level, using former-class abilities during an adventure can forfeit experience for that adventure. Once the new class exceeds the old level, the character can mix functions again, subject to each class's equipment and spellcasting restrictions.\n\nThe supplied UA variant allows some additional class/subclass combinations but does not provide full definitions for every class name it references. The Companion therefore does not invent unsupported class mechanics.";
    ScrollView sc=new ScrollView(this);TextView body=t(m,14,TXT,false);body.setPadding(dp(18),dp(10),dp(18),dp(18));sc.addView(body);
    new AlertDialog.Builder(this).setTitle("Multi-class / Dual-class Rules").setView(sc).setPositiveButton("Close",null).show();
  }

  void customClassDialog(){
    EditText x=e(S("classes",""),"Example: Fighter/Magic-User 4/3",false);
    new AlertDialog.Builder(this).setTitle("Custom / Multi-class").setView(x)
      .setPositiveButton("Use",(d,w)->{String n=x.getText().toString().trim();if(!n.isEmpty()){ss("classes",n);show("Sheet");}})
      .setNegativeButton("Cancel",null).show();
  }

  void writeSelectedClass(){
    if(selectedClass==null)return;
    if(selectedClass.source.startsWith("Custom"))return;
    ss("classes",selectedClass.name+" "+Math.max(1,selectedClassLevel));
  }

  void classSelector(LinearLayout card){
    String stored=S("classes","Fighter 1");
    selectedClassLevel=ClassData.levelFrom(stored);
    ClassData.Entry match=ClassData.findLoose(stored);
    ArrayList<ClassData.Entry> entries=ClassData.all();
    if(match==null&&!stored.trim().isEmpty()){ClassData.Entry legacy=ClassData.legacy(stored);entries.add(0,legacy);match=legacy;}
    if(match==null)match=ClassData.find("Fighter");

    ArrayList<String> labels=new ArrayList<>();int selected=0;
    for(int i=0;i<entries.size();i++){ClassData.Entry e=entries.get(i);labels.add(e.label());if(e.name.equalsIgnoreCase(match.name))selected=i;}

    card.addView(t("Class / Subclass",14,MUT,true));
    Spinner spin=new Spinner(this);ArrayAdapter<String> adapter=classAdapter(labels);adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);spin.setAdapter(adapter);spin.setSelection(selected);card.addView(spin,new LinearLayout.LayoutParams(-1,dp(56)));

    LinearLayout lvl=new LinearLayout(this);lvl.setGravity(Gravity.CENTER_VERTICAL);lvl.addView(t("Class Level",14,MUT,true),new LinearLayout.LayoutParams(dp(125),-2));
    EditText level=e(String.valueOf(selectedClassLevel),"Level",true);lvl.addView(level,new LinearLayout.LayoutParams(0,-2,1));card.addView(lvl);

    TextView req=t("",13,GOLD,true);card.addView(req);
    TextView hit=t("",12,TXT,false);card.addView(hit);
    TextView source=t("",12,MUT,false);card.addView(source);
    TextView note=t("Class selection is informational and persistent. It does not silently change HP, THAC0, saving throws, spell tracks, alignment, or ability scores; those will be automated only when we can do so without damaging existing characters.",11,MUT,false);card.addView(note);

    LinearLayout actions=new LinearLayout(this);
    Button details=b("Class Details");actions.addView(details,new LinearLayout.LayoutParams(0,dp(46),1));
    Button multi=b("Multi-class Rules");actions.addView(multi,new LinearLayout.LayoutParams(0,dp(46),1));
    card.addView(actions);
    Button custom=b("Custom / Multi-class…");card.addView(custom,new LinearLayout.LayoutParams(-1,dp(46)));

    final ArrayList<ClassData.Entry> choices=entries;
    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      @Override public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
        selectedClass=choices.get(position);
        req.setText("Requirements: "+selectedClass.requirements);
        hit.setText("Hit Die: "+selectedClass.hitDie+"  •  Alignment: "+selectedClass.alignment);
        source.setText("Source: "+selectedClass.source+(selectedClass.source.contains("UA variant")?"  •  Variant mechanics kept separate from PHB core.":""));
        writeSelectedClass();
      }
      @Override public void onNothingSelected(AdapterView<?> parent){}
    });

    selectedClass=choices.get(selected);
    req.setText("Requirements: "+selectedClass.requirements);
    hit.setText("Hit Die: "+selectedClass.hitDie+"  •  Alignment: "+selectedClass.alignment);
    source.setText("Source: "+selectedClass.source+(selectedClass.source.contains("UA variant")?"  •  Variant mechanics kept separate from PHB core.":""));

    level.addTextChangedListener(new W(){public void afterTextChanged(Editable z){try{selectedClassLevel=Math.max(1,Integer.parseInt(z.toString()));writeSelectedClass();}catch(Exception ignored){}}});
    details.setOnClickListener(v->classDetails(selectedClass));
    multi.setOnClickListener(v->multiclassRules());
    custom.setOnClickListener(v->customClassDialog());
  }

  @Override void sheet(LinearLayout c){
    LinearLayout a=card("Character");
    field(a,"Name","name","New Character",false);
    raceSelector(a);
    field(a,"Alignment","alignment","",false);
    classSelector(a);
    field(a,"Experience","xp","0",false);
    add(c,a);

    LinearLayout v=card("Core Numbers");
    field(v,"Current HP","hp","8",true);field(v,"Max HP","hpmax","8",true);field(v,"Armor Class","ac","10",true);field(v,"THAC0","thac0","20",true);add(c,v);

    LinearLayout ab=card("Ability Scores");
    ab.addView(t("Use the racial adjustments and class requirements shown above when establishing starting scores. Existing characters are not automatically rewritten.",12,MUT,false));
    for(String k:new String[]{"STR","INT","WIS","DEX","CON","CHA"})field(ab,k,k,"10",true);add(c,ab);

    LinearLayout sv=card("Saving Throws");String[][] saves={{"Poison / Death","save1"},{"Petrify / Poly","save2"},{"Rod / Staff / Wand","save3"},{"Breath Weapon","save4"},{"Spell","save5"}};for(String[] q:saves)field(sv,q[0],q[1],"20",true);add(c,sv);
  }
}
