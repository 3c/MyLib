/**
 *    Filename : BundleUtil.java
 *    Author   : CX
 *    Date     : 2013-4-19
 *
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import android.app.Activity;
import android.os.Bundle;

import com.mobitide.lib.R;

/**
 * @author CX
 * 
 */
public class MBundleUtil {

	public static Bundle getBundle(Activity act) {
		Bundle bundle = act.getIntent().getExtras();
		if (bundle == null) {
			MToastUtil.show(R.string.error_bundle_null);
			act.finish();
			return null;
		}
		return bundle;
	}

}
