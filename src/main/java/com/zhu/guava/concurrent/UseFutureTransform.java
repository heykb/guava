package com.zhu.guava.concurrent;


import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import com.zhu.guava.User;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//转换 ListenableFuture<T> -> ListenableFuture<V>
public class UseFutureTransform {
    private static boolean s = true;

    private static Map<String,User> userMap = Maps.newHashMap();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService listeningExecutorService =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> future = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("第一个future开始");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("第一个future结束");
                return "lisi";
            }
        });


        ListenableFuture<User> userFuture = Futures.transformAsync(future, new AsyncFunction<String, User>(){

            @Override
            public ListenableFuture<User> apply(String s) throws Exception {
                ListenableFuture<User> re;
                if(userMap.containsKey(s)){
                    System.out.println("已找到即可返回");
                    SettableFuture<User> future1 = SettableFuture.create();
                    future1.set(userMap.get(s));
                    re = future1;
                }else{
                    re = listeningExecutorService.submit(new Callable<User>() {

                        @Override
                        public User call() throws Exception {
                            System.out.println("正在创建。。。");
                            Thread.sleep(1000);
                            userMap.put(s,new User(s,0));
                            return userMap.get(s);
                        }
                    });
                }

                return re;
            }
        });
        Futures.addCallback(userFuture, new FutureCallback<User>() {
            @Override
            public void onSuccess(User user) {
                s=false;
                System.out.println("结束返回： "+user);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
        int i = 0;
        while(s){
            Thread.sleep(500);
            System.out.println("------------"+i++);
        }
    }
}
