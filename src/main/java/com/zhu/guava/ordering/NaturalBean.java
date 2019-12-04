package com.zhu.guava.ordering;

import com.google.common.base.Function;

import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.zhu.guava.User;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class NaturalBean {
    private String str;
    private int num;
    private Date date;

    public NaturalBean(String str, int num, Date date) {
        this.str = str;
        this.num = num;
        this.date = date;
    }

    public String getStr() {
        return str;
    }

    public int getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "NaturalBean{" +
                "str='" + str + '\'' +
                ", num=" + num +
                ", date=" + date +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        List<NaturalBean> arrayList = new ArrayList<>();
        arrayList.add(new NaturalBean("b",6,new Date()));

        arrayList.add(new NaturalBean("c",6,new Date()));
        arrayList.add(new NaturalBean("c",7,new Date()));
        TimeUnit.SECONDS.sleep(1);
        arrayList.add(new NaturalBean("c",7,new Date()));
        arrayList.add(null);

        //Ordering.natural()自然排序器，要求最终排序类型实现Comparable接口，onResultOf对元素进行操作后对返回的类型排序
        Ordering<NaturalBean> ordering = Ordering.natural().nullsLast().onResultOf(new Function<NaturalBean, String>() {
            @Override
            public String apply(NaturalBean naturalBean) {
                if(naturalBean == null) return null;
                else return naturalBean.getStr()+naturalBean.getNum()+naturalBean.getDate();
            }
        });
        Collections.sort(arrayList,ordering);
        for(NaturalBean naturalBean: arrayList){
            System.out.println(naturalBean);
        }

        System.out.println("======================================");
        //reversed() 将当前排序器倒转排序
        System.out.println(ordering.reverse().sortedCopy(arrayList));



        System.out.println("======================================");
        //返回最大的2的元素
        List<NaturalBean> re = ordering.reverse().greatestOf(arrayList.iterator(),2);
        for(NaturalBean naturalBean: re){
            System.out.println(naturalBean);
        }


        //二级排序
        Ordering<User> userOrdering = Ordering.from(new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).compound(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Ints.compare(o1.getAge(),o2.getAge());
            }
        });

    }
}
