package com.sensology.framelib.http.download.listener;

import java.io.File;


public interface DownloadListener {

    void update(long bytesRead, long contentLength);

    void onSuccess(File file);

    void onFailure(Throwable t);
}
