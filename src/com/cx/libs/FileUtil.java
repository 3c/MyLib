/**
 *    FILE: FileUtil.java
 *  AUTHOR: CX
 *    DATE: 2012-7-2
 *
 *   Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.cx.libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * @author CX
 */
public class FileUtil {

	public static boolean downFile(String url, String tempDir, String savePath, String fileName) {
		boolean isFinish = false;
		long fileLength;
		File tempFile;
		try {
			URL myUrl = new URL(url);
			URLConnection conn = myUrl.openConnection();
			conn.connect();
			fileLength = conn.getContentLength();
			InputStream is = conn.getInputStream();
			if (is == null) {
				isFinish = false;
			}
			tempFile = new File(tempDir + fileName);
			FileOutputStream fos = new FileOutputStream(tempFile);
			byte[] buf = new byte[4096];
			int numread;
			while (true) {
				numread = is.read(buf);
				if (numread <= 0) {
					break;
				}
				fos.write(buf, 0, numread);
			}
			fos.flush();
			fos.getFD().sync();
			fos.close();
			is.close();
			if (fileLength == tempFile.length()) {
				isFinish = moveFile(tempFile.getAbsolutePath(), savePath);
			}
			tempFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isFinish;
	}

	/**
	 * savePath need contain "/" in end
	 * 
	 * @param url
	 * @param savePath
	 * @param fileName
	 * @return
	 */
	public static boolean downFile(String url, String savePath, String fileName) {
		return downFile(url, GlobalConstants.TEMP_DIR, savePath, fileName);
	}

	/**
	 * 移动文件
	 * 
	 * @param srcFileName
	 *            源文件完整路径
	 * @param destDirName
	 *            目的目录完整路径
	 * @return 文件移动成功返回true，否则返回false
	 */
	public static boolean moveFile(String srcFileName, String destDirName) {

		File srcFile = new File(srcFileName);

		if (!srcFile.exists() || !srcFile.isFile())
			return false;

		File destDir = new File(destDirName);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		// System.out.println("file path " + destDirName + srcFile.getName());
		return srcFile.renameTo(new File(destDirName + srcFile.getName()));
	}

	public static void deleteFilesAll(File file) {
		if (file.exists()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile()) {
					f.delete();
				} else {
					deleteFilesAll(f);
				}
			}
			file.delete();
		}
	}


	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * @param path
	 * @return true if file is exits,otherwise false
	 */
	public static boolean isFileExists(String path) {
		return new File(path).exists();
	}

	public static String padString(String s, int length) {
		return padString(s, ' ', length);
	}

	public static String padString(String s, char padChar, int length) {
		int slen, numPads = 0;
		if (s == null) {
			s = "";
			numPads = length;
		} else if ((slen = s.length()) > length) {
			s = s.substring(0, length);
		} else if (slen < length) {
			numPads = length - slen;
		}
		if (numPads == 0) {
			return s;
		}
		char[] c = new char[numPads];
		Arrays.fill(c, padChar);
		return s + new String(c);
	}

	public static String rightPadString(String s, int length) {
		return (rightPadString(s, ' ', length));
	}

	public static String rightPadString(String s, char padChar, int length) {
		int slen, numPads = 0;
		if (s == null) {
			s = "";
			numPads = length;
		} else if ((slen = s.length()) > length) {
			s = s.substring(length);
		} else if (slen < length) {
			numPads = length - slen;
		}
		if (numPads == 0) {
			return (s);
		}
		char[] c = new char[numPads];
		Arrays.fill(c, padChar);
		return new String(c) + s;
	}

	public static boolean createNewFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				return file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void createNewFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			String newPathFolder = newPath.substring(0, newPath.lastIndexOf("/"));
			File folder = new File(newPathFolder);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[4096];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
				// ToastUtil.log("sum== "+bytesum+" filelength== "+oldfile.length());
				if (bytesum == oldfile.length()) {
					ToastUtil.show(GlobalApplication.mInstance.getString(R.string.picture_save_success) + newPath);
				} else {
					ToastUtil.show(GlobalApplication.mInstance.getString(R.string.picture_save_failure));
				}

			} else {
				ToastUtil.show(GlobalApplication.mInstance.getString(R.string.picture_loading_wait));
			}
		} catch (IOException e) {
			ToastUtil.show(GlobalApplication.mInstance.getString(R.string.file_read_error));
			e.printStackTrace();
		}
	}

	public static void saveImgFile(String filePath) {
		if (filePath == null) {
			ToastUtil.show(GlobalApplication.mInstance.getString(R.string.picture_loading_wait));
		} else {
			File file = new File(filePath);
			if (file.exists()) {
				String fileName = file.getName();
				FileUtil.copyFile(filePath, GlobalConstants.IMG_SAVE_STORE + fileName + ".jpg");
			} else {
				ToastUtil.show(GlobalApplication.mInstance.getString(R.string.picture_loading_wait));
			}

		}
	}

}
