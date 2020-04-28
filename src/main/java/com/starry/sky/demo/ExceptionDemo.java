package com.starry.sky.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2018/1/16  16:27
 * @Description: 异常测试
 */
public class ExceptionDemo {

    //异常有情况如下
    //未被捕获会直接抛出异常并且 不会执行下面的代码
    //若异常被捕获则不会只从try中代码块抛出异常代码下面的代码 但会程序不会终止 会继续执行代码

    public static void main(String[] args) throws Exception {
        try{
            List list = new ArrayList();
            list.get(2);
            System.out.println("异常后执行");
        }catch (Exception e) {//如果使用catch把异常捕获后 还会继续执行下面的代码 但是不会执行
            //但是发生异常后的代码不会执行
            System.out.println("程序发生异常");

        }
        System.out.println("执行");

    }

}
