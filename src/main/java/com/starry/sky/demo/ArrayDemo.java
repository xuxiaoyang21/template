package com.starry.sky.demo;

import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.cache.Cache;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
//        String[][] str = {{"333","233"},{"11","333"}};
//        Map map =  ArrayUtils.toMap(str);
//        for(Object in : map.keySet()) {
//            System.out.println(" k
//
// ey:"+in+" value:"+map.get(in));
//        }
//
//        //输出结果
//        // key:11 value:333
//        // key:333 value:233
//        System.err.println("错误打印");
//        System.out.println("输出打印");
//
//
//        new Thread(new DemoThread()).start();
//        new Thread(new Demo1Thread()).start();

        JFrame f=new JFrame("java小程序");

        f.setSize(1000,800);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //设置是否可见
        f.setVisible(true);
    //设置尺寸可变
        f.setResizable(false);

        f.setLocationRelativeTo(null) ;

        f.setLayout(null);  //设置窗体布局为空布局

        JPanel p=new JPanel();//实例化一个面板

        //设置面板背景色为蓝色，如果不引入AWT包，程序将出错，可以试试看

        p.setBackground(Color.red);

//        p.setSize(800,600);          //设置面板对象大小

//        f.getContentPane().add(p);     //将面板添加到窗体中

        //如果使用下面添加面板的方法，面板将布满整个窗口，可以试试看

        f. setContentPane(p);






    }






}
