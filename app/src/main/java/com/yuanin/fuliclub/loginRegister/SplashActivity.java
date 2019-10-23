package com.yuanin.fuliclub.loginRegister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.mvvm.base.BaseActivity;
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

    private void redirectTO() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
