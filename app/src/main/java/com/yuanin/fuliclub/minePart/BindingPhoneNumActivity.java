package com.yuanin.fuliclub.minePart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.MainActivity;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.loginRegister.LoginRegisterRepository;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.util.AppUtils;
import com.yuanin.fuliclub.util.PhoneNumUtils;
import com.yuanin.fuliclub.util.SharedPreferencesUtils;
import com.yuanin.fuliclub.util.TimerCount;
import com.yuanin.fuliclub.util.ToastUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingPhoneNumActivity extends AbsLifecycleActivity<MyViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.edtPhoneNum)
    EditText edtPhoneNum;
    @BindView(R.id.tvGetMessageNum)
    TextView tvGetMessageNum;
    @BindView(R.id.tvTip)
    TextView tvTip;
    @BindView(R.id.edtMessageNum)
    EditText edtMessageNum;
    @BindView(R.id.btnSaveNickName)
    Button btnSaveNickName;

    private WeakReference<BindingPhoneNumActivity> weakReference;

    private String etPhone;
    private TimerCount time;
    private String erMessageCode;
    public static long timeLimit;
    private Context context = BindingPhoneNumActivity.this;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_binding_phone_num;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        weakReference = new WeakReference<>(this);

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

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(MyRepository.EVENT_KEY_USER_UPDATE_PHONE_SMS, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    time = new TimerCount(60000, 1000, 3, tvGetMessageNum,tvTip);
                    time.start();
                }
                ToastUtils.showToast(returnResult.getMessage());
            }
        });

        registerSubscriber(MyRepository.EVENT_KEY_USER_UPDATE_PHONE, ReturnResult.class).observe(this, loginSuccessEntityReturnResult -> {
            if (loginSuccessEntityReturnResult != null) {
                ReturnResult<LoginSuccessEntity> entity = loginSuccessEntityReturnResult;
                if (loginSuccessEntityReturnResult.isSuccess()) {
                    SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_MBOILE, entity.getData().getMobile());
                    SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_USERID, String.valueOf(entity.getData().getUid()));
                    SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_TOKEN, entity.getData().getAccessToken());
                    StaticMembers.IS_NEED_LOGIN = false;
                    StaticMembers.USER_ID = String.valueOf(entity.getData().getUid());
                    StaticMembers.MOBILE = entity.getData().getMobile();
                    StaticMembers.TOKEN = entity.getData().getAccessToken();
                    //登陆成功后，跳转主页面
                    finish();
                    //BToast.makeText(this, loginSuccessEntityReturnResult.getMessage(), true).show();

                }else {
                    if (loginSuccessEntityReturnResult.getCode() == ParamsValues.TOKEN_FAILURE) {
                        AppUtils.reLoginRemind(context);
                    }
                }

                ToastUtils.showToast(loginSuccessEntityReturnResult.getMessage());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timeLimit != 0) {
            if (time != null) {
                time.cancel();
                time = null;
            }
            time = new TimerCount(timeLimit, 60000, 3, tvGetMessageNum,tvTip);
            time.start();
        }
    }

    @OnClick({R.id.tvGetMessageNum, R.id.btnSaveNickName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetMessageNum:
                etPhone = edtPhoneNum.getText().toString().trim();
                if (etPhone.length() == 0) {
                    ToastUtils.showToast("请输入手机号码");
                } else if (PhoneNumUtils.isPhone(this, etPhone)) {
                    mViewModel.getSmsCodeBindPhone(etPhone);
                }
                break;
            case R.id.btnSaveNickName:
                etPhone = edtPhoneNum.getText().toString().trim();
                erMessageCode = edtMessageNum.getText().toString().trim();
                if(erMessageCode.length() != 6){
                    ToastUtils.showToast("请输入正确的手机验证码");
                }else if (etPhone.length() == 0) {
                    ToastUtils.showToast("请输入手机号码");
                } else if (PhoneNumUtils.isPhone(this, etPhone)) {
                    mViewModel.updateUserPhone(etPhone, erMessageCode);
                }
                break;
        }
    }
}
