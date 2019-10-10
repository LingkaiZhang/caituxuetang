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
}
