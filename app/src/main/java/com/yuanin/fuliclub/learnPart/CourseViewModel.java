package com.yuanin.fuliclub.learnPart;

import android.app.Application;
import android.support.annotation.NonNull;

import com.mvvm.base.AbsViewModel;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseViewModel extends AbsViewModel<CourseRepository> {
    public CourseViewModel(@NonNull Application application) {
        super(application);
    }

    public void getCourseDetails(String courseId) {
        mRepository.getCourseDetails(courseId);
    }

    public void getCourseDetailsLogin(String courseId) {
        mRepository.getCourseDetailsLogin(courseId);
    }

    public void getCourseStartTime(String courseId) {
        mRepository.getCourseStartTime(courseId);
    }

    public void getCoursrKonbbleList(String courseId) {
        mRepository.getCoursrKonbbleList(courseId);
    }

    public void getCoursrKonbbleListLogin(String courseId) {
        mRepository.getCoursrKonbbleListLogin(courseId);
    }
}
