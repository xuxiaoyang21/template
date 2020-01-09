package com.xuxy.designmode.bridge;

import com.xuxy.designmode.bridge.color.Color;
import com.xuxy.designmode.bridge.color.RedColor;
import com.xuxy.designmode.bridge.shape.SquareShape;

/**
 * 桥接模式测试  - 结构性模式
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 10:59 ）
 * @description
 * 将抽象部分和实现部分分离 让他们能够独立变化
 */
public class BridgeDemo {

    public static void main(String[] args) {
        //绘制红色的正方形
        SquareShape squareShape = new SquareShape((shape) ->
                System.out.println("任意颜色的"+shape)
        );
        squareShape.draw();
    }
}
