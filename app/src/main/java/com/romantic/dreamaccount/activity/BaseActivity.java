package com.romantic.dreamaccount.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.romantic.dreamaccount.mvp.presenter.base.Presenter;
import com.romantic.dreamaccount.mvp.presenter.loader.PresenterFactory;
import com.romantic.dreamaccount.mvp.presenter.loader.PresenterLoader;
import com.romantic.dreamaccount.mvp.view.BaseView;
import com.romantic.dreamaccount.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${chenM} on ${2017}.
 */
public abstract class BaseActivity<P extends Presenter<V>, V extends BaseView> extends
        AppCompatActivity implements BaseView, LoaderManager.LoaderCallbacks<P> {
    private static final String TAG_ESC_ACTIVITY = "com.broader.esc";
    private static final int BASE_LOADER_ID = 1000;//loader的id值
    protected P mPresenter;
    protected boolean startBlockKeys = false;//is show can click keyBack
    private MyBroaderEsc receiver;//custom exit broadcast
    private Unbinder butterKnife;//unbinding butterKnife

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(getLayoutId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportLoaderManager().initLoader(BASE_LOADER_ID, null, this);
        receiver = new MyBroaderEsc();
        registerReceiver(receiver, new IntentFilter(TAG_ESC_ACTIVITY));
        butterKnife = ButterKnife.bind(this);
        initData();
    }

    protected abstract int getLayoutId();//init layout xml

    protected abstract P getModelView();//binding model view

    protected abstract void initData();//binding data

    private class MyBroaderEsc extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (TAG_ESC_ACTIVITY.equals(intent.getAction())){
                butterKnife.unbind();
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
    }

    /**
     * exit all activity
     */
    protected void exitApp(){
        Intent intent = new Intent();
        intent.setAction(TAG_ESC_ACTIVITY);
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter!=null)mPresenter.attachView((V) this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter!=null)mPresenter.detachView();
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @NonNull
    @Override
    public Loader<P> onCreateLoader(int id, @Nullable Bundle args) {
        return new PresenterLoader<>(this, new PresenterFactory<P>() {
            @Override
            public P create() {
                return getModelView();
            }
        });
    }

    @Override
    public void onLoadFinished(@NonNull Loader<P> loader, P data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<P> loader) {
        mPresenter = null;
    }

    @Override
    public void onFailure(String error) {
        ToastUtil.showBottomShort(error);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_BACK:
                if (startBlockKeys) return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
