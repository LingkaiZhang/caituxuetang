package com.yuanin.fuliclub.homePart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.coursePart.bean.MyCourseListVo;
import com.yuanin.fuliclub.homePart.banner.BannerVo;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.learnPart.LastLearnVo;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;

import java.util.List;

import io.reactivex.Flowable;
import nl.qbusict.cupboard.annotation.Ignore;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/27 13:40
 */
public class HomeRepository extends BaseRepository {

    public static String EVENT_KEY_COURSE_LIST = null;
    public static String EVENT_KEY_COURSE_LIST_JINJIE = null;
    public static String EVENT_KEY_COURSE_LAST_LEARN = null;
    public static String EVENT_KEY_COURSE_MY_LIST = null;
    public static String EVENT_KEY_COURSE_LIST_HOMEPAGE = null;
    public static String EVENT_KEY_COURSE_LIST_JINJIE_HOMEPAGE = null;
    public static String EVENT_KEY_COURSE_LOG_UPDATE = null;
    public static String EVENT_KEY_HOME_BANNER_LIST = null;

    public HomeRepository() {
        if (EVENT_KEY_COURSE_LIST == null) {
            EVENT_KEY_COURSE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_LIST_JINJIE == null) {
            EVENT_KEY_COURSE_LIST_JINJIE = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_LAST_LEARN == null) {
            EVENT_KEY_COURSE_LAST_LEARN = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_MY_LIST == null) {
            EVENT_KEY_COURSE_MY_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_LIST_HOMEPAGE == null) {
            EVENT_KEY_COURSE_LIST_HOMEPAGE = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_LIST_JINJIE_HOMEPAGE == null) {
            EVENT_KEY_COURSE_LIST_JINJIE_HOMEPAGE = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_LOG_UPDATE == null) {
            EVENT_KEY_COURSE_LOG_UPDATE = StringUtil.getEventKey();
        }
        if (EVENT_KEY_HOME_BANNER_LIST == null) {
            EVENT_KEY_HOME_BANNER_LIST = StringUtil.getEventKey();
        }
    }

    public void getHomePageCourseList(int courseType, int indexpage, int pageSize) {

        Flowable<ReturnResult<CourseListVo>> courseList;
        if (StaticMembers.IS_NEED_LOGIN) {
            courseList = apiService.getCourseList(courseType, indexpage, pageSize);
        } else {
            courseList = apiService.getCourseListLogin(courseType, indexpage, pageSize);
        }

        addDisposable(courseList
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseListVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<CourseListVo> courseListVoReturnResult) {
                        postData(EVENT_KEY_COURSE_LIST_HOMEPAGE, courseListVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getCourseList(int courseType, int indexpage, int pageSize) {

        Flowable<ReturnResult<CourseListVo>> courseList;
        if (StaticMembers.IS_NEED_LOGIN) {
            courseList = apiService.getCourseList(courseType, indexpage, pageSize);
        } else {
            courseList = apiService.getCourseListLogin(courseType, indexpage, pageSize);
        }

        addDisposable(courseList
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseListVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<CourseListVo> courseListVoReturnResult) {
                        postData(EVENT_KEY_COURSE_LIST, courseListVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }


    public void getHomePageCourseListjinjie(int courseType, int indexpage, int pageSize) {

        Flowable<ReturnResult<CourseListVo>> courseList;
        if (StaticMembers.IS_NEED_LOGIN) {
            courseList = apiService.getCourseList(courseType, indexpage, pageSize);
        } else {
            courseList = apiService.getCourseListLogin(courseType, indexpage, pageSize);
        }

        addDisposable(courseList
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseListVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<CourseListVo> courseListVoReturnResult) {
                        postData(EVENT_KEY_COURSE_LIST_JINJIE_HOMEPAGE, courseListVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getCourseListjinjie(int courseType, int indexpage, int pageSize) {

        Flowable<ReturnResult<CourseListVo>> courseList;
        if (StaticMembers.IS_NEED_LOGIN) {
            courseList = apiService.getCourseList(courseType, indexpage, pageSize);
        } else {
            courseList = apiService.getCourseListLogin(courseType, indexpage, pageSize);
        }

        addDisposable(courseList
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<CourseListVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<CourseListVo> courseListVoReturnResult) {
                        postData(EVENT_KEY_COURSE_LIST_JINJIE, courseListVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getLastLearnInfo() {
        addDisposable(apiService.getLastLearnInfo()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<LastLearnVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<LastLearnVo> lastLearnVoReturnResult) {
                        postData(EVENT_KEY_COURSE_LAST_LEARN, lastLearnVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }



    public void getMyCourseList(String page, String limit){
        addDisposable(apiService.getMyCourseList(page, limit)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<MyCourseListVo>>() {
                    @Override
                    public void onSuccess(ReturnResult<MyCourseListVo> myCourseListVoReturnResult) {
                        postData(EVENT_KEY_COURSE_MY_LIST, myCourseListVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getBannerList() {
        addDisposable(apiService.getBannerInfo()
            .compose(RxSchedulers.io_main())
            .subscribeWith(new RxSubscriber<ReturnResult<List<BannerVo>>>() {
                @Override
                public void onSuccess(ReturnResult<List<BannerVo>> bannerVoReturnResult) {
                    postData(EVENT_KEY_HOME_BANNER_LIST, bannerVoReturnResult);
                    postState(StateConstants.SUCCESS_STATE);
                }

                @Override
                public void onFailure(String msg, int code) {

                }
            }));
    }
}
