package com.yuanin.fuliclub.fragment;

import android.os.Bundle;

import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle state) {
        
    }

    @Override
    protected void onStateRefresh() {

    }
}
