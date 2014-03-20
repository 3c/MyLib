package com.mobitide.common.utils;

import android.content.Context;
import android.widget.Toast;

import com.mobitide.common.data.MGlobalContext;

/**
 * Toast tools
 * 
 * @author CX
 * 
 */
public class MToastUtil {
    /**
     * @param msg Toast show msg
     */
    public static void show(String msg) {
        Toast.makeText(MGlobalContext.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msg Toast show msg
     */
    public static void show(int msg) {
        Toast.makeText(MGlobalContext.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msg
     * @param time Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    public static void show(String msg, int time) {
        Toast.makeText(MGlobalContext.getContext(), msg, time).show();
    }

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
