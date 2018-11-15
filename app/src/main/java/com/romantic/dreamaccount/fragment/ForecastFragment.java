package com.romantic.dreamaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.ForecastListAdapter;
import com.romantic.dreamaccount.application.MyApplication;
import com.romantic.dreamaccount.eventBus.RefreshEvent;
import com.romantic.dreamaccount.present.fragment.ForecastFragmentP;
import com.romantic.dreamaccount.util.ToastUtil;
import com.sensology.framelib.event.BusProvider;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by ${chenM} on 2018/8/1.
 */
public class ForecastFragment extends BaseFragment<ForecastFragmentP> implements ForecastListAdapter.OnCallBackForecast {
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private ForecastListAdapter mAdapter;
    private int currPosition = 0;
    private Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forecast_layout;
    }

    @Override
    public ForecastFragmentP newP() {
        return new ForecastFragmentP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        registerEventBus();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new ForecastListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(MyApplication.getInstance().Db.getData());
    }

    @Override
    protected void onStartLazy() {
        super.onStartLazy();
    }

    @Override
    protected void onStopLazy() {
        super.onStopLazy();
    }

    @Override
    protected void onDestroyLazy() {
        super.onDestroyLazy();
        unSubscribeRxBus(disposable);
    }

    public void uploadAccountSuccess() {
        ToastUtil.showBottomShort("success");
        MyApplication.getInstance().Db.delOneAccount(mAdapter.getData().get(currPosition).getId());
        BusProvider.getBus().post(new RefreshEvent(true));
    }

    @Override
    public void onUploadListener(int position) {
        currPosition = position;
        getP().uploadAccount(mAdapter.getData().get(position));
    }

    private void registerEventBus(){
        disposable = BusProvider.getBus().toFlowable(RefreshEvent.class)
                .subscribe(new Consumer<RefreshEvent>() {
                    @Override
                    public void accept(RefreshEvent refreshEvent) throws Exception {
                        if (refreshEvent.isRefresh()) {
                            mAdapter.clearData();
                            mAdapter.setData(MyApplication.getInstance().Db.getData());
                        }
                    }
                });
    }
}
