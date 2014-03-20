/**
 * Filename : WebviewUtil.java Author : CX Date : 2013-4-16
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;

/**
 * @author CX
 * 
 */
public class MWebviewUtil {
    /**
     * 设置webView一般属性
     * 
     * @param webSetting
     */
    public static void setWebViewCommonSetting(WebSettings webSetting) {
        webSetting.setJavaScriptEnabled(true);// 设置加载js
        webSetting.setDefaultTextEncodingName("UTF-8");// 设置编码
        // webSetting.setBlockNetworkImage(!getImageEnable());// 设置是否加载图片
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);// 设置一屏内
    }
    

}
