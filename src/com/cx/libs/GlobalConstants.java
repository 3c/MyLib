package com.cx.libs;

import android.content.Context;
import android.os.Environment;
import android.util.DisplayMetrics;

public class GlobalConstants {

	public static String getDir() {
		return Environment.getExternalStorageDirectory() + "/oularapp/portal/";
	}

	public static String TEMP_DIR = getDir() + "temp/";
	public static String DATA_STORE = getDir() + "data/";
	public static String IMG_SAVE_STORE = getDir() + "image/";

	public static int screenWidth = 480;
	public static int screenHeight = 854;
	public static boolean isLoad;
	
	public static final int BIND_SNS=0x1111;
	
	public static final int MAIN_BG=0x2222;
	
	public static void init(Context context){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
	}
}
