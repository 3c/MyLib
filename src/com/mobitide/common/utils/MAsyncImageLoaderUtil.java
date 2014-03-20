package com.mobitide.common.utils;

import android.widget.ImageView;

import com.mobitide.lib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class MAsyncImageLoaderUtil {

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * 默认的属性。打开 内存 和 sd 卡的缓存
     */
    private DisplayImageOptions mOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
            .showStubImage(R.drawable.default_img).showImageForEmptyUri(R.drawable.default_img)
            .showImageOnFail(R.drawable.default_img).build();



    /**
     * 异步加载图片的基类
     * 
     * @param imageUrl
     * @param imageView
     * @param options
     * @param simpleImageLoadingListener
     */
    public void loadImageAsync(String imageUrl, ImageView imageView, DisplayImageOptions options,
            SimpleImageLoadingListener simpleImageLoadingListener) {
        imageLoader.displayImage(imageUrl, imageView, options, simpleImageLoadingListener);
    }


    public void clearAllCache() {
        clearDiscCache();
        clearMemoryCache();
    }

    public void clearDiscCache() {
        imageLoader.clearDiscCache();
    }

    public void clearMemoryCache() {
        imageLoader.clearMemoryCache();
    }

    /**
     * 最简单的仅获取图片的方法。在接口回调里的 onLoadingComplete 取得图片
     * 
     * @param imageUrl
     * @param simpleImageLoadingListener
     */
    public void loadImageAsync(String imageUrl, SimpleImageLoadingListener simpleImageLoadingListener) {
        imageLoader.loadImage(imageUrl, null, null, simpleImageLoadingListener);
    }

    public void loadImageAsync(String imageUrl, DisplayImageOptions options,
            SimpleImageLoadingListener simpleImageLoadingListener) {
        imageLoader.loadImage(imageUrl, null, options, simpleImageLoadingListener);
    }


    /**
     * 不需要回调的加载图片，可以传入DisplayImageOptions
     * 
     * @param imageUrl
     * @param imageView
     * @param options
     */
    public void loadImageAsync(String imageUrl, ImageView imageView, DisplayImageOptions options) {
        loadImageAsync(imageUrl, imageView, options, null);
    }

    /**
     * 带回调的加载图片。回调的接口可以用于监听加载前，加载完的状态，以实现类似效果：图片加载前显示进度条，图片加载完进度条消失
     * 
     * @param imageUrl
     * @param imageView
     * @param simpleImageLoadingListener
     */
    public void loadImageAsync(String imageUrl, ImageView imageView,
            SimpleImageLoadingListener simpleImageLoadingListener) {
        loadImageAsync(imageUrl, imageView, mOptions, simpleImageLoadingListener);
    }

    /**
     * 不需要回调的加载图片，应该是最常用的
     * 
     * @param imageUrl
     * @param imageView
     */
    public void loadImageAsync(String imageUrl, ImageView imageView) {
        loadImageAsync(imageUrl, imageView, mOptions);
    }

    public void loadImageAsync(String imageUrl, ImageView imageView, int defaultImg) {
        loadImageAsync(imageUrl, imageView, new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
                .showStubImage(defaultImg).showImageForEmptyUri(defaultImg).showImageOnFail(defaultImg).build());
    }


    public void loadImageAsync(String imageUrl, ImageSize imageSize,
            SimpleImageLoadingListener simpleImageLoadingListener) {
        imageLoader.loadImage(imageUrl, imageSize, mOptions, simpleImageLoadingListener);
    }

    // DON'T COPY THIS CODE TO YOUR PROJECT! This is just example of ALL options using.
    // DisplayImageOptions options = new DisplayImageOptions.Builder()
    // .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
    // .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
    // .showImageOnFail(R.drawable.ic_error) // resource or drawable
    // .resetViewBeforeLoading(false) // default
    // .delayBeforeLoading(1000)
    // .cacheInMemory(false) // default
    // .cacheOnDisc(false) // default
    // .preProcessor(...)
    // .postProcessor(...)
    // .extraForDownloader(...)
    // .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
    // .bitmapConfig(Bitmap.Config.ARGB_8888) // default
    // .decodingOptions(...)
    // .displayer(new SimpleBitmapDisplayer()) // default
    // .handler(new Handler()) // default
    // .build();

}
