package com.yuanin.fuliclub.loginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mvvm.base.AbsLifecycleActivity;
import com.mvvm.base.BaseActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.util.PhoneNumUtils;
import com.yuanin.fuliclub.util.PhoneWatcher;
import com.yuanin.fuliclub.view.ClearEditText;
import com.yuanin.fuliclub.view.bamtoast.btoast.BToast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.androidman.SuperButton;

public class LoginRegisterActivity extends AbsLifecycleActivity<LoginRegisterViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.etPhone)
    ClearEditText etPhone;
    @BindView(R.id.btn_next_step)
    SuperButton btnNextStep;
    @BindView(R.id.imBtnWeixin)
    TextView imBtnWeixin;

    private WeakReference<LoginRegisterActivity> weakReference;
    private String phoneNo;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        inputPhoneNoListen();
    }

    private void inputPhoneNoListen() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s == null || s.length() == 0) return;
                if (s.length() == 13) {
                    btnNextStep.setColorNormal(getResources().getColor(R.color.theme_color));
                    btnNextStep.setButtonClickable(true);
                    return;
                } else {
                    if (btnNextStep.isClickable()) {
                        btnNextStep.setColorNormal(getResources().getColor(R.color.btn_unable_click_gray));
                        btnNextStep.setButtonClickable(false);
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    etPhone.setText(sb.toString());
                    etPhone.setSelection(index);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_next_step, R.id.imBtnWeixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next_step:
                phoneNo = etPhone.getText().toString().trim().replaceAll(" ", "");
                if (PhoneNumUtils.isPhone(this, phoneNo)) {
                    mViewModel.sendSmsVerification(phoneNo);
                }
                break;
            case R.id.imBtnWeixin:
                //TODO 微信登录
                mViewModel.weChatLogin();
                break;
        }
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(LoginRegisterRepository.IS_INSTALL_WECHAT, BooleanTest.class).observe(this, booleanTest -> {
            if (booleanTest != null) {
                boolean isTrue = booleanTest.isTrue;
                if (isTrue) {
                    Toast.makeText(this, "您的设备还没有安装微信", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*
        registerSubscriber(LoginRegisterRepository.EVENT_KEY_LOGIN_MESSAGE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    Intent intent = new Intent(this, SmsMessageVerActivity.class);
                    intent.putExtra("phone", phoneNo);
                    startActivity(intent);
                }
                BToast.makeText(this, returnResult.getMessage(), true).show();
            }
        });*/
    }
}
