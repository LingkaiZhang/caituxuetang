package com.yuanin.fuliclub.base;

import com.yuanin.fuliclub.config.URL;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/8/22 11:13
 */
public interface ApiService {

    @POST(URL.SEND_MESSAGE_VERIFICATION)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> sendMessageVerification(@Field("mobile")String phoneNo);

    @POST(URL.LOGIN_PHONE)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> gotoPhoneLoginRegister(@Field("mobile")String phoneNo,@Field("validCode")String phonvalidCodeeNo);

    @POST(URL.LOGIN_WECHAT)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> sendWeChatCodeToLogin(@Field("code") String code);
}
