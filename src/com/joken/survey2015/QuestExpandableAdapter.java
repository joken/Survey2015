package com.joken.survey2015;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

	public QuestExpandableAdapter(Context ct,ArrayList<String> group,ArrayList<ArrayList<QuestItem>> children) {
		// TODO Auto-generated constructor stub
		this.context = ct;
		this.children = children;
		this.groups = group;
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
		return Long.valueOf(children.get(arg0).get(arg1).getID());
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		/*if(arg3 != null && arg4 != null){
			return arg3;
		}*/
		final int gpos = arg0,cpos = arg1;
		View v = this.getBasicView();
		TextView tv = (TextView)v.findViewById(R.id.questText);
		tv.setText(children.get(gpos).get(cpos).getQuest());
		RatingBar rb = (RatingBar)v.findViewById(R.id.answerBar);
		rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				children.get(gpos).get(cpos).setQuestAns((short)rating);
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
		return groups.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		/*if(arg2 != null){
			return arg2;
		}*/
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
