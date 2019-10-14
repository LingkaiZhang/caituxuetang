package com.yuanin.fuliclub.loginRegister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.MainActivity;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.util.PhoneNumUtils;
import com.yuanin.fuliclub.util.SharedPreferencesUtils;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.view.ClearEditText;
import com.yuanin.fuliclub.view.CountDownTextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.androidman.SuperButton;

public class BindPhoneActivity extends AbsLifecycleActivity<LoginRegisterViewModel> {

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

    private WeakReference<BindPhoneActivity> weakReference;
    private Context context = BindPhoneActivity.this;
    private String phoneNo;
    private String smsCode;
    private String weChatUid;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        weChatUid = getIntent().getStringExtra("uid");

        initListener();

    }

    private void initListener() {
        //验证码倒计时
        tvCountDownText.setOnClickListener(v -> {
            phoneNo = etPhone.getText().toString().trim();

            if (!TextUtils.isEmpty(phoneNo)) {
                if (PhoneNumUtils.isPhone(context, phoneNo)) {
                    tvCountDownText.start();
                    mViewModel.smsValidCodeBindMobile(phoneNo);
                } else {
                    tvCountDownText.reset();
                    ToastUtils.showToast("请输入正确的手机号");
                }

            } else {
                tvCountDownText.reset();
                ToastUtils.showToast("请输入正确的手机号");
            }

        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                smsCode = tvSmsCode.getText().toString().trim();
                if (s.length() == 11 && !TextUtils.isEmpty(smsCode) && smsCode.length() == 6) {
                    btnNextStep.setColorNormal(getResources().getColor(R.color.theme_color));
                    btnNextStep.setButtonClickable(true);
                } else {
                    if (btnNextStep.isClickable()) {
                        btnNextStep.setColorNormal(getResources().getColor(R.color.btn_unable_click_gray));
                        btnNextStep.setButtonClickable(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvSmsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNo = etPhone.getText().toString().trim();
                if (s.length() == 6 && !TextUtils.isEmpty(phoneNo) && phoneNo.length() == 11) {
                    btnNextStep.setColorNormal(getResources().getColor(R.color.theme_color));
                    btnNextStep.setButtonClickable(true);
                } else {
                    if (btnNextStep.isClickable()) {
                        btnNextStep.setColorNormal(getResources().getColor(R.color.btn_unable_click_gray));
                        btnNextStep.setButtonClickable(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(LoginRegisterRepository.EVENT_KEY_BIND_PHONE_MESSAGE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                ToastUtils.showToast(returnResult.getMessage());
            }
        });


        registerSubscriber(LoginRegisterRepository.EVENT_KEY_BIND_PHONE, ReturnResult.class).observe(this, loginSuccessEntityReturnResult -> {
            if (loginSuccessEntityReturnResult != null) {
                if (loginSuccessEntityReturnResult.isSuccess()) {
                    ReturnResult<LoginSuccessEntity> entity = loginSuccessEntityReturnResult;
                    SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_MBOILE, entity.getData().getMobile());
                    SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_USERID, String.valueOf(entity.getData().getUid()));
                    SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_TOKEN, entity.getData().getAccessToken());
                    StaticMembers.IS_NEED_LOGIN = false;
                    StaticMembers.USER_ID = String.valueOf(entity.getData().getUid());
                    StaticMembers.MOBILE = entity.getData().getMobile();
                    StaticMembers.TOKEN = entity.getData().getAccessToken();
                    //登陆成功后，跳转主页面
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    //BToast.makeText(this, loginSuccessEntityReturnResult.getMessage(), true).show();
                }

                ToastUtils.showToast(loginSuccessEntityReturnResult.getMessage());

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next_step)
    public void onViewClicked() {
        phoneNo = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(smsCode) && smsCode.length() == 6) {
            ToastUtils.showToast("请输入正确位数的短信验证码");
            return;
        }
        if (PhoneNumUtils.isPhone(context,phoneNo)) {
            mViewModel.gotoBindPhone(phoneNo, smsCode, weChatUid);
        }
    }
}
