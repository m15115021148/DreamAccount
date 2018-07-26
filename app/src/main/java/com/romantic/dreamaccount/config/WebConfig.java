package com.romantic.dreamaccount.config;

/**
 * Created by ${chenM} on ${2017}.
 */
public class WebConfig {
    private static final String HOST_ADDRESS = "http://116.62.205.204:7000/meiglink/";

    private static final String HOST_NAME = HOST_ADDRESS + "api/v1/";

    public static String getHostName() {
        return HOST_NAME;
    }
}
