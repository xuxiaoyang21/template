package com.starry.sky.proxy.cglib;


public class Demo {


    public static void main(String[] args) {
        CglibHandler cglibHandler = new CglibHandler();

        UserManger userManger = (UserManger) cglibHandler.getProxy(UserManger.class);
        userManger.sayHello("xuxy");
//

//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(UserManger.class);
//        enhancer.setCallback(cglibHandler);
//        UserManger userManger = (UserManger) enhancer.create();
//        userManger.sayHello("xuxy");

    }
}
