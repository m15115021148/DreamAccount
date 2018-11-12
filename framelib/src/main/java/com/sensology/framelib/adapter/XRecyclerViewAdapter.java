package com.sensology.framelib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class XRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> data = new ArrayList<>();

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setData(T[] data) {
        if (data != null && data.length > 0) {
            setData(Arrays.asList(data));
        }
    }

    public List<T> getData() {
        return data;
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<T> data) {
        int preSize = this.data.size();
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyItemRangeInserted(preSize, this.data.size());
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(T[] data) {
        addData(Arrays.asList(data));
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return onBindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.initData(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public abstract int getLayoutId();

    public abstract BaseViewHolder onBindViewHolder(View view);


    public void removeElement(T element) {
        if (data.contains(element)) {
            int position = data.indexOf(element);
            data.remove(element);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        }
    }

    /**
     * 删除元素
     *
     * @param position
     */
    public void removeElement(int position) {
        if (data != null && data.size() > position) {
            data.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    public void removeElements(List<T> elements) {
        if (data != null && elements != null && elements.size() > 0
                && data.size() >= elements.size()) {

            for (T element : elements) {
                if (data.contains(element)) {
                    data.remove(element);
                }
            }

            notifyDataSetChanged();
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    public void removeElements(T[] elements) {
        if (elements != null && elements.length > 0) {
            removeElements(Arrays.asList(elements));
        }
    }

    /**
     * 更新元素
     *
     * @param element
     * @param position
     */
    public void updateElement(T element, int position) {
        if (position >= 0 && data.size() > position) {
            data.remove(position);
            data.add(position, element);
            notifyItemChanged(position);
        }
    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void addElement(T element) {
        if (element != null) {
            if (this.data == null) {
                this.data = new ArrayList<T>();
            }
            data.add(element);
            notifyItemInserted(this.data.size());
        }
    }

    public void addElement(int position, T element) {
        if (element != null) {
            if (this.data == null) {
                this.data = new ArrayList<T>();
            }
            data.add(position, element);
            notifyItemInserted(position);
        }
    }

    /**
     * 清除数据源
     */
    public void clearData() {
        if (this.data != null) {
            this.data.clear();
            notifyDataSetChanged();
        }
    }

}
