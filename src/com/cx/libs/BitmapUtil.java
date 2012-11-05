/**
 *    FILE: BitMapUtil.java
 *  AUTHOR: CX
 *    DATE: 2012-7-13
 *
 *   Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.cx.libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;

public class BitmapUtil {

	public static final int UNCONSTRAINED = -1;

	public static Options getOptions(String path) {
		Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		return options;
	}

	/**
	 * get bitmap without oom
	 * 
	 * @param path
	 * @param options
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap getBitmapByPath(String path, Options options, int screenWidth, int screenHeight) {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (options != null) {
			Rect r = getScreenRegion(screenWidth, screenHeight);
			int w = r.width();
			int h = r.height();
			int maxSize = w > h ? w : h;
			int inSimpleSize = computeSampleSize(options, maxSize, w * h);
			options.inSampleSize = inSimpleSize;
			options.inJustDecodeBounds = false;
		}
		Bitmap b = BitmapFactory.decodeStream(in, null, options);
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	
	
	public static Bitmap getBitmapByPath(String path) {
		if (path == null) {
			return null;
		}
		return getBitmapByPath(path, getOptions(path), GlobalConstants.screenWidth+150, GlobalConstants.screenHeight+150);
	}

	public static Bitmap getBitmapByPath(String path, int screenWidth, int screenHeight) {
		return getBitmapByPath(path, getOptions(path), screenWidth, screenHeight);
	}

	private static Rect getScreenRegion(int width, int height) {
		return new Rect(0, 0, width, height);
	}

	/**
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == UNCONSTRAINED) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == UNCONSTRAINED) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math
				.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == UNCONSTRAINED) && (minSideLength == UNCONSTRAINED)) {
			return 1;
		} else if (minSideLength == UNCONSTRAINED) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

}
