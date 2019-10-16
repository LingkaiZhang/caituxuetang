package com.yuanin.fuliclub.homePart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;

import io.reactivex.Flowable;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/27 13:40
 */
public class HomeRepository extends BaseRepository {

    public static String EVENT_KEY_COURSE_LIST = null;
    public static String EVENT_KEY_COURSE_LIST_JINJIE = null;

    public HomeRepository() {
        if (EVENT_KEY_COURSE_LIST == null) {
            EVENT_KEY_COURSE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_COURSE_LIST_JINJIE == null) {
            EVENT_KEY_COURSE_LIST_JINJIE = StringUtil.getEventKey();
        }
    }

    public void getHomePageCourseList(int courseType, int indexpage, int pageSize) {
        Flowable<ReturnResult<CourseListVo>> courseList = apiService.getCourseList(courseType, indexpage, pageSize);
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
        Flowable<ReturnResult<CourseListVo>> courseList = apiService.getCourseList(courseType, indexpage, pageSize);
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
}
