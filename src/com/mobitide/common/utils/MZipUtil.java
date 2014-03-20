/**
 *    FILE: UtilZip.java
 *  AUTHOR: CX
 *    DATE: 2012-4-5
 *
 *   Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.mobitide.common.data.MGlobalStatic;

/**
 * @author CX
 */
public class MZipUtil {

	// /**
	// * 解压缩一个文件
	// *
	// * @param zipFile
	// * 压缩文件
	// * @param folderPath
	// * 解压缩的目标目录
	// * @throws IOException
	// * 当解压缩过程出错时抛出
	// */
	// public static void unZip(String zipFileName) throws Exception {
	// ////APPLOG.d(zipFileName);
	// //APPLOG.d("unzip file path "+zipFileName);
	// // String fileName = zipFileName.substring(zipFileName.lastIndexOf("/") +
	// 1, zipFileName.lastIndexOf("."));
	// File outFile = new File(DataSet.RES_STORE);
	// if (!outFile.exists()) {
	// outFile.mkdirs();
	// }
	// ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));
	// ZipEntry z;
	// String name = "";
	// String extractedFile = "";
	// int counter = 0;
	//
	//
	//
	// while ((z = in.getNextEntry()) != null) {
	// name = z.getName();
	// //APPLOG.d("name= "+name+" isDir= "+z.isDirectory());
	// if (z.isDirectory()) {
	// name = name.substring(0, name.length() - 1);
	// File folder = new File(outFile +File.separator + name);
	// folder.mkdirs();
	// if (counter == 0) {
	// extractedFile = folder.toString();
	// }
	// counter++;
	// } else {
	// File file = new File(outFile +File.separator + name);
	// //APPLOG.d("unzip file is "+file.getAbsolutePath());
	// // FileUtil.createNewFolder(file.getParent());
	// // file.createNewFile();
	// // FileOutputStream out = new FileOutputStream(file);
	// // int ch;
	// // byte[] buffer = new byte[4096];
	// // while ((ch = in.read(buffer)) != -1) {
	// // out.write(buffer, 0, ch);
	// // out.flush();
	// // }
	// // out.close();
	// }
	// }
	//
	// in.close();
	//
	// }


	/**
	 * 解压缩功能. 将zipFile文件解压到folderPath目录下.
	 * 
	 * @throws Exception
	 */
	public static void unZip(String zipFilePath, String folderPath) throws ZipException, IOException {
		File zipFile = new File(zipFilePath);
		if (!zipFile.exists()) {
			return;
		}
		ZipFile zfile = new ZipFile(zipFile);
		Enumeration zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[4096];
		while (zList.hasMoreElements()) {
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				String dirstr = folderPath + ze.getName();
				File f = new File(dirstr);
				f.mkdir();
				continue;
			}
			//APPLOG.d("zip  folder path " + folderPath + "zip.getName() = " + ze.getName());
			OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
		}
		zfile.close();
	}
	
	
    /**
    * 给定根目录，返回一个相对路径所对应的实际文件名.
    * @param baseDir 指定根目录
    * @param absFileName 相对路径名，来自于ZipEntry中的name
    * @return java.io.File 实际的文件
*/
    public static File getRealFileName(String baseDir, String absFileName){
        String[] dirs=absFileName.split("/");
        File ret=new File(baseDir);
        String substr = null;
        if(dirs.length>1){
            for (int i = 0; i < dirs.length-1;i++) {
                substr = dirs[i];
                ret=new File(ret, substr);
            }
            if(!ret.exists())
                ret.mkdirs();
            substr = dirs[dirs.length-1];
            ret=new File(ret, substr);
            return ret;
        }else{
        	return new File(baseDir+absFileName);
        }
    }
}
