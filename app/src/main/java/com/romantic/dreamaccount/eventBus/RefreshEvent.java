package com.romantic.dreamaccount.eventBus;

import com.sensology.framelib.event.IBus;

/**
 * Created by ${chenM} on 2018/11/15.
 */
public class RefreshEvent implements IBus.IEvent {
    private boolean refresh;

    public RefreshEvent(){

    }

    public RefreshEvent(boolean refresh){
        this.refresh = refresh;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
