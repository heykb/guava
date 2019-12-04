package com.zhu.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterTest {
    //类似信号量限制每秒最多允许访问的线程数
    private static RateLimiter limiter = RateLimiter.create(2.0);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for(int i = 0;i<10;i++){
            limiter.acquire();
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务:"+new Date());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });

        }



    }
}
