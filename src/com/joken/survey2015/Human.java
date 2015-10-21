package com.joken.survey2015;

import java.util.ArrayList;

/**
 * 1人分のアンケートデータを保持する
 * @author mi_24
 * */
public class Human {
	public static final short MALE = 0,
								FEMALE = 1,
								BYSEX = 2;

	private String name;
	private short mysex,tgtsex;
	private int myage;
	private int tgtageMin,tgtageMax;
	private ArrayList<Short> answers;

	public void addAnswer(short ans){
		answers.add(ans);
	}

	public final ArrayList<Short> getAnswer(){
		return answers;
	}

	//setter & getter
	public Human(){
		answers = new ArrayList<Short>(40);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getMysex() {
		return mysex;
	}
	public void setMysex(short mysex) {
		this.mysex = mysex;
	}
	public short getTgtsex() {
		return tgtsex;
	}
	public void setTgtsex(short tgtsex) {
		this.tgtsex = tgtsex;
	}
	public int getMyage() {
		return myage;
	}
	public void setMyage(int myage) {
		this.myage = myage;
	}

	public void setTgtage(int min, int max){
		this.setTgtageMin(min);
		this.setTgtageMax(max);
	}
	public int getTgtageMin() {
		return tgtageMin;
	}
	public void setTgtageMin(int tgtageMin) {
		this.tgtageMin = tgtageMin;
	}
	public int getTgtageMax() {
		return tgtageMax;
	}
	public void setTgtageMax(int tgtageMax) {
		this.tgtageMax = tgtageMax;
	}

}
