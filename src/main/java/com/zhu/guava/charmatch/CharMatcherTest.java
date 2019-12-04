package com.zhu.guava.charmatch;


import com.google.common.base.CharMatcher;

//匹配英文字符，并提供trim、retain（保留）、collapse（折叠连续重复字符为单一指定字符）、remove
public class CharMatcherTest {
    public static void main(String[] args) {
        String src = "   aaa395ndjc   jlfdj    ADGDc   ";

        //折叠多个空格为单一空格
        String re = CharMatcher.WHITESPACE.collapseFrom(src,' ');// aaa395ndjc jlfdj ADGDc

    }
}
