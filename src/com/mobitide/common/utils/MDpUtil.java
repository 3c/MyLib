/**
 * Filename : DpUtil.java Author : CX Date : 2013-7-12
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;



/**
 * @author CX
 * 
 */
public class MDpUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        float scale = MResUtil.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        float scale = MResUtil.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
