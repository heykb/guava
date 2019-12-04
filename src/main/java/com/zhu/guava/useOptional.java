package com.zhu.guava;

import com.google.common.base.Optional;

import java.util.Set;


public class useOptional {

    public static void main(String[] args) {
        String s = "123";
        String none = null;

        //创建指定引用的Optional实例，引用为null快速失败
        Optional<String> stringOptional = Optional.of(s);

        //创建指定引用的Optional实例，引用为null表示缺失
        Optional<String> noneOptional = Optional.fromNullable(none);

        //创建默认的引用缺失实例
        noneOptional = Optional.absent();

        //引用是否存在
        Boolean ispresent = stringOptional.isPresent();

        //获取引用，若引用缺失则快速失败
        String value = stringOptional.get();
        //获取引用，若引用缺失则返回指定的值
        value = stringOptional.or("default");
        //获取引用，若引用缺失则返回null
        value = stringOptional.orNull();

        //若引用存在返回只有一个元素的集合，否则返回空集合。
        Set<String> valueSet = stringOptional.asSet();




    }
}
