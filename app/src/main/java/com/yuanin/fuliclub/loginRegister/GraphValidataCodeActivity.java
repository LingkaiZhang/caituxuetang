package com.yuanin.fuliclub.loginRegister;

import android.os.Bundle;
import android.widget.Toast;

import com.luozm.captcha.Captcha;
import com.mvvm.base.BaseActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphValidataCodeActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.captCha)
    Captcha captCha;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_graph_validata_code;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        captCha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(GraphValidataCodeActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                return "验证通过,耗时"+time+"毫秒";
            }

            @Override
            public String onFailed(int failedCount) {
                Toast.makeText(GraphValidataCodeActivity.this,"验证失败",Toast.LENGTH_SHORT).show();
                return "验证失败,已失败"+failedCount+"次";
            }

            @Override
            public String onMaxFailed() {
                Toast.makeText(GraphValidataCodeActivity.this,"验证超过次数，你的帐号被封锁",Toast.LENGTH_SHORT).show();
                return "验证失败,帐号已封锁";
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
