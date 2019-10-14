package com.yuanin.fuliclub.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.loginRegister.LoginRegisterActivity;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/23 16:10
 */
public class AppUtils {

    /**
     *重新登录
     */
    public static void reLoginRemind(Context context) {
        ToastUtils.showToast(context,context.getString(R.string.relogin_remind),Toast.LENGTH_SHORT);
        AppUtils.exitLogin(context);
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        context.startActivity(intent);
    }

    /**
     *退出登录
     */
    public static void exitLogin(Context context) {
        StaticMembers.IS_NEED_LOGIN = true;
        save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_MBOILE, StaticMembers.MOBILE);
        deleteSharedPreferences(context, ParamsKeys.LOGIN_FILE);
        initBooleanData(context);
    }

    public static void initBooleanData(Context context) {
        //是否储存登录信息
        String userid = AppUtils.getFromSharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_USERID);
        if (userid.length() > 0) {
            String mobile = AppUtils.getFromSharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_MBOILE);
            String token = AppUtils.getFromSharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_TOKEN);
            StaticMembers.IS_NEED_LOGIN = false;
            StaticMembers.USER_ID = userid;
            StaticMembers.MOBILE = mobile;
            StaticMembers.TOKEN = token;
        } else {
            StaticMembers.IS_NEED_LOGIN = true;
        }
    }

    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
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



}
