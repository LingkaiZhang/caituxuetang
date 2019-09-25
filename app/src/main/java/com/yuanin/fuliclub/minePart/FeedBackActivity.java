package com.yuanin.fuliclub.minePart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.androidman.SuperButton;

public class FeedBackActivity extends AbsLifecycleActivity<MyViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.tvFeedBackContent)
    EditText tvFeedBackContent;
    @BindView(R.id.tvWordNumber)
    TextView tvWordNumber;
    @BindView(R.id.btnSubmitFeedback)
    SuperButton btnSubmitFeedback;

    private WeakReference<FeedBackActivity> weakReference;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        initListener();
    }

    private void initListener() {
        tvFeedBackContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btnSubmitFeedback.setColorNormal(getResources().getColor(R.color.theme_color));
                    tvWordNumber.setText(s.length()+ "/" + "200字");
                }else {
                    btnSubmitFeedback.setColorNormal(getResources().getColor(R.color.btn_unable_click_gray));
                    tvWordNumber.setText(s.length()+ "/" + "200字");
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSubmitFeedback)
    public void onViewClicked() {
    }
}
