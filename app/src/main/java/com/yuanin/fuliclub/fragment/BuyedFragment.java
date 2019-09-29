package com.yuanin.fuliclub.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.BaseListFragment;
import com.yuanin.fuliclub.homePart.HomeViewModel;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.learnPart.LastLearnVo;
import com.yuanin.fuliclub.util.AdapterPool;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class BuyedFragment extends BaseListFragment<HomeViewModel> implements OnItemClickListener {

    public static BuyedFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BuyedFragment fragment = new BuyedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        refreshHelper.setEnableLoadMore(false);
        loadManager.showSuccess();
    }

    @Override
    protected void dataObserver() {
//        registerSubscriber(HomeRepository.EVENT_KEY_HOME, HomeMergeVo.class)
//                .observe(this, homeMergeVo -> {
//                    if (homeMergeVo != null) {
//                        HomeFragment.this.addItems(homeMergeVo);
//                    }
//                });

    }

    @Override
    protected void onStateRefresh() {

    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getLearnFragmentAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected void getRemoteData() {
        //mViewModel.getHomeListData();
        addItems();
    }

    private void addItems() {
        if (isRefresh) {
            mItems.clear();
        }
        mItems.add(new LastLearnVo());
        mItems.add(new TypeVo("我的课程"));

        setData();
    }

    @Override
    public void onItemClick(View view, int position, Object o) {

    }
}
