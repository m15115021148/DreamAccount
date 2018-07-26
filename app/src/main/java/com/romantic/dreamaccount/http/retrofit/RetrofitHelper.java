package com.romantic.dreamaccount.http.retrofit;


import com.romantic.dreamaccount.config.WebConfig;
import com.romantic.dreamaccount.http.okhttp.OkHttpHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>Description:
 * <p>
 * <p>Created by chenmeng on 2017/9/3.
 */

public class RetrofitHelper {
    private static Retrofit retrofit;


    private RetrofitHelper() {
    }

    private static void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(WebConfig.getHostName())
                .client(OkHttpHelper.getClient())
                .addConverterFactory(StringConverterFactory.create()) //String 转换
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .validateEagerly(true)
                .build();
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitHelper.class) {
                init();
            }
        }
        return retrofit;
    }

}
