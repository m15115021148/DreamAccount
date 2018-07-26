package com.romantic.dreamaccount.mvp.view;

import java.io.File;

/**
 * Created by ${chenM} on 2018/7/24.
 */
public interface DownloadView extends BaseView{
    void onDownloadProgress(long bytesRead, long contentLength);
    void onDownloadSuccess(File file);
    void onDownloadFailure(String error);
    void cancelDownload();
    String getDownloadUrl();
    String getDownloadFilePath();
    String getDownloadFileName();
}
