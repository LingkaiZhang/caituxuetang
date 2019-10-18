package com.yuanin.fuliclub.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mvvm.base.BaseFragment;
import com.mvvm.http.HttpHelper;
import com.mvvm.http.rx.RxSchedulers;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ApiService;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.loginRegister.LoginActivity;
import com.yuanin.fuliclub.minePart.AboutOursActivity;
import com.yuanin.fuliclub.minePart.FeedBackActivity;
import com.yuanin.fuliclub.minePart.MyAccountActivity;
import com.yuanin.fuliclub.minePart.MyMessageActivity;
import com.yuanin.fuliclub.minePart.OrderDetaailsActivity;
import com.yuanin.fuliclub.minePart.OrderListActivity;
import com.yuanin.fuliclub.network.RxSubscriber;
import com.yuanin.fuliclub.minePart.bean.UserInfoEntity;
import com.yuanin.fuliclub.util.PopupWindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Flowable;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class MineFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;
    @BindView(R.id.ivMessage)
    ImageView ivMessage;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserNo)
    TextView tvUserNo;
    @BindView(R.id.tvGoLogin)
    TextView tvGoLogin;
    @BindView(R.id.rl_mine_header)
    RelativeLayout rlMineHeader;
    @BindView(R.id.rl_mine_account)
    RelativeLayout rlMineAccount;
    @BindView(R.id.rl_mine_order)
    RelativeLayout rlMineOrder;
    @BindView(R.id.rl_mine_news)
    RelativeLayout rlMineNews;
    @BindView(R.id.rl_mine_about)
    RelativeLayout rlMineAbout;
    @BindView(R.id.clMain)
    ConstraintLayout clMain;
    @BindView(R.id.llUserInfo)
    LinearLayout llUserInfo;


    private View popupWindowContactUs;

    protected ApiService apiService;
    private Flowable<ReturnResult<UserInfoEntity>> userInfo;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle state) {

        if (null == apiService) {
            apiService = HttpHelper.getInstance().create(ApiService.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (StaticMembers.IS_NEED_LOGIN) {
            tvGoLogin.setVisibility(View.VISIBLE);
            llUserInfo.setVisibility(View.GONE);
            ivUserHeader.setImageDrawable(getResources().getDrawable(R.mipmap.avatar));
        } else {
            tvGoLogin.setVisibility(View.GONE);
            llUserInfo.setVisibility(View.VISIBLE);
            // 请求用户数据
            requsetData();
        }
    }

    @SuppressLint("CheckResult")
    private void requsetData() {
        userInfo = apiService.getUserInfo();
        userInfo.compose(RxSchedulers.io_main())
                .subscribeWith(new RxSubscriber<ReturnResult<UserInfoEntity>>() {
                    @Override
                    public void onSuccess(ReturnResult<UserInfoEntity> stringReturnResult) {
                        UserInfoEntity userInfoEntity = stringReturnResult.getData();
                        tvUserName.setText(userInfoEntity.getNickName());
                        tvUserNo.setText("学号：" + userInfoEntity.getMobile());
                        setUserHeadImage(userInfoEntity.getProfilePictureLink());

                    }

                    @Override
                    public void onFailure(String msg, int code) {
                    }
                });
    }

    private void setUserHeadImage(String profilePictureLink) {
        RequestOptions options = new RequestOptions()
                .circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .placeholder(R.mipmap.avatar);
        Glide.with(getActivity()).load(profilePictureLink)
                .apply(options)
                .into(ivUserHeader);
    }

    @Override
    protected void onStateRefresh() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        popupWindowContactUs = inflater.inflate(R.layout.popupwindow_contact_us, container, false);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(value = {R.id.tvGoLogin, R.id.rl_mine_about, R.id.ivKefu})
    public void unLoginOnClick(View view) {
        switch (view.getId()) {
            case R.id.tvGoLogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;

            case R.id.rl_mine_about:
                startActivity(new Intent(getActivity(), AboutOursActivity.class));
                break;
            case R.id.ivKefu:
                PopupWindow ContactUsPop = PopupWindowUtils.createContactUsPop(popupWindowContactUs, getActivity());
                ContactUsPop.showAtLocation(clMain, Gravity.CENTER, 0, 0);
                break;
        }
    }

    @OnClick({R.id.iv_user_header, R.id.ivMessage, R.id.rl_mine_account, R.id.rl_mine_order, R.id.rl_mine_news})
    public void onViewClick(View view) {

        if (StaticMembers.IS_NEED_LOGIN) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            switch (view.getId()) {
                case R.id.iv_user_header:
                    break;
                case R.id.ivMessage:
                    startActivity(new Intent(getActivity(), MyMessageActivity.class));
                    break;
                case R.id.rl_mine_account:
                    startActivity(new Intent(getActivity(), MyAccountActivity.class));
                    break;
                case R.id.rl_mine_order:
                    //订单详情
                    startActivity(new Intent(getActivity(), OrderListActivity.class));
                    break;
                case R.id.rl_mine_news:
                    startActivity(new Intent(getActivity(), FeedBackActivity.class));
                    break;
            }

        }


    }
}


