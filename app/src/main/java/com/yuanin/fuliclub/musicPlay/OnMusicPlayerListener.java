package com.yuanin.fuliclub.musicPlay;


import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by smile on 2018/5/28.
 */

public interface OnMusicPlayerListener {
    /**
     * 进度通知
     * @param progress
     * @param total
     */
    void onProgress(long progress, long total);

    /**
     * 已经暂停了
     */
    void onPaused(KnobbleDetailsInfoVo data);

    /**
     * 已经播放了
     */
    void onPlaying(KnobbleDetailsInfoVo data);

    /**
     * 播放器初始化完毕
     * @param mediaPlayer
     */
    void onPrepared(IjkMediaPlayer mediaPlayer, KnobbleDetailsInfoVo data);

    /**
     * 当前这首歌播放完毕了
     * @param mediaPlayer
     */
    void onCompletion(IjkMediaPlayer mediaPlayer);


    /**
     * 当发生错误时
     * @param mp
     * @param what
     * @param extra
     */
    void onError(IjkMediaPlayer mp, int what, int extra);
}
