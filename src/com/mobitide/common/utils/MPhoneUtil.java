/**
 * Filename : PhoneUtil.java Author : CX Date : 2013-8-16
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author CX
 * 
 */
public class MPhoneUtil {

    /**
     * 检查是否符合手机号码标准
     * 
     * @param s
     * @return
     */
    public static boolean checkPhone(String s) {
        return s.matches("^1[4358][0-9]{9}$");
    }
    
    public static void callPhone(Context context,String tel){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tel));
        context.startActivity(intent);
    }

}
