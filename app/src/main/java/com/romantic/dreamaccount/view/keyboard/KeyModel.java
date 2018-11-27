package com.romantic.dreamaccount.view.keyboard;

/**
 * Created by ${chenM} on 2018/11/26.
 */
public class KeyModel {
    private Integer code;
    private String lable;

    public KeyModel(Integer code,String lable){
        this.code = code;
        this.lable = lable;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
