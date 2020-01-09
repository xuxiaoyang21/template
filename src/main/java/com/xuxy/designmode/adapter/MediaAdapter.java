package com.xuxy.designmode.adapter;

/**
 * 播放器适配接口
 * @author 徐晓阳
 * @创建日期（ 2020-01-09 10:29 ）
 * @description
 */
public class MediaAdapter implements MedisPlayer {

    /**
     * 引入需要适配的播放器
     */
    AdvancedMediaPlayer advancedMediaPlayer;

    /**
     * 重载构造函数
     * @param autoType 传入使用播放器类型
     */
    public MediaAdapter(PlayTypeEnum autoType) throws Exception {
        if(PlayTypeEnum.VLC.equals(autoType)) {
            advancedMediaPlayer = new VlcPlayer();
        } else if(PlayTypeEnum.MP4.equals(autoType)) {
            advancedMediaPlayer = new Mp4Player();
        } else {
            throw new Exception("此播放器类型未借入不支持>>>>>>>>>>");
        }
    }

    /**
     * 适配
     * @param autoType
     * @param fileName
     */
    @Override
    public void play(PlayTypeEnum autoType, String fileName) {
        if(PlayTypeEnum.VLC.equals(autoType)) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (PlayTypeEnum.MP4.equals(autoType)) {
            advancedMediaPlayer.playMp4(fileName);
        } else {
            System.out.println("此播放类型的视频不支持>>>>>>>>>");
        }
    }
}
