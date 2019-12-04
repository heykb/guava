package com.zhu.guava.concurrent;

import com.google.common.util.concurrent.*;

import java.util.Date;
import java.util.concurrent.*;

//为异步任务添加回调
public class UseListenableFuture {
    public static void main(String[] args) {

        System.out.println(new Date());

        //ListenableFuture 通过ListeningExecutorService.submit(Callable)返回
        //第一步普通ExecutorService转ListeningExecutorService
        ListeningExecutorService listeningExecutorService =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        //获得ListenableFuture
        ListenableFuture listenableFuture = listeningExecutorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "hello";
            }
        });

        //添加回调
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {

            @Override
            public void onSuccess(String s) {
                System.out.println("执行结束："+s + new Date());
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }
}
