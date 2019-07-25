package com.xuxy.demo.imageIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片绘制
 */
public class ImageDemo {


    public static void main(String[] args) {
        BufferedImage bi = new BufferedImage(263,192,BufferedImage.TYPE_INT_BGR);
        getImage(bi);

    }
    private static void getImage(BufferedImage bi) {
        //计算比例
        /**
         * 根据比例  width/height  值
         * 在 0~0.7 画5个线
         * 在 0.7~1.3 画4个线
         */
        Double scale = Double.valueOf(bi.getWidth())/bi.getHeight();
        int lineCount = 0;
        if(scale>0.5&& scale <=0.8) {
            lineCount = 6;
        } else if(scale>0.8 && scale <=4) {
            lineCount = 5;
        }
        //计计算画线间隔
        int interval = bi.getHeight()/lineCount;
        //偏移量
        int offset  = 5;
        //计算纵向画线间隔
        int zxInterval = bi.getWidth()/4;

        //计算字体的大小 和可以容纳的字体
        /**
         * 字体大小为 间隔高度的0.5
         * 如果字体大小小于15按0.7来算
         * 如果字体大于25 按
         */
        int fontSize = (int) (interval*0.5) < 15 ? (int) (interval*0.7) : (int) (interval*0.5);
        if(fontSize >25) {
            fontSize = 25;
        }
        System.out.println("字体大小："+fontSize);
        /**
         * 字体的个数
         * （竖列间隔*2-偏移量*2）/fontSize
         */
        int fontCount = (zxInterval*2-2*offset)/fontSize;
        /**
         * 如果个数少于6个 但是字体大于15 可适当减小字体比例
         */
        for(int i = fontSize;i >15;i--) {
            fontCount = (zxInterval*2-2*offset)/i;
            if(fontCount >5) {
                fontSize = i;
                break;
            }
        }

        System.out.println("名称最多容纳"+fontCount+"个字");
        if(fontCount < 6) {
            System.out.println("文字溢出 不能展示");
            return;
        }


        //开始画线
        Graphics graphics = bi.getGraphics();
        //画笔改为红色
        graphics.setColor(Color.red);
        //画横线--
        for(int i = 1; i < bi.getHeight()/interval;i++) {
            graphics.drawLine(0,i*interval,bi.getWidth(),i*interval);
        }
        //画竖线--
        graphics.setFont(new Font(null,0,fontSize));
        graphics.drawString("停车场名称",offset,interval-offset);
        for(int i = 2; i < bi.getWidth()/zxInterval;i++) {
            if(i == 2) {
                graphics.setFont(new Font(null,0,fontSize));
                graphics.drawString("余车位",i*zxInterval+offset,interval-offset);
            } else if(i == 3) {
                graphics.setFont(new Font(null,0,fontSize));
                graphics.drawString("总车位",i*zxInterval+offset,interval-offset);
            }
            graphics.setColor(Color.red);
            graphics.drawLine(i*zxInterval,0,i*zxInterval,bi.getHeight());
        }
        graphics.setFont(new Font(null,0,fontSize));
        String parkName = "停车场";
        //绘制停车场文字信息
        for(int i = 2;i <= lineCount;i++) {
            graphics.setColor(Color.green);
            graphics.drawString("num"+i+parkName,offset,interval*i-offset);
            graphics.setColor(Color.green);
            graphics.drawString(50*i+"",2*zxInterval+offset,interval*i-offset);
            graphics.setColor(Color.red);
            graphics.drawString(500*i+"",3*zxInterval+offset,interval*i-offset);
        }
        graphics.dispose();
        try {
            ImageIO.write(bi,"png",new File("/Users/starrysky/Desktop/demo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
