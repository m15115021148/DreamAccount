package com.romantic.dreamaccount.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.FragmentListAdapter;
import com.romantic.dreamaccount.log.LogUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/7/26.
 */
public class ListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private FragmentListAdapter mAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_list_layout;
    }

    @Override
    protected void startLoad() {
        if (mAdapter!=null)mAdapter.setData(getData());
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new FragmentListAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getData(){
        String[] str = {"Java","Android","C++","PHP","JS","IOS"};
        return Arrays.asList(str);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("onDestroyView");
    }
}
