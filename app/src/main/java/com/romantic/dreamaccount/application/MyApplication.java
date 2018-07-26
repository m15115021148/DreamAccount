package com.romantic.dreamaccount.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.romantic.dreamaccount.log.LogUtil;

/**
 * Created by ${chenM} on 2018/7/25.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    public int screenWidth = 0;
    public int screenHeight = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        getScreenSize();
    }


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
        LogUtil.w("height:" + screenHeight + " width:" + screenWidth);
    }

    /**
     * get version name
     *
     * @return name
     */
    public String getVersionName() {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get App versionCode
     *
     * @return code
     */
    public String getVersionCode() {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取手机设备id
     */
    @SuppressLint("MissingPermission")
    public String getDeviceID(Activity activity) {
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
