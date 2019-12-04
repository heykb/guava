package com.zhu.guava.collection;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.zhu.guava.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FluentIterableTest {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan",5));
        list.add(new User("lisi",10));
        list.add(new User("wange",20));
        list.add(new User("renbin",30));
        list.add(new User("taiz",40));
        list.add(new User("baici",50));

        //过滤
        List<User> re = FluentIterable.from(list)
                .filter(new Predicate<User>() { //过滤
                    @Override
                    public boolean apply(User user) {
                        return user.getAge()<30;
                    }
                })
                .toList();
        System.out.println(re);
        
        //转换
        List<String> res = FluentIterable.from(list)
                .transform(new Function<User, String>() {
                    @Override
                    public String apply(User user) {
                        return Joiner.on("#").join(user.getName(),user.getAge());
                    }
                }).filter(new Predicate<String>() {
                    @Override
                    public boolean apply(String s) {
                        return s.endsWith("0");
                    }
                })
                .toList();
        System.out.println(res);
    }
}
