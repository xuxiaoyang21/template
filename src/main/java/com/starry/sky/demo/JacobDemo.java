package com.starry.sky.demo;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobDemo {


    public static void main(String[] args) {
        getAlarmWav("语音朗读的内容", "E:/1test.wav");
    } /**
     * @Title: Voice.java
     * @Package org.util
     * @Description: TODO该方法的主要作用：
     * @author A18ccms A18ccms_gmail_com
     * @date 2017-7-3 下午9:03:45
     * @version V1.0
     */




        /**
         *
         * @Title: strat
         * @Description: 该方法的主要作用：朗读
         * @param  @param content
         * @param  @param type 设定文件   0:开始，1停止
         * @return  返回类型：void
         * @throws
         */
        public static void getAlarmWav(String text, String path) {
            ActiveXComponent ax = null;
            try {
                ax = new ActiveXComponent("Sapi.SpVoice");
                Dispatch spVoice = ax.getObject();

                ax = new ActiveXComponent("Sapi.SpFileStream");
                Dispatch spFileStream = ax.getObject();

                ax = new ActiveXComponent("Sapi.SpAudioFormat");
                Dispatch spAudioFormat = ax.getObject();

                //设置音频流格式
                Dispatch.put(spAudioFormat, "Type", new Variant(22));

                //设置文件输出流格式
                Dispatch.putRef(spFileStream, "Format", spAudioFormat);

                //调用输出 文件流打开方法，创建一个.wav文件
                Dispatch.call(spFileStream, "Open", new Variant(path), new Variant(3), new Variant(true));
                //设置声音对象的音频输出流为输出文件对象
                Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
                //设置音量 0到100
                Dispatch.put(spVoice, "Volume", new Variant(100));
                //设置朗读速度
                Dispatch.put(spVoice, "Rate", new Variant(-2));
                //开始朗读
                Dispatch.call(spVoice, "Speak", new Variant(text));

                //关闭输出文件
                Dispatch.call(spFileStream, "Close");
                Dispatch.putRef(spVoice, "AudioOutputStream", null);

                spAudioFormat.safeRelease();
                spFileStream.safeRelease();
                spVoice.safeRelease();
                ax.safeRelease();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
