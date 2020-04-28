package com.starry.sky.demo.demoTest;

import com.starry.sky.demo.annotation.DocAnnotation;
import com.starry.sky.entities.Person;
import com.starry.sky.demo.fanxing.Gun;
import com.starry.sky.demo.jdk.emnu.EnumTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DemoJDK {

    public static void main(String[] args) throws NoSuchFieldException {
        fanxing();
//        string();
//        annotation();
    }


    public static void fanxing() {
        Gun<String> gun = new Gun();
        gun.set("泛型测试：：");
        System.out.println(gun.get());
        //泛型方法
        gun.getGun(gun);
    }

    public static void string(){
        /**
         * char数组的拷贝
         */
        String str = "i l徐小ove you";
        String str1 = "1徐小样3456789";
        //string转 字节数据
        char[] cha = str.toCharArray();
        System.out.println(cha); //打印当前的自己数据
        //将当前string的值字节 从0-5拷贝到cha字节数组中从第4个索引位开始
        str1.getChars(0,5,cha,4);
        System.out.println(cha);

        /**
         * byte数组的拷贝
         */
        byte[] bytes = str.getBytes();
        System.out.println(bytes);
        str.getBytes(0,5,bytes,1);
        System.out.println(bytes);

        /**
         * equal 方法 对比每一个自己的大小是否相等
         * == 对比的是内存地址
         */
        str.equals(str1);

        /**
         * compareTo  一次对比每个字节的大小 用
         */
        int reuslt = str.compareTo(str1);
        /**
         * compareToIgnoreCase 忽略大小写对比
         */
        int rst  = str.compareToIgnoreCase(str);
        /**
         * regionMatches 比较两个字符喘特定区域的是否相等
         *  参数
         */

        String s1 = "1234567";
        String s2 = "2345678";
        // 参数一 愿字符串开始位置   2 对比字符串 3 对比字符开始位置  4对比长度
        boolean bool = s1.regionMatches(2,s2,1,4);
        boolean boolIgnore = s1.regionMatches(false,2,s2,2,4);
        System.out.println(bool);
        /**
         * startsWith 开头判断是否相等命令
         */
        System.out.println(s1.startsWith("234",2)); //false
        System.out.println(s1.startsWith("234",1)); //true
        System.out.println(s1.startsWith("123")); //true

        String sss = s1.intern();

    }

    public static void enums() {
        EnumTest ss = EnumTest.ERROR;
    }

    public static void annotation() throws NoSuchFieldException {
        Person person = new Person();
        Class clazz = person.getClass();

       //得到当前的所有字段
        Field field = clazz.getField("address");
        DocAnnotation docAnnotation  = field.getAnnotation(DocAnnotation.class);
        if(docAnnotation != null) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
        Class<? extends Annotation> annotation = docAnnotation.annotationType();
        Annotation[] annotation1 = annotation.getAnnotations();
        for(Annotation an : annotation1) {
            System.out.println();
        }
        System.out.println();
    }



}
