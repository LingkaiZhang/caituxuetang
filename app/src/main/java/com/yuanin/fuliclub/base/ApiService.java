package com.yuanin.fuliclub.base;

import com.yuanin.fuliclub.config.URL;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.minePart.bean.PersonalInfoEntity;
import com.yuanin.fuliclub.minePart.bean.UpdateFileCallbackEntity;
import com.yuanin.fuliclub.minePart.bean.UserInfoEntity;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/8/22 11:13
 */
public interface ApiService {

    @POST(URL.SEND_MESSAGE_VERIFICATION)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> sendMessageVerification(@Field("mobile")String phoneNo);

    @POST(URL.SMSVALIDCODE_BIND_MOBILE)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> smsValidCodeBindMobile(@Field("mobile")String phoneNo);

    @POST(URL.LOGIN_PHONE)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> gotoPhoneLoginRegister(@Field("mobile")String phoneNo,@Field("validCode")String phonvalidCodeeNo);

    @POST(URL.AUTHORIZE_MOBILE_LOGIN)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> gotoBindPhone(@Field("mobile") String phoneNo, @Field("validCode") String phonvalidCodeeNo, @Field("wxId") String weChatUid);

    @POST(URL.LOGIN_WECHAT)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> sendWeChatCodeToLogin(@Field("code") String code);

    @POST(URL.BIND_WECHAT)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> bindWechat(@Field("code") String code);


    @POST(URL.USER_CAPACITY)
    Flowable<ReturnResult<UserInfoEntity>> getUserInfo();

    @POST(URL.UPDATE_FILE)
    @Multipart
    Flowable<ReturnResult<UpdateFileCallbackEntity>> upLoadPicture(@Part MultipartBody.Part part);


    @POST(URL.USER_HEAD_IMAGE_MODIFY)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> updateUserHeaderImage(@Field("saveName") String saveName);

    @POST(URL.USER_INFO)
    Flowable<ReturnResult<PersonalInfoEntity>> getUserAccountInfo();

    @POST(URL.USER_NICK_NAME_MODIFY)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> saveNewNickName(@Field("nickName") String newNickName);
}
