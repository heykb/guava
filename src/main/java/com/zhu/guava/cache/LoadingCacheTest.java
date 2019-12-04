package com.zhu.guava.cache;

import com.google.common.base.Ticker;
import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.zhu.guava.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LoadingCacheTest {
    private static LoadingCache<String, User> userCache1 = CacheBuilder
            .newBuilder()
            .concurrencyLevel(10)//并发数默认4
            //.maximumSize(1000) //最大数目，接近这个数目会发生回收
            .maximumWeight(10000)//或者使用总权重接近这个值发起回收，和权重函数配合使用
            .expireAfterAccess(10, TimeUnit.MINUTES)//在10分钟没有读写操作过期
            //.expireAfterWrite(10, TimeUnit.MINUTES)//在10分钟没有写操作过期
            .softValues()//软引用来存储值，内存不足时使用lru回收策略回收
            .recordStats()//开启统计功能
            .weigher(new Weigher<String, User>() {//每个元素计算权重的函数
                @Override
                public int weigh(String s, User user) {
                    return user.getName().length();
                }
            })
            .removalListener(//缓存被移除时调用,并且使用RemovalListeners.asynchronous()将方法包装从异步执行
                    RemovalListeners.asynchronous(
                            new RemovalListener<String, User>() {
                                @Override
                                public void onRemoval(RemovalNotification<String, User> removalNotification) {
                                    //记录删除日志。。
                                    removalNotification.getCause();
                                    System.out.println(removalNotification.getKey()+"已删除");

                                }
                            }, Executors.newFixedThreadPool(10)
                            )
            )
            .build(
                    //使用CacheLoader.asyncReloading()将cacheLoader.reload()异步刷新
                  CacheLoader.asyncReloading(
                           new CacheLoader<String, User>() {
                               @Override
                               public User load(String s){
                                   //查询数据库
                                   User user = new User(s,0);
                                   System.out.println("loading key :"+s);
//                                   System.out.println(Thread.currentThread().getName());
                                   return user;
                               }

                               //默认的reload()调用load()方法重新加载，重载reload()可以根据旧值自定义加载
                               @Override
                               public ListenableFuture<User> reload(String key, User oldValue) throws Exception {
                                    Thread.sleep(3000);
                                   return super.reload(key, oldValue);
                               }
                           }
                           ,Executors.newFixedThreadPool(10)
                  )

                );

    public static void main(String[] args) throws InterruptedException {
        userCache1.getUnchecked("lisi");//load()函数没有抛出异常可以使用这个
        userCache1.refresh("lisi");//重新加载key
        System.out.println(userCache1.getUnchecked("lisi"));;
        CacheStats stats = userCache1.stats();
        System.out.println(stats.hitRate());//缓存命中率
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
