package com.starry.sky.designmode.adapter;

public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("开始播放vlc>>>>>>>>>>>");
    }

    @Override
    public void playMp4(String fileName) {
        //todo
    }
}
