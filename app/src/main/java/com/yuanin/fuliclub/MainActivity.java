package com.yuanin.fuliclub;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hxb.easynavigition.constant.Anim;
import com.hxb.easynavigition.view.EasyNavigitionBar;
import com.mvvm.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yuanin.fuliclub.fragment.BuyedFragment;
import com.yuanin.fuliclub.fragment.CourseFragment;
import com.yuanin.fuliclub.fragment.HomeFragment;
import com.yuanin.fuliclub.fragment.MineFragment;
import com.yuanin.fuliclub.learnPart.CourseKnobbleDetailsActivity;
import com.yuanin.fuliclub.learnPart.CourseKnobbleInfoVo;
import com.yuanin.fuliclub.loginRegister.SplashActivity;
import com.yuanin.fuliclub.musicPlay.Consts;
import com.yuanin.fuliclub.musicPlay.NotificationUtil;
import com.yuanin.fuliclub.view.GeneralDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    // 双击back退出程序
    private long mLastBackTime = 0;

    private long TIME_DIFF = 2 * 1000;

    private String[] tabText = {"首页", "学习", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_home_gray, R.mipmap.tab_study_gray, R.mipmap.tab_my_gray};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_home,R.mipmap.tab_study, R.mipmap.tab_my};

    private List<android.support.v4.app.Fragment> fragments = new ArrayList<>();
    private GeneralDialog generalDialog;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        loadManager.showSuccess();

        initNavBar();
    }

    private void initNavBar() {
        EasyNavigitionBar navigitionBar = findViewById(R.id.mNavigitionBar);
        fragments.add(HomeFragment.newInstance());
        fragments.add(BuyedFragment.newInstance());
        fragments.add(MineFragment.newInstance());

        navigitionBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .selectTextColor(Color.parseColor("#ff9500"))
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .anim(Anim.ZoomIn)
                .build();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        processIntent(intent);
//
//    }
//
//    private void processIntent(Intent intent) {
//        if (intent.getAction().equals(Consts.ACTION_MUSIC_PLAYER)) {
//            String courseKnobbleId = getIntent().getStringExtra("courseKnobbleId");
//            Intent intent1 = new Intent(this, CourseKnobbleDetailsActivity.class);
//            intent1.putExtra("courseKnobbleId", Integer.valueOf(courseKnobbleId));
//            startActivity(intent1);
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxPermission();

        checkNotify();
    }

    private void rxPermission() {
        // Must be done during an initialization phase like onCreate
        final RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                    } else {
                        // Oups permission denied
                    }
                });


    }

    //通知栏权限
    private void checkNotify(){
        if(!NotificationUtil.checkNotifySetting(MainActivity.this)){
            generalDialog = new GeneralDialog(this, false, "通知权限", "检测到您没有打开通知权限，为不影响使用，请去打开", "取消", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    generalDialog.dismiss();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent localIntent = new Intent();
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 9) {
                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        localIntent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                    } else if (Build.VERSION.SDK_INT <= 8) {
                        localIntent.setAction(Intent.ACTION_VIEW);

                        localIntent.setClassName("com.android.settings",
                                "com.android.settings.InstalledAppDetails");

                        localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                MainActivity.this.getPackageName());
                    }
                    startActivity(localIntent);
                    generalDialog.dismiss();
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 双击back,退出程序
            long now = new Date().getTime();
            if (now - mLastBackTime < TIME_DIFF) {
                finish();
                return super.onKeyDown(keyCode, event);
            } else {
                mLastBackTime = now;
                Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
