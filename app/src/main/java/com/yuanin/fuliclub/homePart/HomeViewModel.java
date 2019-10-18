package com.yuanin.fuliclub.homePart;

import android.app.Application;
import android.support.annotation.NonNull;

import com.mvvm.base.AbsViewModel;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/27 13:39
 */
public class HomeViewModel extends AbsViewModel<HomeRepository> {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getHomePageCourseList(int courseType,int indexpage, int pageSize) {
        mRepository.getHomePageCourseList(courseType,indexpage, pageSize);
    }

    public void getCourseList(int courseType,int indexpage, int pageSize) {
        mRepository.getCourseList(courseType,indexpage, pageSize);
    }

    public void getHomePageCourseListjinjie(int courseType,int indexpage, int pageSize) {
        mRepository.getHomePageCourseListjinjie(courseType,indexpage, pageSize);
    }

    public void getCourseListjinjie(int courseType,int indexpage, int pageSize) {
        mRepository.getCourseListjinjie(courseType,indexpage, pageSize);
    }

    public void getLastLearnInfo() {
        mRepository.getLastLearnInfo();
    }

    public void getMyCourseList(String pageIndex, String limit) {
        mRepository.getMyCourseList(pageIndex, limit);
    }
}
