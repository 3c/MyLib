/**
 * Filename : ApkUtil.java Author : CX Date : 2013-8-16
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author CX
 * 
 */
public class MApkUtil {

    /**
     * 安装本地的apk
     * @param con
     * @param path
     */
    public static void setupAPK(Context con, String path) {
        // 创建URI
        Uri uri = Uri.fromFile(new File(path));
        // 创建Intent意图
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 启动新的activity
        // 设置Uri和类型
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行安装
        con.startActivity(intent);
    }

}
