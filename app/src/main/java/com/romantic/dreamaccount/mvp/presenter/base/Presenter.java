package com.romantic.dreamaccount.mvp.presenter.base;

import com.romantic.dreamaccount.mvp.view.BaseView;

/**
 * Created by ${chenM} on ${2017}.
 */
public interface Presenter<V extends BaseView> {
    void attachView(V baseView);//binding
    void detachView();//unbinding
}
