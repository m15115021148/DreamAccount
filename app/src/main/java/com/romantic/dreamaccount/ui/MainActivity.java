package com.romantic.dreamaccount.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.MainFragmentAdapter;
import com.romantic.dreamaccount.fragment.BaseFragment;
import com.romantic.dreamaccount.fragment.CheckFragment;
import com.romantic.dreamaccount.fragment.ForecastFragment;
import com.romantic.dreamaccount.fragment.ListFragment;
import com.romantic.dreamaccount.fragment.MySelfFragment;
import com.romantic.dreamaccount.present.ui.MainP;
import com.sensology.framelib.router.Router;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseActivity<MainP> implements TabLayout.OnTabSelectedListener {
    private MainActivity mContext;
    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    public ViewPager mViewPager;//viewPager
    private List<BaseFragment> mFragmentList = new ArrayList<>();//fragment集合
    private MainFragmentAdapter mAdapter;
    @BindArray(R.array.MainTabLayout)
    public String[] mData;
    @BindView(R.id.add)
    public ImageView mAdd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainP newP() {
        return new MainP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mContext = this;
        mAdd.setOnClickListener(this);
        initTabLayout();
    }

    @Override
    public void onClick(View v) {
        if (v == mAdd){
            Router.newIntent(context)
                    .to(AddAccountActivity.class)
                    .launch();
        }
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    private void initTabLayout() {
        mFragmentList.add(new ListFragment());
        mFragmentList.add(new ForecastFragment());
        mFragmentList.add(new CheckFragment());
        mFragmentList.add(new MySelfFragment());

        mTabLayout.removeAllTabs();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mViewPager.setOffscreenPageLimit(3);
        mAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mData, mFragmentList, mContext);
        mViewPager.setAdapter(mAdapter);
        //绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                View v = mAdapter.getView(i);
                if (i == 0) {//默认第一个选中
                    v.setSelected(true);
                }
                tab.setCustomView(v);
            }
        }
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mAdd.setVisibility(tab.getPosition()==0?View.VISIBLE:View.GONE);
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

}
