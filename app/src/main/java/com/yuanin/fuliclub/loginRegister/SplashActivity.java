package com.yuanin.fuliclub.loginRegister;

import android.Manifest;
import android.content.Intent;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.mvvm.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yuanin.fuliclub.MainActivity;
import com.yuanin.fuliclub.R;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.app_logo)
    ImageView appLogo;


    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        loadManager.showSuccess();

        //渐变展示启动屏
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTO();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        appLogo.setAnimation(alphaAnimation);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // rxPermission();
    }

    private void redirectTO() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void rxPermission() {
        // Must be done during an initialization phase like onCreate
        final RxPermissions rxPermissions = new RxPermissions(SplashActivity.this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NOTIFICATION_POLICY)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                    } else {
                        // Oups permission denied
                    }
                });
    }
}
