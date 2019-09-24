package com.mvvm.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.mvvm.util.EasyStatusBarUtil;
import com.tqzhang.stateview.core.LoadManager;
import com.tqzhang.stateview.stateview.BaseStateControl;

import butterknife.ButterKnife;
import trecyclerview.com.mvvm.R;

/**
 * @author：tqzhang on 18/3/12 19:22
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isBlack = true;
    private int mode;

    protected LoadManager loadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //状态栏
        initStatusBar();
        //设置布局内容
        setContentView(getLayoutId());
        loadManager = new LoadManager.Builder()
                .setViewParams(this)
                .setListener(new BaseStateControl.OnRefreshListener() {
                    @Override
                    public void onRefresh(View v) {
                        onStateRefresh();
                    }
                })
                .build();

        ButterKnife.bind(this);

        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();

        setTextBlack();

        super.onCreate(savedInstanceState);

    }

    /**
     *
     */
    protected  void onStateRefresh(){

    }


    protected void initStatusBar() {
        if (getScreenMode() == 1) {
            //魅族MX3(4.2.1)出错
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        } else if (getScreenMode() == 2) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }

    /**
     * 屏幕状态
     */
    protected abstract int getScreenMode();

    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化views
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化toolbar
     */
    protected void initToolBar() {
        //doSomething
    }


    /**
     * 显示进度条
     */
    public void showProgressBar() {
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressBar() {

    }

    public void setTextBlack() {
        if (isBlack) {
            mode =  EasyStatusBarUtil.StatusBarLightMode(this, R.color.white, R.color.status_bar_color); //设置白底黑字
        }
    }


    public void setBlack(boolean black) {
        isBlack = black;
    }


    public int getMode() {
        return mode;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

