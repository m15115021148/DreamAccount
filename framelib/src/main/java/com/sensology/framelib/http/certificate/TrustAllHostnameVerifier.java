package com.sensology.framelib.http.certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class TrustAllHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        HostnameVerifier v = HttpsURLConnection.getDefaultHostnameVerifier();
        boolean verify = v.verify("", session);
        return verify;
    }
}
