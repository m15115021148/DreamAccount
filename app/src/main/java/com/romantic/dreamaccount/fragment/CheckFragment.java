package com.romantic.dreamaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.eventBus.TypeEvent;
import com.romantic.dreamaccount.log.LogUtil;
import com.romantic.dreamaccount.util.time.DoubleDateSelectDialog;
import com.sensology.framelib.event.BusProvider;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ${chenM} on 2018/7/26.
 */
public class CheckFragment extends BaseFragment {
    private Disposable subscribe;
    private int type = 1;
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    @BindView(R.id.time)
    public TextView mTime;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_check_layout;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTime.setOnClickListener(this);
    }

    @Override
    protected void onStartLazy() {
        super.onStartLazy();
        registerEventBus();
    }

    @Override
    protected void onStopLazy() {
        super.onStopLazy();
        unSubscribeRxBus(subscribe);
    }

    @Override
    public void onClick(View view) {
        if (view == mTime){
            DoubleDateSelectDialog dialog = new DoubleDateSelectDialog(context);
            dialog.show();
        }
    }

    private void registerEventBus(){
        subscribe = BusProvider.getBus().toFlowable(TypeEvent.class)
                .subscribe(new Consumer<TypeEvent>() {
                    @Override
                    public void accept(TypeEvent typeEvent) throws Exception {
                        type = typeEvent.getType();
                    }
                });
    }

}
