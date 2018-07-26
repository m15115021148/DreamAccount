package com.romantic.dreamaccount.http.rxjava.observer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import io.reactivex.disposables.Disposable;

/**
 * <p>Description:
 * <p>dialog
 * <p>Created by chenmeng on 2017/9/5.
 */

public abstract class ProgressObserver<T> extends BaseObserver<T> {

    private static final String DEFAULT_MSG = "Load...";

    private ProgressDialog progressDialog;
    private Activity activity;
    private String msg;
    private boolean cancelable;

    public ProgressObserver(Activity activity) {
        this(activity,DEFAULT_MSG);
    }

    public ProgressObserver(Activity activity, String msg) {
        this(activity, msg, false);
    }

    public ProgressObserver(Activity activity, String msg, boolean cancelable) {
        this.activity = activity;
        this.msg = msg;
        this.cancelable = cancelable;
    }


    @Override
    public void onSubscribe(final Disposable d) {
        super.onSubscribe(d);
        progressDialog = ProgressDialog.show(activity, null, msg, true, cancelable);
        if (cancelable) {
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    d.dispose();
                }
            });
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        progressDialog.cancel();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        progressDialog.cancel();
    }


}
