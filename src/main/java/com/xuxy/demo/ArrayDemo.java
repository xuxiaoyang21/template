package com.xuxy.demo;

import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2018/1/16  15:55
 * @Description: Array对象
 */
public class ArrayDemo {


    public static void main(String[] args) {
        //把双数组改成map 格式支持 第二个数组中的集合为两个 多的化自动去掉 只会去两个
        //例如{{"333","233"},{"11","333"}}
        //转换后  1  key 333  value 233
        //转换后  2  key 11  value 333
        String[][] str = {{"333","233"},{"11","333"}};
        Map map =  ArrayUtils.toMap(str);
        for(Object in : map.keySet()) {
            System.out.println(" key:"+in+" value:"+map.get(in));
        }

        //输出结果
        // key:11 value:333
        // key:333 value:233
    }
}
