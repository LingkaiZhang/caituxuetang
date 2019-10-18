package com.yuanin.fuliclub.fragment;

import android.content.Intent;
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
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.AdvanceCourseListActivity;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.coursePart.RookieCourseListActivity;
import com.yuanin.fuliclub.homePart.HomeRepository;
import com.yuanin.fuliclub.homePart.HomeViewModel;
import com.yuanin.fuliclub.homePart.banner.BannerListVo;
import com.yuanin.fuliclub.homePart.banner.BannerVo;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.CategoryVo;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.homePart.itemView.BannerView;
import com.yuanin.fuliclub.learnPart.CourseDetailsActivity;
import com.yuanin.fuliclub.minePart.MyRepository;
import com.yuanin.fuliclub.minePart.bean.UpdateFileCallbackEntity;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class HomeFragment extends BaseListFragment<HomeViewModel> implements OnItemClickListener {

    private int indexpage = 1;
    private int pageSize = 3;

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


        registerSubscriber(HomeRepository.EVENT_KEY_COURSE_LIST_HOMEPAGE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    CourseListVo courseListVo = (CourseListVo) returnResult.getData();
                    List<CourseInfoVo> courseInfoVos = courseListVo.getList();

                    addItems();

                    mItems.add(new TypeVo("小白入门"));

                    mItems.addAll(courseInfoVos);

                    mItems.add(new BottomBackgroundVo());

                    mViewModel.getHomePageCourseListjinjie(1,2,3);

                }else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });

        registerSubscriber(HomeRepository.EVENT_KEY_COURSE_LIST_JINJIE_HOMEPAGE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    CourseListVo courseListVo = (CourseListVo) returnResult.getData();
                    List<CourseInfoVo> courseInfoVos = courseListVo.getList();

                    mItems.add(new TypeVo("进阶学习"));

                    mItems.addAll(courseInfoVos);

                    mItems.add(new BottomBackgroundVo());

                    setData();

                }else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });

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
        //addItems();
        mViewModel.getHomePageCourseList(1, indexpage , pageSize);
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
        mItems.add(new CategoryVo("列表"));

    }

    @Override
    public void onItemClick(View view, int position, Object o) {
        if (o instanceof CourseInfoVo) {
            Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
            intent.putExtra("courseId", String.valueOf(((CourseInfoVo) o).getId()));
            startActivity(intent);
        } else if (o instanceof TypeVo) {
            if (((TypeVo) o).title.equals("小白入门")){
                Intent intent = new Intent(getActivity(), RookieCourseListActivity.class);
                startActivity(intent);
            } else if (((TypeVo) o).title.equals("进阶学习")){
                Intent intent = new Intent(getActivity(), AdvanceCourseListActivity.class);
                startActivity(intent);
            }
        }
    }
}
