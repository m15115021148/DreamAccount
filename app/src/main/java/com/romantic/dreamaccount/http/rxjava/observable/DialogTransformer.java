package com.romantic.dreamaccount.http.rxjava.observable;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Description:dialog 显示 取消
 * Created by chenmeng on 2017/9/3.
 */
public class DialogTransformer {

    private static final String DEFAULT_MSG = "Load...";

    private Context activity;
    private String msg;
    private boolean cancelable;
    private ProgressDialog progressDialog;

    public DialogTransformer(Context activity) {
        this(activity, DEFAULT_MSG);
    }

    public DialogTransformer(Context activity, String msg) {
        this(activity, msg, false);
    }

    public DialogTransformer(Context activity, String msg, boolean cancelable) {
        this.activity = activity;
        this.msg = msg;
        this.cancelable = cancelable;
    }

    public <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {

                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull final Disposable disposable) throws Exception {
                        progressDialog = ProgressDialog.show(activity, null, msg, true, cancelable);
                        if (cancelable) {
                            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    disposable.dispose();
                                }
                            });
                        }
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                    }
                });
            }
        };
    }
}
