package com.zhu.guava.joiner;

import com.google.common.base.Joiner;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//连接集合元素或者对实现了Appendable的实例追加连接
public class JoinerTest {
    public static void main(String[] args){
        List<Object> strings = new ArrayList<>();
        strings.add("aaaa");
        strings.add(null);
        strings.add("bbbb");
        strings.add("cccc");
        strings.add(4561);

        /**
         * join()可传入数组、迭代器、或者变长参数，实质是将每个对象的toString()连接
         */
        //使用"|"连接，跳过null。
        String re = Joiner.on("|")
                .skipNulls()
                .join(strings);//aaaa|bbbb|cccc|4561


        //使用"|"连接，null用 no value替代
        re = Joiner.on("|")
                .useForNull("null")
                .join(strings);//aaaa|null|bbbb|cccc|4561

        /**
         * appendTo()  对所有实现了Appendable接口都可用：如 StringBuilder、Writer、PrintStream、nio.charBuffer
         * 在当前对象后追加 ，并且使用指定连接符连接
         */

        //StrinBuilder
        Joiner joiner = Joiner.on("-")
                .useForNull("null");
        StringBuilder sb = new StringBuilder("start");
        re = joiner.join(sb,"foo","bar",null);//start-foo-bar-null
        System.out.println(sb.toString());//start

        //FileWriter
        try(FileWriter fileWriter = new FileWriter(new File("E:\\helloworld.txt"))){
            List<Date> dataList = new ArrayList<>();
            dataList.add(new Date());
            FileWriter newfw = joiner.appendTo(fileWriter,dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(new FileReader("E:\\helloworld.txt"))){
            String line ;
            while((line =  br.readLine())!=null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
