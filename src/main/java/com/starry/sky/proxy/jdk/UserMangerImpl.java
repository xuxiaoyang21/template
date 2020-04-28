package com.starry.sky.proxy.jdk;

public class UserMangerImpl implements  UserManger {
    @Override
    public void sayHello(String name) {
            System.out.println(name + "hello proxy");
    }
}
