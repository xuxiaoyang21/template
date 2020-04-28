package com.starry.sky.listener;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 *
 * 自定义监听器
 * @author 徐晓阳
 * @创建日期（ 2019-11-21 21:43 ）
 * @description
 */
public class DemoContextLoaderListener extends ContextLoaderListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("重写方法 初始.............");
        /**
         * 执行父类的初始化方法
         */
        super.contextInitialized(event);
    }
}
