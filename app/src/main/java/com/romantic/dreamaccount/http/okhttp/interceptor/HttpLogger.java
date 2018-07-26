package com.romantic.dreamaccount.http.okhttp.interceptor;


import com.romantic.dreamaccount.log.LogUtil;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by chenMeng on 2018/1/4.
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        LogUtil.w("jack", message);
    }
}
