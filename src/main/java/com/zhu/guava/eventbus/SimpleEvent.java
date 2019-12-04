package com.zhu.guava.eventbus;

public class SimpleEvent extends Event {

    public SimpleEvent(String type, Object msg) {
        super(type,msg);
    }
}
