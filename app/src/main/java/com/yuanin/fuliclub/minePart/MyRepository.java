package com.yuanin.fuliclub.minePart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.yuanin.fuliclub.base.App;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.loginRegister.BooleanTest;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.minePart.bean.PersonalInfoEntity;
import com.yuanin.fuliclub.minePart.bean.UpdateFileCallbackEntity;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/25 13:53
 */
public class MyRepository extends BaseRepository {

    public static String IS_INSTALL_WECHAT = null;

    public static String EVENT_KEY_UPLOAD_FILE = null;

    public static String EVENT_KEY_UPLOAD_USER_HEADER = null;

    public static String EVENT_KEY_USER_ACCOUNT_INFO = null;

    public static String EVENT_KEY_SAVE_USER_NICKNAME = null;

    public static String EVENT_KEY_MESSAGE_LIST = null;

    public static String EVENT_KEY_MESSAGE_UPDATE_STATUS = null;

    private BooleanTest booleanTest = new BooleanTest();

    private Flowable<ReturnResult<UpdateFileCallbackEntity>> upLoadPicture;

    public MyRepository() {
        if (IS_INSTALL_WECHAT == null) {
            IS_INSTALL_WECHAT = StringUtil.getEventKey();
        }

        if (EVENT_KEY_UPLOAD_FILE == null) {
            EVENT_KEY_UPLOAD_FILE = StringUtil.getEventKey();
        }

        if (EVENT_KEY_UPLOAD_USER_HEADER == null) {
            EVENT_KEY_UPLOAD_USER_HEADER = StringUtil.getEventKey();
        }
        if (EVENT_KEY_USER_ACCOUNT_INFO == null) {
            EVENT_KEY_USER_ACCOUNT_INFO = StringUtil.getEventKey();
        }
        if (EVENT_KEY_SAVE_USER_NICKNAME == null) {
            EVENT_KEY_SAVE_USER_NICKNAME = StringUtil.getEventKey();
        }
        if (EVENT_KEY_MESSAGE_LIST == null) {
            EVENT_KEY_MESSAGE_LIST = StringUtil.getEventKey();
        }
        if (EVENT_KEY_MESSAGE_UPDATE_STATUS == null) {
            EVENT_KEY_MESSAGE_UPDATE_STATUS = StringUtil.getEventKey();
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

    public void upLoadPicture(String picturePath) {

        File file = new File(picturePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("spaceAuth", "1", requestBody);

        upLoadPicture = apiService.upLoadPicture(body);
        addDisposable(upLoadPicture
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<UpdateFileCallbackEntity>>() {
                    @Override
                    public void onSuccess(ReturnResult<UpdateFileCallbackEntity> returnResult) {
                        postData(EVENT_KEY_UPLOAD_FILE, returnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void updateUserHeaderImage(String saveName) {
        addDisposable(apiService.updateUserHeaderImage(saveName)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<String>>() {

                    @Override
                    public void onSuccess(ReturnResult<String> stringReturnResult) {
                        postData(EVENT_KEY_UPLOAD_USER_HEADER, stringReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getUserInfo() {
        addDisposable(apiService.getUserAccountInfo()
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<PersonalInfoEntity>>() {

                    @Override
                    public void onSuccess(ReturnResult<PersonalInfoEntity> returnResult) {
                        postData(EVENT_KEY_USER_ACCOUNT_INFO, returnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void saveNewNickName(String newNickName) {
        addDisposable(apiService.saveNewNickName(newNickName)
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<String>>() {

                    @Override
                    public void onSuccess(ReturnResult<String> stringReturnResult) {
                        postData(EVENT_KEY_SAVE_USER_NICKNAME, stringReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void getMessageList(String pageNum) {
        addDisposable(apiService.getMessageList(pageNum, "15")
                .compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<List<MyMessageVo>>>() {

                    @Override
                    public void onSuccess(ReturnResult<List<MyMessageVo>> messageVoReturnResult) {
                        postData(EVENT_KEY_MESSAGE_LIST, messageVoReturnResult);
                        postState(StateConstants.SUCCESS_STATE);
                    }

                    @Override
                    public void onFailure(String msg, int code) {

                    }
                }));
    }

    public void updateMessageStatus(String newId, String type){
        addDisposable(apiService.updateMessageStatus(newId, type)
        .compose(RxSchedulers.io_main())
        .subscribeWith(new RxSubscriber<ReturnResult<String>>(){

            @Override
            public void onSuccess(ReturnResult<String> stringReturnResult) {
                postData(EVENT_KEY_MESSAGE_UPDATE_STATUS, stringReturnResult);
                postState(StateConstants.SUCCESS_STATE);
            }

            @Override
            public void onFailure(String msg, int code) {

            }
        }));
    }
}
