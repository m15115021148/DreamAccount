package com.romantic.dreamaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.FragmentListAdapter;
import com.romantic.dreamaccount.bean.AccountResult;
import com.romantic.dreamaccount.present.fragment.ListFragmentP;
import com.sensology.framelib.kit.Kits;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/7/26.
 */
public class ListFragment extends BaseFragment<ListFragmentP> implements FragmentListAdapter.OnFragmentListCallBack {
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private FragmentListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_layout;
    }

    @Override
    public ListFragmentP newP() {
        return new ListFragmentP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new FragmentListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStartLazy() {
        super.onStartLazy();
        getP().getAccount(Kits.Date.getCurrentAgeTime(24*3),Kits.Date.getCurrentTime());
    }

    @Override
    protected void onStopLazy() {
        super.onStopLazy();
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onLocationListener(int position) {

    }

    public void getAccountSuccess(AccountResult result){
        mAdapter.setData(result.getData());
    }
}
