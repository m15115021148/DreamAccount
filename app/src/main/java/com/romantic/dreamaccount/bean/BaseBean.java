package com.romantic.dreamaccount.bean;

/**
 * 基类 实体类
 * Created by chenMeng on 2017/9/14.
 */
public abstract class BaseBean {
    private int result;
    private String reason;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
