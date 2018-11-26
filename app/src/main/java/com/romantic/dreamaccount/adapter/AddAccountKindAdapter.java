package com.romantic.dreamaccount.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.KindResult;
import com.sensology.framelib.adapter.BaseViewHolder;
import com.sensology.framelib.adapter.XRecyclerViewAdapter;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/11/21.
 */
public class AddAccountKindAdapter extends XRecyclerViewAdapter<KindResult.Data> {
    private OnCallBackKind mCallBack;

    public AddAccountKindAdapter(OnCallBackKind callBack){
        this.mCallBack = callBack;
    }

    public interface OnCallBackKind{
        void onClickKindListener(int position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.add_account_kind_item_layout;
    }

    @Override
    public BaseViewHolder onBindViewHolder(View view) {
        return new Holder(view);
    }

    public class Holder extends BaseViewHolder{
        @BindView(R.id.select)
        public RelativeLayout mSelect;
        @BindView(R.id.icon)
        public ImageView mIcon;
        @BindView(R.id.name)
        public TextView mName;
        @BindView(R.id.layout)
        public LinearLayout mLayout;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void initData(final int position) {

            KindResult.Data model = getData().get(position);

            mName.setText(model.getKind());

            mSelect.setSelected(model.isSelect());

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallBack != null) mCallBack.onClickKindListener(position);
                }
            });

        }
    }
}
