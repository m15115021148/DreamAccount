package com.sensology.framelib.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import com.sensology.framelib.XConfigure;
import com.sensology.framelib.event.BusProvider;
import com.sensology.framelib.kit.KnifeHelper;
import com.sensology.framelib.mvp.loader.PresenterFactory;
import com.sensology.framelib.mvp.loader.PresenterLoader;
import com.sensology.framelib.mvp.present.IPresent;
import com.sensology.framelib.mvp.view.IView;
import com.sensology.framelib.mvp.VDelegate;
import com.sensology.framelib.mvp.VDelegateBase;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Objects;

import butterknife.Unbinder;

public abstract class XFragment<P extends IPresent> extends LazyFragment implements IView<P>, LoaderManager.LoaderCallbacks<P> {

    private VDelegate vDelegate;
    private P p;

    private RxPermissions rxPermissions;
    private Unbinder unbinder;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(getRealRootView());
        }
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
        bindEvent();
        initData(savedInstanceState);
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeHelper.bind(this, rootView);
    }

    @Override
    public void bindEvent() {

    }


    public VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
        }
        if (p != null) {
            if (!p.hasV()) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onDestroyLazy() {
        super.onDestroyLazy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destroy();

        p = null;
        vDelegate = null;
    }


    protected RxPermissions getRxPermissions() {
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        }
        rxPermissions.setLogging(XConfigure.DEV);
        return rxPermissions;
    }


    @Override
    public int getOptionsMenuId() {
        return 0;
    }


    @Override
    public boolean useEventBus() {
        return false;
    }


    @NonNull
    @Override
    public Loader<P> onCreateLoader(int id, @Nullable Bundle args) {
        return new PresenterLoader<>(context, new PresenterFactory<P>() {
            @Override
            public P create() {
                return newP();
            }
        });
    }

    @Override
    public void onLoadFinished(@NonNull Loader<P> loader, P data) {
        p = data;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<P> loader) {
        p = null;
    }
}
