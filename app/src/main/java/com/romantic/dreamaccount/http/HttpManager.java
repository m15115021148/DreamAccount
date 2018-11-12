package com.romantic.dreamaccount.http;

import com.romantic.dreamaccount.config.WebConfig;
import com.sensology.framelib.http.XApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ${chenM} on 2018/10/25.
 */
public class HttpManager {
    private static ApiService service;

    public static ApiService getApiService() {
        if (service == null) {
            synchronized (HttpManager.class) {
                if (service == null) {
                    service = XApi.getInstance().getRetrofit(WebConfig.getBaseUrl(), true).create(ApiService.class);
                }
            }
        }
        return service;
    }

    /**
     * body传递参数
     *
     * @param data json格式
     * @return
     */
    public static RequestBody getParameter(String data) {
        return RequestBody.create(MediaType.parse("application/json"), data);
    }

    public static RequestBody getParameterText(String data){
        return RequestBody.create(MediaType.parse("application/plain"), data);
    }

    /**
     * 文件 传输
     *
     * @param file
     * @return
     */
    public static MultipartBody.Part getMultipartBody(String key,File file) {
        if (file == null) return null;
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), body);
    }

}
