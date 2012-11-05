package com.cx.libs;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * soft key board help
 * 
 * @author CX
 * 
 */
public class SoftKeyboardUtil {
	private Context mContext;

	public SoftKeyboardUtil(Context context) {
		this.mContext = context;
	}

	/**
	 * show soft keyboard
	 */
	public void ShowSoftKeyboard(int index) {
		if (index == 0) {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					InputMethodManager m = (InputMethodManager) mContext
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					m.showSoftInput(((Activity) mContext).getCurrentFocus(), 0);
				}
			}, 1000);
		} else {
			InputMethodManager m = (InputMethodManager) mContext
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			m.showSoftInput(((Activity) mContext).getCurrentFocus(), 0);

		}
	}

	/**
	 * hide soft keyboard
	 */
	public void HideSoftKeyboard() {
		InputMethodManager m = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (m != null && ((Activity) mContext).getCurrentFocus() != null) {
			m.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus()
					.getWindowToken(), 0);
		}
	}

}
