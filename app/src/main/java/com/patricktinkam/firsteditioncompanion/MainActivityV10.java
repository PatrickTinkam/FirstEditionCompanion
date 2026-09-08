package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import org.json.*;
import java.util.*;

/** v0.8.0: character-creation race selector and native racial reference. */
public class MainActivityV10 extends MainActivityV9 {
  RaceData.Race selectedRace;

  @Override JSONObject makeBackup(){
    JSONObject o=super.makeBackup();
    try{o.put("appVersion","0.8.0");}catch(Exception ignored){}
    return o;
  }

  @Override void shell(){
    LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);setContentView(root);
    TextView title=t("FIRST EDITION COMPANION  •  v0.8.0",18,GOLD,true);title.setGravity(Gravity.CENTER);root.addView(title,new LinearLayout.LayoutParams(-1,dp(52)));
    float w=getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density;boolean wide=w>=700;
    LinearLayout body=new LinearLayout(this);body.setOrientation(wide?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);root.addView(body,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout nav=new LinearLayout(this);nav.setOrientation(wide?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
    String[] tabs={"Sheet","Combat","Spells","Gear","Rules","Dice","Notes"};
    for(String x:tabs){Button z=b(x);z.setOnClickListener(v->show(x));nav.addView(z,wide?new LinearLayout.LayoutParams(-1,dp(50)):new LinearLayout.LayoutParams(dp(100),dp(48)));}
    if(wide)body.addView(nav,new LinearLayout.LayoutParams(dp(155),-1));else{HorizontalScrollView h=new HorizontalScrollView(this);h.addView(nav);body.addView(h,new LinearLayout.LayoutParams(-1,-2));}
    host=new LinearLayout(this);body.addView(host,new LinearLayout.LayoutParams(wide?0:-1,wide?-1:0,1));
  }

  ArrayAdapter<String> raceAdapter(ArrayList<String> labels){
    return new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,labels){
      @Override public View getView(int position,View convertView,android.view.ViewGroup parent){
        TextView v=(TextView)super.getView(position,convertView,parent);v.setTextColor(TXT);v.setTextSize(15);v.setPadding(dp(10),dp(10),dp(10),dp(10));return v;
      }
      @Override public View getDropDownView(int position,View convertView,android.view.ViewGroup parent){
        TextView v=(TextView)super.getDropDownView(position,convertView,parent);v.setTextColor(TXT);v.setBackgroundColor(PANEL);v.setTextSize(15);v.setPadding(dp(12),dp(14),dp(12),dp(14));return v;
      }
    };
  }

  void raceDetails(RaceData.Race r){
    if(r==null)return;
    ScrollView sc=new ScrollView(this);TextView body=t(r.fullDetails(),14,TXT,false);body.setPadding(dp(18),dp(10),dp(18),dp(18));sc.addView(body);
    new AlertDialog.Builder(this).setTitle(r.name).setView(sc).setPositiveButton("Close",null).show();
  }

  void customRaceDialog(){
    EditText x=e(S("race",""),"Custom / campaign race",false);
    new AlertDialog.Builder(this).setTitle("Custom / Campaign Race").setView(x)
      .setPositiveButton("Use",(d,w)->{String n=x.getText().toString().trim();if(!n.isEmpty()){ss("race",n);show("Sheet");}})
      .setNegativeButton("Cancel",null).show();
  }

  void raceSelector(LinearLayout card){
    String stored=S("race","Human");RaceData.Race match=RaceData.find(stored);
    ArrayList<RaceData.Race> races=RaceData.all();
    if(match==null&&!stored.trim().isEmpty()){RaceData.Race legacy=RaceData.legacy(stored);races.add(0,legacy);match=legacy;}
    if(match==null)match=RaceData.find("Human");

    ArrayList<String> labels=new ArrayList<>();int selected=0;
    for(int i=0;i<races.size();i++){RaceData.Race r=races.get(i);labels.add(r.label());if(r.name.equalsIgnoreCase(match.name))selected=i;}

    TextView caption=t("Race / Subrace",14,MUT,true);card.addView(caption);
    Spinner spin=new Spinner(this);ArrayAdapter<String> adapter=raceAdapter(labels);adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);spin.setAdapter(adapter);spin.setSelection(selected);card.addView(spin,new LinearLayout.LayoutParams(-1,dp(56)));

    TextView adjustment=t("",13,GOLD,true);card.addView(adjustment);
    TextView source=t("",12,MUT,false);card.addView(source);
    TextView note=t("Racial modifiers are displayed as a creation reference; the Companion does not silently alter existing ability scores when you change race, preventing old characters from being adjusted twice.",11,MUT,false);card.addView(note);

    LinearLayout actions=new LinearLayout(this);
    Button details=b("Race Details");actions.addView(details,new LinearLayout.LayoutParams(0,dp(46),1));
    Button custom=b("Custom…");custom.setOnClickListener(v->customRaceDialog());actions.addView(custom,new LinearLayout.LayoutParams(0,dp(46),1));
    card.addView(actions);

    final ArrayList<RaceData.Race> choices=races;
    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
      @Override public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
        selectedRace=choices.get(position);ss("race",selectedRace.name);
        adjustment.setText("Ability adjustments: "+selectedRace.adjustments);
        source.setText("Source: "+selectedRace.source+(selectedRace.source.contains("UA variant")||selectedRace.source.contains("supplied UA")?"  •  UA-only mechanics are kept separate from verified PHB core rules.":""));
      }
      @Override public void onNothingSelected(AdapterView<?> parent){}
    });
    selectedRace=choices.get(selected);adjustment.setText("Ability adjustments: "+selectedRace.adjustments);source.setText("Source: "+selectedRace.source+(selectedRace.source.contains("UA variant")||selectedRace.source.contains("supplied UA")?"  •  UA-only mechanics are kept separate from verified PHB core rules.":""));
    details.setOnClickListener(v->raceDetails(selectedRace));
  }

  @Override void sheet(LinearLayout c){
    LinearLayout a=card("Character");
    field(a,"Name","name","New Character",false);
    raceSelector(a);
    field(a,"Alignment","alignment","",false);
    field(a,"Class(es)","classes","Fighter 1",false);
    field(a,"Experience","xp","0",false);
    add(c,a);

    LinearLayout v=card("Core Numbers");
    field(v,"Current HP","hp","8",true);field(v,"Max HP","hpmax","8",true);field(v,"Armor Class","ac","10",true);field(v,"THAC0","thac0","20",true);add(c,v);

    LinearLayout ab=card("Ability Scores");
    ab.addView(t("Use the racial adjustment shown above when establishing the character's final starting scores. Existing characters are left unchanged automatically.",12,MUT,false));
    for(String k:new String[]{"STR","INT","WIS","DEX","CON","CHA"})field(ab,k,k,"10",true);add(c,ab);

    LinearLayout sv=card("Saving Throws");String[][] saves={{"Poison / Death","save1"},{"Petrify / Poly","save2"},{"Rod / Staff / Wand","save3"},{"Breath Weapon","save4"},{"Spell","save5"}};for(String[] q:saves)field(sv,q[0],q[1],"20",true);add(c,sv);
  }
}
