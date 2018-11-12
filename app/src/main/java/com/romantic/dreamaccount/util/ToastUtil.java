package com.romantic.dreamaccount.util;

import android.view.Gravity;
import android.widget.Toast;

import com.romantic.dreamaccount.application.MyApplication;

/**
 * Created by ${chenM} on 2018/9/18.
 */
public class ToastUtil {
    private static Toast mToast;

    /**
     * 长时间显示 位置居中
     *
     * @param title 显示的内容
     */
    public static void showCenterLong(String title) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), title, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 显示时间两秒，位置居中
     *
     * @param title 显示的内容
     */
    public static void showCenterShort(String title) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), title, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 长时间显示 位置居下
     *
     * @param title
     */
    @SuppressWarnings("deprecation")
    public static void showBottomLong(String title) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), title, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.BOTTOM, 0, MyApplication.getInstance().screenHeight / 10);
        mToast.show();
    }

    /**
     * 短时间显示 位置居下
     *
     * @param title
     */
    @SuppressWarnings("deprecation")
    public static void showBottomShort(String title) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), title, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.BOTTOM, 0, MyApplication.getInstance().screenHeight / 10);
        mToast.show();
    }
}
