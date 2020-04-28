package com.starry.sky.designmode.prototype.impl;

import com.starry.sky.designmode.prototype.Shape;

/**
 * 圆形绘制类
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 13:37 ）
 * @description
 */
public class CircleShape extends Shape {

    public CircleShape() {
        this.type = "circle";
    }

    @Override
    protected void draw() {
        System.out.println("绘制图形为："+type);
    }
}
