package com.xuxy.proxy.jdk;

public class UserMangerImpl implements  UserManger {
    @Override
    public void sayHello(String name) {
            System.out.println(name + "hello proxy");
    }
}
