package com.zhu.guava.collection;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.*;
import com.zhu.guava.User;

import java.util.*;

public class ListsTest {
    public static void main(String[] args) {
        User user1 = new User("zhangsan",5);
        User user2 = new User("lisi",10);
        User user3 = new User("wange",20);
        User user4 = new User("renbin",30);
        User user5 = new User("taiz",40);
        User user6 = new User("baici",50);
        //方便的创建
        List<User> userList = Lists.newArrayList(user1,user2,user3,user4,user5,user6);
        //分区
        List<List<User>> subList = Lists.partition(userList,2);
        /**
         * Sets
         */
        Set<String> s1 = Sets.newHashSet("1","2","3","5");
        Set<String> s2 = Sets.newHashSet("2","3","4");
        //s1有s2没有
        Sets.SetView re = Sets.difference(s1,s2);//[1, 5]

        //返回两个集合差异的元素
        Sets.symmetricDifference(s1,s2);//[1, 5, 4]

        //返回交集
        Sets.intersection(s1,s2);//[2, 3]
        //并集
        Sets.union(s1,s2);//[1, 2, 3, 4, 5]


        /**
         * maps
         */
        //将集合转map,集合的值做value
        Map<String,User> map = Maps.uniqueIndex(userList, new Function<User, String>() {
            //返回的值就是每个元素的key
            @Override
            public String apply(User user) {
                return user.getName();
            }
        });

        //将集合转map,集合的值做key
        Map<String,String> map1 = Maps.asMap(s1, new Function<String,String>(){

            @Override
            public String apply(String s) {
                return s+"45678135";
            }
        });

        //转换map的value
        Map<String,String> transtormValuesMap = Maps.transformValues(map, new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getAge().toString();
            }
        });


        /**
         * 类似value为ArrayList的map
         */
        ArrayListMultimap<String,String> multimap = ArrayListMultimap.create();
        multimap.put("foo","1");
        multimap.put("foo","2");
        multimap.put("foo","3");
        multimap.put("foo","3");
        multimap.get("foo");//[1, 2, 3]
        multimap.size();//4
        System.out.println(multimap);//{foo=[1, 2, 3, 3]}


        HashMultimap<String,String> hashMultimap = HashMultimap.create();
        hashMultimap.put("bar","1");
        hashMultimap.put("bar","2");
        hashMultimap.put("bar","3");
        hashMultimap.put("bar","3");
        hashMultimap.put("bar","3");
        System.out.println(hashMultimap);//{bar=[1, 2, 3]}

        /**
         * BiMap双向映射
         */
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","tom");
        biMap.put("2","tony");
        biMap.get("1");//tom
        //替换
        biMap.forcePut("1","zhu");
        biMap.forcePut("3","tony");
        biMap.get("1");//zhu
        biMap.inverse().get("tony");//3

        /**
         * Table类似Map<R,Map<C,V>> 两个有序的索引确定值
         */
        Table<String,String,String> table = HashBasedTable.create();
        table.put("1","100","tom");
        table.put("1","101","tony");
        table.put("2","100","baici");
        table.put("3","103","wowo");

        table.get("1","101");//tony
        table.cellSet();//[(1,100)=tom, (1,101)=tony, (2,100)=baici, (3,103)=wowo]
        table.row("1");//{100=tom, 101=tony}
        table.column("100");//{1=tom, 2=baici}
        table.rowKeySet();//[1, 2, 3]
        table.columnKeySet();//[100, 101, 103]
        table.columnMap();//{100={1=tom, 2=baici}, 101={1=tony}, 103={3=wowo}}
        table.rowMap();//{1={100=tom, 101=tony}, 2={100=baici}, 3={103=wowo}}
        table.size();//4

        /**
         * range 实现了Predicate，
         */
        Range<Integer> numberRangeClose = Range.closed(1,10);//[1,10]闭区间
        Range<Integer> numberRangeOpen = Range.open(1,10);//(1,10)开区间
        Range<Integer> numberRangeOpenClose = Range.openClosed(1,10);//(1,10]
        Range<Integer> numberRangeCloseOpen = Range.closedOpen(1,10);//(1,10]
        
       Predicate<User> predicate = Predicates.compose(Range.closed(10, 30), new Function<User, Integer>() {
           @Override
           public Integer apply(User user) {
               return user.getAge();
           }
       });


        boolean isRange = predicate.apply(new User("dd",50));//false不在区间内
    }

}
