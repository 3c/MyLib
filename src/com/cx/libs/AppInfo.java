/**
 *    FILE: AppInfo.java
 *  AUTHOR: CX
 *    DATE: 2012-5-31
 *
 *   Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.cx.libs;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @author CX
 */
public class AppInfo {
	
	/**
	 * @param context
	 * @return the Application Version
	 */
	public static String getAppVersion(Context context) {
		String AppVersion = null;
		try {
			AppVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (Exception e) {
			Log.e("CATCH", "Exception getAppInfo error", e);
		}
		return AppVersion;
	}

	/**
	 * @param context
	 * @return the Device ID
	 * needs  <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>  in AndroidManifest.xml
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();
	}

}
