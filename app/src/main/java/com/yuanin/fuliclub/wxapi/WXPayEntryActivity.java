package com.yuanin.fuliclub.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import com.yuanin.fuliclub.base.App;

import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.event.OnClickKefuEvent;
import com.yuanin.fuliclub.event.WechatPaySuccessEvent;
import com.yuanin.fuliclub.event.WechatPayUnusualeEvent;
import com.yuanin.fuliclub.learnPart.CourseDetailsLoginActivity;
import com.yuanin.fuliclub.util.LogUtils;
import com.yuanin.fuliclub.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = App.mWxApi.handleIntent(getIntent(), this);
            if(!result){
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d("wechat", resp.toString());
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                ToastUtils.showToast("支付成功!");

                //发送支付成功消息
                EventBus.getDefault().post(new WechatPaySuccessEvent());

                //TODO 跳转购买记录
//                PayResp payReq = (PayResp) resp;
//                String courseId = payReq.extData;
//                Intent intent = new Intent(this, CourseDetailsLoginActivity.class);
//                intent.putExtra("courseId", courseId);
//                startActivity(intent);

            } else if (resp.errCode == -2) {
                ToastUtils.showToast("您已取消付款!");

                //发送支付异常消息
                EventBus.getDefault().post(new WechatPayUnusualeEvent());

                finish();
            } else {
                ToastUtils.showToast("参数错误!");

                //发送支付异常消息
                EventBus.getDefault().post(new WechatPayUnusualeEvent());

                finish();
            }
        } else {
            finish();
        }
    }
}
