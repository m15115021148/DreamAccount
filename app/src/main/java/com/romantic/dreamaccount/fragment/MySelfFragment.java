package com.romantic.dreamaccount.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.MySelfListAdapter;
import com.romantic.dreamaccount.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/7/26.
 */
public class MySelfFragment extends BaseFragment implements MySelfListAdapter.OnMyselfListCallBack {
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    @BindArray(R.array.MySelfList)
    public String[] mData;
    private int[] resColor = {Color.parseColor("#32a5e3"),Color.parseColor("#32a5e3"),Color.parseColor("#32a5e3")};
    private int[] res = {R.drawable.myself_info_bg,R.drawable.myself_about_bg,R.drawable.myself_settings_bg};


    @Override
    public int getLayoutId() {
        return R.layout.fragment_myself_layout;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        MySelfListAdapter mAdapter = new MySelfListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(getData(mData));
    }

    @Override
    protected void onStartLazy() {
        super.onStartLazy();
    }

    @Override
    protected void onStopLazy() {
        super.onStopLazy();
    }

    private List<TypeBean> getData(String[] str){
        List<TypeBean> list = new ArrayList<>();
        for (int i=0;i<str.length;i++){
            TypeBean bean = new TypeBean();
            bean.setName(str[i]);
            bean.setRes(res[i]);
            bean.setResColor(resColor[i]);
            list.add(bean);
        }
        return list;
    }

    @Override
    public void onItemClickListener(int position) {

    }

}
