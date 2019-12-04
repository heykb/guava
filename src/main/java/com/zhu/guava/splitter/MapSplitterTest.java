package com.zhu.guava.splitter;

import com.google.common.base.Splitter;

import java.util.Map;

public class MapSplitterTest {
    public static void main(String[] args) {
        Splitter.MapSplitter mapSplitter = Splitter.on("&").withKeyValueSeparator("=");
        Map<String,String> map = mapSplitter.split("hh=&name=zhu&addr=cd&age=10");
        for(String key: map.keySet()){
            System.out.println(key+"="+map.get(key));
        }
    }
}
