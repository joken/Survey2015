package com.joken.survey2015;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyAlertDialogFragment extends DialogFragment {

	public static MyAlertDialogFragment newInstance(int title, int message, int type){
		MyAlertDialogFragment frg = new MyAlertDialogFragment();
		Bundle naiyou = new Bundle();
		naiyou.putInt("title", title);
		naiyou.putInt("message", message);
		naiyou.putInt("type", type);
		frg.setArguments(naiyou);
		return frg;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		int title = this.getArguments().getInt("title", 0);
		int message = this.getArguments().getInt("message", 0);
		final int type = this.getArguments().getInt("type", 0);
		Dialog dlog = null;

		switch(type){
		case 1:
			dlog = new AlertDialog.Builder(getActivity())
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog,int witch){
					((MainActivity)getActivity()).onDialogCallback(type);
				}
			})
			.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int witch){
					//なにもしない
				}
			})
			.create();
		default:
			dlog = new AlertDialog.Builder(getActivity())
			.setMessage(R.string.dialog_defmsg)
			.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自動生成されたメソッド・スタブ
					((MainActivity)getActivity()).onDialogCallback(type);
				}
			})
			.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int witch){
					//なにもしない
				}
			})
			.create();
		}

		return dlog;
	}

}
