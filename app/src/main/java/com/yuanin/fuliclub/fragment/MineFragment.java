package com.yuanin.fuliclub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.loginRegister.LoginActivity;
import com.yuanin.fuliclub.loginRegister.LoginRegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    Unbinder unbinder;

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
        button.setOnClickListener(v -> startActivity(new Intent(getActivity(), LoginRegisterActivity.class)));
        button2.setOnClickListener(v -> startActivity(new Intent(getActivity(), LoginActivity.class)));
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
}
