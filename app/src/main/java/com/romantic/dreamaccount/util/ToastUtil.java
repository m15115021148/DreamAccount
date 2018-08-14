package com.romantic.dreamaccount.util;

import android.view.Gravity;
import android.widget.Toast;

import com.romantic.dreamaccount.application.MyApplication;

public class ToastUtil {
	private static Toast mToast;

	public ToastUtil(){
		if (mToast == null)
			mToast = new Toast(MyApplication.getInstance().getApplicationContext());
	}

	/**
	 * 显示时间两秒，位置居中
	 *
	 * @param title
	 *            显示的内容
	 */
	public static void showCenterShort(String title) {
		if (mToast != null){
			mToast.setText(title);
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			mToast.show();
		}
	}

	/**
	 * 短时间显示 位置居下
	 * 
	 * @param title
	 */
	public static void showBottomShort(String title) {
		if (mToast != null){
			mToast.setText(title);
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setGravity(Gravity.BOTTOM, 0, MyApplication.getInstance().screenHeight/100);
			mToast.show();
		}
	}
}
