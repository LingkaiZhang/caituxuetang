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
}
