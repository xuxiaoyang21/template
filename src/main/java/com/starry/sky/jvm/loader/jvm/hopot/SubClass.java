package com.starry.sky.jvm.loader.jvm.hopot;

import java.util.Stack;

public class SubClass extends SuperClass {


    static {
        System.out.println("SubClass is running!!!!!");
    }


    public static void main(String[] args) {
        System.out.println(SubClass.value);
        System.out.println("数据测试入口");
        Stack stack = new Stack();
    }
}
