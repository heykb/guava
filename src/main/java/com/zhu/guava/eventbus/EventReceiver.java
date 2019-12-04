package com.zhu.guava.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class EventReceiver{
    private static ArrayBlockingQueue<Event> eventWaiting =  new ArrayBlockingQueue(10);

    /**
     * EventBus不会从多个线程调用处理程序方法,一旦一个处理方法阻塞其他都会阻塞。除非使用AllowConcurrentEvents
     * @param event
     * @throws InterruptedException
     */
    @Subscribe
    @AllowConcurrentEvents
    public void access(SimpleEvent event) throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"get event[ type:"+event.getType()+", msg:"+event.getMsg()+" ]");
        eventWaiting.put(event);
    }

    public static ArrayBlockingQueue<Event> getEventWaiting() {
        return eventWaiting;
    }
}