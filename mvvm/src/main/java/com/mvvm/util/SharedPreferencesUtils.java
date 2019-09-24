package com.mvvm.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * description ： SharedPreferences工具类
 * author : lingkai
 * date : 2019/8/26 13:08
 */
public class SharedPreferencesUtils {
    /**
     * 保存数据至SharedPreferences
     */
    public static void save2SharedPreferences(Context context, String fileName, String dataName, String data) {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences = context.getSharedPreferences(fileName,
                Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(dataName, data);
        editor.apply();
    }

    /**
     * 获取数据从SharedPreferences
     */
    public static String getFromSharedPreferences(Context context, String fileName, String dataName) {
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String data = sharedPreferences.getString(dataName, "");
        return data;
    }

    /**
     * 删除SharedPreferences数据
     */
    public static void deleteSharedPreferences(Context context, String fileName) {
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
