package com.yuanin.fuliclub;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hxb.easynavigition.constant.Anim;
import com.hxb.easynavigition.view.EasyNavigitionBar;
import com.mvvm.base.BaseActivity;
import com.yuanin.fuliclub.fragment.BuyedFragment;
import com.yuanin.fuliclub.fragment.CourseFragment;
import com.yuanin.fuliclub.fragment.HomeFragment;
import com.yuanin.fuliclub.fragment.MineFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    // 双击back退出程序
    private long mLastBackTime = 0;

    private long TIME_DIFF = 2 * 1000;

    private String[] tabText = {"首页", "学习", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_home_gray, R.mipmap.tab_study_gray, R.mipmap.tab_my_gray};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_home,R.mipmap.tab_study, R.mipmap.tab_my};

    private List<android.support.v4.app.Fragment> fragments = new ArrayList<>();

    @Override
    protected int getScreenMode() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();

        initNavBar();
    }

    private void initNavBar() {
        EasyNavigitionBar navigitionBar = findViewById(R.id.mNavigitionBar);
        fragments.add(HomeFragment.newInstance());
        fragments.add(BuyedFragment.newInstance());
        fragments.add(MineFragment.newInstance());

        navigitionBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .anim(Anim.ZoomIn)
                .build();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 双击back,退出程序
            long now = new Date().getTime();
            if (now - mLastBackTime < TIME_DIFF) {
                finish();
                return super.onKeyDown(keyCode, event);
            } else {
                mLastBackTime = now;
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
