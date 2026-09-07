package com.patricktinkam.firsteditioncompanion;

import android.app.*;
import android.content.*;
import android.net.Uri;
import android.widget.*;
import org.json.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivityV4 extends MainActivityV3 {
  static final int REQ_EXPORT_SAVES=4101,REQ_IMPORT_SAVES=4102;
  static final String BACKUP_FORMAT="FirstEditionCompanionBackup";
  static final int BACKUP_SCHEMA=1;

  JSONObject makeBackup(){
    JSONObject root=new JSONObject();
    try{
      root.put("format",BACKUP_FORMAT);
      root.put("schemaVersion",BACKUP_SCHEMA);
      root.put("appVersion","0.4.0");
      root.put("exportedAt",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ",Locale.US).format(new Date()));
      root.put("currentCharacter",snapshot());
      root.put("savedCharacters",savedCharacters());
    }catch(Exception ignored){}
    return root;
  }

  String backupFilename(){return "FirstEditionCompanion-Backup-"+new SimpleDateFormat("yyyyMMdd-HHmm",Locale.US).format(new Date())+".json";}

  void exportSaves(){
    Intent i=new Intent(Intent.ACTION_CREATE_DOCUMENT);
    i.addCategory(Intent.CATEGORY_OPENABLE);
    i.setType("application/json");
    i.putExtra(Intent.EXTRA_TITLE,backupFilename());
    try{startActivityForResult(i,REQ_EXPORT_SAVES);}catch(Exception e){toast("No file picker is available on this device");}
  }

  void importSaves(){
    Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);
    i.addCategory(Intent.CATEGORY_OPENABLE);
    i.setType("*/*");
    i.putExtra(Intent.EXTRA_MIME_TYPES,new String[]{"application/json","text/plain","application/octet-stream"});
    try{startActivityForResult(i,REQ_IMPORT_SAVES);}catch(Exception e){toast("No file picker is available on this device");}
  }

  void writeBackup(Uri uri){
    try(OutputStream os=getContentResolver().openOutputStream(uri,"wt");OutputStreamWriter w=new OutputStreamWriter(os,StandardCharsets.UTF_8)){
      w.write(makeBackup().toString(2));
      w.flush();
      toast("Character backup exported");
    }catch(Exception e){toast("Could not export backup");}
  }

  String readText(Uri uri)throws Exception{
    StringBuilder out=new StringBuilder();
    try(InputStream in=getContentResolver().openInputStream(uri);BufferedReader r=new BufferedReader(new InputStreamReader(in,StandardCharsets.UTF_8))){
      char[] buf=new char[4096];int n;
      while((n=r.read(buf))!=-1){out.append(buf,0,n);if(out.length()>4_000_000)throw new IOException("Backup file is too large");}
    }
    return out.toString();
  }

  JSONObject parseBackup(String text)throws Exception{
    JSONObject root=new JSONObject(text.trim());
    if(BACKUP_FORMAT.equals(root.optString("format"))){
      int schema=root.optInt("schemaVersion",1);
      if(schema>BACKUP_SCHEMA)throw new IOException("Backup was created by a newer app version");
      if(root.optJSONArray("savedCharacters")==null)root.put("savedCharacters",new JSONArray());
      return root;
    }
    // v0.1-v0.3 clipboard backups contained a single character snapshot directly.
    if(root.has("name")||root.has("classes")||root.has("tracks")){
      JSONObject wrapped=new JSONObject();
      wrapped.put("format",BACKUP_FORMAT);
      wrapped.put("schemaVersion",BACKUP_SCHEMA);
      wrapped.put("appVersion","legacy");
      wrapped.put("currentCharacter",root);
      wrapped.put("savedCharacters",new JSONArray());
      return wrapped;
    }
    throw new IOException("Not a First Edition Companion backup");
  }

  String profileName(JSONObject q){
    if(q==null)return "Imported Character";
    String n=q.optString("_profile",q.optString("name","Imported Character")).trim();
    return n.isEmpty()?"Imported Character":n;
  }

  boolean profileNameExists(JSONArray a,String name){for(int i=0;i<a.length();i++){JSONObject q=a.optJSONObject(i);if(q!=null&&profileName(q).equalsIgnoreCase(name))return true;}return false;}
  String uniqueProfileName(JSONArray a,String base){String n=base;if(!profileNameExists(a,n))return n;n=base+" (Imported)";int i=2;while(profileNameExists(a,n))n=base+" (Imported "+(i++)+")";return n;}

  JSONObject copiedProfile(JSONObject src,JSONArray destination)throws Exception{
    JSONObject q=new JSONObject(src.toString());
    q.put("_profile",uniqueProfileName(destination,profileName(q)));
    return q;
  }

  boolean meaningfulCurrent(JSONObject q){
    if(q==null)return false;
    if(!q.optString("name","").trim().isEmpty()||!q.optString("classes","").trim().isEmpty()||!q.optString("notes","").trim().isEmpty()||!q.optString("gear","").trim().isEmpty())return true;
    String tracks=q.optString("tracks","[]").trim();
    return !tracks.isEmpty()&&!tracks.equals("[]");
  }

  void mergeBackup(JSONObject backup){
    try{
      JSONArray existing=savedCharacters(),incoming=backup.optJSONArray("savedCharacters");
      int added=0;
      if(incoming!=null)for(int i=0;i<incoming.length();i++){JSONObject q=incoming.optJSONObject(i);if(q!=null){existing.put(copiedProfile(q,existing));added++;}}
      JSONObject current=backup.optJSONObject("currentCharacter");
      if(meaningfulCurrent(current)){JSONObject q=new JSONObject(current.toString());q.put("_profile",uniqueProfileName(existing,profileName(q)));existing.put(q);added++;}
      saveCharacters(existing);show("Sheet");toast("Imported "+added+" character profile"+(added==1?"":"s"));
    }catch(Exception e){toast("Could not merge backup");}
  }

  void replaceFromBackup(JSONObject backup){
    try{
      JSONArray incoming=backup.optJSONArray("savedCharacters");
      saveCharacters(incoming==null?new JSONArray():new JSONArray(incoming.toString()));
      JSONObject current=backup.optJSONObject("currentCharacter");
      if(current!=null)applySnapshot(current);
      show("Sheet");toast("Backup restored");
    }catch(Exception e){toast("Could not restore backup");}
  }

  void chooseImportMode(JSONObject backup){
    JSONArray incoming=backup.optJSONArray("savedCharacters");int count=incoming==null?0:incoming.length();boolean hasCurrent=meaningfulCurrent(backup.optJSONObject("currentCharacter"));
    String message="Backup contains "+count+" saved profile"+(count==1?"":"s")+(hasCurrent?" plus a current working character.":".")+"\n\nMerge keeps your existing saves and imports these under unique names. Restore/Replace replaces the app's saved profiles and current working character with the backup.";
    new AlertDialog.Builder(this).setTitle("Import Character Backup").setMessage(message)
      .setPositiveButton("Restore / Replace",(d,w)->replaceFromBackup(backup))
      .setNeutralButton("Merge",(d,w)->mergeBackup(backup))
      .setNegativeButton("Cancel",null).show();
  }

  void importFromClipboard(){
    try{
      android.content.ClipboardManager cm=(android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
      if(!cm.hasPrimaryClip()||cm.getPrimaryClip()==null||cm.getPrimaryClip().getItemCount()==0){toast("Clipboard is empty");return;}
      CharSequence text=cm.getPrimaryClip().getItemAt(0).coerceToText(this);
      if(text==null||text.toString().trim().isEmpty()){toast("Clipboard is empty");return;}
      chooseImportMode(parseBackup(text.toString()));
    }catch(Exception e){toast("Clipboard does not contain a valid Companion backup");}
  }

  @Override protected void onActivityResult(int requestCode,int resultCode,Intent data){
    super.onActivityResult(requestCode,resultCode,data);
    if(resultCode!=RESULT_OK||data==null||data.getData()==null)return;
    Uri uri=data.getData();
    if(requestCode==REQ_EXPORT_SAVES){writeBackup(uri);return;}
    if(requestCode==REQ_IMPORT_SAVES){try{chooseImportMode(parseBackup(readText(uri)));}catch(Exception e){toast(e.getMessage()==null?"Could not import backup":e.getMessage());}}
  }

  @Override void sheet(LinearLayout c){
    LinearLayout backup=card("Backup & Restore");
    backup.addView(t("Export all named character profiles plus the current working character to a portable JSON file. Import it after reinstalling or moving to a new device.",13,MUT,false));
    LinearLayout row=new LinearLayout(this);Button ex=b("Export Saves");ex.setOnClickListener(v->exportSaves());row.addView(ex,new LinearLayout.LayoutParams(0,dp(48),1));Button im=b("Import Saves");im.setOnClickListener(v->importSaves());row.addView(im,new LinearLayout.LayoutParams(0,dp(48),1));backup.addView(row);
    Button legacy=b("Import Legacy Clipboard Backup");legacy.setOnClickListener(v->importFromClipboard());backup.addView(legacy);
    backup.addView(t("Tip: export a backup before uninstalling an older prototype build. v0.4.0 and later use a stable prototype signing key so normal in-place updates should preserve local data automatically.",12,MUT,false));
    add(c,backup);
    super.sheet(c);
  }
}
