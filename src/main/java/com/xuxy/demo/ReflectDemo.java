package com.xuxy.demo;

import java.lang.reflect.Method;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/11/28  16:49
 * @Description:反射demo
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        //加载person类  得到当前类的class对象
        Class person =  Class.forName("com.xuxy.entities.Person");
        System.out.println("当前类的class对象为："+person);

        Method[]  methods = person.getDeclaredMethods();//获取公开的方法
        for(Method method : methods){
            System.out.println(method);
        }

    }
}
