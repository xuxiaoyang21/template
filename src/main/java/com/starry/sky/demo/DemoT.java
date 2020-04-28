package com.starry.sky.demo;

import com.starry.sky.demo.proxy.Person;
import com.starry.sky.demo.proxy.Sky;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class DemoT {


    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("999",122);
        hashMap.get("000");
        if(false) {
            System.out.println("");
        } else {
            System.out.println("");
        }
        /**
         * java动态代理 必须实现接口
         *@author 徐晓阳
         *@创建日期（ 2019-11-04 16:09 ）
         *@description
         */
        Sky sky = new Sky();
        Person person = (Person) Proxy.newProxyInstance(sky.getClass().getClassLoader(), sky.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开始");
                if(method.getName().equalsIgnoreCase("say")) {
                    method.invoke(sky,args);
                }
                if(method.getName().equalsIgnoreCase("eat")){
                    method.invoke(sky,args);
                }
                System.out.println("结束");
                return null;
            }
        });
        person.say("说话");
        person.eat("吃饭");

        /**
         * CGlib 不能是最终类 代理类是继承了目标类
         *@author 徐晓阳
         *@创建日期（ 2019-11-04 16:09 ）
         *@description
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(sky.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("预处理——————");
                methodProxy.invokeSuper(o, objects); //调用业务类（父类中）的方法
                System.out.println("调用后操作——————");
                return null;
            }
        });
        Sky sky1 = (Sky) enhancer.create();

        sky1.eat("dsdf");
        sky1.say("sdfsdfff");






    }
}
