package com.zhu.guava.cache;

import com.google.common.base.Function;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zhu.guava.User;
//https://my.oschina.net/realfighter?tab=newest&catalogId=576615
public class CacheBuilderSpecTest {
    //通过字符串配置 建立CacheBuilder
    private static String configString = "concurrencyLevel,refreshAfterWriter=5s";
    private static CacheBuilderSpec cacheBuilderSpec = CacheBuilderSpec.parse(configString);
    private static LoadingCache<String, User> userCache = CacheBuilder
            .from(cacheBuilderSpec)
            .ticker(Ticker.systemTicker())
            .maximumSize(10)
            //另一种创建cacheLoader的方法
            .build(CacheLoader.from(new Function<String, User>() {
                @Override
                public User apply(String s) {
                    return new User(s,15);
                }
            }));
}
