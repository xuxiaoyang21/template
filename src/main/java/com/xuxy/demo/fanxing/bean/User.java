package com.xuxy.demo.fanxing.bean;

import java.util.List;

public class User<T> extends LongId<T> {


    private T t;




    public static <E> List<? extends  E> getUserName(List< ? extends E> t) {



        return t ;
    }
}
