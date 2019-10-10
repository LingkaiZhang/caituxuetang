package com.yuanin.fuliclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yuanin.fuliclub.learnPart.TabIndicatorEntity;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    // ViewPager包含Fragment个数的集合
    private List<TabIndicatorEntity> list;
    // ViewPager中Fragment集合
    private List<Fragment> fragmentList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<TabIndicatorEntity> list, List<Fragment> fragmentList) {
        super(fm);
        this.list = list;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(list.get(position).type);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
