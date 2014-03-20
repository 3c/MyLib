/**
 * FILE: FileUtil.java AUTHOR: CX DATE: 2012-7-2
 * 
 * Copyright(c) 2011 Mobitide Android Team. All Rights Reserved.
 */
package com.mobitide.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.Environment;

import com.mobitide.common.data.MGlobalConstants;
import com.mobitide.common.data.MGlobalContext;
import com.mobitide.common.data.MGlobalStatic;
import com.mobitide.lib.R;

/**
 * @author CX
 */
public class MFileUtil {

    /**
     * 获取 存储 目录
     * 
     * @return
     */
    public static String getDir() {

        return getRootDir() + File.separator + MGlobalStatic.folderName;

    }

    /**
     * 
     * @return 程序根目录
     */
    public static String getRootDir() {
        if (MSdCardUtil.sdCardEnabled()) {
            return Environment.getExternalStorageDirectory() + File.separator + MGlobalConstants.FileInfo.ROOT_DIR;
        }
        // 若SD卡不可用，在 程序 内部创建文件夹，但是图片不应该存在这里，太占空间。
        return MGlobalContext.getContext().getFilesDir().getAbsolutePath() + File.separator
                + MGlobalConstants.FileInfo.ROOT_DIR;

    }

    /**
     * 将从网络请求到的数据保存到sd卡中
     * 
     * @param object
     * @param name
     */
    public static void saveObject(Object object, String name) {
        FileOutputStream f_out = null;
        try {
            f_out = new FileOutputStream(MGlobalStatic.DATA_STORE + name + MGlobalConstants.Common.FILE_SUFFIX);
            ObjectOutputStream s = new ObjectOutputStream(f_out);
            s.writeObject(object);
            s.flush();
            s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (f_out != null) {
                try {
                    f_out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 从sd卡中读取列表
     * 
     * @param name
     * @return
     */
    public static Object readObject(String name) {

        FileInputStream f_in = null;
        Object object = null;
        try {
            f_in = new FileInputStream(MGlobalStatic.DATA_STORE + name + MGlobalConstants.Common.FILE_SUFFIX);
            ObjectInputStream s = new ObjectInputStream(f_in);
            object = s.readObject();
            s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            if (f_in != null) {
                try {
                    f_in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return object;

    }

    public static boolean createNewFileEndWithOular(String path) {
        return createNewFile(path + MGlobalConstants.Common.FILE_SUFFIX);
    }

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
        return downFile(url, MGlobalStatic.TEMP_DIR, savePath, fileName);
    }

    /**
     * 移动文件
     * 
     * @param srcFileName 源文件完整路径
     * @param destDirName 目的目录完整路径
     * @return 文件移动成功返回true，否则返回false
     */
    public static boolean moveFile(String srcFileName, String destDirName) {

        File srcFile = new File(srcFileName);

        if (!srcFile.exists() || !srcFile.isFile()) return false;

        File destDir = new File(destDirName);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        // APPLOG.log("file path " + destDirName + srcFile.getName());
        return srcFile.renameTo(new File(destDirName + srcFile.getName()));
    }

    /**
     * 删除包含该文件夹在内的所有文件
     * 
     * @param file
     */
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

    /**
     * 删除该文件夹内所有文件，不包含该文件夹
     * 
     * @param file
     */
    public static void deleteFiles(String path) {
        File fileDel = new File(path);
        if (fileDel.exists()) {
            File[] files = fileDel.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

    /**
     * 删除单个文件
     * @param path
     */
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
                    MToastUtil.show(MResUtil.getString(R.string.picture_save_success) + newPath);
                } else {
                    MToastUtil.show(MResUtil.getString(R.string.picture_save_failure));
                }

            } else {
                MToastUtil.show(MResUtil.getString(R.string.picture_loading_wait));
            }
        } catch (IOException e) {
            MToastUtil.show(MResUtil.getString(R.string.file_read_error));
            e.printStackTrace();
        }
    }

    public static void saveImgFile(String filePath) {
        if (filePath == null) {
            MToastUtil.show(MResUtil.getString(R.string.picture_loading_wait));
        } else {
            String realPath = MGlobalStatic.DATA_STORE + filePath;
            File file = new File(realPath);
            if (file.exists()) {
                String fileName = file.getName();
                MFileUtil.copyFile(realPath, MGlobalStatic.IMG_SAVE_STORE + fileName + ".jpg");
            } else {
                MToastUtil.show(MResUtil.getString(R.string.picture_loading_wait));
            }

        }
    }

}
