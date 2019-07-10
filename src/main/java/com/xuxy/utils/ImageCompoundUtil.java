package com.xuxy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageCompoundUtil {

        private final static Logger LOGGER = LoggerFactory.getLogger(ImageCompoundUtil.class);


        /**
         * 图片压缩
         *
         * @param输入：InputStream
         * @param返回 ：InputStream
         */
        public static InputStream photoCompress(InputStream leftTop, IAutoSize autoSize) {
            InputStream is = null;
            try {
                Image leftTopImg = getImage(leftTop);
                Point size = autoSize.autoSize(leftTopImg);//借用下point  不再单独定义包含长宽属性的对象了
                BufferedImage mBufferedImage = new BufferedImage((size.x) / 4, (size.y) / 4, BufferedImage.TYPE_INT_RGB);
                compress(mBufferedImage, leftTopImg, 0, 0, (size.x) / 4, (size.y) / 4);

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(mBufferedImage, "jpg", os);
                is = new ByteArrayInputStream(os.toByteArray());
            } catch (IOException e) {
                LOGGER.error("", e);
            }
            ;
            return is;
        }


        /**
         * 2*2图片合成
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom
         * @param rightButtom
         */
        public static void meger2_2(String roadAndedge, OutputStream out, File leftTop, File rightTop, File leftButtom, File rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 2, size.y * 2, BufferedImage.TYPE_INT_RGB);
//				Graphics2D g2 = mBufferedImage.createGraphics();
//				g2.drawImage(leftTopImg, 0,0,size.x,size.y, Color.white, null);
//				g2.drawImage(rightTopImg, size.x,0,size.x,size.y, Color.white, null);
//				g2.drawImage(leftButtomImg, 0,size.y,size.x,size.y, Color.white, null);
//				g2.drawImage(rightButtomImg, size.x,size.y,size.x,size.y, Color.BLACK, null);
//				g2.dispose();
                compress(mBufferedImage, leftTopImg, 0, 0, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 0, size.x, size.y);
                compress(mBufferedImage, leftButtomImg, 0, size.y, size.x, size.y);
                compress(mBufferedImage, rightButtomImg, size.x, size.y, size.x, size.y);
                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);  //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 30);  //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 2*1图片合成，无水印
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom  任意
         * @param rightButtom 任意
         */
        public static void meger2_1_no(String roadAndedge, OutputStream out, InputStream leftTop, InputStream rightTop, InputStream leftButtom, InputStream rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 2, size.y, BufferedImage.TYPE_INT_RGB);
                compress(mBufferedImage, leftTopImg, 0, 0, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 0, size.x, size.y);

                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);  //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 40);  //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 2*2图片合成+无水印
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom
         * @param rightButtom
         */
        public static void meger2_2_no(String roadAndedge, OutputStream out, InputStream leftTop, InputStream rightTop, InputStream leftButtom, InputStream rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 2, size.y * 2, BufferedImage.TYPE_INT_RGB);
//				Graphics2D g2 = mBufferedImage.createGraphics();
//				g2.drawImage(leftTopImg, 0,0,size.x,size.y, Color.white, null);
//				g2.drawImage(rightTopImg, size.x,0,size.x,size.y, Color.white, null);
//				g2.drawImage(leftButtomImg, 0,size.y,size.x,size.y, Color.white, null);
//				g2.drawImage(rightButtomImg, size.x,size.y,size.x,size.y, Color.BLACK, null);
//				g2.dispose();
                compress(mBufferedImage, leftTopImg, 0, 0, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 0, size.x, size.y);
                compress(mBufferedImage, leftButtomImg, 0, size.y, size.x, size.y);
                compress(mBufferedImage, rightButtomImg, size.x, size.y, size.x, size.y);

                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                 //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);   //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 40);  //10,40 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 3图片合成，无水印
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom  任意
         * @param rightButtom 任意
         */
        public static void meger3_1_no(String roadAndedge, OutputStream out, InputStream leftTop, InputStream rightTop, InputStream leftButtom, InputStream rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 3, size.y, BufferedImage.TYPE_INT_RGB);
                compress(mBufferedImage, leftTopImg, 0, 0, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 0, size.x, size.y);
                compress(mBufferedImage, leftButtomImg, size.x * 2, 0, size.x, size.y);

                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);  //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 40);  //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 2*1图片合成+顶端加黑条显示水印
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom  任意
         * @param rightButtom 任意
         */
        public static void meger2_1_yes(String roadAndedge, OutputStream out, InputStream leftTop, InputStream rightTop, InputStream leftButtom, InputStream rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 2, size.y + 60, BufferedImage.TYPE_INT_RGB);
                compress(mBufferedImage, leftTopImg, 0, 60, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 60, size.x, size.y);

                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);  //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 40);  //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 2*2图片合成+顶端加黑条显示水印
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom
         * @param rightButtom
         */
        public static void meger2_2_yes(String roadAndedge, OutputStream out, InputStream leftTop, InputStream rightTop, InputStream leftButtom, InputStream rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 2, size.y * 2 + 60, BufferedImage.TYPE_INT_RGB);
