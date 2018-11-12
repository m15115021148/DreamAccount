package com.romantic.dreamaccount.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.config.SDKConfig;
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
        initConfig();
    }


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    private void initConfig(){
        SDKConfig.initHttp(getApplicationContext(),environmentConfiguration());
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

    private String environmentConfiguration() {
        String type = getString(R.string.environment_configuration_testing);
        try {
            type = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA)
                    .metaData.getString("type");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return type;
    }

}
