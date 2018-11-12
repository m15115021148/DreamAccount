package com.romantic.dreamaccount.bean;

import com.sensology.framelib.http.IModel;

import java.io.Serializable;

/**
 * Created by ${chenM} on 2018/10/25.
 */
public class BaseModel implements IModel,Serializable {
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}
