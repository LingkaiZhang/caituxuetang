package com.yuanin.fuliclub.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mvvm.http.HttpHelper;
import com.mvvm.stateview.ErrorState;
import com.mvvm.stateview.LoadingState;
import com.next.easytitlebar.view.EasyTitleBar;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tqzhang.stateview.core.LoadState;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.config.AppConst;
import com.yuanin.fuliclub.config.URL;
import com.yuanin.fuliclub.learnPart.CourseDetailsActivity;
import com.yuanin.fuliclub.learnPart.CourseKnobbleDetailsActivity;
import com.yuanin.fuliclub.view.MusicPlayControlView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author：tqzhang on 18/4/19 17:57
 */
public class App extends Application implements ComponentCallbacks2 {

    private static final String TAG = "FloatWindow";

    public static App mInstance;

    public static IWXAPI mWxApi;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_ffca00, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static App instance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        registToWX();

        mInstance = this;
        new HttpHelper.Builder(this)
                .initOkHttp()
                .createRetrofit(URL.BASE_URL)
                .build();
        new LoadState.Builder()
                .register(new ErrorState())
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();

        initCallBack();
        initEasyTitleBar();

        initFloatWindow();

    }

    private void initFloatWindow() {
        MusicPlayControlView musicPlayControlView = new MusicPlayControlView(getApplicationContext());

        FloatWindow
                .with(getApplicationContext())
                .setView(musicPlayControlView)
                .setWidth(Screen.width, 1.0f) //设置悬浮控件宽高
                .setX(Screen.width, 0.0f)
                .setY(Screen.height, 0.6f)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, CourseKnobbleDetailsActivity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                .setDesktopShow(false)
                .build();


        musicPlayControlView.setOnPlayClickListener(new MusicPlayControlView.OnPlayClickListener() {
            @Override
            public void onplayClick() {
                Toast.makeText(App.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };


    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, AppConst.WEIXIN.APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(AppConst.WEIXIN.APP_ID);
    }


    private void initEasyTitleBar() {
        EasyTitleBar.init()
                .backIconRes(R.drawable.black)
                .backgroud(ContextCompat.getColor(this, R.color.white))
                .titleSize(18)
                .showLine(true)
                .titleColor(ContextCompat.getColor(this, R.color.common_text_2))
                .titleBarHeight(52);
    }

    private void initCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {

                ActivityBean bean = new ActivityBean();
                Unbinder unbinder = ButterKnife.bind(activity);
                bean.setUnbinder(unbinder);
                activity.getIntent().putExtra("ActivityBean", bean);

                //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
                if (activity.findViewById(R.id.titleBar) != null) { //找到 Toolbar 并且替换 Actionbar
                    ((EasyTitleBar) activity.findViewById(R.id.titleBar)).getBackLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            activity.onBackPressed();
                        }
                    });
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityBean bean = activity.getIntent().getParcelableExtra("ActivityBean");
                bean.getUnbinder().unbind();
            }

        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }
}
