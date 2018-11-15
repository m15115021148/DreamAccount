package com.romantic.dreamaccount.adapter;

import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.AccountsBean;
import com.sensology.framelib.adapter.BaseViewHolder;
import com.sensology.framelib.adapter.XRecyclerViewAdapter;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/7/30.
 */
public class FragmentListAdapter extends XRecyclerViewAdapter<AccountsBean> {
    private OnFragmentListCallBack mCallBack;

    public FragmentListAdapter(OnFragmentListCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_item_layout;
    }

    @Override
    public BaseViewHolder onBindViewHolder(View view) {
        return new Holder(view);
    }

    public interface OnFragmentListCallBack {
        void onItemClickListener(int position);

        void onLocationListener(int position);
    }

    public class Holder extends BaseViewHolder {
        private View mItemView;
        @BindView(R.id.name)
        public TextView mName;
        @BindView(R.id.time)
        public TextView mTime;
        @BindView(R.id.money)
        public TextView mMoney;
        @BindView(R.id.detail)
        public TextView mDetail;
        @BindView(R.id.address)
        public TextView mAddress;
        @BindView(R.id.layout)
        public LinearLayout mLayout;
        @BindView(R.id.addressLayout)
        public LinearLayout mLocation;

        public Holder(View itemView) {
            super(itemView);
            mItemView = itemView;
        }

        @Override
        public void initData(final int position) {
            AccountsBean model = getData().get(position);

            mName.setText(model.getKind());
            mTime.setText(model.getTime());
            mMoney.setText(Html.fromHtml(String.format(
                    mItemView.getResources().getString(R.string.account_money), model.getType() == 0 ? "#00FF00" : "#FF0000", model.getMoney())
            ));

            mDetail.setText(Html.fromHtml(String.format(
                    mItemView.getResources().getString(R.string.account_detail), model.getNote()
            )));

            mAddress.setText(model.getAddress());
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallBack != null) mCallBack.onItemClickListener(position);
                }
            });
            mLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallBack != null) mCallBack.onLocationListener(position);
                }
            });

        }
    }
}
