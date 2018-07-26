package com.romantic.dreamaccount.http.service;



import com.romantic.dreamaccount.http.retrofit.RetrofitHelper;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <p>Description:
 *
 * <p>Created by chenmeng on 2017/3/29.
 */

public class HttpManager {

    public static ApiService getApiService() {
        return RetrofitHelper.getRetrofit().create(ApiService.class);
    }

    /**
     * body传递参数
     * @param data json格式
     * @return
     */
    public static RequestBody getParameter(String data){
        return RequestBody.create(MediaType.parse("application/json"), data);
    }

}
