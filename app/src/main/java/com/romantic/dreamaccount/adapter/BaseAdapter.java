package com.romantic.dreamaccount.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenM} on 2018/7/30.
 */
public abstract class BaseAdapter<T, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {
    protected List<T> list = new ArrayList<>();

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        holder.initData(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    protected abstract int getLayoutId(int viewType);

    protected abstract V getViewHolder(View view);

    public void setData(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public List<T> getData() {
        return this.list;
    }

    public void clear() {
        this.list.clear();
        this.notifyDataSetChanged();
    }

}
