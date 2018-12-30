package com.romantic.dreamaccount.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.KindModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenM} on 2018/12/24.
 */
public class AddListAdapter extends PagerAdapter {
    private List<KindModel> mList = new ArrayList<>();
    private OnCallBackKind mCallBack;
    private AddAccountKindAdapter mAdapter;

    public AddListAdapter(OnCallBackKind callBack){
        this.mCallBack = callBack;

    }

    public interface OnCallBackKind{
        void onClickKindListener(int position);
    }

    public void setData(List<KindModel> list){
        this.mList = list;
        this.notifyDataSetChanged();
    }

    public List<KindModel> getData(){
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.add_account_item_layout, null);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);


        initRecyclerView(recyclerView,container.getContext(),position);

        container.addView(view);
        return view;
    }


    private void initRecyclerView(RecyclerView recyclerView, Context context,int position) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        recyclerView.getLayoutManager().setAutoMeasureEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        mAdapter = new AddAccountKindAdapter(mCallBack);
        mAdapter.setData(mList.get(position).getData().getData());

        recyclerView.setAdapter(mAdapter);
    }

}
