package com.yuanin.fuliclub.minePart;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mvvm.base.BaseActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class AboutOursActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.tvVersion)
    TextView tvVersion;

    private WeakReference<AboutOursActivity> weakReference;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_ours;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        String version = packageCode(this);
        tvVersion.setText("当前版本: " + version);
    }

    public static String packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        String code = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
}
