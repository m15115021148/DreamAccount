package com.romantic.dreamaccount.config;

import android.content.Context;

import com.romantic.dreamaccount.BuildConfig;
import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.http.CustomInterceptor;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.NetProvider;
import com.sensology.framelib.http.RequestHandler;
import com.sensology.framelib.http.XApi;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by ${chenM} on 2018/10/26.
 */
public class SDKConfig {

    public static void initHttp(Context context, String type) {
        NetProvider provider = new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[]{
                        new CustomInterceptor()
                };
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return BuildConfig.DEBUG;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public boolean dispatchProgressEnable() {
                return false;
            }
        };

        WebConfig.baseUrl = WebConfig.HOST_ADDRESS_IP_TESTING;
        if (context.getString(R.string.environment_configuration_testing).equals(type)) {
            WebConfig.baseUrl = WebConfig.HOST_ADDRESS_IP_TESTING;
        } else if (context.getString(R.string.environment_configuration_product).equals(type)) {
            WebConfig.baseUrl = WebConfig.HOST_ADDRESS_IP_PRODUCT;
        }
        XApi.registerProvider(WebConfig.getBaseUrl(), provider);
    }

}
