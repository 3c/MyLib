package com.mobitide.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobitide.common.data.MGlobalContext;

public class MPrefUtils {

    public static final String PREF_NAME = "mobitide";

    private static MPrefUtils instance;
    private SharedPreferences mSharedPreferences;

    public static MPrefUtils getInstance() {

        if (instance == null) {
            instance = new MPrefUtils(MGlobalContext.getContext());
        }
        return instance;
    }

    private MPrefUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * @param key
     * @param bool
     */
    public void saveBoolean(String key, boolean bool) {
        mSharedPreferences.edit().putBoolean(key, bool).commit();
    }

    /**
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean bool) {
        return mSharedPreferences.getBoolean(key, bool);
    }

    /**
     * @param key
     * @param value
     */
    public void saveString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * @param key
     * @return
     */
    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void saveInt(String key, Integer value) {
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * @param key
     * @return
     */
    public int getInt(String key) {

        return mSharedPreferences.getInt(key, 0);

    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void saveLong(String key, Long value) {
        mSharedPreferences.edit().putLong(key, value).commit();
    }



    /**
     * @param key
     * @return
     */
    public Long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public Long getLong(String key, int defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void saveFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * @param key
     * @return
     */
    public Float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public Float getFloat(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

}
