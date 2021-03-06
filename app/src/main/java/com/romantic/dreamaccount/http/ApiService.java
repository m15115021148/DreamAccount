package com.romantic.dreamaccount.http;


import com.romantic.dreamaccount.bean.AccountResult;
import com.romantic.dreamaccount.bean.BaseResult;
import com.romantic.dreamaccount.bean.KindResult;
import com.romantic.dreamaccount.bean.LoginResult;

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

    @POST("dbAction_register.do")
    @FormUrlEncoded
    Flowable<BaseResult> register(@FieldMap Map<String, Object> map);

    @POST("dbAction_login.do")
    @FormUrlEncoded
    Flowable<LoginResult> login(@FieldMap Map<String, Object> map);

    @POST("dbAction_uploadAccount.do")
    @FormUrlEncoded
    Flowable<BaseResult> uploadAccount(@FieldMap Map<String, Object> map);

    @POST("dbAction_getAccountList.do")
    @FormUrlEncoded
    Flowable<AccountResult> getAccountList(@FieldMap Map<String, Object> map);

    @POST("dbAction_getKinds.do")
    @FormUrlEncoded
    Flowable<KindResult> getKind(@FieldMap Map<String, Object> map);


    @Multipart
    @POST("/javaApis/ec/addComment")
    Flowable<BaseResult> addComment(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part... file);

}
