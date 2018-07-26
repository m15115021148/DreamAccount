package com.romantic.dreamaccount.http.upload;


/**
 * 上传接口
 * Created by chenMeng on 2017/9/6.
 */
public interface UpLoadListener {
    void upLoad(long bytesRead, long contentLength);
    void onSuccess(String result);
    void onFailure(Throwable t);
}
