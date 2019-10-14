package com.yuanin.fuliclub.fragment;

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

import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.loginRegister.LoginActivity;
import com.yuanin.fuliclub.minePart.AboutOursActivity;
import com.yuanin.fuliclub.minePart.FeedBackActivity;
import com.yuanin.fuliclub.minePart.MyAccountActivity;
import com.yuanin.fuliclub.minePart.MyMessageActivity;
import com.yuanin.fuliclub.minePart.OrderDetaailsActivity;
import com.yuanin.fuliclub.util.PopupWindowUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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


    }

    @Override
    public void onResume() {
        super.onResume();
        if (StaticMembers.IS_NEED_LOGIN) {
            tvGoLogin.setVisibility(View.VISIBLE);
            llUserInfo.setVisibility(View.GONE);
            ivUserHeader.setImageDrawable(getResources().getDrawable(R.mipmap.avatar));
        }else {
            tvGoLogin.setVisibility(View.GONE);
            llUserInfo.setVisibility(View.VISIBLE);
            // 请求用户数据

        }
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

    @OnClick({R.id.ivKefu, R.id.iv_user_header, R.id.ivMessage, R.id.tvGoLogin, R.id.rl_mine_account, R.id.rl_mine_order, R.id.rl_mine_news, R.id.rl_mine_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                break;
            case R.id.ivMessage:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.tvGoLogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.rl_mine_account:
                startActivity(new Intent(getActivity(), MyAccountActivity.class));
                break;
            case R.id.rl_mine_order:
                //订单详情
                startActivity(new Intent(getActivity(), OrderDetaailsActivity.class));
                break;
            case R.id.rl_mine_news:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
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

}
