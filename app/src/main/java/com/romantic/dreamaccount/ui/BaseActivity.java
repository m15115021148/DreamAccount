package com.romantic.dreamaccount.ui;

import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.config.Constants;
import com.romantic.dreamaccount.eventBus.ExitAppEvent;
import com.sensology.framelib.cache.SharedPref;
import com.sensology.framelib.event.BusProvider;
import com.sensology.framelib.mvp.XActivity;
import com.sensology.framelib.mvp.present.IPresent;
import com.sensology.framelib.util.ActivityUtil;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ${chenM} on 2018/10/19.
 */
public abstract class BaseActivity<P extends IPresent> extends XActivity<P> implements View.OnClickListener {

    public Vibrator vibrator;
    public SoundPool sp;
    public int soundID;

    private Disposable mExitAppDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            registerEventBusBase();
            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            this.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void exitApp() {
        BusProvider.getBus().post(new ExitAppEvent(true, false));
    }

    public void toLoginApp() {
        BusProvider.getBus().post(new ExitAppEvent(true, true));
    }

    public void registerEventBusBase() {
        mExitAppDisposable = BusProvider.getBus().toFlowable(ExitAppEvent.class)
                .subscribe(new Consumer<ExitAppEvent>() {
                    @Override
                    public void accept(ExitAppEvent exitApp) throws Exception {
                        if (exitApp.isExit() && exitApp.isLogin()) {

                        } else if (exitApp.isExit()) {
                            BusProvider.getBus().unregister(this);
                            finish();
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        clickVibrator();
    }


    public void clickVibrator() {
        if (vibrator != null) {
            vibrator.vibrate(50);
        }
        if (sp != null) {
            sp.play(soundID, 0.8f, 0.8f, 1, 0, 1.0f);
        }
    }


    /**
     * 触摸Activity空白处关闭软键盘(适用于总布局不是ScrollView的情况)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ActivityUtil.hindSoftInput(context);
        return super.onTouchEvent(event);
    }

    /**
     * 统一Activity后退时的动画
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.finish_enter, R.anim.finish_exit);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (SharedPref.getInstance(this)
                .getBoolean(Constants.ALLOW_VIBRATOR, true)) {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        } else {
            vibrator = null;
        }
        if (SharedPref.getInstance(this)
                .getBoolean(Constants.ALLOW_VOICE, true)) {
            sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundID = sp.load(getApplicationContext(), R.raw.control_button_sound, 1);
        } else {
            sp = null;
        }
    }

    /**
     * 当前界面打开软键盘 跳转新的界面的时候关闭关键盘
     */
    @Override
    protected void onPause() {
        ActivityUtil.hindSoftInput(context);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unSubscribeRxBus(mExitAppDisposable);
        super.onDestroy();
        if (sp != null) {
            sp.stop(soundID);
            sp.release();
            sp = null;
        }
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    protected void unSubscribeRxBus(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (!TextUtils.isEmpty(Comment.USER_ID)) {
            SharedPref.getInstance(this).putString(Comment.PrefKey.USER_ID, Comment.USER_ID);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String token = SharedPref.getInstance(this).getString(Comment.PrefKey.USER_ID, "");
        if (!TextUtils.isEmpty(token)) Comment.USER_ID = token;
    }

}
