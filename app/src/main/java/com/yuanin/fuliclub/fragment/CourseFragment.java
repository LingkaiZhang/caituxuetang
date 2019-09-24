package com.yuanin.fuliclub.fragment;

import android.os.Bundle;

import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class CourseFragment extends BaseFragment {

    public static CourseFragment newInstance() {

        Bundle args = new Bundle();

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_course;
    }

    @Override
    public void initView(Bundle state) {

    }

    @Override
    protected void onStateRefresh() {

    }
}
