package com.zhu.guava.strings;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;

public class StringsTest {
    public static void main(String[] args) {
        //用指定字符在指定位置（头或尾）填充字符串到指定长度
        String re = Strings.padEnd("foo",6,'x');//fooxxx
        re = Strings.padStart("foo",6,'x');//xxxfoo

        //如果为null转为“”
        Strings.nullToEmpty(re);

        //重复字符串指定次
        Strings.repeat("abc",2);//abcabc

    }
}
