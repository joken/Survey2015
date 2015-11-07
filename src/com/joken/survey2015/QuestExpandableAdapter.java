package com.joken.survey2015;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class QuestExpandableAdapter extends BaseExpandableListAdapter {
	private ArrayList<ArrayList<QuestItem>> children;
	private ArrayList<String> groups;
	private Context context;
	private int questcount;

	public QuestExpandableAdapter(Context ct) {
		// TODO Auto-generated constructor stub
		this.context = ct;
		this.children = new ArrayList<ArrayList<QuestItem>>();
		this.groups = new ArrayList<String>();
		setQuest();
		setGroup();
	}

	private void setQuest(){
		String[] questres;
		int day = context.getSharedPreferences(MainActivity.MAIN_PREF, Activity.MODE_PRIVATE).getInt(MainActivity.DAY_VAL,1);
		questcount = context.getSharedPreferences(MainActivity.MAIN_PREF,Activity.MODE_PRIVATE).getInt(MainActivity.QUEST_COUNT, 10);
		switch(day){
		case 1:
			questres = context.getResources().getStringArray(R.array.quest_day1);
			break;
		case 2:
			questres = context.getResources().getStringArray(R.array.quest_day2);
		default:
			questres = new String[40];
			questres[0] = "could not get quest string";
		}
		ArrayList<QuestItem> q = new ArrayList<QuestItem>(9);
		for(String s : questres){
			q.add(new QuestItem(s));
			if(q.size() > 10){
				Log.d(s, "added");
				children.add(q);
				q = null;
				q = new ArrayList<QuestItem>(9);
			}
			Log.d(context.getPackageCodePath(), q.toString());
		}
		/*for(int j = 0; j < 4; j++){
			ArrayList<QuestItem> q = new ArrayList<QuestItem>();
			for(int i = j*questcount; i < j*questcount+questcount; i++){
				if(i >= questres.length){break;}
				Log.d(this.getClass().getName(), "quest"+i);
				q.add(new QuestItem(questres[i]));
			}
   Log.d(context.getPackageCodePath(),q.toString());
			children.add(q);
		}*/
	}

	private void setGroup(){
		int min = 1,max = questcount;
		for(int i = 0; i < children.size(); i++){
			groups.add("問題" + min + "～" + max);
			min += 10;
			max += 10;
		}
	}

	@SuppressLint("InflateParams")
	private View getBasicView(){
		return LayoutInflater.from(context).inflate(R.layout.questitem, null);
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return children.get(arg0).get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(final int arg0, final int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		if(arg3 != null && arg4 != null){
			return arg3;
		}
		View v = this.getBasicView();
		TextView tv = (TextView)v.findViewById(R.id.questText);
		tv.setText(children.get(arg0).get(arg1).getQuest());
		RatingBar rb = (RatingBar)v.findViewById(R.id.answerBar);
		rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				children.get(arg0).get(arg1).setQuestAns((short)rating);
			}
		});
		return v;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return children.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return groups.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return children.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		if(arg2 != null){
			return arg2;
		}
		TextView tv = new TextView(context);
		tv.setText(groups.get(arg0));
		tv.setTextColor(context.getResources().getColor(R.color.black));
		ViewGroup.MarginLayoutParams m = new ViewGroup.MarginLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		m.setMargins(300, 0, 10, 10);
		tv.setLayoutParams(m);
		tv.setTextSize(32);
		tv.setBackgroundColor(Color.argb(204,54,189,255));
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
