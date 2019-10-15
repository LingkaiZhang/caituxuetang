package com.yuanin.fuliclub.minePart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.util.AppUtils;
import com.yuanin.fuliclub.util.ToastUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NickNameModifyActivity extends AbsLifecycleActivity<MyViewModel> {
    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.edtNickName)
    EditText edtNickName;
    @BindView(R.id.btnSaveNickName)
    Button btnSaveNickName;

    private String nickName;
    private String newNickName;

    private WeakReference<NickNameModifyActivity> weakReference;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_nick_name_modify;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        nickName = getIntent().getStringExtra("nickName");
        edtNickName.setText(nickName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSaveNickName)
    public void onViewClicked() {
        newNickName = edtNickName.getText().toString().trim();
        if (newNickName.length() == 0) {
            ToastUtils.showToast("昵称不能为空");
        }else if (newNickName.equals(nickName)) {
            ToastUtils.showToast("新编辑的昵称与原昵称相同.");
        }else {
            mViewModel.saveNewNickName(newNickName);
        }
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(MyRepository.EVENT_KEY_SAVE_USER_NICKNAME, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    finish();
                }
                ToastUtils.showToast(returnResult.getMessage());
            }
        });
    }
}
