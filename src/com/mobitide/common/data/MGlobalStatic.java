/**
 * Filename : GlobalStatic.java Author : CX Date : 2013-4-12
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.data;

import java.io.File;

import com.mobitide.common.utils.MFileUtil;

/**
 * 一些静态常量
 * @author CX
 * 
 */
public class MGlobalStatic {

    /**
     * 文件夹名称。可变，每个appid对应一个文件夹
     */
    public static String folderName = "static";
    /**
     * 临时文件 路径
     */
    public static String TEMP_DIR = MFileUtil.getRootDir() + File.separator + MGlobalConstants.FileInfo.TEMP
            + File.separator;
    /**
     * 数据存储 路径
     */
    public static String DATA_STORE = MFileUtil.getDir() + File.separator + MGlobalConstants.FileInfo.DATA
            + File.separator;

    /**
     * 图片存储 路径
     */
    public static String IMG_SAVE_STORE = MFileUtil.getRootDir() + File.separator + MGlobalConstants.FileInfo.IMAGE
            + File.separator;

    /**
     * 屏幕 宽
     */
    public static int sScreenWidth = 480;
    /**
     * 屏幕 高
     */
    public static int sScreenHeight = 854;




}
