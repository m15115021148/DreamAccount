package com.romantic.dreamaccount.http;


import com.romantic.dreamaccount.bean.BaseResult;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by ${chenM} on 2018/10/25.
 */
public interface ApiService {

    @POST("/javaApis/login/loginByPhone")
    @FormUrlEncoded
    Flowable<BaseResult> login(@FieldMap Map<String, Object> map);

    @Multipart
    @POST("/javaApis/ec/addComment")
    Flowable<BaseResult> addComment(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part... file);

}
