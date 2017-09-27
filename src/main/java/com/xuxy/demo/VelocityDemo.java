package com.xuxy.demo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mxia on 2017/7/13.
 */
public class VelocityDemo {

    public static void main(String[] args) {

        //加载引擎
        VelocityEngine ve = new VelocityEngine();
        //设置驱动和加载文件
        ve.setProperty(Velocity.RESOURCE_LOADER,"class");
        ve.setProperty("class.resource.loader.class","org.apache.velocity.runtime" +
                ".resource.loader.ClasspathResourceLoader");

        //初始化
        ve.init();

        //加载模板
        Template t = ve.getTemplate("template/velocity_template.vm","UTF-8");
        //设置初始化数据
        VelocityContext context = new VelocityContext();
        //存放变量
        context.put("name","小明");

        //存放数组
        String[] strings = {"上班","吃饭","望月"};
        context.put("strings",strings);

        //存放集合
        List list = new ArrayList();
        list.add("日出");
        list.add("日落");

        context.put("list",list);

        //设置输出
        StringWriter writer = new StringWriter();
        //将环境数据转化输出
        t.merge(context,writer);

        System.out.println(writer.toString());
    }





}
