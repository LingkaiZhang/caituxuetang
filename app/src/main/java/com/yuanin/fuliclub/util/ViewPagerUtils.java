package com.yuanin.fuliclub.util;

import com.yuanin.fuliclub.learnPart.TabIndicatorEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager工具类, 用于提供ViewPager相关的公用方法
 */
public class ViewPagerUtils {

    // 获取ViewPager的TabIndicator列表
    public static List<TabIndicatorEntity> getTabIndicator(Integer number) {
        List<TabIndicatorEntity> list = new ArrayList<TabIndicatorEntity>();
        for (int i = 0; i < number; i++) {
            TabIndicatorEntity indicator = new TabIndicatorEntity();
            indicator.type = i;
            list.add(indicator);
        }
        return list;
    }
}