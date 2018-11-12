package com.romantic.dreamaccount.http;

import com.romantic.dreamaccount.eventBus.ExitAppEvent;
import com.sensology.framelib.event.BusProvider;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${chenM} on 2018/10/25.
 */
public class CustomInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        if (response.code() == 401) {
            BusProvider.getBus().post(new ExitAppEvent(true, true));
            throw new IOException("登录已过期或者您的账号在其他手机上登录");
        } else if (response.code() != 200) {
            throw new IOException("您的网络异常，请稍后重试。");
        }

        return response;
    }
}
