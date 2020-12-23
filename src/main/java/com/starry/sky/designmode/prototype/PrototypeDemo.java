package com.starry.sky.designmode.prototype;

import com.starry.sky.designmode.prototype.impl.CircleShape;
import com.starry.sky.designmode.prototype.impl.SquareShape;

/**
 * 创造型
 * 原型模式
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 13:20 ）
 * @description
 * 适用于频繁的创造对象或者创建对象比较高昂的情况
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        //耗时 千次 31ms     万次78ms
//        createInfoOfDefault(1000);
        //耗时 12ms   千次
        createInfoOfProtory(1000);

    }

    /**
     *
     * 使用圆形模式
     * @author 徐晓阳
     * @creatTime 2020-10-11 17:22
     * @return
     * @version 1.0.0
     */
    private static void createInfoOfProtory(int createNumber) {
        long start = System.currentTimeMillis();
        /**
         * 加载形状缓存
         */
        ShapeCache.loadCache();
        /**
         * 疯狂创建对象
         */
        for(int i = 0 ; i < createNumber;i++) {
            Shape shape = ShapeCache.getShape("1");
            Shape shape1 = ShapeCache.getShape("2");
            shape.draw();
            shape1.draw();
        }
        System.out.println("创建对象：："+createNumber+"次，耗时："+(System. currentTimeMillis()-start)+"毫秒");
    }

    private static void createInfoOfDefault(int createNumber) {
        long start = System.currentTimeMillis();
        /**
         * 疯狂创建对象
         */
        for(int i = 0 ; i < createNumber;i++) {
            Shape shape = new CircleShape();
            Shape shape1 = new SquareShape();
            shape.draw();
            shape1.draw();
        }


        System.out.println("创建对象：："+createNumber+"次，耗时："+(System. currentTimeMillis()-start));
    }
}
