package com.romantic.dreamaccount.present.fragment;

import com.romantic.dreamaccount.bean.AccountResult;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.fragment.ListFragment;
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
public class ListFragmentP extends XPresent<ListFragment> {

    public void getAccount(String startTime,String endTime) {

        Map<String, Object> params = SignalUtils.getSignal();
        params.put("userID", Comment.USER_ID);
        params.put("type", "");//类别 0 收入   1 支出  全部""
        params.put("kind", "");//类型  全部""
        params.put("startTime", startTime);//开始时间 全部""
        params.put("endTime", endTime);//结束时间  全部""
        params.put("note", "");//note 模糊查询
        params.put("page", 0);//默认0

        HttpManager.getApiService().getAccountList(params)
                .compose(XApi.<AccountResult>getApiTransformer())
                .compose(XApi.<AccountResult>getScheduler())
//                .compose(new DialogTransformer(getV().getActivity()).<AccountResult>transformer())
                .compose(getV().<AccountResult>bindToLifecycle())
                .subscribe(new CustomApiSubscriber<AccountResult>() {
                    @Override
                    protected void onFail(NetError error) {
//                        ToastUtil.showBottomShort(error.getMessage());
                    }

                    @Override
                    public void onNext(AccountResult result) {
                        if (result.getResult() == Comment.SUCCESS){
                            getV().getAccountSuccess(result);
                        }else {
                            ToastUtil.showBottomShort(result.getErrorMsg());
                        }
                    }
                });
    }

}
