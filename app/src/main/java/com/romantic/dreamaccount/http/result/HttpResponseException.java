package com.romantic.dreamaccount.http.result;

/**
 * <p>Description:
 *异常处理
 * <p>Created by chenmeng on 2017/9/4.
 */

public class HttpResponseException extends RuntimeException {

    private  int status;

    public HttpResponseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
