package com.yuanin.fuliclub.learnPart;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseIntroduceFragment extends BaseFragment {
    @BindView(R.id.llAgentWebview)
    com.yuanin.fuliclub.view.ObservableWebView llAgentWebview;
    Unbinder unbinder;

    private String courseInterUrl;

    public static CourseIntroduceFragment newInstance() {

        Bundle args = new Bundle();

        CourseIntroduceFragment fragment = new CourseIntroduceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_course_introduce;
    }

    @Override
    public void initView(Bundle state) {
        llAgentWebview.setVerticalScrollBarEnabled(false);
        llAgentWebview.setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onStateRefresh() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setDatas(String courseInterUrl) {
        this.courseInterUrl = courseInterUrl;
        //llAgentWebview.loadData(courseInterUrl, "text/html; charset=UTF-8", null);
        // courseInterUrl = courseInterUrl.replace("<img", "<img style=\"display:        ;max-width:100%;\"");
        initWebView(courseInterUrl);
    }

    //自定义重写title的webview
    private void initWebView(String courseInterUrl) {
        WebSettings settings = llAgentWebview.getSettings();
        settings.setSupportZoom(false);          //支持缩放
        settings.setBuiltInZoomControls(false);  //启用内置缩放装置
        settings.setJavaScriptEnabled(true);    //启用JS脚本
        settings.setDomStorageEnabled(true);
        llAgentWebview.setWebViewClient(new WebViewClient() {
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
        llAgentWebview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && llAgentWebview.canGoBack()) {
                        llAgentWebview.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        llAgentWebview.setWebChromeClient(new WebChromeClient() {
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
        llAgentWebview.loadData(courseInterUrl, "text/html; charset=UTF-8", null);
    }
}
