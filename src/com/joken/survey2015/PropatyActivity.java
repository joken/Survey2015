package com.joken.survey2015;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PropatyActivity extends Activity {
	private static final String SUCCESS = "success changes";
	private static final String FAILED = "failed changes";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_propaty);
		setSpinner();
		setButton();
		setEditor();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//めにゅーはないです！
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.propaty, menu);
		return false;
	}

	private void setSpinner(){
		final String[] daylist = this.getResources().getStringArray(R.array.day_list);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(
				this.getApplicationContext(),
				R.layout.list,
				daylist);
		ad.setDropDownViewResource(R.layout.dropdown_list);
		Spinner s = (Spinner)findViewById(R.id.day_spinner);
		s.setAdapter(ad);
		s.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
				Editor e = getSharedPreferences(MainActivity.MAIN_PREF, MODE_PRIVATE).edit();
				Spinner s = (Spinner)parent;
				String day = (String)s.getSelectedItem();
				int i;
				if(day.equals(daylist[0])){
					i = 1;
				}else if(day.equals(daylist[1])){
					i = 2;
				}else{i = 1;}
				e.putInt(MainActivity.DAY_VAL, i);
				editorCommit(e);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent){}
		});
	}

	private void setButton(){
		Button idbtn = (Button)findViewById(R.id.id_enter_button);
		idbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				EditText id = (EditText)findViewById(R.id.id_edit);
				if(id.getText() == null){
					showtoast("null");
					return;
					}
				Editor e = getSharedPreferences(MainActivity.MAIN_PREF, MODE_PRIVATE).edit();
				try {
					e.putInt(MainActivity.CURRENT_ID,
							Integer.parseInt(id.getText().toString()));
				} catch (NumberFormatException e2) {
					// TODO: handle exception
					showtoast("NumberFormatExceptionがでてるゾ");
				}
				editorCommit(e);
			}
		});
		Button countbtn = (Button)findViewById(R.id.questcount_enter_button);
		countbtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				EditText count = (EditText)findViewById(R.id.questcount_edit);
				if(count.getText() == null){
					showtoast("null");
					return;
				}
				Editor e = getSharedPreferences(MainActivity.MAIN_PREF, MODE_PRIVATE).edit();
				try{
					e.putInt(MainActivity.QUEST_COUNT, Integer.parseInt(count.getText().toString()));
				}catch(NumberFormatException e2){
					showtoast("NumberFormatExceptionがでてるゾ");
				}
				editorCommit(e);
			}
		});
	}

	private void setEditor(){
		EditText id = (EditText)findViewById(R.id.id_edit),
				count = (EditText)findViewById(R.id.questcount_edit);
		id.setText(String.valueOf(this.getSharedPreferences(MainActivity.MAIN_PREF, MODE_PRIVATE).getInt(MainActivity.CURRENT_ID, 500)));
		count.setText(String.valueOf(this.getSharedPreferences(MainActivity.MAIN_PREF, MODE_PRIVATE).getInt(MainActivity.QUEST_COUNT, 10)));
	}

	private void showtoast(String msg){
    	Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

	private void editorCommit(Editor e) {
		if(e.commit()){
			showtoast(SUCCESS);
		}else{
			showtoast(FAILED);
		}
	}

}
