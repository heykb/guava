package com.zhu.guava.objects;

import java.util.Objects;

public class useHashCode {
    private String name;
    private String addr;
    private Integer age;
    private String id;
    @Override
    public int hashCode() {

        //为所有字段计算顺序敏感的hash
        int hashcode = Objects.hashCode(this);
        //指定字段计算hash
        hashcode = Objects.hashCode(this.id);
        return hashcode;
    }
}
