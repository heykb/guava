package com.zhu.guava.objects;


import com.google.common.base.Objects;

public class useFirstNotNull {
    public static void main(String[] args) {


        String src = "aaa";
        Objects.firstNonNull(src,"default value");


    }
}
