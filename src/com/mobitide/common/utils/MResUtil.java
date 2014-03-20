/**
 * Filename : ResUtil.java Author : CX Date : 2013-4-12
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import android.content.res.Resources;

import com.mobitide.common.data.MGlobalContext;



/**
 * @author CX
 * 
 */
public class MResUtil {
    /**
     * 从strings.xml中读取String
     * 
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 从colors.xml中读取color
     * 
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 从arrays.xml中读取array[]
     * 
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 从dimens.xml里读取dimen
     * 
     * @param resId
     * @return
     */
    public static int getDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获得全局的resources
     * @return
     */
    public static Resources getResources() {
        return MGlobalContext.getContext().getResources();
    }

}
