package com.joken.survey2015;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class QuestExpandableAdapter extends BaseExpandableListAdapter {
	private ArrayList<ArrayList<QuestItem>> children;
	private ArrayList<String> groups;
	private Context context;

	public QuestExpandableAdapter(Context ct) {
		// TODO Auto-generated constructor stub
		this.context = ct;
		this.children = new ArrayList<ArrayList<QuestItem>>();
		this.groups = new ArrayList<String>();
	}
	
	private View getBasicView(){
		return LayoutInflater.from(context).inflate(R.layout.questitem, null);
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		// TODO Auto-generated method stub
		if(arg3 != null && arg4 != null){
			return arg3;
		}
		View v = this.getBasicView();
		TextView tv = (TextView)v.findViewById(R.id.questText);
		return v;
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
