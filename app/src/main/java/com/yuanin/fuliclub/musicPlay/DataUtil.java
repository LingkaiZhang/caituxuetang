package com.yuanin.fuliclub.musicPlay;

import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;

import java.util.Collection;
import java.util.List;


/**
 * Created by smile on 2018/6/6.
 */

public class DataUtil {
    /**
     * 将音乐中，对象的字段，展开
     *
     * @param songs
     * @return
     */
  /*  public static List<KnobbleDetailsInfoVo> fill(List<KnobbleDetailsInfoVo> songs) {
        for (KnobbleDetailsInfoVo s:songs
             ) {
            s.fill();
        }
        return songs;
    }*/

    /**
     * 更改是否在播放类别表示
     * @return
     */
    public static Collection<? extends KnobbleDetailsInfoVo> changePlayListFlag(List<KnobbleDetailsInfoVo> songs, boolean isPlayList) {
        for (KnobbleDetailsInfoVo s:songs
                ) {
            s.setPlayList(isPlayList);
        }
        return songs;
    }
}
