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

    public void createCourseOrder(String courseId, String periodsId) {
        mRepository.createCourseOrder(courseId,periodsId);
    }

    public void createWeChatOrder(String orderNo, int productType, String productId, String productName, String price, String key) {
        mRepository.createWeChatOrder(orderNo, productType, productId, productName, price, key);
    }

    public void getCourseKnobbleDetails(String id){
        mRepository.getCourseKnobbleDetails(id);
    }

    public void getCourseKnobbleDetailsLogin(String id){
        mRepository.getCourseKnobbleDetailsLogin(id);
    }
}
