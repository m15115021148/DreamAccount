package com.romantic.dreamaccount.mvp.model;

import com.romantic.dreamaccount.http.download.DownloadHelper;
import com.romantic.dreamaccount.http.download.listener.DownloadListener;
import com.romantic.dreamaccount.mvp.presenter.callback.HttpRequestProgressCallBack;

import java.io.File;

/**
 * Created by ${chenM} on 2018/7/24.
 */
public class DownloadModel {
    private DownloadHelper helper;

    public void startDownload(String url, String filePath, String fileName, final HttpRequestProgressCallBack<File> callBack){
        helper = new DownloadHelper(url,filePath,fileName);
        helper.downloadFile(new DownloadListener() {
            @Override
            public void update(long bytesRead, long contentLength) {
                callBack.onProgress(bytesRead, contentLength);
            }

            @Override
            public void onSuccess(File file) {
                callBack.onSuccess(file);
            }

            @Override
            public void onFailure(Throwable t) {
                callBack.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void cancelDownload(){
        if (helper!=null)helper.cancelDownload();
    }
}
