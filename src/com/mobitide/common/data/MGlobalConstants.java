package com.mobitide.common.data;

import com.mobitide.lib.R;

/**
 * @author CX 全局 常量
 */
public final class MGlobalConstants {

    public static final class Config {
        /**
         * 是否打印log
         */
        public final static boolean logEnabled = true;
        /**
         * 请求结果的每页返回数量
         */
        public static final int COUNT_PER_PAGE = 10;
        /**
         * 默认图片
         */
        public static final int DEFAULT_IMG_RES = R.drawable.icon;

    }

    public static final class FileInfo {

        /**
         * 文件根目录
         */
        public final static String ROOT_DIR = "oularapp";
        /**
         * 临时文件 存储 目录名
         */
        public final static String TEMP = "temp";
        /**
         * 数据 存储目录名
         */
        public final static String DATA = "data";
        /**
         * 图片存储目录名（若在该目录下存储图片，会显示在 图库 中）
         */
        public final static String IMAGE = "image";
    }

    public static final class Common {


        public final static String FILE_SUFFIX = "oular";
        public final static String SEED = "qq408366772";



    }



    /**
     * 请求结果常量标识
     * 
     * @author CX
     * 
     */
    public static final class ResultConstans {

        /**
         * 请求成功
         */
        public final static int ERROR_00 = 0;
        /**
         * 错误信息,可显示的
         */
        public final static int ERROR_01 = 1;
        /**
         * 错误信息,不用显示的
         */
        public final static int ERROR_02 = 2;
        /**
         * 服务器出错
         */
        public final static int ERROR_04 = 4;
        /**
         * 是需要用户登录的
         */
        public final static int ERROR_10 = 10;
        /**
         * 新浪微博重新绑定
         */
        public final static int ERROR_11 = 11;
        /**
         * 腾讯微博重新绑定
         */
        public final static int ERROR_12 = 12;
        /**
         * 人人重新绑定
         */
        public final static int ERROR_13 = 13;
        /**
         * 没有网络
         */
        public final static int ERROR_NO_NET = 999;

        /**
         * 未知错误。有网络，但请求失败。可能是超时，服务器不稳定等
         */
        public final static int ERROR_DONT_KNOW = -999;
    }

}
