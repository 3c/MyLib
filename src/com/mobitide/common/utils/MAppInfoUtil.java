/**
 * Filename : AppInfoUtil.java Author : CX Date : 2013-4-12
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 程序相关信息工具类
 * 
 * @author CX
 * 
 */
public class MAppInfoUtil {

    /**
     * 获取当前的应用名和版本号字符串
     * 
     * @param context
     * @return 
     */
    public static String getAppInfo(Context context) {
        String appStr = null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            appStr = pm.getApplicationLabel(pi.applicationInfo) + "-Android(" + pi.versionName + ")";
        } catch (Exception e) {
            Log.e("CATCH", "Exception getAppInfo error", e);
        }
        return appStr;
    }

    /**
     * 获取设备ID
     * 
     * @param context
     * @return Device ID
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getDeviceId();
    }

    /**
     * 获取App版本名称
     * 
     * @param context
     * @return App Version Name
     */
    public static String getAppVersionName(Context context) {
        String AppVersion = null;
        try {
            AppVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {}
        return AppVersion;
    }


    /**
     * 获取App版本号
     * @param context
     * @return App Version Code
     */
    public static int getAppVersionCode(Context context) {
        int AppVersion = 0;
        try {
            AppVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {}
        return AppVersion;
    }

    /**
     * 获取 应用名称
     * 
     * @return AppName
     */
    public String getAppName(Context context) {
        String appName = null;
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            appName = info.applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (NameNotFoundException e) {}
        return appName;
    }
}
