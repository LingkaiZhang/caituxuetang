package com.yuanin.fuliclub.musicPlay;



import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;

import java.util.List;

/**
 * Created by smile on 2018/6/23.
 */

public interface PlayListManager {
    List<KnobbleDetailsInfoVo> getPlayList();

    void setPlayList(List<KnobbleDetailsInfoVo> datum);

    void play(KnobbleDetailsInfoVo song);

    void pause();

    void resume();

    void delete(KnobbleDetailsInfoVo song);

    KnobbleDetailsInfoVo getPlayData();

    KnobbleDetailsInfoVo next();

    KnobbleDetailsInfoVo previous();

    int getLoopModel();

    int changeLoopModel();

    void addPlayListListener(PlayListListener listener);

    void removePlayListListener(PlayListListener listener);

    void destroy();

    /**
     * 下一首播放
     * @param song
     */
    void nextPlay(KnobbleDetailsInfoVo song);
}
