package com.xuxy.designmode.bridge.shape;

import com.xuxy.designmode.bridge.color.Color;

/**
 * 抽象类 形状
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 11:31 ）
 * @description
 */
public abstract class Shape {

    Color color;

    public Shape(Color color) {
        this.color = color;
    }

    /**
     * 图形绘制方法
     */
    public abstract void draw();
}
