package com.sensology.framelib.http.transformer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.sensology.framelib.http.IModel;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

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
        init();
    }

    private void init(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(activity);
        }
        progressDialog.setMessage(msg);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(cancelable);
    }

    /**
     * 加载的dialog
     *
     * @return
     */
    public <T extends IModel> FlowableTransformer<T, T> transformer(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(final Subscription subscription) throws Exception {
                        if (!progressDialog.isShowing()){
                            progressDialog.show();
                        }
                        if (cancelable) {
                            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    subscription.cancel();
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
