package com.zhu.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadReceiver{
    private DeadReceiver(){}
    public static DeadReceiver instance = new DeadReceiver();
    @Subscribe
    public void subscribe(DeadEvent event) {
        System.out.println("no subscriber for ["+event.getEvent().getClass().getName()+"]");
    }

}