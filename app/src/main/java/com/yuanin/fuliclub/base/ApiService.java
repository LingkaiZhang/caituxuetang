package com.yuanin.fuliclub.base;

import com.yuanin.fuliclub.config.URL;
import com.yuanin.fuliclub.coursePart.bean.ClassInfoVo;
import com.yuanin.fuliclub.coursePart.bean.CourseDetailsVo;
import com.yuanin.fuliclub.coursePart.bean.CourseOrderCreatVo;
import com.yuanin.fuliclub.coursePart.bean.CourseStartTimeListVo;
import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;
import com.yuanin.fuliclub.coursePart.bean.MyCourseListVo;
import com.yuanin.fuliclub.coursePart.bean.WeChatOrderVo;
import com.yuanin.fuliclub.homePart.banner.BannerInfoVo;
import com.yuanin.fuliclub.homePart.banner.BannerVo;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.learnPart.CourseKnobbleInfoVo;
import com.yuanin.fuliclub.learnPart.LastLearnVo;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.minePart.bean.KeFuInfoVo;
import com.yuanin.fuliclub.minePart.bean.MyMessageListVo;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.minePart.bean.MyOrderListVo;
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
    Flowable<ReturnResult<LoginSuccessEntity>> sendWeChatCodeToLogin(@Field("code") String code,@Field("appId") String appId);

    @POST(URL.BIND_WECHAT)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> bindWechat(@Field("code") String code,@Field("appId") String appId);


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
    Flowable<ReturnResult<MyMessageListVo>> getMessageList(@Field("pageNum") String pageNum, @Field("pageSize") String pageSize);

    @POST(URL.MESSAGE_STATUS_UPDATE)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> updateMessageStatus(@Field("newId") String newId, @Field("type") String type);


    @GET(URL.COURSE_LIST)
    Flowable<ReturnResult<CourseListVo>> getCourseList(@Path("typeId") int id, @Query("page") int page, @Query("limit") int limit);

    @GET(URL.COURSE_LIST_LOGIN)
    Flowable<ReturnResult<CourseListVo>> getCourseListLogin(@Path("typeId") int id, @Query("page") int page, @Query("limit") int limit);

    @GET(URL.COURSE_DETAILS)
    Flowable<ReturnResult<CourseDetailsVo>> getCourseDetails(@Path("id") String id);

    @GET(URL.COURSE_DETAILS_LOGIN)
    Flowable<ReturnResult<CourseDetailsVo>> getCourseDetailsLogin(@Path("id") String id, @Query("token") String token);

    @POST(URL.COURSE_START_TIME_LIST)
    @FormUrlEncoded
    Flowable<ReturnResult<List<CourseStartTimeListVo>>> getCourseStartTimeList(@Field("courseId") String courseId);


    @GET(URL.COURSE_KNOBBLE_LIST)
    Flowable<ReturnResult2<List<CourseKnobbleInfoVo>>> getCourseKnobbleList(@Path("parentId") String courseId);

    @GET(URL.COURSE_KNOBBLE_LIST_LOGIN)
    Flowable<ReturnResult2<List<CourseKnobbleInfoVo>>> getCourseKnobbleListLogin(@Path("parentId") String courseId, @Query("token") String token);

    @GET(URL.COURSE_CREATE_ORDER)
    Flowable<ReturnResult<CourseOrderCreatVo>> createCourseOrder(@Query("courseId") String courseId,@Query("periodsId") String periodsId, @Query("payType") String payType);

    @POST(URL.WECHAT_CREATE_ORDER)
    @FormUrlEncoded
    Flowable<ReturnResult<WeChatOrderVo>> weChatCreateOrder(@Field("orderNo") String orderNo, @Field("productType") String productType,
                @Field("productId") String productId, @Field("price") String price, @Field("productName") String productName,
                                                            @Field("key") String key);

    @GET(URL.COURSE_LAST_LEARN)
    Flowable<ReturnResult<LastLearnVo>> getLastLearnInfo();

    @GET(URL.KEFU_INFO)
    Flowable<ReturnResult<KeFuInfoVo>> getKefuInfo();

    @GET(URL.COURSE_MY_LIST)
    Flowable<ReturnResult<MyCourseListVo>> getMyCourseList(@Query("page") String page, @Query("limit") String limit);

    @GET(URL.COURSE_ORDER_LIST)
    Flowable<ReturnResult<MyOrderListVo>> getMyOrderList(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    @POST(URL.COURSE_UPDATE_LAST_LEARN)
    @FormUrlEncoded
    Flowable<ReturnResult<String>> updateLearnLog(@Field("courseId") String courseId);

    @GET(URL.COURSE_KNOBBLE_DETAILS)
    Flowable<ReturnResult<KnobbleDetailsInfoVo>> getCourseKnobbleDetails(@Path("id") String id);

    @GET(URL.COURSE_KNOBBLE_DETAILS_lOGIN)
    Flowable<ReturnResult<KnobbleDetailsInfoVo>> getCourseKnobbleDetailsLogin(@Path("id") String id, @Query("token") String token);

    @GET(URL.COURSE_USER_CLASS_INFO)
    Flowable<ReturnResult<ClassInfoVo>> getUserClassInfo(@Query("periodsId") int periodsId);

    @GET(URL.HOME_BANNER)
    Flowable<ReturnResult<List<BannerVo>>> getBannerInfo();
}
