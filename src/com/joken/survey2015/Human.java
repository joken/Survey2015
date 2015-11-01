package com.joken.survey2015;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 1人分のアンケートデータを保持する
 * @author mi_24
 * */
public class Human {
	//TODO BYSEXは仕様上使用されません
	public static final short MALE = 0,
								FEMALE = 1,
								BYSEX = 2;

	private int ID;
	private String name;
	private short mysex,tgtsex;
	private int myage;
	private int tgtageMin,tgtageMax;
	private ArrayList<Short> answers;

	public Human(int id){
		answers = new ArrayList<Short>(40);
		ID = id;
	}

	/**
	 * 指定された場所にデータを吐き出す
	 * データのセットは完了していなければならない
	 * @throws IOException write中のIOエラー
	 * @throws IllegalStateException データが不完全なとき
	 * */
	public void exportExternalStrorage(BufferedWriter out) throws IOException,IllegalStateException{
		if(name == null || answers.isEmpty()){
			throw new IllegalStateException("data is not set");
		}
		out.write(String.valueOf(ID));out.write(",");
		out.write(name);out.write(",");
		writeSex(out,mysex);out.write(",");
		out.write(String.valueOf(myage));out.write(",");
		writeSex(out,tgtsex);out.write(",");
		out.write(String.valueOf(tgtageMin));out.write(",");
		out.write(String.valueOf(tgtageMax));
		for(short ans : answers){
			out.write(",");
			out.write(String.valueOf(ans));
		}
		out.newLine();
		out.flush();
	}

	private void writeSex(BufferedWriter out, short sex) throws IOException{
		switch(sex){
		case MALE:
			out.write("0");
			break;
		case FEMALE:
			out.write("1");
			break;
		case BYSEX:
			out.write("B");
			break;
		}
	}

	//setter & getter

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void addAnswer(short ans){
		answers.add(ans);
	}

	public final ArrayList<Short> getAnswer(){
		return answers;
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
