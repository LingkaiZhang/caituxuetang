package com.yuanin.fuliclub.minePart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class OrderDetaailsActivity extends AbsLifecycleActivity<MyViewModel> {
    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;

    private WeakReference<OrderDetaailsActivity> weakReference;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detaails;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

    }
}
