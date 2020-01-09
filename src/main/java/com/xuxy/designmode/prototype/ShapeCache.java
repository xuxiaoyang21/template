package com.xuxy.designmode.prototype;

import com.xuxy.designmode.prototype.impl.CircleShape;
import com.xuxy.designmode.prototype.impl.SquareShape;

import java.util.HashMap;
import java.util.Map;

/**
 * 形状类创建缓存
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 13:39 ）
 * @description
 */
public class ShapeCache {

    /**
     * 容器
     */
    public static Map<String,Shape> containerMap = new HashMap<>();

    /**
     * 获取形状类 根据id
     * @param id
     * @return
     */
    public static Shape getShape(String id) {
        return (Shape) containerMap.get(id).clone();
    }

    /**
     * 加载各种形状类的缓存
     */
    public static void loadCache() {
        CircleShape circleShape = new CircleShape();
        circleShape.setId("1");

        SquareShape squareShape = new SquareShape();
        squareShape.setId("2");

        containerMap.put(circleShape.getId(),circleShape);
        containerMap.put(squareShape.getId(),squareShape);
    }
}
