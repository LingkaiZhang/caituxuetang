package com.yuanin.fuliclub.learnPart;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.view.ObservableWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseKnobbleDetailsActivity extends AbsLifecycleActivity<CourseViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.llWriteNote)
    LinearLayout llWriteNote;
    @BindView(R.id.llDoHomeWork)
    LinearLayout llDoHomeWork;
    @BindView(R.id.obsWebView)
    ObservableWebView obsWebView;


    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_knobble_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.llWriteNote, R.id.llDoHomeWork})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llWriteNote:
                break;
            case R.id.llDoHomeWork:
                break;
        }
    }
}
