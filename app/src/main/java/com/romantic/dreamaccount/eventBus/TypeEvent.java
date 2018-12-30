package com.romantic.dreamaccount.eventBus;

import com.sensology.framelib.event.IBus;

/**
 * Created by ${chenM} on 2018/12/30.
 */
public class TypeEvent implements IBus.IEvent {
    private int type;

    public TypeEvent(){}

    public TypeEvent(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
