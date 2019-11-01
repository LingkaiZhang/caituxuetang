package com.yuanin.fuliclub.minePart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.minePart.bean.OrderDetailsInfoVo;
import com.yuanin.fuliclub.util.ClipboardHelper;
import com.yuanin.fuliclub.util.DateUtil;
import com.yuanin.fuliclub.util.DensityUtil;
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
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvPayType)
    TextView tvPayType;
    @BindView(R.id.tvCreatTime)
    TextView tvCreatTime;
    @BindView(R.id.tvOrderStatus)
    TextView tvOrderStatus;
    @BindView(R.id.tvContactUs)
    TextView tvContactUs;

    private WeakReference<OrderDetaailsActivity> weakReference;
    private Context mContext = OrderDetaailsActivity.this;

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

        Intent intent = getIntent();
        OrderDetailsInfoVo orderDetails = (OrderDetailsInfoVo) intent.getSerializableExtra("orderDetails");
        if (orderDetails != null) {
            setDate(orderDetails);
        }
    }

    private void setDate(OrderDetailsInfoVo orderDetails) {
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(DensityUtil.dip2px(mContext,8));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions
                .bitmapTransform(roundedCorners)
                .override(300, 300)
                .placeholder(R.mipmap.item_course);

        Glide.with(mContext).load(orderDetails.getImageurl())
                .apply(options)
                .into(ivOrderIMG);

        tvOrderName.setText(orderDetails.getProductName());
        tvOrderPrice.setText("已支付：" + orderDetails.getPrice() + "元");
        tvProductName.setText(orderDetails.getProductName());
        tvOrderNo.setText(orderDetails.getOrderNo());

//        if (orderDetails.getProductType() == 1) {
//            tvOrderType.setText("虚拟课程");
//        }

        tvOrderType.setText(orderDetails.getProductTypeName());
        tvCreatTime.setText(DateUtil.timeStamp2Date(String.valueOf(orderDetails.getCreateTime()/1000), null));
        tvPayType.setText(orderDetails.getPayWay());
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
