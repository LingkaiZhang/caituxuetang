package com.yuanin.fuliclub.learnPart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.bean.CourseDetailsVo;
import com.yuanin.fuliclub.minePart.bean.PersonalInfoEntity;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseRepository extends BaseRepository {

    public static String EVENT_KEY_COURSE_DETAILS = null;

    public CourseRepository() {
        if (EVENT_KEY_COURSE_DETAILS == null) {
            EVENT_KEY_COURSE_DETAILS = StringUtil.getEventKey();
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
}
