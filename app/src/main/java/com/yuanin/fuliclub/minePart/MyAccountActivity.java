package com.yuanin.fuliclub.minePart;

import android.os.Bundle;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAccountActivity extends AbsLifecycleActivity<MyViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.tvPhoneNo)
    TextView tvPhoneNo;
    @BindView(R.id.tvWeChatName)
    TextView tvWeChatName;

    private WeakReference<MyAccountActivity> weakReference;

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
}
