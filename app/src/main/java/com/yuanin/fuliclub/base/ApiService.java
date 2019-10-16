package com.yuanin.fuliclub.base;

import com.yuanin.fuliclub.config.URL;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.minePart.bean.PersonalInfoEntity;
import com.yuanin.fuliclub.minePart.bean.UpdateFileCallbackEntity;
import com.yuanin.fuliclub.minePart.bean.UserInfoEntity;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/8/22 11:13
 */
public interface ApiService {

    @POST(URL.SEND_MESSAGE_VERIFICATION)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> sendMessageVerification(@Field("mobile") String phoneNo);

    @POST(URL.SMSVALIDCODE_BIND_MOBILE)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> smsValidCodeBindMobile(@Field("mobile") String phoneNo);

    @POST(URL.USER_SMSVALIDCODE_CHANGE_MOBILE)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> smsValidCodeUpdateMobile(@Field("mobile") String phoneNo);

    @POST(URL.USER_UPDATE_MOBILE_MODIFY)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> UpdateMobile(@Field("mobile") String phoneNo, @Field("validCode") String validCode);

    @POST(URL.LOGIN_PHONE)
    @FormUrlEncoded
    Flowable<ReturnResult<LoginSuccessEntity>> gotoPhoneLoginRegister(@Field("mobile") String phoneNo, @Field("validCode") String phonvalidCodeeNo);

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

    @POST(URL.USER_MESSAGE_LIST)
    @FormUrlEncoded
    Flowable<ReturnResult<List<MyMessageVo>>> getMessageList(@Field("pageNum") String pageNum, @Field("pageSize") String pageSize);

    @POST(URL.MESSAGE_STATUS_UPDATE)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> updateMessageStatus(@Field("newId") String newId, @Field("type") String type);


    @GET(URL.COURSE_LIST)
    Flowable<ReturnResult<CourseListVo>> getCourseList(@Path("typeId") int id, @Query("page") int page, @Query("limit") int limit);

}
