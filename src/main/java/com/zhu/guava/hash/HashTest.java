package com.zhu.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.*;
import com.zhu.guava.User;


public class HashTest {
    public static void main(String[] args) {
        HashFunction hashFunction = Hashing.md5();
        HashCode hashCode = hashFunction.hashString("adnckiegnindfifngd,", Charsets.UTF_8);
        hashCode.toString();

        //校验和hash
        Hashing.crc32();
        Hashing.adler32();


        /**
         * 创建一个插入对象为一亿个，误判率为0.0001的布隆过滤器
         * 默认fpp是0.03
         */
        BloomFilter<User> bloomFilter = BloomFilter.create(new Funnel<User>() {
            @Override
            public void funnel(User user, PrimitiveSink primitiveSink) {
                primitiveSink.putString(user.getName(),Charsets.UTF_8);
            }
        },100000000,0.0001);
        User user = new User("lisi",15);
        bloomFilter.mightContain(user);//false
        bloomFilter.put(user);
        bloomFilter.mightContain(user);//true

        /**
         * 一致性hash
         */
        int b = Hashing.consistentHash(
                Hashing.md5().hashString("userIdsgsdgsdg",Charsets.UTF_8),4);
        System.out.println(b);//3
        b = Hashing.consistentHash(
                Hashing.md5().hashString("userIdsgsdgsdg4",Charsets.UTF_8),4);
        System.out.println(b);//4
        b = Hashing.consistentHash(
                Hashing.md5().hashString("userIdsgsdgsdg4",Charsets.UTF_8),5);
        System.out.println(b);//4
        b = Hashing.consistentHash(
                Hashing.md5().hashString("userIdsgsdgsdg",Charsets.UTF_8),3);
        System.out.println(b);//1

        b = Hashing.consistentHash(
                Hashing.md5().hashString("userId",Charsets.UTF_8),3);
        System.out.println(b);
    }
}
