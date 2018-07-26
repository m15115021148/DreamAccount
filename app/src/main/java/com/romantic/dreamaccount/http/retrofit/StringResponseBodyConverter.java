package com.romantic.dreamaccount.http.retrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * <p>Description:
 * <p>Created by chenmeng on 2017/9/3.
 */

public final class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {

        try {
            return value.string();
        } finally {
            value.close();
        }


    }
}
