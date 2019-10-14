package com.yuanin.fuliclub.loginRegister;

import android.app.Application;
import android.support.annotation.NonNull;

import com.mvvm.base.AbsViewModel;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.yuanin.fuliclub.base.App;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/8/22 11:07
 */
public class LoginRegisterViewModel extends AbsViewModel<LoginRegisterRepository> {

    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void weChatLogin() {
        mRepository.weChatLgin();
    }

    public void sendSmsVerification(String phoneNo) {
        mRepository.sendSmsVerification(phoneNo);
    }

    public void gotoLoginRegister(String phone, String code) {
        mRepository.gotoLoginRegister(phone, code);
    }

    public void smsValidCodeBindMobile(String phoneNo) {
        mRepository.smsValidCodeBindMobile(phoneNo);
    }

    public void gotoBindPhone(String phoneNo, String smsCode, String weChatUid) {
        mRepository.gotoBindPhone(phoneNo, smsCode, weChatUid);
    }
}
