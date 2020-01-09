package com.xuxy.designmode.bridge.shape;

import com.xuxy.designmode.bridge.color.Color;

/**
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 12:28 ）
 * @description
 */
public class CircleShape extends Shape {


    public CircleShape(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        color.bePiant("圆形");
    }
}
