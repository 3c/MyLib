package com.cx.libs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Progress Dialog tools
 * @author CX
 */
public class AppProgressDialog {

	public static ProgressDialog pro;

	/**
	 * show ProgressBar Dialog
	 * 
	 * @param con
	 */
	public static void show(Context con) {
		pro = new ProgressDialog(con);
		pro.setTitle(con.getResources().getString(R.string.progressbar_title));
		pro.setMessage(con.getResources().getString(R.string.progressbar_message));
		pro.show();
	}

	/**
	 * show ProgressBar Dialog of custom title
	 * 
	 * @param con
	 * @param title
	 */
	public static void show(Context con, String title) {
		pro = new ProgressDialog(con);
		pro.setTitle(title);
		pro.setMessage(con.getResources().getString(R.string.progressbar_message));
		pro.show();
	}

	/**
	 * show ProgressBar Dialog of custiom title and message
	 * 
	 * @param con
	 * @param title
	 * @param messge
	 */
	public static void show(Context con, String title, String messge) {
		pro = new ProgressDialog(con);
		pro.setTitle(title);
		pro.setMessage(messge);
		pro.show();
	}

	/**
	 * cancel ProgressBar Dialog
	 */
	public static void cancel() {
		if (pro != null && pro.isShowing()) {
			pro.cancel();
		}
	}
}
