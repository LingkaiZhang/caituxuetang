package com.yuanin.fuliclub.learnPart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import butterknife.BindView;

public class OrderPayActivity extends AbsLifecycleActivity<CourseViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());


    }
}
