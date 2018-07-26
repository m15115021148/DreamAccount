package com.romantic.dreamaccount.http.okhttp.certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Created by chenMeng on 2018/1/3.
 */

public class TrustAllHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        HostnameVerifier v = HttpsURLConnection.getDefaultHostnameVerifier();
        boolean verify = v.verify("www.meigelink.com", session);
        return verify;
    }
}
