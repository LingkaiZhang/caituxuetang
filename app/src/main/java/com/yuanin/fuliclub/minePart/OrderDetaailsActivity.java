package com.yuanin.fuliclub.minePart;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.util.ClipboardHelper;
import com.yuanin.fuliclub.util.ToastUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetaailsActivity extends AbsLifecycleActivity<MyViewModel> {
    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.ivOrderIMG)
    ImageView ivOrderIMG;
    @BindView(R.id.tvOrderName)
    TextView tvOrderName;
    @BindView(R.id.tvOrderPrice)
    TextView tvOrderPrice;
    @BindView(R.id.tvOrderNo)
    TextView tvOrderNo;
    @BindView(R.id.tvCopy)
    TextView tvCopy;
    @BindView(R.id.tvOrderType)
    TextView tvOrderType;
    @BindView(R.id.tvPayType)
    TextView tvPayType;
    @BindView(R.id.tvOrderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tvContactUs)
    TextView tvContactUs;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvCopy, R.id.tvContactUs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击复制
            case R.id.tvCopy:
                String orderNo = tvOrderNo.getText().toString().trim();
                ClipboardHelper.getInstance(this).copyText("order_no", orderNo);
                ToastUtils.showToast("已成功复制到剪切板");
                break;

            //联系我们
            case R.id.tvContactUs:

                break;
        }
    }
}
