package com.sensology.framelib.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public interface RequestHandler {
    Request onBeforeRequest(Request request, Interceptor.Chain chain);

    Response onAfterRequest(Response response, Interceptor.Chain chain);
}
