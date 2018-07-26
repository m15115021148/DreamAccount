package com.romantic.dreamaccount.http.result;

/**
 * <p>Description:
 *基类  暂时不用
 * <p>Created by chenmeng on 2017/9/4.
 */

public class HttpResponseResult<T> {

    private static final int SUCCESS_STATUS = 1;

    private String msg;
    private Integer state;
    private T result;

    public boolean isSuccess() {
        return state != null && state == SUCCESS_STATUS;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getState() {
        return state;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "msg='" + msg + '\'' +
                ", state=" + state +
                ", result=" + result +
                '}';
    }
}
