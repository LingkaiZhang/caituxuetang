package com.yuanin.fuliclub.loginRegister;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.view.ClearEditText;
import com.yuanin.fuliclub.view.CountDownTextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.androidman.SuperButton;

public class LoginActivity extends AbsLifecycleActivity<LoginRegisterViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.etPhone)
    ClearEditText etPhone;
    @BindView(R.id.tvSmsCode)
    EditText tvSmsCode;
    @BindView(R.id.btn_next_step)
    SuperButton btnNextStep;
    @BindView(R.id.imBtnWeixin)
    TextView imBtnWeixin;
    @BindView(R.id.tvCountDownText)
    CountDownTextView tvCountDownText;

    private WeakReference<LoginActivity> weakReference;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        //验证码倒计时
        tvCountDownText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCountDownText.start();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_next_step, R.id.imBtnWeixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step:
                break;
            case R.id.imBtnWeixin:
                break;
        }
    }
}
