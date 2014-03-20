package com.mobitide.common.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 全局Application 在AndroidManifest.xml配置此类 如果有自己的Application，继承此类
 * 
 * @author CX
 * 
 */
public class MGlobalApplication extends Application {

    /**
     * 程序实例。
     */
    private static MGlobalApplication mInstance;



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        init(mInstance);

    }


    /**
     * @param context
     */
    private void init(Context context) {
        initImageLoader();
    }


    private MGlobalApplication() {
        // Exists only to defeat instantiation.
    }

    public static MGlobalApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MGlobalApplication();
        }
        return mInstance;
    }

    /**
     * 初始化图片加载配置
     * 
     * @param context
     */
    private void initImageLoader() {
        // config more:https://github.com/nostra13/Android-Universal-Image-Loader
        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(config);
    }



}
