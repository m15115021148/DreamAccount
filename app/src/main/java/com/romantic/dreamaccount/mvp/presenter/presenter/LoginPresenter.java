package com.romantic.dreamaccount.mvp.presenter.presenter;

import com.romantic.dreamaccount.mvp.model.LoginModel;
import com.romantic.dreamaccount.mvp.presenter.base.BasePresenter;
import com.romantic.dreamaccount.mvp.presenter.callback.HttpRequestCallBack;
import com.romantic.dreamaccount.mvp.view.LoginView;

/**
 * Created by ${chenM} on ${2017}.
 */
public class LoginPresenter extends BasePresenter<LoginView> implements ILoginPresenter {
    private LoginModel mLoginModel;
    private LoginView mLoginView;

    public LoginPresenter(LoginModel model){
        this.mLoginModel = model;
    }

    @Override
    public void login(){
        checkViewAttach();
        mLoginView = getView();
        mLoginModel.login(mLoginView.getLogin(), new HttpRequestCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                mLoginView.showResult(result);
            }

            @Override
            public void onFailure(String error) {
                mLoginView.onFailure(error);
            }
        });
    }
}
