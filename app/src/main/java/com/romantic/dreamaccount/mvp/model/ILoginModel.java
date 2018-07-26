package com.romantic.dreamaccount.mvp.model;

import com.romantic.dreamaccount.mvp.presenter.callback.HttpRequestCallBack;

/**
 * Created by ${chenM} on ${2017}.
 */
public interface ILoginModel {
    void login(String data, HttpRequestCallBack callBack);
}
