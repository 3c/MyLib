package com.mobitide.common.data;

import com.mobitide.common.utils.MPrefUtils;

/**
 * 这个类主要是为MPrefUtils嫁接一个桥梁，方便使用
 * 
 * @author CX
 * 
 */
public class MDataAccess {


    // String类型
    public static void saveValueByKey(String key, String saveValue) {
        MPrefUtils.getInstance().saveString(key, saveValue);
    }

    public static String getStringValueByKey(String key) {
        return MPrefUtils.getInstance().getString(key);
    }

    public static String getStringValueByKey(String key, String defaultValue) {
        return MPrefUtils.getInstance().getString(key, defaultValue);
    }

    // int
    public static void saveValueByKey(String key, int saveValue) {
        MPrefUtils.getInstance().saveInt(key, saveValue);
    }

    public static int getIntValueByKey(String key) {
        return MPrefUtils.getInstance().getInt(key);
    }

    public static int getIntValueByKey(String key, int defaultValue) {
        return MPrefUtils.getInstance().getInt(key, defaultValue);
    }

    // float
    public static void saveValueByKey(String key, float saveValue) {
        MPrefUtils.getInstance().saveFloat(key, saveValue);
    }

    public static float getFloatValueByKey(String key) {
        return MPrefUtils.getInstance().getFloat(key);
    }

    public static float getFloatValueByKey(String key, float defaultValue) {
        return MPrefUtils.getInstance().getFloat(key, defaultValue);
    }

    // boolean
    public static void saveValueByKey(String key, boolean saveValue) {
        MPrefUtils.getInstance().saveBoolean(key, saveValue);
    }

    public static boolean getBooleanValueByKey(String key) {
        return MPrefUtils.getInstance().getBoolean(key);
    }

    public static boolean getBooleanValueByKey(String key, boolean defaultValue) {
        return MPrefUtils.getInstance().getBoolean(key, defaultValue);
    }
}
