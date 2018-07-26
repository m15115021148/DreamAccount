package com.romantic.dreamaccount.http.okhttp;



import com.romantic.dreamaccount.http.okhttp.certificate.TrustAllCerts;
import com.romantic.dreamaccount.http.okhttp.certificate.TrustAllHostnameVerifier;
import com.romantic.dreamaccount.http.okhttp.interceptor.HeadInterceptor;
import com.romantic.dreamaccount.http.okhttp.interceptor.HttpLogger;
import com.romantic.dreamaccount.http.okhttp.interceptor.LogInterceptor;

import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <p>Description:
 * <p>
 * <p>Created by chenmeng on 2017/9/4.
 */

public class OkHttpHelper {

    /**
     * 连接超时
     */
    private static final int CONNECT_TIMEOUT = 10;
    /**
     * 读取超时
     */
    private static final int READ_TIMEOUT = 25;
    /**
     * 写入超时
     */
    private static final int WRITE_TIMEOUT = 25;


    private OkHttpHelper() {
    }

    /**
     * http  请求
     * @return
     */
    public static OkHttpClient getClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new LogInterceptor())  //请求信息的打印 ，可在 release 时关闭
//                .addNetworkInterceptor(logInterceptor)
                .addInterceptor(new HeadInterceptor())
                .build();
        return okHttpClient;
    }

    /**
     * https 证书请求
     * @return
     */
    public static OkHttpClient getClientCertificate() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {

            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.retryOnConnectionFailure(true);
            builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
            builder.addInterceptor(new LogInterceptor()) ; //请求信息的打印 ，可在 release 时关闭
//            builder.addNetworkInterceptor(logInterceptor);
            builder.addInterceptor(new HeadInterceptor());
            builder.sslSocketFactory(TrustAllCerts.getSSL(),new TrustAllCerts());
            builder.hostnameVerifier(new TrustAllHostnameVerifier());

        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.build();
    }

    /**
     * https 无证书请求
     * @return
     */
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.retryOnConnectionFailure(true);
            builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
            builder.addInterceptor(new LogInterceptor());  //请求信息的打印 ，可在 release 时关闭
            builder.addInterceptor(new HeadInterceptor());
//            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
//            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addNetworkInterceptor(logInterceptor);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
