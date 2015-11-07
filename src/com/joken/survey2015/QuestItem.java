package com.joken.survey2015;

public class QuestItem {
	private String questRaw;
	private short questAns;

	public QuestItem() {}

	public QuestItem(String quest){
		this.questRaw = quest;
	}

	public String getQuest(){
		return this.questRaw;
	}

	public void setQuestAns(short ans){
		this.questAns = ans;
	}

	public short getQuestAns(){
		return this.questAns;
	}

	@Override
	public String toString(){
		return "quest text:"+questRaw + " ans : "+questAns;
	}

}
