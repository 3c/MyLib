package com.mobitide.common.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.mobitide.lib.R;

/**
 * Progress Dialog tools
 * 
 * @author CX
 */
public class MProgressDialogUtil {

	public static ProgressDialog pro;

	/**
	 * show ProgressBar Dialog
	 * 
	 * @param con
	 */
	public static void show(Context con) {
		show(con, MResUtil.getString(R.string.progressbar_title), MResUtil.getString(R.string.progressbar_message));
	}

	/**
	 * show ProgressBar Dialog of custom title
	 * 
	 * @param con
	 * @param title
	 */
	public static void show(Context con, String title) {

		show(con, title, MResUtil.getString(R.string.progressbar_message));
	}

	/**
	 * show ProgressBar Dialog of custiom title and message
	 * 
	 * @param con
	 * @param title
	 * @param messge
	 */
	public static void show(Context con, String title, String messge) {
		if (pro == null || !pro.isShowing()) {
			pro = new ProgressDialog(con);
			pro.setTitle(title);
			pro.setMessage(messge);
			pro.show();
		}
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
