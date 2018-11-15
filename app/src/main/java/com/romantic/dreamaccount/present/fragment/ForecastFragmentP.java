package com.romantic.dreamaccount.present.fragment;

import com.romantic.dreamaccount.bean.AccountsBean;
import com.romantic.dreamaccount.bean.BaseResult;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.fragment.ForecastFragment;
import com.romantic.dreamaccount.http.CustomApiSubscriber;
import com.romantic.dreamaccount.http.HttpManager;
import com.romantic.dreamaccount.http.SignalUtils;
import com.romantic.dreamaccount.util.ToastUtil;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.XApi;
import com.sensology.framelib.http.transformer.DialogTransformer;
import com.sensology.framelib.mvp.present.XPresent;

import java.util.Map;

/**
 * Created by ${chenM} on 2018/11/15.
 */
public class ForecastFragmentP extends XPresent<ForecastFragment> {

    public void uploadAccount(AccountsBean bean) {

        Map<String, Object> params = SignalUtils.getSignal();
        params.put("userID", Comment.USER_ID);
        params.put("type", bean.getType());
        params.put("kind", bean.getKind());
        params.put("money", bean.getMoney());
        params.put("note", bean.getNote());
        params.put("time", bean.getTime());
        params.put("lat", bean.getLat());
        params.put("lng", bean.getLng());
        params.put("address", bean.getAddress());

        HttpManager.getApiService().uploadAccount(params)
                .compose(XApi.<BaseResult>getApiTransformer())
                .compose(XApi.<BaseResult>getScheduler())
                .compose(new DialogTransformer(getV().getActivity()).<BaseResult>transformer())
                .compose(getV().<BaseResult>bindToLifecycle())
                .subscribe(new CustomApiSubscriber<BaseResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtil.showBottomShort(error.getMessage());
                    }

                    @Override
                    public void onNext(BaseResult result) {
                        if (result.getResult() == Comment.SUCCESS){
                            getV().uploadAccountSuccess();
                        }else {
                            ToastUtil.showBottomShort(result.getErrorMsg());
                        }
                    }
                });
    }

}
