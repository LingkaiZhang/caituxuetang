package com.yuanin.fuliclub.config;

/**
 * description ： 网络接口地址
 * author : lingkai
 * date : 2019/8/20 14:23
 */
public class URL {


    //正式
    //public static final String BASE_URL = "https://open.caituketang.com";
    //public static final String NET_URL_H5 = "https://m.caituketang.com";
    //测试
    public static final String BASE_URL = "https://open.yuanin.com";
    public static final String NET_URL_H5 = "https://m.yuanin.com";

    /**
     * 网络请求模块参数
     */

    public final static String FLKT = "/flkt";
    //用户模块
    public final static String MODULE_USER = "/user/";
    //咨询
    public final static String MODULE_CONSULT = "/consult/";
    //课程
    public final static String MODULE_COURSE = "/course/";
    //Z支付
    public final static String MODULE_PAY = "/pay/";
    //其它
    public final static String MODULE_ORDER = "/orders/";


    //短信发送接口
    public static final String SEND_MESSAGE_VERIFICATION = URL.FLKT + URL.MODULE_USER + "smsValidCode";
    //登录
    public static final String LOGIN_PHONE = URL.FLKT + URL.MODULE_USER + "login";
    //微信登录发送code到service
    public static final String LOGIN_WECHAT = URL.FLKT + URL.MODULE_USER + "authorizeLogin";
}

