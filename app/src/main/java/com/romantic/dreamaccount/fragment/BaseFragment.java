package com.romantic.dreamaccount.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${chenM} on ${2017}.
 */
public abstract class BaseFragment extends Fragment {
    protected boolean isInit = false;//视图是否已经初初始化
    protected boolean isLoad = false;//是否加载
    protected final String TAG = this.getClass().getSimpleName();
    private View view;//视图
    private Unbinder butterKnife;//取消绑定
    protected FragmentActivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        butterKnife = ButterKnife.bind(this, view);
        isInit = true;
        initData();
        isCanLoadData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            startLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
        butterKnife.unbind();
    }

    /**
     * set layout view id
     *
     * @return 布局的layoutId
     */
    protected abstract int setContentView();

    /**
     * get content view
     *
     * @return
     */
    protected View getContentView() {
        return view;
    }

    protected abstract void initData();//init data or net data

    protected abstract void startLoad();//view show

    protected void stopLoad() {
    }
}
