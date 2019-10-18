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
    //发送短信验证码-绑定手机号
    public static  final String SMSVALIDCODE_BIND_MOBILE = URL.FLKT + URL.MODULE_USER + "smsValidCodeBindMobile";
    //登录
    public static final String LOGIN_PHONE = URL.FLKT + URL.MODULE_USER + "login";
    //授权登录绑定手机号
    public static final String AUTHORIZE_MOBILE_LOGIN = URL.FLKT + URL.MODULE_USER + "authorizeMobileLogin";
    //微信登录发送code到service
    public static final String LOGIN_WECHAT = URL.FLKT + URL.MODULE_USER + "authorizeLogin";
    //绑定微信
    public static final String BIND_WECHAT = URL.FLKT + URL.MODULE_USER + "bindWx";
    //修改手机号发送短信
    public static  final String USER_SMSVALIDCODE_CHANGE_MOBILE = URL.MODULE_USER + "smsValidCodeChangMobile";
    //修改用户手机号
    public static  final String USER_UPDATE_MOBILE_MODIFY =  URL.MODULE_USER + "updateMobile";


    //我的
    public static  final String USER_CAPACITY =  URL.MODULE_USER + "userCapacity";
    //编辑头像
    public static  final String USER_HEAD_IMAGE_MODIFY =  URL.MODULE_USER + "updateImage";
    //文件上传
    public static final String UPDATE_FILE = URL.FLKT + "/gene/fileIntact/uploadFile";
    //我的账户
    public static  final String USER_INFO =  URL.MODULE_USER + "userInfo";
    //编辑用户昵称
    public static  final String USER_NICK_NAME_MODIFY =  URL.MODULE_USER + "updateName";
    //消息列表
    public static final String USER_MESSAGE_LIST = URL.MODULE_ORDER + "per/newSList";
    //标记消息为已读或删除
    public static final String MESSAGE_STATUS_UPDATE = URL.MODULE_ORDER + "per/newUpdate";

    //课程列表
    public static final String COURSE_LIST = "/course/ct/course/queryListByType/{typeId}";
    //课程详情
    public static final String COURSE_DETAILS = "/course/ct/course/queryDetailById/{id}";
    public static final String COURSE_DETAILS_LOGIN= "/course/ct/course/queryDetailWithToken/{id}";
    //课程分期下拉
    public static final String COURSE_START_TIME_LIST =  URL.MODULE_ORDER + "per/perComboboxBan";
    //课程小结列表
    public static final String COURSE_KNOBBLE_LIST = "/course/ct/courseChild/queryChild/{parentId}";
    public static final String COURSE_KNOBBLE_LIST_LOGIN = "/course/ct/courseChild/queryChildWithToken/{parentId}";

    //创建订单
    public static final String COURSE_CREATE_ORDER = URL.MODULE_ORDER + "CtcreateOrder";
    //微信下单
    public static final String WECHAT_CREATE_ORDER = URL.MODULE_PAY + "weiXinCtAppPay";
    //更新上次学到记录
    public static final String COURSE_UPDATE_LAST_LEARN = "/course/ct/course/joinCourse";
    //上次学习
    public static final String COURSE_LAST_LEARN = "/course/ct/course/querylogByUserId";


}

