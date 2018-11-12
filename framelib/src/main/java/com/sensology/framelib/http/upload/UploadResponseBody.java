package com.sensology.framelib.http.upload;

import android.support.annotation.NonNull;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 上传进度body
 * Created by chenMeng on 2017/9/6.
 */
public class UploadResponseBody extends ResponseBody {

    private long contentLength;
    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    private UpLoadListener upLoadListener;

    public UploadResponseBody(ResponseBody responseBody, UpLoadListener listener) {
        this.responseBody = responseBody;
        this.upLoadListener = listener;
        contentLength = responseBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long currentTotalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                currentTotalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (upLoadListener != null) {
                    Observable.just(currentTotalBytesRead)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(@NonNull Long aLong) throws Exception {
                                    upLoadListener.upLoad(currentTotalBytesRead, contentLength);
                                }
                            });

                }
                return bytesRead;
            }
        };
    }
}
