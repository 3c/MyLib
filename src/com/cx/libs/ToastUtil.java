package com.cx.libs;

import android.widget.Toast;

/**
 * Toast tools
 * @author CX
 * 
 */
public class ToastUtil {
	/**
	 * @param msg
	 *            Toast show msg
	 */
	public static void show(String msg) {
		Toast.makeText(GlobalApplication.mInstance, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * @param msg
	 * @param time
	 *            Toast.LENGTH_SHORT or Toast.LENGTH_LONG
	 */
	public static void show(String msg, int time) {
		Toast.makeText(GlobalApplication.mInstance, msg, time).show();
	}
}
