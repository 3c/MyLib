package com.cx.libs;

import android.app.Application;

import com.cx.exception.CrashHandler;

public class GlobalApplication extends Application {

	public static Application mInstance;

	public void onCreate() {
		super.onCreate();
		mInstance = this;
		// 异常处理，不需要处理时注释掉这两句即可！
		CrashHandler crashHandler = CrashHandler.getInstance();
		// 注册crashHandler
		crashHandler.init(getApplicationContext());

	};
}
