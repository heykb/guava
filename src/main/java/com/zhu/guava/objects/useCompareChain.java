package com.zhu.guava.objects;

import com.google.common.collect.ComparisonChain;

public class useCompareChain implements Comparable<useCompareChain>{
    public String name;
    public String addr;
    public Integer age;
    public String id;

    @Override
    public int compareTo(useCompareChain o) {
        //一直比较，直至全部比较完或者发现false不相等
        return ComparisonChain.start()
                .compare(name,o.name)
                .compare(addr,o.addr)
                .compare(age,o.age)
                .compare(id,o.id)
                .result();
    }
}
