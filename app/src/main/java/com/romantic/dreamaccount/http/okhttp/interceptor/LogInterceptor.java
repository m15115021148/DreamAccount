package com.romantic.dreamaccount.http.okhttp.interceptor;


import com.romantic.dreamaccount.log.LogUtil;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 日志拦截器 打印返回的json数据拦截器
 * Created by chenMeng on 2017/9/4.
 */
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Buffer requestBuffer = new Buffer();
        if (request.body() != null) {
            request.body().writeTo(requestBuffer);
        } else {
//                Log.d("jack", "request.body() == null");
        }
        //打印url信息
        LogUtil.w("jack", "url:"+ URLDecoder.decode(request.url().toString(), "utf-8"));

        //打印得到的数据
        Response response = chain.proceed(request);
        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        LogUtil.i("jack","result:"+buffer.clone().readString(Charset.forName("utf-8")));
        return response;
    }
}
