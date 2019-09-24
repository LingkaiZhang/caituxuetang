package com.yuanin.fuliclub.loginRegister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.MainActivity;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.util.SharedPreferencesUtils;
import com.yuanin.fuliclub.view.CodeView;
import com.yuanin.fuliclub.view.CountDownTextView;
import com.yuanin.fuliclub.view.bamtoast.btoast.BToast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.androidman.SuperButton;

public class SmsMessageVerActivity extends AbsLifecycleActivity<LoginRegisterViewModel> {


    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.codeView)
    CodeView codeView;
    @BindView(R.id.tvDes_2)
    TextView tvDes2;
    @BindView(R.id.btnReGetMessage)
    SuperButton btnReGetMessage;
    @BindView(R.id.tvCountDown)
    CountDownTextView tvCountDown;
    @BindView(R.id.tvNoCode)
    TextView tvNoCode;

    private WeakReference<SmsMessageVerActivity> weakReference;
    private String phone;
    private Context context = SmsMessageVerActivity.this;

    private static final int GRAPH_VALIDATA_CODE = 100;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sms_message_ver;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        phone = getIntent().getStringExtra("phone");
        if (!TextUtils.isEmpty(phone)) {
            tvDes2.setText("+86 " + phone);
        }
        //开始倒计时
        tvCountDown.start();
        tvCountDown.setOnClickListener(v -> mViewModel.sendSmsVerification(phone));

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        codeView.setmInputListener(code -> {
            //输入验证码完成，进行校验
            mViewModel.gotoLoginRegister(phone, code);

        });

    }

    protected void dataObserver() {

        registerSubscriber(LoginRegisterRepository.EVENT_KEY_LOGIN_PHONE, ReturnResult.class).observe(this, loginSuccessEntityReturnResult -> {
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

                    BToast.makeText(this, loginSuccessEntityReturnResult.getMessage(), true).show();
                } else {
                    BToast.makeText(this, loginSuccessEntityReturnResult.getMessage(), false).show();
                }


            }
        });

        registerSubscriber(LoginRegisterRepository.EVENT_KEY_LOGIN_MESSAGE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                BToast.makeText(this, returnResult.getMessage(), true).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            codeView.setText("");
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R.id.tvNoCode)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvNoCode:
                Intent intent = new Intent(context, GraphValidataCodeActivity.class);
                startActivityForResult(intent, GRAPH_VALIDATA_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //此处可以根据两个Code进行判断，本页面和结果页面跳过来的值
        if (requestCode == GRAPH_VALIDATA_CODE) {
            mViewModel.sendSmsVerification(phone);
            tvCountDown.reset();
        }
    }
}
