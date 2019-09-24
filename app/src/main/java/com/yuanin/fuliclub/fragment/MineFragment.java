package com.yuanin.fuliclub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.loginRegister.LoginRegisterActivity;
import com.yuanin.fuliclub.view.LeftIconToast;
import com.yuanin.fuliclub.view.bamtoast.btoast.BToast;

import butterknife.BindView;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class  MineFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

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
    }

    @Override
    protected void onStateRefresh() {

    }
}
