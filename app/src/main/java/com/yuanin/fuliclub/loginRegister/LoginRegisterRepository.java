package com.yuanin.fuliclub.loginRegister;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.yuanin.fuliclub.base.App;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;

import io.reactivex.Flowable;

/**
 * description ： 登录注册模块网络请求类
 * author : lingkai
 * date : 2019/8/22 11:08
 */
public class LoginRegisterRepository extends BaseRepository {

    public static String IS_INSTALL_WECHAT = null;

    public static String EVENT_KEY_LOGIN_MESSAGE = null;

    public static String EVENT_KEY_LOGIN_PHONE = null;



    private BooleanTest booleanTest = new BooleanTest();

    private Flowable<ReturnResult<String>> sendMessageVerification;

    public LoginRegisterRepository() {
        if (IS_INSTALL_WECHAT == null) {
            IS_INSTALL_WECHAT = StringUtil.getEventKey();
        }

        if (EVENT_KEY_LOGIN_MESSAGE == null) {
            EVENT_KEY_LOGIN_MESSAGE = StringUtil.getEventKey();
        }
        if (EVENT_KEY_LOGIN_PHONE == null) {
            EVENT_KEY_LOGIN_PHONE = StringUtil.getEventKey();
        }

    }

    public void weChatLgin() {
        if (!App.mWxApi.isWXAppInstalled()) {
            booleanTest.isTrue = false;
            postData(IS_INSTALL_WECHAT, booleanTest);
            postState(StateConstants.SUCCESS_STATE);
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        App.mWxApi.sendReq(req);
    }

    public void sendSmsVerification(String phoneNo) {
        sendMessageVerification = apiService.sendMessageVerification(phoneNo);
        addDisposable(sendMessageVerification
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<String>>() {
                    @Override
                    public void onSuccess(ReturnResult<String> stringReturnResult) {
                        postData(EVENT_KEY_LOGIN_MESSAGE, stringReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                })
        );
    }

    public void gotoLoginRegister(String phone, String code) {
        addDisposable(apiService.gotoPhoneLoginRegister(phone, code)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<LoginSuccessEntity>>() {
                    @Override
                    public void onSuccess(ReturnResult<LoginSuccessEntity> loginSuccessEntityReturnResult) {
                        postData(EVENT_KEY_LOGIN_PHONE, loginSuccessEntityReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }
}
