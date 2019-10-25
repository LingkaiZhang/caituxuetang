package com.yuanin.fuliclub.learnPart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.alipay.PayResult;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.bean.AliPayOrderVo;
import com.yuanin.fuliclub.coursePart.bean.CourseOrderCreatVo;
import com.yuanin.fuliclub.coursePart.bean.WeChatOrderVo;
import com.yuanin.fuliclub.util.DensityUtil;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.wxapi.WXPayManager;

import java.util.Map;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPayActivity extends AbsLifecycleActivity<CourseViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.ivOrderIMG)
    ImageView ivOrderIMG;
    @BindView(R.id.tvOrderName)
    TextView tvOrderName;
    @BindView(R.id.tvItemCourseSlogan)
    TextView tvItemCourseSlogan;
    @BindView(R.id.tvOrderPrice)
    TextView tvOrderPrice;
    @BindView(R.id.ivPayWechat)
    ImageView ivPayWechat;
    @BindView(R.id.ivSelectPay1)
    ImageView ivSelectPay1;
    @BindView(R.id.rlWeChatPay)
    RelativeLayout rlWeChatPay;
    @BindView(R.id.ivPayalipay)
    ImageView ivPayalipay;
    @BindView(R.id.ivSelectPay2)
    ImageView ivSelectPay2;
    @BindView(R.id.rlAliPay)
    RelativeLayout rlAliPay;
    @BindView(R.id.ll_PAY_TYPE)
    LinearLayout llPAYTYPE;
    @BindView(R.id.tvUserProtocol)
    TextView tvUserProtocol;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tvPayMoney)
    TextView tvPayMoney;
    @BindView(R.id.vi2)
    View vi2;
    @BindView(R.id.btnPay)
    Button btnPay;

    private String courseId;
    private String periodsId;
    private Context mContext = OrderPayActivity.this;
    private String PayType = "weChatPay";
    private CourseOrderCreatVo orderDate;

    private static final int SDK_PAY_FLAG = 1;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                Toast.makeText(OrderPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderPayActivity.this, CourseDetailsLoginActivity.class);
                intent.putExtra("courseId", courseId);
                startActivity(intent);

            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(OrderPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        };
    };

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        periodsId = intent.getStringExtra("periodsId");
        if (!TextUtils.isEmpty(courseId) && !TextUtils.isEmpty(periodsId)) {
            //创建订单
            mViewModel.createCourseOrder(courseId, periodsId);
        }

    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(CourseRepository.EVENT_KEY_COURSE_CREATE_ORDER, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    orderDate = (CourseOrderCreatVo) returnResult.getData();
                    setOrderDate(orderDate);
                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });

        registerSubscriber(CourseRepository.EVENT_KEY_WECHAT_CREATE_ORDER, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    WeChatOrderVo weChatOrderVo = (WeChatOrderVo) returnResult.getData();
                    if (weChatOrderVo != null) {
                        if (WXPayManager.chechWXCanPay(mContext)) {
                            WXPayManager.toPay((Activity) mContext, weChatOrderVo.getAppid(), weChatOrderVo.getPartnerid(),
                                    weChatOrderVo.getPrepayid(), weChatOrderVo.getNonceStr(), weChatOrderVo.getTimeStamp(),
                                    weChatOrderVo.getPackageValue(),
                                    weChatOrderVo.getSign(), courseId);
                        }
                    }

                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });

        registerSubscriber(CourseRepository.EVENT_KEY_ALIPAY_CREATE_ORDER, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    AliPayOrderVo aliPayOrderVo = (AliPayOrderVo) returnResult.getData();
                    if (aliPayOrderVo != null) {
                       //调起支付宝支付
                        String sin = aliPayOrderVo.getSin();
                        AliPay(sin);
                    }

                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });
    }

    private void AliPay(String sin) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderPayActivity.this);
                Map<String,String> result = alipay.payV2(sin,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void setOrderDate(CourseOrderCreatVo orderDate) {
        tvOrderName.setText(orderDate.getProductName());
        tvItemCourseSlogan.setText(orderDate.getProductInfo());

        tvOrderPrice.setText("需支付：" + orderDate.getPrice() + "元");
        tvPayMoney.setText("￥" + orderDate.getPrice());


        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dip2px(mContext, 8));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions
                .bitmapTransform(roundedCorners)
                .override(300, 300)
                .placeholder(R.mipmap.item_course);

        Glide.with(mContext).load(orderDate.getImageUrl())
                .apply(options)
                .into(ivOrderIMG);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rlWeChatPay, R.id.rlAliPay, R.id.tvUserProtocol, R.id.btnPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlWeChatPay:
                ivSelectPay1.setVisibility(View.VISIBLE);
                ivSelectPay2.setVisibility(View.GONE);
                PayType = "weChatPay";
                break;
            case R.id.rlAliPay:
                ivSelectPay1.setVisibility(View.GONE);
                ivSelectPay2.setVisibility(View.VISIBLE);
                PayType = "aliPay";
                break;
            case R.id.tvUserProtocol:
                break;
            case R.id.btnPay:

                if (orderDate == null) {
                    ToastUtils.showToast("订单数据为空。");

                } else {
                    if (PayType.equals("weChatPay")) {
                        //微信统一下单
                        mViewModel.createWeChatOrder(orderDate.getOrderNo(), orderDate.getProductType(),
                                orderDate.getProductId(), orderDate.getProductName(), orderDate.getPrice(), orderDate.getKey());

                    } else if (PayType.equals("aliPay")) {
                        //支付宝支付
                        mViewModel.createAliPayOrder(orderDate.getOrderNo(), orderDate.getProductType(),
                                orderDate.getProductId(), orderDate.getProductName(), orderDate.getPrice(), orderDate.getKey());
                    }
                }


                break;
        }
    }
}
