package com.yuanin.fuliclub.wxapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mvvm.http.HttpHelper;
import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import com.yuanin.fuliclub.MainActivity;
import com.yuanin.fuliclub.base.ApiService;
import com.yuanin.fuliclub.base.App;

import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.config.StaticMembers;

import com.yuanin.fuliclub.loginRegister.BindPhoneActivity;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.AppUtils;
import com.yuanin.fuliclub.util.LogUtils;
import com.yuanin.fuliclub.util.SharedPreferencesUtils;
import com.yuanin.fuliclub.util.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.reflect.Type;
import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.yuanin.fuliclub.loginRegister.LoginRegisterRepository.EVENT_KEY_LOGIN_MESSAGE;


/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/6/9 18:13
 *
 * @version 1.0
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {


    private String regId;
    // private MessageReceiver mMessageReceiver;
    private Context context = WXEntryActivity.this;

    protected ApiService apiService;
    private Flowable<ReturnResult<LoginSuccessEntity>> weChatCodeToLogin;
    private Flowable<ReturnResult<String>> bindWechat;

    /**
     * 微信登录相关
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == apiService) {
            apiService = HttpHelper.getInstance().create(ApiService.class);
        }

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = App.mWxApi.handleIntent(getIntent(), this);
            if (!result) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        App.mWxApi.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        App.mWxApi.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtils.d("baseReq:", new Gson().toJson(baseReq));
    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtils.d("baseResp:", new Gson().toJson(baseResp));
        LogUtils.d("baseResp:", baseResp.errStr + "," + baseResp.openId + "," + baseResp.transaction + "," + baseResp.errCode);
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String wxCode = ((SendAuth.Resp) baseResp).code;
                LogUtils.d("code = ", wxCode);

                if (StaticMembers.IS_NEED_LOGIN) {
                    //sendCodeToService(wxCode);
                    sendCodeToLogin(wxCode);
                } else {
                    sendCodeBindWeChat(wxCode);
                }

                /*String code = ((SendAuth.Resp) baseResp).code;
                LogUtils.d("code = " , code);
                RequestParams params = new RequestParams("https://api.weixin.qq.com/sns/oauth2/access_token");
                params.addParameter("appid", AppConst.WEIXIN.APP_ID);
                params.addParameter("secret",AppConst.WEIXIN.APP_SECRET);
                params.addParameter("code",code);
                params.addParameter("grant_type","authorization_code");
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                    LogUtils.d("lingkai",result);

                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                    }
                    @Override
                    public void onCancelled(Callback.CancelledException cex) {
                    }
                    @Override
                    public void onFinished() {
                    }
                });*/


                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                LogUtils.d("lingkai", 2 + result);
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                LogUtils.d("lingkai", 3 + result);
                finish();
                break;
            default:
                result = "发送返回";
                LogUtils.d("lingkai", 0 + result);
                finish();
                break;
        }

    }

    @SuppressLint("CheckResult")
    private void sendCodeToLogin(String wxCode) {
        weChatCodeToLogin = apiService.sendWeChatCodeToLogin(wxCode, "android");
        weChatCodeToLogin.compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<LoginSuccessEntity>>() {
                    @Override
                    public void onSuccess(ReturnResult<LoginSuccessEntity> loginSuccessEntityReturnResult) {
                        if (loginSuccessEntityReturnResult.isSuccess()) {
                            if ( loginSuccessEntityReturnResult.getData().getSceneCode().equals("LOGIN")) {
                                SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_MBOILE, loginSuccessEntityReturnResult.getData().getMobile());
                                SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_USERID, String.valueOf(loginSuccessEntityReturnResult.getData().getWxId()));
                                SharedPreferencesUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_TOKEN, loginSuccessEntityReturnResult.getData().getAccessToken());
                                StaticMembers.IS_NEED_LOGIN = false;
                                StaticMembers.USER_ID = String.valueOf(loginSuccessEntityReturnResult.getData().getWxId());
                                StaticMembers.MOBILE = loginSuccessEntityReturnResult.getData().getMobile();
                                StaticMembers.TOKEN = loginSuccessEntityReturnResult.getData().getAccessToken();
                                //通知MainActivity跳到首页
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                            } else if (loginSuccessEntityReturnResult.getData().getSceneCode().equals("REGISTER")){
                                StaticMembers.USER_ID = String.valueOf(loginSuccessEntityReturnResult.getData().getWxId());
                                Intent intent = new Intent(context, BindPhoneActivity.class);
                                intent.putExtra("uid", String.valueOf(loginSuccessEntityReturnResult.getData().getWxId()));
                                startActivity(intent);
                                ToastUtils.showToast("微信授权成功");
                            }
                        } else {
                            if (loginSuccessEntityReturnResult.getCode() == ParamsValues.TOKEN_FAILURE) {
                                AppUtils.exitLogin(context);
                            }
                            ToastUtils.showToast(context, loginSuccessEntityReturnResult.getMessage(), Toast.LENGTH_SHORT);
                        }
                        //AppUtils.showToast(WXEntryActivity.this,entity.getMessage());
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void sendCodeBindWeChat(String code) {
        bindWechat = apiService.bindWechat(code, "android");
        bindWechat.compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<String>>() {
                    @Override
                    public void onSuccess(ReturnResult<String> stringReturnResult) {
                        ToastUtils.showToast(stringReturnResult.getMessage());
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String msg, int code) {
                    }
                });
    }

    /*private void sendCodeToService(String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code",code);
        map.put("appId", ParamsValues.APP_ID);
        map.put("versionNum", ParamsValues.VERSION_CODE);
        Type type = new TypeToken<ReturnResult<LoginSuccessEntity>>() {
        }.getType();
        NetUtils.request(ParamsValues.USER_AUTHORIZE_LOGIN, map, type, false, new IHttpRequestCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onSuccess(Object object) {
                ReturnResult<LoginSuccessEntity> entity = (ReturnResult<LoginSuccessEntity>) object;
                if (entity.isSuccess()) {
                    if ( entity.getData().getMobile() != null && entity.getData().getMobile().length() > 0) {
                        AppUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_MBOILE, entity.getData().getMobile());
                        AppUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_USERID, String.valueOf(entity.getData().getWxId()));
                        AppUtils.save2SharedPreferences(context, ParamsKeys.LOGIN_FILE, ParamsKeys.LOGIN_TOKEN, entity.getData().getAccessToken());
                        LogUtils.d("flkt", entity.data.getAccessToken());
                        StaticMembers.IS_NEED_LOGIN = false;
                        StaticMembers.USER_ID = String.valueOf(entity.getData().getWxId());
                        StaticMembers.MOBILE = entity.getData().getMobile();
                        StaticMembers.TOKEN = entity.getData().getAccessToken();
                        AppManager.getAppManager().finishActivity(LoginOneActivity.class);
                        //通知MainActivity跳到首页
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    } else {
                        StaticMembers.USER_ID = String.valueOf(entity.getData().getWxId());
                        Intent intent = new Intent(context, ImportPhoneActivity.class);
                        intent.putExtra("uid", String.valueOf(entity.getData().getWxId()));
                        startActivity(intent);
                    }
                } else {
                    if (entity.getCode() == ParamsValues.TOKEN_FAILURE) {
                        AppUtils.exitLogin(context);
                    }
                    AppUtils.showToast(context, entity.getMessage());
                }
                //AppUtils.showToast(WXEntryActivity.this,entity.getMessage());
            }

            @Override
            public void onFailure() {

            }
        });
    }
*/
    @Override
    protected void onDestroy() {
        // LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}