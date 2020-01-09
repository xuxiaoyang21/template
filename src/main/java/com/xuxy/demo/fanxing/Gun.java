package com.xuxy.demo.fanxing;

import java.util.List;

/**
 * 定义一个泛型类
 * @param <T>
 */
public class Gun <T>  {

    public T t;

    public T get() {
        return t;
    }
    public void set(T t) {
        this.t = t;
    }

    /**
     * 定义一个泛型方法
     */
    public <E> E getGun(E e) {
        if(e instanceof String) {
            System.out.println("字符串类型");
        } else if(e instanceof Integer) {
            System.out.println("整数类型");
        } else {
            System.out.println("其他类型");
        }
        return e;
    }

}
