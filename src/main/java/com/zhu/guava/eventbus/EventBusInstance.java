package com.zhu.guava.eventbus;

import com.google.common.eventbus.EventBus;

public class EventBusInstance {
    private EventBusInstance(){}
    private static EventBus bus = new EventBus();
    public static EventBus bus(){
        return bus;
    }
}
