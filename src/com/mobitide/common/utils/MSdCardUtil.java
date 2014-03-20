package com.mobitide.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import com.mobitide.common.application.MGlobalApplication;
import com.mobitide.common.data.MGlobalContext;

public class MSdCardUtil {

	/**
	 * 判断sd卡是否可用
	 * 
	 * @return true if sdcard is enabled
	 */
	public static boolean sdCardEnabled() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 获得sdcard的可用空间大小
	 * 
	 * @return
	 */
	public static long getSDCardAvailaleSize() {

		if (!isSDCardSizeAvailable()) {
			return 0;
		}
		File path = Environment.getExternalStorageDirectory();

		StatFs stat = new StatFs(path.getPath());

		long blockSize = stat.getBlockSize();

		long availableBlocks = stat.getAvailableBlocks();

		return availableBlocks * blockSize / (1024 * 1024);

	}

	public static boolean isSDCardSizeAvailable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取手机可用的内存空间 返回 单位 M
	 * 
	 * @return
	 */
	public float getMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long sumBlocks = stat.getBlockCount();
		// return (sumBlocks * blockSize) * 1.0f / (1024 * 1024);
		return (availableBlocks * blockSize) * 1.0f / (1024 * 1024);
	}

	// 获取手机总的内存空间 返回 单位 M
	public float getTotalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long sumBlocks = stat.getBlockCount();
		return (sumBlocks * blockSize) * 1.0f / (1024 * 1024);
		// return (availableBlocks * blockSize) * 1.0f / (1024 * 1024);
	}

	// 获取手机可用的内存空间 返回 单位 G
	public float getSDSize() {
		String state = Environment.getExternalStorageState();
		// SD卡不可用
		if (!Environment.MEDIA_MOUNTED.equals(state)) {
			return -1;

		}

		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long sumBlocks = stat.getBlockCount();
		return (availableBlocks * blockSize) * 1.0f / (1024 * 1024);
		// return (sumBlocks * blockSize) * 1.0f / (1024 * 1024 );

	}

	// 获取手机可用的内存空间 返回 单位 G
	public float getSDTotalSize() {
		String state = Environment.getExternalStorageState();
		// SD��������
		if (!Environment.MEDIA_MOUNTED.equals(state)) {
			return -1;

		}

		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long sumBlocks = stat.getBlockCount();
		// return (availableBlocks * blockSize) * 1.0f / (1024 * 1024 );
		return (sumBlocks * blockSize) * 1.0f / (1024 * 1024);

	}

	@SuppressLint("NewApi")
	private String getAvailMemory() {// 获取android当前可用内存大小
		ActivityManager am = (ActivityManager) MGlobalContext.getContext().getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi); // mi.availMem; 当前系统的可用内存
		return Formatter.formatFileSize(MGlobalContext.getContext(), mi.availMem);// 将获取的内存大小规格化
	}

	@SuppressLint("NewApi")
	private String getTotalMemory() {
		String str1 = "/proc/meminfo";// 系统内存信息文件
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
			str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
			arrayOfString = str2.split("\\s+");
			for (String num : arrayOfString) {
				Log.i(str2, num + "\t");
			}
			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
			localBufferedReader.close();
		} catch (IOException e) {

		}
		return Formatter.formatFileSize(MGlobalContext.getContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化
	}

}
