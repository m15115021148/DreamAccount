package com.romantic.dreamaccount.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.FragmentListAdapter;
import com.romantic.dreamaccount.bean.AccountsBean;
import com.romantic.dreamaccount.present.fragment.ListFragmentP;

import java.util.ArrayList;
import java.util.List;

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
        if (mAdapter != null) mAdapter.setData(getData());
    }

    @Override
    protected void onStopLazy() {
        super.onStopLazy();
    }

    private List<AccountsBean> getData() {
        List<AccountsBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AccountsBean bean = new AccountsBean();
            bean.setType(i % 2);
            bean.setKind("消费" + i);
            bean.setTime(String.valueOf(2018 + i));
            bean.setMoney(100 + i);
            bean.setNote("test" + i);
            bean.setAddress("China");
            list.add(bean);
        }
        return list;
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onLocationListener(int position) {

    }
}
