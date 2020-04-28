package com.starry.sky.designmode.adapter;

/**
 * 适配器模式测试
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 10:20 ）
 * @description
 */
public class AdapterMain {

    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play(PlayTypeEnum.MP3,"123");
        player.play(PlayTypeEnum.VLC,"123");
        player.play(PlayTypeEnum.MP4,"123");
        player.play(PlayTypeEnum.AVI,"123");
    }
}
