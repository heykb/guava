package com.zhu.guava.splitter;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.function.Consumer;

public class SplitterTest {
    public static  void print(Iterable it){
        it.forEach(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.print(o+"\t");
            }
        });
        System.out.println();
    }
    public static void main(String[] args) {
        Iterable it = Splitter.on("|")  //按字符串或字符切分
                .omitEmptyStrings() //去除空字符串
                .trimResults(CharMatcher.is('_')) //对每个结果去除首尾指定字符,默认空格
                //.trimResults(CharMatcher.anyOf("#_"))
                .trimResults()
                .split("##foo#| hhh  ||_ddd_");
        print(it);


       it = Splitter.onPattern("\\d+") //按正则表达式切分
               .split("aaaa2222aaaa444ddddgg44");
        print(it);

        it = Splitter.fixedLength(3) //按固定长度切分
                .limit(2)//切割出2个截止
                .split("aaabbbcccdddee");
        print(it);

        it = Splitter.on("(")
                .trimResults(CharMatcher.is(')'))
                .omitEmptyStrings()
                .split("(aaa)(bbbbbb)(2222222)(dddd)");
        print(it);
    }
}
