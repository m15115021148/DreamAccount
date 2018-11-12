package com.sensology.framelib.mvp.loader;

import android.content.Context;
import android.support.v4.content.Loader;

import com.sensology.framelib.mvp.present.IPresent;

public class PresenterLoader<P extends IPresent> extends Loader<P> {
    private PresenterFactory<P> factory;
    private P presenter;

    public PresenterLoader(Context context, PresenterFactory<P> presenterFactory) {
        super(context);
        this.factory = presenterFactory;
    }

    @Override
    protected void onStartLoading() {
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected void onForceLoad() {
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter = null;
    }
}
