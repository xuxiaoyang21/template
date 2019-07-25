package com.xuxy.demo.demoTest;

import clojure.lang.Obj;
import com.xuxy.entities.User;
import org.apache.poi.ss.formula.functions.Column;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo1 {


    public static void main(String[] args) {
        User user = new User();
        Class clazz = user.getClass();
        System.out.println(clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if(annotation != null) {
                System.out.println(annotation.required());
            }
            System.out.println(field.getName()+"  "+field.getType());
        }

        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
        }

        try {
            Method method = clazz.getMethod("setName", String.class);
//            User obj = (User) clazz.newInstance();
            method.invoke(clazz.newInstance(),"xuxy");

            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            User obj = (User) clazz.newInstance();
            System.out.println();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
