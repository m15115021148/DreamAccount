package com.romantic.dreamaccount.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.AccountsBean;
import com.sensology.framelib.adapter.BaseViewHolder;
import com.sensology.framelib.adapter.XRecyclerViewAdapter;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/11/15.
 */
public class ForecastListAdapter extends XRecyclerViewAdapter<AccountsBean> {

    private OnCallBackForecast mCallBack;

    public ForecastListAdapter(OnCallBackForecast callBack){
        this.mCallBack = callBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forecast_list_item_layout;
    }

    @Override
    public BaseViewHolder onBindViewHolder(View view) {
        return new Holder(view);
    }

    public interface OnCallBackForecast{
        void onUploadListener(int position);
    }

    public class Holder extends BaseViewHolder{
        @BindView(R.id.time)
        public TextView mTime;
        @BindView(R.id.layout)
        public LinearLayout mLayout;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void initData(final int position) {
            AccountsBean bean = getData().get(position);
            mTime.setText(bean.getTime());
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallBack != null )mCallBack.onUploadListener(position);
                }
            });
        }
    }
}
