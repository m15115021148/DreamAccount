package com.romantic.dreamaccount.mvp.model;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.romantic.dreamaccount.bean.ClientBean;
import com.romantic.dreamaccount.http.rxjava.observable.DialogTransformer;
import com.romantic.dreamaccount.http.rxjava.observer.BaseObserver;
import com.romantic.dreamaccount.http.service.HttpManager;
import com.romantic.dreamaccount.mvp.presenter.callback.HttpRequestCallBack;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${chenM} on ${2017}.
 */
public class LoginModel implements ILoginModel{
    private Context mContext;

    public LoginModel(Context context){
        this.mContext = context;
    }

    @Override
    public void login(String data, final HttpRequestCallBack callBack){
        HttpManager.getApiService().registerApp(HttpManager.getParameter(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new DialogTransformer(mContext).<ClientBean>transformer())
                .subscribe(new BaseObserver<ClientBean>() {
                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(e.getMessage());
                    }

                    @Override
                    protected void onSuccess(ClientBean model) {
                        if (model.getResult()==200){
                            callBack.onSuccess(JSON.toJSONString(model));
                        }else{
                            callBack.onFailure(model.getReason());
                        }
                    }
                });
    }

}
