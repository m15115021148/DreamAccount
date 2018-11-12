package com.romantic.dreamaccount.eventBus;

import com.sensology.framelib.event.IBus;

public class ExitAppEvent implements IBus.IEvent{
    private  boolean login;
    private boolean isExit;
    private String message;

    public ExitAppEvent(boolean isExit, String message) {
        super();
        this.isExit = isExit;
        this.message = message;
    }

    public ExitAppEvent(boolean isExit) {
        super();
        this.isExit = isExit;
    }
    public ExitAppEvent(boolean isExit, boolean login) {
        super();
        this.isExit = isExit;
        this.login = login;
    }
    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
