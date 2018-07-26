package com.romantic.dreamaccount.http.retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * <p>Description:
 *
 * <p>Created by chenmeng on 2017/9/3.
 */

public final class StringConverterFactory extends Converter.Factory {

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        if (!String.class.equals(type)) {
            return null;
        }
        return new StringResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        if (!String.class.equals(type)) {
            return null;
        }
        return new StringRequestBodyConverter();
    }
}
