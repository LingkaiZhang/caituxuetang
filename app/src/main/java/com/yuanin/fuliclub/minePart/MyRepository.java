package com.yuanin.fuliclub.minePart;

import com.mvvm.http.rx.RxSchedulers;
import com.mvvm.stateview.StateConstants;
import com.yuanin.fuliclub.base.BaseRepository;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.loginRegister.LoginSuccessEntity;
import com.yuanin.fuliclub.minePart.bean.UpdateFileCallbackEntity;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.util.StringUtil;

import java.io.File;

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

    public static String EVENT_KEY_UPLOAD_FILE = null;
    private Flowable<ReturnResult<UpdateFileCallbackEntity>> upLoadPicture;

    public MyRepository() {
        if (EVENT_KEY_UPLOAD_FILE == null) {
            EVENT_KEY_UPLOAD_FILE = StringUtil.getEventKey();
        }
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
}
