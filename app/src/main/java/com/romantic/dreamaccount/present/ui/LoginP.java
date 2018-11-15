package com.romantic.dreamaccount.present.ui;

import com.romantic.dreamaccount.bean.LoginResult;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.http.CustomApiSubscriber;
import com.romantic.dreamaccount.http.HttpManager;
import com.romantic.dreamaccount.http.SignalUtils;
import com.romantic.dreamaccount.ui.LoginActivity;
import com.romantic.dreamaccount.util.ToastUtil;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.XApi;
import com.sensology.framelib.http.transformer.DialogTransformer;
import com.sensology.framelib.kit.Kits;
import com.sensology.framelib.mvp.present.XPresent;

import java.util.Map;

/**
 * Created by ${chenM} on 2018/11/14.
 */
public class LoginP extends XPresent<LoginActivity> {

    public void login(String userName, final String pwd) {

        Map<String, Object> params = SignalUtils.getSignal();
        params.put("name", userName);
        params.put("password", Kits.MD5.getMD5(pwd));

        HttpManager.getApiService().login(params)
                .compose(XApi.<LoginResult>getApiTransformer())
                .compose(XApi.<LoginResult>getScheduler())
                .compose(new DialogTransformer(getV()).<LoginResult>transformer())
                .compose(getV().<LoginResult>bindToLifecycle())
                .subscribe(new CustomApiSubscriber<LoginResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showBottomShort(error.getMessage());
                    }

                    @Override
                    public void onNext(LoginResult result) {
                        if (result.getResult() == Comment.SUCCESS){
                            getV().login(result,pwd);
                        }else {
                            ToastUtil.showBottomShort(result.getErrorMsg());
                        }
                    }
                });
    }

}
