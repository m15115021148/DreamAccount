package com.sensology.framelib.http.upload;

import android.support.annotation.NonNull;

import com.sensology.framelib.http.certificate.TrustAllCerts;
import com.sensology.framelib.http.certificate.TrustAllHostnameVerifier;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;


public class UpLoadHelper {

    private Map<String, Object> mMap;//上传参数
    private String url;//上传url
    private Call call;

    public UpLoadHelper(String url, Map<String, Object> map) {
        this.url = url;
        this.mMap = map;
    }

    public void upLoadFile(final UpLoadListener listener) {
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : mMap.keySet()) {
                Object object = mMap.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, object.toString());
                } else {
                    File file = (File) object;
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
                }
            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            Request request = new Request.Builder().url(url).post(body).build();
            //单独设置参数 比如读取超时时间
            call = initClient(listener).newBuilder().writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
            Observable.just(call)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Consumer<Call>() {
                        @Override
                        public void accept(@NonNull Call call) throws Exception {
                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            } else {
                                if (listener != null) {
                                    BufferedSource source = response.body().source();
                                    Buffer buffer = source.buffer();
                                    String s = buffer.readString(Charset.forName("utf-8"));
                                    Observable.just(s)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<String>() {
                                                @Override
                                                public void accept(String s) throws Exception {
                                                    listener.onSuccess(s);
                                                }
                                            });
                                }
                            }
                        }

                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) {
                            Observable.just(throwable).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Throwable>() {
                                        @Override
                                        public void accept(@NonNull Throwable throwable) throws Exception {
                                            if (listener != null) {
                                                listener.onFailure(throwable);
                                            }
                                        }
                                    });
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cancelUpload() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }

    private OkHttpClient initClient(final UpLoadListener upLoadListener) {
        return new OkHttpClient.Builder()
//                .addInterceptor(new HeadInterceptor())
//                .addInterceptor(new LogInterceptor())
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Accept-Encoding", "identity").build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new UploadResponseBody(originalResponse.body(), upLoadListener)).build();
                    }

                })
                .build();
    }

    /**
     * init ssl certs
     * @param upLoadListener
     * @return
     */
    private OkHttpClient initClientCertificate(final UpLoadListener upLoadListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(new HeadInterceptor());
//        builder.addInterceptor(new LogInterceptor());
        builder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Accept-Encoding", "identity").build();
                        return chain.proceed(request);
                    }
                });
        builder.addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new UploadResponseBody(originalResponse.body(), upLoadListener)).build();
                    }

                });
        builder.sslSocketFactory(TrustAllCerts.getSSL(),new TrustAllCerts());
        builder.hostnameVerifier(new TrustAllHostnameVerifier());

        return builder.build();
    }
}
