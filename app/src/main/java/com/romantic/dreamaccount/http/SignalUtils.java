package com.romantic.dreamaccount.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SignalUtils {

    public static Map<String, Object> getSignal() {
        StringBuilder sb = new StringBuilder();
        HashMap<String, Object> maps = new HashMap<String, Object>();
        long mTime = System.currentTimeMillis();
        for (int i = 0; i < 6; i++) {
            sb.append((int) (Math.random() * 10));
        }

        maps.put("timestamp", mTime);
        maps.put("nonce", sb.toString());
        maps.put("token", "sensology-teamx");
        String decrypt = splitParams(lexicographicOrder(getParamsName(maps)), maps);
        String mSignal = new Sha1.SHA1().getDigestOfString(decrypt.getBytes());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("signature", mSignal);
        map.put("timestamp", mTime + "");
        map.put("nonce", sb.toString());
        return map;
    }

    private static List<String> getParamsName(Map<String, Object> maps) {
        List<String> paramNames = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            paramNames.add(entry.getValue() + "");
        }
        return paramNames;
    }

    private static List<String> lexicographicOrder(List<String> paramNames) {
        Collections.sort(paramNames);
        return paramNames;
    }

    private static String splitParams(List<String> paramNames, Map<String, Object> maps) {
        StringBuilder paramStr = new StringBuilder();
        for (String paramName : paramNames) {
            paramStr.append(paramName);
        }
        return paramStr.toString();
    }
}
