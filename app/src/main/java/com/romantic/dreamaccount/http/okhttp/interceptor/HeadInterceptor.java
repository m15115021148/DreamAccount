package com.romantic.dreamaccount.http.okhttp.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求添加头部
 * Created by chenMeng on 2017/9/14.
 */

public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .build();
        return chain.proceed(request);
    }
}
