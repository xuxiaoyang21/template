package com.xuxy.designmode.prototype;

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
        /**
         * 加载形状缓存
         */
        ShapeCache.loadCache();
        /**
         * 疯狂创建对象
         */
        Shape shape = ShapeCache.getShape("1");
        Shape shape1 = ShapeCache.getShape("2");

        shape.draw();
        shape1.draw();
    }
}
