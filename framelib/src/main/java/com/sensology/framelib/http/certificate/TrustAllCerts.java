package com.sensology.framelib.http.certificate;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okio.Buffer;

public class TrustAllCerts implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null){
            throw new IllegalArgumentException("check server X509Certificate is null");
        }
        if (chain.length < 0){
            throw new IllegalArgumentException("check server X509Certificate is empty");
        }
        for (X509Certificate cert : chain){
            cert.checkValidity();
            try {
                cert.verify(getCertificate(key_certificate).getPublicKey());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    private X509Certificate getCertificate(String car){
        InputStream inputStream;
        X509Certificate certificate = null;
        try {
            inputStream = new Buffer().writeUtf8(car).inputStream();
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            certificate = (X509Certificate) factory.generateCertificate(inputStream);
        }catch (CertificateException e) {
            e.printStackTrace();
        }
        return certificate;
    }

    public static SSLSocketFactory getSSL(){
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new TrustAllCerts()}, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

    private static String key_certificate =
            "-----BEGIN CERTIFICATE-----\n" +
                    "MIIEkzCCA3ugAwIBAgIQBopUjPeAWrQQaF7KJnrZVDANBgkqhkiG9w0BAQsFADBu\n" +
                    "MQswCQYDVQQGEwJVUzEVMBMGA1UEChMMRGlnaUNlcnQgSW5jMRkwFwYDVQQLExB3\n" +
                    "d3cuZGlnaWNlcnQuY29tMS0wKwYDVQQDEyRFbmNyeXB0aW9uIEV2ZXJ5d2hlcmUg\n" +
                    "RFYgVExTIENBIC0gRzEwHhcNMTgwMTA0MDAwMDAwWhcNMTkwMTA0MTIwMDAwWjAc\n" +
                    "MRowGAYDVQQDExF3d3cubWVpZ2VsaW5rLmNvbTCCASIwDQYJKoZIhvcNAQEBBQAD\n" +
                    "ggEPADCCAQoCggEBAIFVG6zlsR+5QveNl6Vi+WHU1g7izirbyjKyBFMk3DvWbVVD\n" +
                    "u+dAKdN9O0z2EBWmQFAocL0BC6IuMrS0gPtBjrKqcL2RjCKY61ElTu2AtGJ/2ADJ\n" +
                    "03R1D2mm6GsrmZ7H7KzXkx6huYsI9l601Y4NM6VBZfYitJ1JIaMMYSqZY3UWlktx\n" +
                    "p/6da7mUxFWyFh/YCTwWEVhTRLTHyQoIBFZjUjr7FwGXOiExxfcEqidHXC/g8l3i\n" +
                    "rpVYdQ202nIAZ7A8DFjo+ibXMFRgVX18kRpKO0DOPl8dHYIqLWamHXiUP7ySglTR\n" +
                    "WlqY2ZsN2DPE2WhXhCH1l8XRIUASCCUvVsanieMCAwEAAaOCAX0wggF5MB8GA1Ud\n" +
                    "IwQYMBaAFFV0T7JyT/VgulDR1+ZRXJoBhxrXMB0GA1UdDgQWBBTGeQYd6Rz5mNcF\n" +
                    "qycD6SKSxW2fRDArBgNVHREEJDAighF3d3cubWVpZ2VsaW5rLmNvbYINbWVpZ2Vs\n" +
                    "aW5rLmNvbTAOBgNVHQ8BAf8EBAMCBaAwHQYDVR0lBBYwFAYIKwYBBQUHAwEGCCsG\n" +
                    "AQUFBwMCMEwGA1UdIARFMEMwNwYJYIZIAYb9bAECMCowKAYIKwYBBQUHAgEWHGh0\n" +
                    "dHBzOi8vd3d3LmRpZ2ljZXJ0LmNvbS9DUFMwCAYGZ4EMAQIBMIGBBggrBgEFBQcB\n" +
                    "AQR1MHMwJQYIKwYBBQUHMAGGGWh0dHA6Ly9vY3NwMi5kaWdpY2VydC5jb20wSgYI\n" +
                    "KwYBBQUHMAKGPmh0dHA6Ly9jYWNlcnRzLmRpZ2ljZXJ0LmNvbS9FbmNyeXB0aW9u\n" +
                    "RXZlcnl3aGVyZURWVExTQ0EtRzEuY3J0MAkGA1UdEwQCMAAwDQYJKoZIhvcNAQEL\n" +
                    "BQADggEBALF9hq9RQ80dvmhMJneYAD01XuUXwVoZoWIgXfOmRSyHFkfZaQdPiC+p\n" +
                    "AbFSe16WXOCAFEMnERpg71TTCqaHU7o7+qjqG8mm4qgzI1Z1R+D7FDXtmY2S1UrV\n" +
                    "tOaBtVfqedPKUw1U5ZvhpTERmUD4Oc13cGiiROmLXQPlay2J0wHtPal77VcdFv8y\n" +
                    "Xg1CJaJ2/ZbN1HGaCLe1av5G9QdzXJR4fxTHrbg6bARiYlCupxhgnp0aAI4qCzVo\n" +
                    "gXW2q/gKREq72lN2vOqyevTDykjuL4HhOCHZW5Lvl/JN29BtMV0TD11AEnSYkNCM\n" +
                    "C4IMMJECrDhTlY15lHX0HOv1J7LA73s=\n" +
                    "-----END CERTIFICATE-----";
}
