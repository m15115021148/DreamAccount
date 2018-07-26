package com.romantic.dreamaccount.util;

import android.view.Gravity;
import android.widget.Toast;

import com.romantic.dreamaccount.application.MyApplication;

public class ToastUtil {

	/**
	 * 显示时间两秒，位置居中
	 *
	 * @param title
	 *            显示的内容
	 */
	public static void showCenterShort(String title) {
		Toast toast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), title, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/**
	 * 短时间显示 位置居下
	 * 
	 * @param title
	 */
	@SuppressWarnings("deprecation")
	public static void showBottomShort(String title) {
		Toast toast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), title, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, MyApplication.getInstance().screenHeight/100);
		toast.show();
	}
}
