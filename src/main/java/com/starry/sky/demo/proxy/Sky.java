package com.starry.sky.demo.proxy;

public class Sky implements Person {
    @Override
    public void say(String som) {
        System.out.println("说话>>>>>>>>>>"+som);
    }

    @Override
    public void eat(String som) {
        System.out.println("吃饭>>>>>>>>>>"+som);
    }
}
