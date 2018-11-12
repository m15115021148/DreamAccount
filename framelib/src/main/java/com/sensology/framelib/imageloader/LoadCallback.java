package com.sensology.framelib.imageloader;

import android.graphics.Bitmap;


public abstract class LoadCallback {
    void onLoadFailed(Throwable e) {}

    public abstract void onLoadReady(Bitmap bitmap);

    void onLoadCanceled() {}
}
