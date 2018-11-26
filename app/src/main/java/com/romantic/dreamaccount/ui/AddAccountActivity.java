package com.romantic.dreamaccount.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.AddAccountKindAdapter;
import com.romantic.dreamaccount.bean.KindResult;
import com.romantic.dreamaccount.present.ui.AddAccountP;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddAccountActivity extends BaseActivity<AddAccountP> implements AddAccountKindAdapter.OnCallBackKind {
    @BindView(R.id.back)
    public LinearLayout mBack;
    @BindView(R.id.title)
    public TextView mTitle;
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private AddAccountKindAdapter mAdapter;
    @BindView(R.id.expenses)
    public TextView mExpenses;
    @BindView(R.id.income)
    public TextView mIncome;
    private int type = 1;// 1 expenses; 0 income
    private List<KindResult.Data> mKindList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_account;
    }

    @Override
    public AddAccountP newP() {
        return new AddAccountP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mBack.setOnClickListener(this);
        mTitle.setText(R.string.add_account_title);
        mExpenses.setOnClickListener(this);
        mIncome.setOnClickListener(this);
        mExpenses.setSelected(true);
        mIncome.setSelected(false);

        initKindRecycleView();
        getP().getKind();
    }

    private void initKindRecycleView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        mAdapter = new AddAccountKindAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == mBack) finish();
        if (v == mExpenses) {
            mExpenses.setSelected(!mExpenses.isSelected());
            mIncome.setSelected(!mExpenses.isSelected());
            type = 1;
            mAdapter.setData(getKindData(type));
        }
        if (v == mIncome) {
            mIncome.setSelected(!mIncome.isSelected());
            mExpenses.setSelected(!mIncome.isSelected());
            type = 0;
            mAdapter.setData(getKindData(type));
        }
    }

    public void getKindSuccess(KindResult result) {
        mKindList.clear();
        if (result.getData().size() > 0) {
            for (int i = 0, len = result.getData().size(); i < len; i++) {
                result.getData().get(i).setSelect(i == 0);
            }

            mKindList = result.getData();
        }
        mAdapter.setData(getKindData(type));
    }

    private List<KindResult.Data> getKindData(int type) {
        List<KindResult.Data> list = new ArrayList<>();
        int i = 0;
        for (KindResult.Data data : mKindList) {
            if (data.getType() == type) {
                data.setSelect(i == 0);
                list.add(data);
                i++;
            }
        }
        return list;
    }


    @Override
    public void onClickKindListener(int position) {
        for (int i = 0, len = mAdapter.getData().size(); i < len; i++) {
            mAdapter.getData().get(i).setSelect(position == i);
        }
        mAdapter.notifyDataSetChanged();
    }
}
