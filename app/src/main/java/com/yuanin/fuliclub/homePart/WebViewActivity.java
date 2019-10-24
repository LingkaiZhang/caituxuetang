package com.yuanin.fuliclub.homePart;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;

import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.view.ObservableWebView;

import java.lang.ref.WeakReference;

import butterknife.BindView;


public class WebViewActivity extends AbsLifecycleActivity<HomeViewModel> {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.wvHref)
    ObservableWebView wvHref;


    private WeakReference<WebViewActivity> weakReference;


    private String type;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        type = getIntent().getStringExtra(ParamsKeys.TYPE);
        if (type.equals(ParamsValues.BANNER)) {
            String banner_url = getIntent().getStringExtra("banner_url");
            initWebViewTitle(banner_url);
        }
    }

    //自定义重写title的webview
    private void initWebView(String url) {
        WebSettings settings = wvHref.getSettings();
        settings.setSupportZoom(true);          //支持缩放
        settings.setBuiltInZoomControls(true);  //启用内置缩放装置
        settings.setJavaScriptEnabled(true);    //启用JS脚本
        settings.setDomStorageEnabled(true);
        wvHref.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed(); // 接受证书
            }

            //当点击链接时,希望覆盖而不是打开新窗口
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);  //加载新的url
                return true;    //返回true,代表事件已处理,事件流到此终止
            }


        });

        //点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
        wvHref.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvHref.canGoBack()) {
                        wvHref.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        wvHref.setWebChromeClient(new WebChromeClient() {
            //当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                initTopBar(title, toptitleView, true);
            }
        });
        wvHref.loadUrl(url);
    }

    //显示页面title的webView
    private void initWebViewTitle(String url) {
        WebSettings settings = wvHref.getSettings();
        settings.setSupportZoom(true);          //支持缩放
        settings.setBuiltInZoomControls(true);  //启用内置缩放装置
        settings.setJavaScriptEnabled(true);    //启用JS脚本
        settings.setDomStorageEnabled(true);
        wvHref.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed(); // 接受证书
            }

            //当点击链接时,希望覆盖而不是打开新窗口
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);  //加载新的url
                return true;    //返回true,代表事件已处理,事件流到此终止
            }


        });

        //点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
        wvHref.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wvHref.canGoBack()) {
                        wvHref.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        wvHref.setWebChromeClient(new WebChromeClient() {
            //当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleBar.setTitle(title);
            }
        });
        wvHref.loadUrl(url);
        //http://csapi.yuanin.com/html/showasset.php?productid=
    }

}