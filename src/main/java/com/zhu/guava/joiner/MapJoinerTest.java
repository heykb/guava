package com.zhu.guava.joiner;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

public class MapJoinerTest {
    public static void main(String[] args) {
        Map<String,String> testMap = new HashMap<>();
        testMap.put("name","zhu");
        testMap.put("addr","cd");
        testMap.put("age","10");
        testMap.put("hh",null);
        //创建mapJoiner
        Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");

        String re = mapJoiner.useForNull("")
                .join(testMap);
        System.out.println(re);
    }
}
