package com.zhu.guava.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EventBusSimpleTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    static {
        EventBus bus = EventBusInstance.bus();
        //当发布的消息没人订阅的时候调用
        bus.register(DeadReceiver.instance);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Event event  = null;
                    try {
                        event = EventReceiver.getEventWaiting().take();
                        System.out.println(event.getType());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        bus.post(new SimpleEvent("greeting"+atomicInteger.getAndIncrement(),"hello"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000);
        EventBus bus = EventBusInstance.bus();
        EventReceiver receiver = new EventReceiver();
        bus.register(receiver);
        countDownLatch.countDown();

        Thread.sleep(1000);
        //EventReceiver.getEventWaiting().
        Thread.sleep(Integer.MAX_VALUE);

        //bus.post(new Event("greeting","我来了"));//no subscriber for com.zhu.guava.eventbus.EventBusSimpleTest$Event



    }
}
