package com.yuanin.fuliclub.minePart;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.permissions.RxPermissions;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.wildma.pictureselector.ImageUtils;
import com.wildma.pictureselector.PictureSelector;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.loginRegister.BooleanTest;
import com.yuanin.fuliclub.loginRegister.LoginRegisterRepository;
import com.yuanin.fuliclub.loginRegister.SmsMessageVerActivity;
import com.yuanin.fuliclub.minePart.bean.PersonalInfoEntity;
import com.yuanin.fuliclub.minePart.bean.UpdateFileCallbackEntity;
import com.yuanin.fuliclub.util.AppUtils;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.view.GeneralDialog;
import com.yuanin.fuliclub.view.bamtoast.btoast.BToast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyAccountActivity extends AbsLifecycleActivity<MyViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.tvPhoneNo)
    TextView tvPhoneNo;
    @BindView(R.id.tvWeChatName)
    TextView tvWeChatName;
    @BindView(R.id.ivHeaderImage)
    ImageView ivHeaderImage;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvExitLogin)
    TextView tvExitLogin;

    private WeakReference<MyAccountActivity> weakReference;
    private GeneralDialog exitDialog;

    private boolean isBindWeChat = false;
    private String nickName;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_account;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        mViewModel.getUserInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivHeaderImage, R.id.tvExitLogin, R.id.tvWeChatName, R.id.tvNickName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivHeaderImage:

                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
                RxPermissions permissions = new RxPermissions(this);
                permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureSelector
                                    .create((Activity) MyAccountActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                                    .selectPicture(200, 200, 1, 1);
                        } else {
                            Toast.makeText(MyAccountActivity.this,
                                    getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });


                break;
            case R.id.tvExitLogin:
                exitDialog = new GeneralDialog(this, true, "提示", "您确定要退出登录吗？", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtils.exitLogin(MyAccountActivity.this);
                        MyAccountActivity.this.finish();
                    }
                });
                break;

            case R.id.tvNickName:
                Intent intent = new Intent(this, NickNameModifyActivity.class);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;

            case R.id.tvWeChatName:
                if (isBindWeChat) {
                    ToastUtils.showToast("您的账号已绑定微信！");
                } else {
                    //绑定微信
                    mViewModel.weChatLogin();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                final String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                //ivHeadImage.setImageBitmap(ImageUtils.getBitmap(picturePath));
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(this).load(ImageUtils.getBitmap(picturePath)).apply(requestOptions).into(ivHeaderImage);

                new Thread() {
                    @Override
                    public void run() {
                        //这里写入子线程需要做的工作
                        //图片上传
                        mViewModel.upLoadPicture(picturePath);
                    }
                }.start();
            }
        }
    }

    @Override
    protected void dataObserver() {

        registerSubscriber(MyRepository.IS_INSTALL_WECHAT, BooleanTest.class).observe(this, booleanTest -> {
            if (booleanTest != null) {
                boolean isTrue = booleanTest.isTrue;
                if (isTrue) {
                    // Toast.makeText(this, "您的设备还没有安装微信", Toast.LENGTH_SHORT).show();
                    ToastUtils.showToast("您的设备还没有安装微信");
                }
            }
        });

        registerSubscriber(MyRepository.EVENT_KEY_UPLOAD_FILE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    UpdateFileCallbackEntity updateFileCallbackEntity = (UpdateFileCallbackEntity) returnResult.getData();
                    //图片上传结果,更新我的头像
                    mViewModel.updateUserHeaderImage(updateFileCallbackEntity.getSaveName());
                }
                ToastUtils.showToast(returnResult.getMessage());
            }
        });

        registerSubscriber(MyRepository.EVENT_KEY_UPLOAD_USER_HEADER, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                ToastUtils.showToast(returnResult.getMessage());
            }
        });

        registerSubscriber(MyRepository.EVENT_KEY_USER_ACCOUNT_INFO, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    PersonalInfoEntity personalInfoEntity = (PersonalInfoEntity) returnResult.getData();
                    setUserInfo(personalInfoEntity);
                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });
    }

    private void setUserInfo(PersonalInfoEntity entity) {
        setUserHeadImage(entity.getProfilePictureLink());
        tvNickName.setText(entity.getNickName());
        tvPhoneNo.setText(entity.getMobile());
        nickName = entity.getNickName();

        if (entity.getRelevance() == 0) {
            tvWeChatName.setText("未关联");
            isBindWeChat = false;
        } else if (entity.getRelevance() == 1) {
            tvWeChatName.setText("已关联");
            isBindWeChat = true;
        }
    }

    private void setUserHeadImage(String profilePictureLink) {
        RequestOptions options = new RequestOptions()
                .circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .placeholder(R.mipmap.avatar);
        Glide.with(this).load(profilePictureLink)
                .apply(options)
                .into(ivHeaderImage);
    }
}
