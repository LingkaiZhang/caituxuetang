package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvvm.base.AbsLifecycleActivity;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.adapter.ViewPagerFragmentAdapter;
import com.yuanin.fuliclub.util.ViewPagerUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseDetailsActivity extends AbsLifecycleActivity<CourseViewModel> {
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.ivItemCourseImage)
    ImageView ivItemCourseImage;
    @BindView(R.id.tvCourseName)
    TextView tvCourseName;
    @BindView(R.id.tvItemCourseSlogan)
    TextView tvItemCourseSlogan;
    @BindView(R.id.rlCourseHeader)
    RelativeLayout rlCourseHeader;
    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.tvCoursePrice)
    TextView tvCoursePrice;
    @BindView(R.id.tvOriginalPrice)
    TextView tvOriginalPrice;

   /* @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
*/

    private List<Fragment> fragmentList;
    private static final String[] CHANNELS = new String[]{"课程介绍", "课程目录"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    private WeakReference<CourseDetailsActivity> weakReference;
    private CourseIntroduceFragment courseIntroduceFragment;
    private CourseDetailListFragment courseDetailListFragment;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //状态栏颜色设置为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);

            //去除半透明状态栏(如果有)
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：让内容显示到状态栏
            //SYSTEM_UI_FLAG_LAYOUT_STABLE：状态栏文字显示白色
            //SYSTEM_UI_FLAG_LIGHT_STATUS_BAR：状态栏文字显示黑色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }


        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();

        initViewPager();
        initMagicIndicator();

    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(context.getResources().getColor(R.color.color_text_gray));
                simplePagerTitleView.setSelectedColor(context.getResources().getColor(R.color.theme_color));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(1);
                indicator.setColors(context.getResources().getColor(R.color.theme_color));
                //indicator.setLineHeight(6);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    private void initViewPager() {
        mViewPager.setOffscreenPageLimit(2);
        // 包含3个fragment界面
        List<TabIndicatorEntity> list = ViewPagerUtils.getTabIndicator(2);
        // 3个fragment界面封装
        fragmentList = new ArrayList<Fragment>();
        courseIntroduceFragment = new CourseIntroduceFragment();
        fragmentList.add(courseIntroduceFragment);
        courseDetailListFragment = new CourseDetailListFragment();
        fragmentList.add(courseDetailListFragment);
        // 设置ViewPager适配器
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), list, fragmentList);
        mViewPager.setAdapter(viewPagerFragmentAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.ivShare, R.id.tvBuyButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivShare:
                Toast.makeText(CourseDetailsActivity.this, "点击分享课程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvBuyButton:
                Intent intent = new Intent(CourseDetailsActivity.this, OrderPayActivity.class);
                startActivity(intent);
                break;
        }
    }

}
