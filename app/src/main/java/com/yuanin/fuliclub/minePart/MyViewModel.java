package com.yuanin.fuliclub.minePart;

import android.app.Application;
import android.support.annotation.NonNull;

import com.mvvm.base.AbsViewModel;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/25 13:52
 */
public class MyViewModel extends AbsViewModel<MyRepository> {

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public void upLoadPicture(String picturePath) {
        mRepository.upLoadPicture(picturePath);
    }

    public void updateUserHeaderImage(String saveName) {
        mRepository.updateUserHeaderImage(saveName);
    }

    public void getUserInfo() {
        mRepository.getUserInfo();
    }

    public void weChatLogin() {
        mRepository.weChatLgin();
    }

    public void saveNewNickName(String newNickName) {
        mRepository.saveNewNickName(newNickName);
    }

    public void getMessageList(String pageNum) {
        mRepository.getMessageList(pageNum);
    }

    public void upDateMessageStatus(String newid, String type){
        mRepository.updateMessageStatus(newid,type);
    }

    public void getSmsCodeBindPhone(String etPhone) {
        mRepository.getSmsCodeBindPhone(etPhone);
    }

    public void updateUserPhone(String etPhone, String erMessageCode) {
        mRepository.upDateUserPhone(etPhone, erMessageCode);
    }
}
