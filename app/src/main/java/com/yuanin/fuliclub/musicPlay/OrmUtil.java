package com.yuanin.fuliclub.musicPlay;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 06/03/2018.
 */

public class OrmUtil {
    static LiteOrm orm;
    private static OrmUtil instance;

    public OrmUtil(Context context) {
        orm = LiteOrm.newSingleInstance(context, "caituxuetang-music.db");
    }

    public static OrmUtil getInstance(Context context) {
        if (instance == null) {
            instance = new OrmUtil(context);
        }
        return instance;
    }

    public void saveSong(KnobbleDetailsInfoVo song, String userId) {
        song.setUserId(userId);
        orm.save(song);
    }

    /*public void deleteSongs(String userId) {
        orm.delete(new WhereBuilder(KnobbleDetailsInfoVo.class)
                .where("userId=?", new String[]{userId}));
    }*/

    public List<KnobbleDetailsInfoVo> queryPlayList(String userId) {
        ArrayList<KnobbleDetailsInfoVo> songs = orm
                .query(new QueryBuilder<KnobbleDetailsInfoVo>(KnobbleDetailsInfoVo.class)
                        .whereEquals("userId", userId)
                        .whereAppendAnd()
                        .whereEquals("playList", true)
                        .appendOrderAscBy("id"));

        return songs;
    }

    public void deleteSong(KnobbleDetailsInfoVo song) {
        orm.delete(song);
    }

//    public List<KnobbleDetailsInfoVo> queryLocalMusic(String userId, String orderBy) {
//        ArrayList<KnobbleDetailsInfoVo> songs = orm
//                .query(new QueryBuilder<KnobbleDetailsInfoVo>(KnobbleDetailsInfoVo.class)
//                        .whereEquals("userId", userId)
//                        .whereAppendAnd()
//                        .whereEquals("source", KnobbleDetailsInfoVo.SOURCE_LOCAL)
//                        .appendOrderAscBy(orderBy));
//
//        return songs;
//    }
//
//    public int countOfLocalMusic(String userId) {
//        return (int) orm.queryCount(new QueryBuilder<KnobbleDetailsInfoVo>(KnobbleDetailsInfoVo.class)
//                .whereEquals("userId", userId)
//                .whereAppendAnd()
//                .whereEquals("source", KnobbleDetailsInfoVo.SOURCE_LOCAL));
//
//    }

    public KnobbleDetailsInfoVo findSongById(String id) {
        return orm.queryById(id,KnobbleDetailsInfoVo.class);
    }

//    public List<SearchHistory> queryAllSearchHistory() {
//        return orm.query(new QueryBuilder<SearchHistory>(SearchHistory.class).appendOrderDescBy("created_at"));
//    }
//
//    public void createOrUpdate(SearchHistory searchHistory) {
//        orm.save(searchHistory);
//    }
//
//    public void deleteSearchHistory(SearchHistory data) {
//        orm.delete(data);
//    }


}
