package com.cx.libs;

import android.util.Log;

/**
 * @author CX
 */
public abstract class CXLOG {

	public final static String TAG = "CX";

	public static void log(String msg) {
		logd(TAG, msg);
	}

	public static void logd(String tag, String msg) {
		Log.d(TAG, msg);
	}

	public static void logv(String msg) {
		Log.v(TAG, msg);
	}

	public static void logi(String msg) {
		Log.i(TAG, msg);
	}

	public static void logw(String msg) {
		Log.w(TAG, msg);
	}

	public static void loge(String msg) {
		Log.e(TAG, msg);
	}

}
