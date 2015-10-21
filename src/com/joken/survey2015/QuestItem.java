package com.joken.survey2015;

public class QuestItem {
	private int questRaw;
	private short questAns;

	public QuestItem() {
		questRaw = R.array.quest_day1;
	}
	
	public int getName(){
		return this.questRaw;
	}
	
	public void setQuestAns(short ans){
		this.questAns = ans;
	}
	
	public short getQuestAns(){
		return this.questAns;
	} 

}
