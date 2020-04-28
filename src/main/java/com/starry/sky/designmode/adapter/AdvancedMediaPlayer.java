package com.starry.sky.designmode.adapter;

/**
 * 播放器接口 vls mp4
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 10:26 ）
 * @description
 */
public interface AdvancedMediaPlayer {

    /**
     * 播放vls接口
     * @param fileName
     */
    void playVlc(String fileName);

    /**（
     * 播放mp4接口
     * @param fileName
     */
    void playMp4(String fileName);
}