//				Graphics2D g2 = mBufferedImage.createGraphics();
//				g2.drawImage(leftTopImg, 0,0,size.x,size.y, Color.white, null);
//				g2.drawImage(rightTopImg, size.x,0,size.x,size.y, Color.white, null);
//				g2.drawImage(leftButtomImg, 0,size.y,size.x,size.y, Color.white, null);
//				g2.drawImage(rightButtomImg, size.x,size.y,size.x,size.y, Color.BLACK, null);
//				g2.dispose();
                compress(mBufferedImage, leftTopImg, 0, 60, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 60, size.x, size.y);
                compress(mBufferedImage, leftButtomImg, 0, size.y + 60, size.x, size.y);
                compress(mBufferedImage, rightButtomImg, size.x, size.y + 60, size.x, size.y);

                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                 //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);   //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 40);  //10,40 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 2*1图片合成+顶端加黑条显示水印
         *
         * @param leftTop
         * @param rightTop
         * @param leftButtom  任意
         * @param rightButtom 任意
         */
        public static void meger3_1_yes(String roadAndedge, OutputStream out, InputStream leftTop, InputStream rightTop, InputStream leftButtom, InputStream rightButtom, IAutoSize autoSize) {
            try {
                Image leftTopImg = getImage(leftTop);
                Image rightTopImg = getImage(rightTop);
                Image leftButtomImg = getImage(leftButtom);
                Image rightButtomImg = getImage(rightButtom);
                Point size = autoSize.autoSize(leftTopImg, rightTopImg, leftButtomImg, rightButtomImg);//借用下point  不再单独定义包含长宽属性的对象了

                BufferedImage mBufferedImage = new BufferedImage(size.x * 3, size.y + 60, BufferedImage.TYPE_INT_RGB);
                compress(mBufferedImage, leftTopImg, 0, 60, size.x, size.y);
                compress(mBufferedImage, rightTopImg, size.x, 60, size.x, size.y);
                compress(mBufferedImage, leftButtomImg, size.x*2, 60, size.x, size.y);

                //得到画笔对象
                Graphics g = mBufferedImage.getGraphics();
                g.setColor(Color.white);                //设置字体颜色。
                Font f = new Font("宋体", Font.BOLD, 32);  //最后一个参数用来设置字体的大小
                g.setFont(f);
                g.drawString(roadAndedge, 10, 40);  //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.dispose();

//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
//            encoder.setJPEGEncodeParam(param);
//            encoder.encode(mBufferedImage);
                ImageIO.write(mBufferedImage, "jpg", out);
                out.flush();
                out.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }


        /**
         * 尺寸缩放，默认策略为拉伸自适应长宽，不做平铺，多余部分填黑色
         *
         * @param mBufferedImage 画板
         * @param image          原始图片
         * @param pX             在mBufferedImage中基准起始点
         * @param pY             在mBufferedImage中基准起始点
         * @param width          在mBufferedImage中目标宽
         * @param height         在mBufferedImage中目标高
         * @return
         */
        private static Image compress(BufferedImage mBufferedImage, Image image, int pX, int pY, int width, int height) {
            Graphics2D g2 = mBufferedImage.createGraphics();

            int x = pX;
            int y = pY;
            int w = width;
            int h = height;
            if (image == null) {
                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, w, h);
            } else {
                double scale = (double) width / height;
                double imageScale = (double) image.getWidth(null) / image.getHeight(null);
                if (imageScale / scale > 1) {//拉伸自适应后过矮
                    y = (int) (pY + (height - (double) width / image.getWidth(null) * image.getHeight(null)) / 2);
                    h = (int) (image.getHeight(null) * ((double) width / image.getWidth(null)));
                } else if (imageScale / scale < 1) {//拉伸自适应后过窄
                    x = (int) (pX + (width - (double) height / image.getHeight(null) * image.getWidth(null)) / 2);
                    w = (int) (image.getWidth(null) * ((double) height / image.getHeight(null)));
                } else {//拉伸自适应后比例刚好
                    w = (int) (width * (imageScale / scale));
                    h = (int) (height * (imageScale / scale));
                }
                g2.drawImage(image, x, y, w, h, Color.BLACK, null);
            }

            g2.dispose();
            return mBufferedImage;
        }

        private static Image getImage(File file) throws IOException {
            if (file == null || !file.exists() || !file.isFile()) {
                return null;
            } else {
                return ImageIO.read(file);
            }
        }

        private static Image getImage(InputStream in) throws IOException {
            if (in == null) {
                return null;
            } else {
                return ImageIO.read(in);
            }
        }


        private interface IAutoSize {
            public Point autoSize(Image... images);
        }


        public enum AUTO_SIZE implements IAutoSize {

            //自动计算合成图片大小时，按所有待合成图片中的第一张有效图片的长宽作为基准大小，缩放所有图片
            BASE_ON_FIRST() {
                @Override
                public Point autoSize(Image... images) {
                    for (Image image : images) {
                        if (image != null) {
                            return new Point(image.getWidth(null), image.getHeight(null));
                        }
                    }
                    return new Point(0, 0);
                }
            },

            //自动计算合成图片大小时，按所有待合成图片中的最小的长宽作为基准大小，缩放所有图片
            @Deprecated
            BASE_ON_MINSIZE() {
                @Override
                public Point autoSize(Image... images) {
                    // TODO Auto-generated method stub
                    return null;
                }
            },

            //自动计算合成图片大小时，按所有待合成图片中的最大的长宽作为基准大小，缩放所有图片
            @Deprecated
            BASE_ON_MAXSIZE() {
                @Override
                public Point autoSize(Image... images) {
                    // TODO Auto-generated method stub
                    return null;
                }
            },

            //自动计算合成图片大小时，按指定的长宽作为基准大小，缩放所有图片
            @Deprecated
            BASE_ON_FIXEDSIZE() {
                @Override
                public Point autoSize(Image... images) {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
        }






}
