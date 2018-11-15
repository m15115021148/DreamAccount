package com.romantic.dreamaccount.fragment;

import android.view.View;

import com.romantic.dreamaccount.ui.BaseActivity;
import com.sensology.framelib.mvp.fragment.XFragment;
import com.sensology.framelib.mvp.present.IPresent;

import io.reactivex.disposables.Disposable;


/**
 * Created by ${chenM} on ${2017}.
 */
public abstract class BaseFragment<P extends IPresent> extends XFragment<P> implements View.OnClickListener  {

    @Override
    public void onClick(View view) {
        if (getActivity() !=null && getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).clickVibrator();
        }
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    protected void unSubscribeRxBus(Disposable disposable){
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
