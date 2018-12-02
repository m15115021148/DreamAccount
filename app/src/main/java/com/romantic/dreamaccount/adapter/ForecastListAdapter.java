package com.romantic.dreamaccount.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.db.AccountsBean;
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
        private View mItemView;
        @BindView(R.id.time)
        public TextView mTime;
        @BindView(R.id.layout)
        public LinearLayout mLayout;
        @BindView(R.id.upload)
        public ImageView mUpload;
        @BindView(R.id.name)
        public TextView mName;
        @BindView(R.id.money)
        public TextView mMoney;
        @BindView(R.id.detail)
        public TextView mDetail;
        @BindView(R.id.address)
        public TextView mAddress;

        public Holder(View itemView) {
            super(itemView);
            mItemView = itemView;
        }

        @Override
        public void initData(final int position) {
            AccountsBean bean = getData().get(position);
            mTime.setText(bean.getTime());
            mName.setText(bean.getKind());
            mMoney.setText(Html.fromHtml(String.format(
                    mItemView.getResources().getString(R.string.account_money), bean.getType() == 0 ? "#00FF00" : "#FF0000", bean.getMoney())
            ));

            mDetail.setText(Html.fromHtml(String.format(
                    mItemView.getResources().getString(R.string.account_detail), bean.getNote()
            )));

            mAddress.setText(bean.getAddress());

            mUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallBack != null )mCallBack.onUploadListener(position);
                }
            });
        }
    }
}
