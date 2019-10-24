package com.yuanin.fuliclub.wxapi;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yuanin.fuliclub.base.App;
import com.yuanin.fuliclub.config.AppConst;

public class WXPayManager {

    // MARK: - 判断手机是否安装微信，微信版本是否支持支付
    public static Boolean chechWXCanPay(Context context) {

        Boolean isCanPay = true;
        if (!App.mWxApi.isWXAppInstalled()) {
            Toast.makeText(context, "您的手机没有安装微信", Toast.LENGTH_SHORT).show();
            isCanPay = false;
        } else {

            boolean isPaySupported = App.mWxApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;

            if (!isPaySupported) {
//            Toast.makeText(activity, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "您的微信版本过低，不支持支付", Toast.LENGTH_SHORT).show();
                isCanPay = false;
            }
        }
        return isCanPay;
    }


    // MARK: - 进入微信客服端去支付
    public static void toPay(final Activity activity,
                             final String appId,
                             final String mch_id,
                             final String prepayid,
                             final String noncestr,
                             final String timestamp,
                             final String packageValue,
                             final String sign,
                             final String courseId) {

        // 在主线程中执行
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // IWXAPI 是第三方app和微信通信的openapi接口
                // 通过WXAPIFactory工厂，获取IWXAPI的实例
                IWXAPI api = WXAPIFactory.createWXAPI(activity, AppConst.WEIXIN.APP_ID, false);

                // 将该app注册到微信
                //api.registerApp(AppConst.WEIXIN.APP_ID);

                System.out.println("appId==" + appId);
                System.out.println("mch_id==" + mch_id);
                System.out.println("prepayid==" + prepayid);
                System.out.println("noncestr==" + noncestr);
                System.out.println("timestamp==" + timestamp);
                System.out.println("packageValue==" + packageValue);
                System.out.println("sign==" + sign);

                PayReq req = new PayReq();

                req.appId			= appId;
                req.partnerId		= mch_id;
                req.prepayId		= prepayid;
                req.nonceStr		= noncestr;
                req.timeStamp		= timestamp;
                req.packageValue	= packageValue;
                req.sign			= sign;
                req.extData         = courseId;


                Toast.makeText(activity, "正在调起支付", Toast.LENGTH_SHORT).show();
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
            }
        });

    }


}
