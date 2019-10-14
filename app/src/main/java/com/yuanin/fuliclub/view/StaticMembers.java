package com.yuanin.fuliclub.view;

import android.os.Environment;
import android.os.ParcelUuid;


public class StaticMembers {

    //是否展示精选咨询页面
    public static boolean IS_SHOW_PREEN_CONSULT = false;
    //是否开启设置权限弹框
    public static boolean isShowCheckPermission = true;

    //屏幕宽高
    public static int SCREEN_WIDTH; // 按下状态

    public static int SCREEN_HEIGHT; // 错误状态

    //状态栏高度
    public static int STATUS_HEIGHT;

    //是否储存登录信息
    public static boolean IS_NEED_LOGIN = true;
    //登录用户userid
    public static String USER_ID;
    public static String MOBILE;
    public static String TOKEN;

    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DOWN_PATH = "ttdownload";


    /**
     * 显示格式化日期
     */
    public final static String DATE_FORMAT_STR = "yyyy年MM月dd日 HH:mm";

    //分页加载每页的条数
    public static String PAGE_SIZE = "15";

    //是否第一次进入播放页面
    public static boolean isFristPlay = true;
    public static String PLAYING_COURSE_ID;

}
