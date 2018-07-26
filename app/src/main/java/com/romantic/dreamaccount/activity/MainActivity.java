package com.romantic.dreamaccount.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.MainFragmentAdapter;
import com.romantic.dreamaccount.fragment.BaseFragment;
import com.romantic.dreamaccount.fragment.MySelfFragment;
import com.romantic.dreamaccount.mvp.presenter.base.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener,TabLayout.OnTabSelectedListener {
    private MainActivity mContext;
    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    public ViewPager mViewPager;//viewPager
    private List<BaseFragment> mFragmentList = new ArrayList<>();//fragment集合
    private MainFragmentAdapter mAdapter;
    @BindArray(R.array.MainTabLayout)
    public String[] mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Presenter getModelView() {
        return null;
    }

    @Override
    protected void initData() {
        mContext = this;
        initTabLayout();
    }

    @Override
    public void onClick(View v) {

    }

    private void initTabLayout() {
        mFragmentList.add(new MySelfFragment());
        mFragmentList.add(new MySelfFragment());
        mFragmentList.add(new MySelfFragment());

        mTabLayout.removeAllTabs();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mViewPager.setOffscreenPageLimit(1);
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
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

}
