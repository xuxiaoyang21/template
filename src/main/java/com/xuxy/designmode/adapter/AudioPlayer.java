package com.xuxy.designmode.adapter;

/**
 * 播放mp3实现类
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 10:24 ）
 * @description
 */
public class AudioPlayer implements MedisPlayer {


    @Override
    public void play(PlayTypeEnum autoType, String fileName) {
        if(PlayTypeEnum.MP3.equals(autoType)) {
            System.out.println("开始播放mp3>>>>>>>>>>");
        } else {
            try {
                MediaAdapter adapter = new MediaAdapter(autoType);
                adapter.play(autoType,fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
