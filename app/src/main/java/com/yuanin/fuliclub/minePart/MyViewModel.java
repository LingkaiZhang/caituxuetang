package com.yuanin.fuliclub.minePart;

import android.app.Application;
import android.support.annotation.NonNull;

import com.mvvm.base.AbsViewModel;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/25 13:52
 */
public class MyViewModel extends AbsViewModel<MyRepository> {

    public MyViewModel(@NonNull Application application) {
        super(application);
    }
}
