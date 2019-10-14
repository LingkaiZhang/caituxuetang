package com.yuanin.fuliclub.minePart;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.util.AppUtils;
import com.yuanin.fuliclub.view.GeneralDialog;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccountActivity extends AbsLifecycleActivity<MyViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.tvPhoneNo)
    TextView tvPhoneNo;
    @BindView(R.id.tvWeChatName)
    TextView tvWeChatName;
    @BindView(R.id.ivHeaderImage)
    ImageView ivHeaderImage;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvExitLogin)
    TextView tvExitLogin;

    private WeakReference<MyAccountActivity> weakReference;
    private GeneralDialog exitDialog;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_account;
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

    @OnClick({R.id.ivHeaderImage, R.id.tvExitLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivHeaderImage:
                break;
            case R.id.tvExitLogin:
                exitDialog = new GeneralDialog(this, true, "提示", "您确定要退出登录吗？", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtils.exitLogin(MyAccountActivity.this);
                        MyAccountActivity.this.finish();
                    }
                });
                break;
        }
    }
}
