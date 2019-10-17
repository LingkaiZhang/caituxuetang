package com.yuanin.fuliclub.learnPart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.bean.CourseDetailsVo;
import com.yuanin.fuliclub.coursePart.bean.CourseOrderCreatVo;
import com.yuanin.fuliclub.coursePart.bean.CourseStartTimeListVo;
import com.yuanin.fuliclub.coursePart.bean.WeChatOrderVo;
import com.yuanin.fuliclub.minePart.bean.PersonalInfoEntity;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;
import com.yuanin.fuliclub.view.StaticMembers;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseRepository extends BaseRepository {

    public static String EVENT_KEY_COURSE_DETAILS = null;
    public static String EVENT_KEY_COURSE_DETAILS_LOGIN = null;
    public static String EVENT_KEY_COURSE_START_TIME_LIST = null;
    public static String EVENT_KEY_COURSE_KNOBBLE_LIST = null;
    public static String EVENT_KEY_COURSE_KNOBBLE_LIST_LOGIN = null;
    public static String EVENT_KEY_COURSE_CREATE_ORDER = null;
    public static String EVENT_KEY_WECHAT_CREATE_ORDER = null;

    public CourseRepository() {
        if (EVENT_KEY_COURSE_DETAILS == null) {
            EVENT_KEY_COURSE_DETAILS = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_DETAILS_LOGIN == null) {
            EVENT_KEY_COURSE_DETAILS_LOGIN = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_START_TIME_LIST == null) {
            EVENT_KEY_COURSE_START_TIME_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_KNOBBLE_LIST == null) {
            EVENT_KEY_COURSE_KNOBBLE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_KNOBBLE_LIST_LOGIN == null) {
            EVENT_KEY_COURSE_KNOBBLE_LIST_LOGIN = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_CREATE_ORDER == null) {
            EVENT_KEY_COURSE_CREATE_ORDER = StringUtil.getEventKey();
        }
        if (EVENT_KEY_WECHAT_CREATE_ORDER == null) {
            EVENT_KEY_WECHAT_CREATE_ORDER = StringUtil.getEventKey();
        }
    }

    public void getCourseDetails(String courseId) {
        addDisposable(apiService.getCourseDetails(courseId)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseDetailsVo>>() {

                    @Override
                    public void onSuccess(ReturnResult<CourseDetailsVo> returnResult) {
                        postData(EVENT_KEY_COURSE_DETAILS, returnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getCourseDetailsLogin(String courseId) {
        addDisposable(apiService.getCourseDetailsLogin(courseId, StaticMembers.TOKEN)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseDetailsVo>>() {

                    @Override
                    public void onSuccess(ReturnResult<CourseDetailsVo> returnResult) {
                        postData(EVENT_KEY_COURSE_DETAILS_LOGIN, returnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getCourseStartTime(String courseId) {
        addDisposable(apiService.getCourseStartTimeList(courseId)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<List<CourseStartTimeListVo>>>() {

                    @Override
                    public void onSuccess(ReturnResult<List<CourseStartTimeListVo>> returnResult) {
                        postData(EVENT_KEY_COURSE_START_TIME_LIST, returnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getCoursrKonbbleList(String courseId) {
        addDisposable(apiService.getCourseKnobbleList(courseId)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<List<CourseKnobbleInfoVo>>>() {
                    @Override
                    public void onSuccess(ReturnResult<List<CourseKnobbleInfoVo>> listReturnResult) {
                        postData(EVENT_KEY_COURSE_KNOBBLE_LIST, listReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));

    }

    public void getCoursrKonbbleListLogin(String courseId) {
        addDisposable(apiService.getCourseKnobbleListLogin(courseId, StaticMembers.TOKEN)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<List<CourseKnobbleInfoVo>>>() {
                    @Override
                    public void onSuccess(ReturnResult<List<CourseKnobbleInfoVo>> listReturnResult) {
                        postData(EVENT_KEY_COURSE_KNOBBLE_LIST_LOGIN, listReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));

    }

    public void createCourseOrder(String courseId, String periodsId) {
        addDisposable(apiService.createCourseOrder(courseId, periodsId, "Android")
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseOrderCreatVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<CourseOrderCreatVo> courseOrderCreatVoReturnResult) {
                        postData(EVENT_KEY_COURSE_CREATE_ORDER, courseOrderCreatVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void createWeChatOrder(String orderNo, int productType, String productId, String productName, String price, String key) {

        addDisposable(apiService.weChatCreateOrder(orderNo, String.valueOf(productType), productId, price, productName, key)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<WeChatOrderVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<WeChatOrderVo> weChatOrderVoReturnResult) {
                        postData(EVENT_KEY_WECHAT_CREATE_ORDER, weChatOrderVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }
}
