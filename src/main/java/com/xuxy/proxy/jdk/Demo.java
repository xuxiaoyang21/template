package com.xuxy.proxy.jdk;

public class Demo {

    public static void main(String[] args) {

        /**
         * 动态代理的对象 必须实现接口 他是通过调用接口的来实现的
         */
       LogHandler logHandler = new LogHandler();
       UserManger userManger1 = (UserManger) logHandler.newInvocationHandler(new UserMangerImpl());
       userManger1.sayHello("xiaoyang");
    }
}
