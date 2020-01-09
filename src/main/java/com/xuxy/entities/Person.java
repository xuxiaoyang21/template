package com.xuxy.entities;

import com.xuxy.demo.annotation.DocAnnotation;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/11/28  16:49
 * @Description:
 */
public class Person {

    public String name;

    @DocAnnotation
    public String address;

    public void add(){

        System.out.println("执行了add");
    }

    public String select(){
        System.out.println("执行了select()");
        return "true";
    }

    private void delete (){
        System.out.println("执行了delete()");
    }
}
