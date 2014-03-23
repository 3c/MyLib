/**
 *    Filename : MLibConfig.java
 *    Author   : CX
 *    Date     : 2014-3-6
 *
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.application;

import java.io.File;

import com.mobitide.common.data.MGlobalConstants;
import com.mobitide.common.data.MGlobalStatic;
import com.mobitide.common.data.MGlobalConstants.FileInfo;
import com.mobitide.common.utils.MFileUtil;

/**
 * @author CX
 * 
 */
public class MLibConfig {

	public static void init() {

	}

	public static void setFileFolder(String folderName) {
		FileInfo.ROOT_DIR = folderName;
		MGlobalStatic.folderName = folderName;
		MGlobalStatic.TEMP_DIR = MFileUtil.getRootDir() + File.separator
				+ MGlobalConstants.FileInfo.TEMP + File.separator;
		MGlobalStatic.DATA_STORE = MFileUtil.getDir() + File.separator
				+ MGlobalConstants.FileInfo.DATA + File.separator;
		MGlobalStatic.IMG_SAVE_STORE = MFileUtil.getRootDir() + File.separator
				+ MGlobalConstants.FileInfo.IMAGE + File.separator;

	}

}
