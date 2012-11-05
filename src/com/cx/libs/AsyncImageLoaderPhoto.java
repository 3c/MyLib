package com.cx.libs;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoaderPhoto {
	private HashMap<String, SoftReference<Drawable>> imageCache;
	private ThreadPoolExecutor executor;

	@SuppressWarnings("rawtypes")
	private BlockingQueue queue;

	@SuppressWarnings("rawtypes")
	public AsyncImageLoaderPhoto() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
		queue = new LinkedBlockingQueue();
		executor = new ThreadPoolExecutor(3, 30, 180, TimeUnit.SECONDS, queue);
	}

	public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {

		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}

		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
			}
		};

		executor.execute(new Runnable() {
			@Override
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl);// load img
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		});
		return null;
	}

	public Drawable loadImageFromUrl(String url) {

		Drawable drawable = null;
		if (url == null || "".equals(url.trim())) {
			return null;
		}
		// a url mapping a hashCode,it's unique
		String fileName = String.valueOf(url.hashCode());
		String path = GlobalConstants.DATA_STORE + fileName;
		if (FileUtil.isFileExists(path)) {
			drawable = getDrawable(path);
		} else {
			CXLOG.log("down image url == " + url);
			if (FileUtil.downFile(url, GlobalConstants.DATA_STORE, fileName)) {
				drawable = getDrawable(url);
			}
		}
		return drawable;
	}

	public Drawable getDrawable(String path) {
		return new BitmapDrawable(BitmapFactory.decodeFile(path));
	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

}
