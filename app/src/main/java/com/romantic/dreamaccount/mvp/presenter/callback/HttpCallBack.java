package com.romantic.dreamaccount.mvp.presenter.callback;

/**
 * Created by ${chenM} on 2018/7/24.
 */
public interface HttpCallBack<T> {
    void onSuccess(T result);
    void onFailure(String error);
}
