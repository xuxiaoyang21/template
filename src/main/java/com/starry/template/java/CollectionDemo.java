package com.starry.template.java;


import java.util.ArrayList;

/**
 *
 *  集合框架demo
 *  @author 徐晓阳
 *  @creatTime（ 2020-09-14 22:37 ）
 *  @description
 *
 */
public class CollectionDemo {

    public static void main(String[] args) {

        //位移运算
        displacementCal();
    }

    /**
     * 位移运算测试
     * @author 徐晓阳
     * @creatTime 2020-10-07 18:58
     * @version 1.0
     */
    private static void displacementCal() {
        //左移  是相当于2的n次方
        //右移  相当于除以这个数2的n次方
        int num = 4;
        num = num << 4;
        System.out.println("4左移动"+num);
        int num2 = 16;
        num2 = num2 >> 5;
        System.out.println("右移动："+num2);

        Integer integer = new Integer(2);
        Module module = integer.getClass().getModule();

    }

}
