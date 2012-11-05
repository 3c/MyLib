/**
 *    FILE: LibTest.java
 *  AUTHOR: CX
 *    DATE: 2012-5-31
 *
 *   Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.cx.libs.test;

import android.content.Context;
import android.test.AndroidTestCase;

import com.cx.libs.AppInfo;
import com.cx.libs.GlobalApplication;
import com.cx.libs.PrefUtils;

/**
 * @author CX
 */
public class LibTest extends AndroidTestCase {
	private Context context = GlobalApplication.mInstance;

	public void testAppInfo() {
		AppInfo.getAppVersion(context);
	}

	public void testDataAccess() {
		PrefUtils mPre = PrefUtils.getInstance();
		mPre.saveString("key", "value");
		mPre.getString("key");
		mPre.getString("key", "defaultValue");
		PrefUtils.getInstance().saveString("key", "value");
	}

}
