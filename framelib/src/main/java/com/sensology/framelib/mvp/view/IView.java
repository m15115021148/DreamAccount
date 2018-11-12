package com.sensology.framelib.mvp.view;

import android.os.Bundle;
import android.view.View;

public interface IView<P> {
    void bindUI(View rootView);

    void bindEvent();

    int getLayoutId();

    P newP();

    void initData(Bundle savedInstanceState);

    int getOptionsMenuId();

    boolean useEventBus();

}
