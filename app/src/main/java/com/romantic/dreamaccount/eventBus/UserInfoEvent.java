package com.romantic.dreamaccount.eventBus;

import com.sensology.framelib.event.IBus;

/**
 * Created by ${chenM} on 2018/10/19.
 */
public class UserInfoEvent implements IBus.IEvent {

    private String name;
    private int type;

    public UserInfoEvent(String name,int type){
        this.name = name;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
