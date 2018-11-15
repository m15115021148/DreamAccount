package com.romantic.dreamaccount.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.TypeBean;
import com.sensology.framelib.adapter.BaseViewHolder;
import com.sensology.framelib.adapter.XRecyclerViewAdapter;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/8/7.
 */
public class MySelfListAdapter extends XRecyclerViewAdapter<TypeBean> {
    private OnMyselfListCallBack mCallBack;

    public MySelfListAdapter(OnMyselfListCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.myself_list_item_layout;
    }

    @Override
    public BaseViewHolder onBindViewHolder(View view) {
        return new Holder(view);
    }

    public interface OnMyselfListCallBack {
        void onItemClickListener(int position);
    }

    public class Holder extends BaseViewHolder {
        @BindView(R.id.name)
        public TextView mName;
        @BindView(R.id.layout)
        public RelativeLayout mLayout;
        @BindView(R.id.img)
        public ImageView mImg;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void initData(final int position) {
            TypeBean bean = getData().get(position);
            mImg.setImageResource(bean.getRes());
            mImg.setColorFilter(bean.getResColor());
            mName.setText(bean.getName());
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallBack != null) mCallBack.onItemClickListener(position);
                }
            });
        }
    }
}
