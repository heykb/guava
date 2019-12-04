package com.zhu.guava.eventbus;

import com.google.common.base.Preconditions;

public class Event{
    private String type;
    private Object msg;

    public Event(String type, Object msg) {
        this.type = Preconditions.checkNotNull(type,"type not null");
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public Object getMsg() {
        return msg;
    }
}