package com.xuxy.designmode.adapter;

/**
 * 媒体播放器接口
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 10:23 ）
 * @description
 */
public interface MedisPlayer {
    /**
     * 播放接口
     * @param autoType
     * @param fileName
     */
    void play(PlayTypeEnum autoType,String fileName);
}
