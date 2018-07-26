package com.romantic.dreamaccount.http.download.listener;

import java.io.File;

/**
 * Description:
 * Company:
 * Created by chenmeng on 2017/9/3.
 */
public interface DownloadListener {

    void update(long bytesRead, long contentLength);

    void onSuccess(File file);

    void onFailure(Throwable t);
}
