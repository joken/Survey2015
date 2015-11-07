package com.joken.survey2015;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	public static final String MAIN_PREF = "main_pref";
	public static final String DAY_VAL = "day_value";
	public static final String QUEST_COUNT = "quest_count";
	public static final String CURRENT_ID = "current_id";

	private Human human;
	private int questcount;
	private ArrayList<ArrayList<QuestItem>> children;
	private ArrayList<String> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHuman();
        setExpandableListView();
        setSpinner();
    }

    private void setHuman(){
    	int index = this.getSharedPreferences(MAIN_PREF, MODE_PRIVATE).getInt(CURRENT_ID, 500);
    	human = new Human(index);
    }

    private void setQuest(){
		String[] questres;
		this.children = new ArrayList<ArrayList<QuestItem>>();
		int day = getSharedPreferences(MainActivity.MAIN_PREF, Activity.MODE_PRIVATE).getInt(MainActivity.DAY_VAL,1);
		questcount = getSharedPreferences(MainActivity.MAIN_PREF,Activity.MODE_PRIVATE).getInt(MainActivity.QUEST_COUNT, 10);
		switch(day){
		case 1:
			questres = getResources().getStringArray(R.array.quest_day1);
			break;
		case 2:
			questres = getResources().getStringArray(R.array.quest_day2);
			break;
		default:
			questres = new String[1];
			questres[0] = "could not get quest string";
		}
		ArrayList<QuestItem> q = new ArrayList<QuestItem>();
		for(String s : questres){
			q.add(new QuestItem(s));
			Log.d(getPackageCodePath(), q.toString());
		}
		children = CollectionUtils.devide(q, questcount);
	}

	private void setGroup(){
		groups = new ArrayList<String>();
		int min = 1,max = questcount;
		for(int i = 0; i < children.size(); i++){
			groups.add("問題" + min + "～" + max);
			min += 10;
			max += 10;
		}
	}

	private void setExpandableListView() {
		this.setQuest();
		this.setGroup();
		ExpandableListView ex = (ExpandableListView)this.findViewById(R.id.questList);
		ex.addFooterView(getFooterView(), null, false);
        ex.setAdapter(new QuestExpandableAdapter(this.getApplicationContext(), this.groups, this.children));
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
        case R.id.action_settings:
            Intent it = new Intent(this.getApplicationContext(), PropatyActivity.class);
            this.startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    private View getFooterView(){
    	Button bt = new Button(this.getApplicationContext());
    	bt.setText("確定");
    	bt.setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v){
    			showdialog(0,0,0);
    		}
    	});
    	return bt;
    }

    private boolean setdata(){
    	TextView name = (TextView)findViewById(R.id.namearea)
    			,myage = (TextView)findViewById(R.id.myagearea)
    			,tgtagemin = (TextView)findViewById(R.id.tgtagearea1)
    			,tgtagemax = (TextView)findViewById(R.id.tgtagearea2);
    	human.setName(name.getText().toString());
    	try {
			human.setMyage(Integer.valueOf(myage.getText().toString()));
			human.setTgtage(Integer.valueOf(tgtagemin.getText().toString()),
					Integer.valueOf(tgtagemax.getText().toString()));
		} catch (NumberFormatException e) {
			showtoast("入力がおかしいよ");
   return false;
		}
		ExpandableListView ls = (ExpandableListView)findViewById(R.id.questList);
    	Log.w(this.getLocalClassName(), ls.getExpandableListAdapter().toString());
    	QuestExpandableAdapter ad = (QuestExpandableAdapter)ls.getExpandableListAdapter();
    	for(int i = 0; i < ad.getGroupCount(); i++){
    		for(int j = 0; j < ad.getChildrenCount(i); j++){
    			QuestItem qi = (QuestItem)ad.getChild(i, j);
    			human.addAnswer(qi.getQuestAns());
    		}
    	}
    	name.setText(null);myage.setText(null);tgtagemin.setText(null);tgtagemax.setText(null);
     return true;
    }

    //TODO IDを設定(1000~1999)->500~599に変更
    private void exportdata(boolean isEnableExport){
     if(!isEnableExport){return;}
    	int day = this.getSharedPreferences(MAIN_PREF, MODE_PRIVATE).getInt(DAY_VAL, 1);
    	File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/joken/day"+day+".csv");
    	try{
			if(!file.exists()){
				File d =  new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/joken");
				d.mkdir();
				//file.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
			Log.d(getLocalClassName(), human.toString());
			human.exportExternalStrorage(out);
			out.close();
	    	showtoast("success save csv");
      showdialog(R.string.exp_success_tit, "あなたのIDは"+human.getID()+"です。\n＊おぼえてね！", 2);
	    	Editor ed = this.getSharedPreferences(MAIN_PREF, MODE_PRIVATE).edit();
	    	ed.putInt(CURRENT_ID, human.getID() + 1);
	    	ed.commit();
	    	setHuman();
    	} catch(IllegalStateException e){
    		showtoast("回答を記述してください");
    		return;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			showtoast("an error occured");
		}
    }

    private void setSpinner(){
    	ArrayAdapter<String> ad = new ArrayAdapter<String>(
    			this.getApplicationContext(),
    			R.layout.list,
    			this.getResources().getStringArray(R.array.sex_list));
    	ad.setDropDownViewResource(R.layout.dropdown_list);
    	Spinner s1 = (Spinner)findViewById(R.id.mysexarea),
    				s2 = (Spinner)findViewById(R.id.tgtsexarea);
    	s1.setAdapter(ad);s2.setAdapter(ad);
    	s1.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自動生成されたメソッド・スタブ
				Spinner s = (Spinner)parent;
				String sex = (String)s.getSelectedItem();
				human.setMysex(parsesex(sex));
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {	}
    	});
    	s2.setOnItemSelectedListener(new OnItemSelectedListener(){
    		@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自動生成されたメソッド・スタブ
				Spinner s = (Spinner)parent;
				String sex = (String)s.getSelectedItem();
				human.setTgtsex(parsesex(sex));
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {	}
    	});
    }

    private short parsesex(String sex){
    	String[] list = this.getResources().getStringArray(R.array.sex_list);
    	if(sex.equals(list[0])){
    		return Human.MALE;
    	}else if(sex.equals(list[1])){
    		return Human.FEMALE;
    	//}else if(sex.equals(list[2])){
    		//return Human.BYSEX;
    	}else{
    		return -1;
    	}
    }

    public void onDialogCallback(int type){
    	switch(type){
    	case 0:
    		this.exportdata(this.setdata());
    		break;
    	}
    }

    private void showdialog(int title,int msg, int type){
    	MyAlertDialogFragment.newInstance(title, msg, type).show(getFragmentManager(), this.getPackageName());
    }

    private void showdialog(int title,String msg, int type){
     MyAlertDialogFragment.newInstance(title, msg, type).show(getFragmentManager(), this.getPackageName());
    }

    private void showtoast(String msg){
    	Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
