package com.xuxy.demo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mxia on 2017/7/13.
 */
public class VelocityDemo {

    /*public static void main(String[] args) {

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
    }*/

    /*public static void main(String[] args) throws Exception {
        createImage("请在这里输入文字", new Font("微软雅黑", Font.PLAIN, 32), new File("d:/a.png"), 416, 304);
    }

    // 根据str,font的样式以及输出文件目录
    public static void createImage(String str, Font font, File outFile,
                                   Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.black);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        *//** 用于获得垂直居中y *//*
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        for (int i = 0; i < 6; i++) {// 256 340 0 680
            g.drawString(str, i * 680, y);// 画出字符串
        }
        g.dispose();
        ImageIO.write(image, "png", outFile);// 输出png图片

        Reader reader = new InputStreamReader(new FileInputStream(new File("")));
        Writer writer = new OutputStreamWriter(new ObjectOutputStream(new FileOutputStream(new File(""))));
    }*/

    public static void main(String[] args){
        File outFile = new File("E:/a.png");
        Integer width = 304;
        Integer height = 304;
        String str = "哈哈 你是誰";

        try {
            // 创建图片
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_BGR);
            Font font = new Font("隶书",Font.BOLD,25);
            Graphics g = image.getGraphics();
//            g.setColor(Color.BLUE);
//            g.fillRect(0, 0, width, height);//填充底色
            g.setClip(0, 0, 250, 250);//设置剪切区
//            g.setColor(Color.RED);//设置画笔为黑色
//            g.fillRect(0, 0, 25, 25);// 先用黑色填充整张图片,也就是背景
            //g.draw3DRect(0,0,width,height,true);
//            g.fill3DRect(0,0,width,height,true);

            g.setColor(Color.RED);
//            g.fillArc(0,0,width,height,0 ,360 );
            g.fillRect(0, 0, width, height);//填充底色
            g.setColor(Color.black);// 在换成黑色
           // g.setFont(font);// 设置画笔字体
//            * 用于获得垂直居中y
            Rectangle clip = g.getClipBounds();
            System.out.println(clip.height);
            FontMetrics fm = g.getFontMetrics(font);
            int ascent = fm.getAscent();
            int descent = fm.getDescent();
            int y = (clip.height - (ascent + descent)) / 2 + ascent;
            for (int i = 0; i < 6; i++) {// 256 340 0 680
                g.drawString(str,   600, y);// 画出字符串
            }
            g.dispose();
            ImageIO.write(image, "bmp", outFile);// 输出png图片

        }catch (IOException e){

        }

    }






}
