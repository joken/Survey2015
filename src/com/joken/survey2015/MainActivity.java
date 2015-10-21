package com.joken.survey2015;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends Activity {
	private Human human;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        human = new Human();
        setSpinner();
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
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    	}else if(sex.equals(list[2])){
    		return Human.BYSEX;
    	}else{
    		return -1;
    	}
    }

}
