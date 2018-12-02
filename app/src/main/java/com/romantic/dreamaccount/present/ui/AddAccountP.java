package com.romantic.dreamaccount.present.ui;

import com.romantic.dreamaccount.bean.KindResult;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.http.CustomApiSubscriber;
import com.romantic.dreamaccount.http.HttpManager;
import com.romantic.dreamaccount.ui.AddAccountActivity;
import com.romantic.dreamaccount.util.ToastUtil;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.XApi;
import com.sensology.framelib.http.transformer.DialogTransformer;
import com.sensology.framelib.mvp.present.XPresent;

import java.util.HashMap;

/**
 * Created by ${chenM} on 2018/11/20.
 */
public class AddAccountP extends XPresent<AddAccountActivity> {


    public void getKind() {

        HttpManager.getApiService().getKind(new HashMap<String, Object>())
                .compose(XApi.<KindResult>getApiTransformer())
                .compose(XApi.<KindResult>getScheduler())
//                .compose(new DialogTransformer(getV()).<KindResult>transformer())
                .compose(getV().<KindResult>bindToLifecycle())
                .subscribe(new CustomApiSubscriber<KindResult>() {
                    @Override
                    protected void onFail(NetError error) {
//                        ToastUtil.showBottomShort(error.getMessage());
                    }

                    @Override
                    public void onNext(KindResult result) {
                        if (result.getResult() == Comment.SUCCESS){
                            getV().getKindSuccess(result);
                        }else {
                            ToastUtil.showBottomShort(result.getErrorMsg());
                        }
                    }
                });
    }

}
