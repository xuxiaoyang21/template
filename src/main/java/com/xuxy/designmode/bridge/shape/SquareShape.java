package com.xuxy.designmode.bridge.shape;

import com.xuxy.designmode.bridge.color.Color;

/**
 * 正方形绘制
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 12:29 ）
 * @description
 */
public class SquareShape extends Shape {
    public SquareShape(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        color.bePiant("正方形");
    }
}
