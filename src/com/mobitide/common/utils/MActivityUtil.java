/**
 * Filename : ActivityUtil.java Author : CX Date : 2013-4-17
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity工具类
 * 
 * @author CX
 * 
 */
public class MActivityUtil {

    /**
     * 调用某一个Action
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }


    /**
     * 一个Activity 开启 另一个 Activity
     * 
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 一个Activity 开启 另一个 Activity，带Bundle
     * 
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startActivity(Context context, Class cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 一个Activity 开启 另一个 Activity，带Bundle，并且可以返回处理的数据
     * 
     * @param act
     * @param cls
     * @param bundle
     * @param Code
     */
    public static void startActivityForResult(Activity act, Class cls, Bundle bundle, int Code) {
        Intent intent = new Intent(act, cls);
        intent.putExtras(bundle);
        act.startActivityForResult(intent, Code);
    }

    /**
     * 一个Activity 开启 另一个 Activity，并且可以返回处理的数据
     * 
     * @param act
     * @param cls
     * @param Code
     */
    public static void startActivityForResult(Activity act, Class cls, int Code) {
        Intent intent = new Intent(act, cls);
        act.startActivityForResult(intent, Code);
    }


}
