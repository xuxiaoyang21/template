package com.xuxy.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {


    private Object targerObject;

    public Object newInvocationHandler(Object targerObject) {
        this.targerObject = targerObject;
        return Proxy.newProxyInstance(targerObject.getClass().getClassLoader(),
                targerObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

       //根据传入的参数怎加日志功能
        System.out.println("日志开始>>>>>");
        method.invoke(targerObject,args);
        System.out.println("日志结束>>>>>");
        return null;
    }
}
