package com.yuanin.fuliclub.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.BaseListFragment;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.homePart.HomeRepository;
import com.yuanin.fuliclub.homePart.HomeViewModel;
import com.yuanin.fuliclub.homePart.banner.BannerListVo;
import com.yuanin.fuliclub.homePart.banner.BannerVo;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.CategoryVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.homePart.itemView.BannerView;
import com.yuanin.fuliclub.util.AdapterPool;

import java.util.ArrayList;
import java.util.List;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class HomeFragment extends BaseListFragment<HomeViewModel> implements OnItemClickListener {

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(final Bundle state) {
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
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getHomeAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected void onStateRefresh() {

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
        List<BannerVo> data = new ArrayList();
        for (int i = 0; i < 3; i++) {
            BannerVo banner = new BannerVo();
            banner.setImgUrl("http://img0.imgtn.bdimg.com/it/u=1022109268,3759531978&fm=26&gp=0.jpg");
            data.add(banner);
        }

        mItems.add(new BannerListVo(data));
        mItems.add(new CategoryVo("title"));
        mItems.add(new TypeVo("小白入门"));
        mItems.add(new CourseInfoVo());
        mItems.add(new CourseInfoVo());
        mItems.add(new CourseInfoVo());
        mItems.add(new BottomBackgroundVo());

        mItems.add(new TypeVo("进阶学习"));
        mItems.add(new CourseInfoVo());
        mItems.add(new CourseInfoVo());
        mItems.add(new CourseInfoVo());
        mItems.add(new BottomBackgroundVo());

        setData();
    }

    @Override
    public void onItemClick(View view, int position, Object o) {

    }
}
