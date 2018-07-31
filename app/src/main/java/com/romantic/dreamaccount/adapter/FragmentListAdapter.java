package com.romantic.dreamaccount.adapter;

import android.view.View;
import android.widget.TextView;

import com.romantic.dreamaccount.R;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/7/30.
 */
public class FragmentListAdapter extends BaseAdapter<String,FragmentListAdapter.Holder>{

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.fragment_list_item_layout;
    }

    @Override
    protected Holder getViewHolder(View view) {
        return new Holder(view);
    }

    public class Holder extends BaseViewHolder{
        @BindView(R.id.name)
        public TextView mName;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initData(int position) {
            String s = list.get(position);
            mName.setText(s);
        }
    }
}
