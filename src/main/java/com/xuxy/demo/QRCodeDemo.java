package com.xuxy.demo;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2020. 浙江浙大中控信息技术有限公司
 * author:xuxiaoyang.
 * date: .
 * time:19:36.
 * Description: 二维码生成器 测试demo
 */
public class QRCodeDemo {


    public static void main(String[] args) {

        //设置二维码宽度
        int width = 300;
        //设置二维码长度
        int height = 300;
        //设置生成图片格式
        String  format = "png";
        //设置生成二维码类型
        String contents = "丫头 爱你哦 么么哒";
        Map map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN, 0);
        try {
            BitMatrix bm = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height,map);
            Path file=new File("D:/img.png").toPath();
            MatrixToImageWriter.writeToPath(bm, format, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//            MultiFormatReader reader=new MultiFormatReader();//需要详细了解MultiFormatReader的小伙伴可以点我一下官方去看文档
//            File f=new File("D:/img.png");
//            BufferedImage image=ImageIO.read(f);
//            BinaryBitmap bb=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
//            HashMap map =new HashMap();
//            map.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            Result result = reader.decode(bb,map);
//            System.out.println("解析结果："+result.toString());
//            System.out.println("二维码格式类型："+result.getBarcodeFormat());
//            System.out.println("二维码文本内容："+result.getText());
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
