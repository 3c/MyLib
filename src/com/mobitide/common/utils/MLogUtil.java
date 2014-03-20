package com.mobitide.common.utils;

import android.util.Log;

import com.mobitide.common.data.MGlobalConstants;

/**
 * @author CX
 */
public class MLogUtil {

	protected static String TAG = "mobitide";

	public static void d(String msg) {
		if (MGlobalConstants.Config.logEnabled) {
			Log.d(TAG, msg);
		}
	}

	public static void v(String msg) {
		if (MGlobalConstants.Config.logEnabled) {
			Log.v(TAG, msg);
		}
	}

	public static void i(String msg) {
		if (MGlobalConstants.Config.logEnabled) {
			Log.i(TAG, msg);
		}
	}

	public static void w(String msg) {
		if (MGlobalConstants.Config.logEnabled) {
			Log.w(TAG, msg);
		}
	}

	public static void e(String msg) {
		if (MGlobalConstants.Config.logEnabled) {
			Log.e(TAG, msg);
		}
	}

}
