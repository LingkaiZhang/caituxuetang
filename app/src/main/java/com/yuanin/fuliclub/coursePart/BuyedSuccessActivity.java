package com.yuanin.fuliclub.coursePart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.learnPart.CourseDetailsLoginActivity;
import com.yuanin.fuliclub.learnPart.CourseViewModel;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.androidman.SuperButton;

public class BuyedSuccessActivity extends AbsLifecycleActivity<CourseViewModel> {


    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.tvCourseName)
    TextView tvCourseName;
    @BindView(R.id.btnGotoStudy)
    SuperButton btnGotoStudy;


    private WeakReference<BuyedSuccessActivity> weakReference;
    private String courseId;
    private String courseName;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_buyed_success;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        courseName = intent.getStringExtra("courseName");

        tvCourseName.setText( courseName );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvCourseName, R.id.btnGotoStudy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvCourseName:

                break;
            case R.id.btnGotoStudy:
                Intent intent = new Intent(this, CourseDetailsLoginActivity.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);
                break;
        }
    }
}
