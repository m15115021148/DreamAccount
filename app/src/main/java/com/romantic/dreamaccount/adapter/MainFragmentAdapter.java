package com.romantic.dreamaccount.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.TypeBean;
import com.romantic.dreamaccount.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenM} on ${2017}.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<TypeBean> mList;
    private List<BaseFragment> mFragmentList;
    private Context mContext;
    private int[] res = {
            R.drawable.menu_home_bg,R.drawable.menu_account_bg,R.drawable.menu_check_bg,R.drawable.menu_info_bg
    };

    public MainFragmentAdapter(FragmentManager fm, String[] data, List<BaseFragment> fragments, Context context) {
        super(fm);
        this.mList = getData(data);
        this.mFragmentList = fragments;
        this.mContext = context;
    }

    private List<TypeBean> getData(String[] data){
        List<TypeBean> list = new ArrayList<>();
        for (int i=0;i<data.length;i++){
            TypeBean model = new TypeBean();
            model.setName(data[i]);
            model.setRes(res[i]);
            list.add(model);
        }
        return list;
    }

    public View getView(int pos){
        View view = LayoutInflater.from(mContext).inflate(R.layout.tablyout_main_item_layout,null);
        TextView name = view.findViewById(R.id.name);
        ImageView iv = view.findViewById(R.id.img);
        name.setText(mList.get(pos).getName());
        iv.setImageResource(mList.get(pos).getRes());
        view.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        return view;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getName();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
