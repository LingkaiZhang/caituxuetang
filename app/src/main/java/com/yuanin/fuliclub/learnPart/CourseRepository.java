package com.yuanin.fuliclub.learnPart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.bean.CourseDetailsVo;
import com.yuanin.fuliclub.coursePart.bean.CourseStartTimeListVo;
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
}
