package com.romantic.dreamaccount.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.activity.TestActivity;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/8/1.
 */
public class ForecastFragment extends BaseFragment {
    @BindView(R.id.test)
    public Button mTest;

    @Override
    protected int setContentView() {
        return R.layout.fragment_forecast_layout;
    }

    @Override
    protected void initData() {
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void startLoad() {

    }
}
