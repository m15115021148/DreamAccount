package com.romantic.dreamaccount.mvp.presenter.callback;

/**
 * Created by ${chenM} on 2018/7/24.
 */
public interface HttpRequestProgressCallBack<T> extends HttpCallBack<T>{
    void onProgress(long byteReads, long contentReads);
}
