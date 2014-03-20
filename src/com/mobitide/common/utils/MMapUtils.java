/**
 * file_name: MapUtils.java
 * create_at: 2011-8-16
 *    author: alan
 *    
 *
 * Copyright 2011 Mobitide All rights reserved.
 */
package com.mobitide.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.mobitide.lib.R;

/**
 * @author alan
 *
 */
public class MMapUtils {

	
	public static  int getZoomRateByDistance(double maxDistance) {
		int zoomLvl = 17;
		if (maxDistance < 24576)
			zoomLvl = 1;
		if (maxDistance < 12288)
			zoomLvl = 2;
		if (maxDistance < 6144)
			zoomLvl = 3;
		if (maxDistance < 3072)
			zoomLvl = 4;
		if (maxDistance < 1536)
			zoomLvl = 5;
		if (maxDistance < 768)
			zoomLvl = 6;
		if (maxDistance < 384)
			zoomLvl = 7;
		if (maxDistance < 192)
			zoomLvl = 8;
		if (maxDistance < 96)
			zoomLvl = 9;
		if (maxDistance < 48)
			zoomLvl = 10;
		if (maxDistance < 24)
			zoomLvl = 11;
		if (maxDistance < 11)
			zoomLvl = 12;
		if (maxDistance < 4.8)
			zoomLvl = 13;
		if (maxDistance < 3.2)
			zoomLvl = 14;
		if (maxDistance < 1.6)
			zoomLvl = 15;
//		if (maxDistance < 0.8)
//			zoomLvl = 16;
//		if (maxDistance < 0.3)
//			zoomLvl = 17;
		return zoomLvl;
		}
	 /**
	 * @param distance
	 * @return
	 */
	public static  int getZoomRateByDistance(String distance) {
		int KmIndex = distance.lastIndexOf(MResUtil.getString(R.string.kilogram));
		if (KmIndex == -1) {
			return 16;
		}
		//APPLOG.d("dist(km)= " + distance.substring(0, KmIndex));
		double dist = Double.valueOf(distance.substring(0, KmIndex));
		return getZoomRateByDistance(dist);
		
	}
	
	
	
	public static void enableGPS(Context context) {
		final Intent poke = new Intent();
	    poke.setClassName("com.android.settings",   "com.android.settings.widget.SettingsAppWidgetProvider");
	    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	    poke.setData(Uri.parse("3"));
	    context.sendBroadcast(poke);
	}

	
	public static boolean isEnabledGPS(Context context) {
	    String provider = Settings.Secure.getString(context.getContentResolver(), 
	    		Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
	    return provider.contains("gps"); 
	}
	
}
