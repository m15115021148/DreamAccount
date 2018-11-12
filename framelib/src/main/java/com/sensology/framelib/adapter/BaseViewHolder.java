package com.sensology.framelib.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sensology.framelib.kit.KnifeHelper;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        KnifeHelper.bind(this,itemView);
    }

    public void initData(int position){

    }

}
