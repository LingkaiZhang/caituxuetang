package com.yuanin.fuliclub.learnPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.yuanin.fuliclub.base.BaseListFragment;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.util.AdapterPool;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseIntroFragment extends BaseListFragment<CourseViewModel> {

    public static CourseIntroFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CourseIntroFragment fragment = new CourseIntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);
        refreshHelper.setEnableLoadMore(false);
        refreshHelper.setEnableRefresh(false);
        loadManager.showSuccess();
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getCourseIntroImageListFragmentAdapter(getActivity())
                .build();
    }

    @Override
    protected void getRemoteData() {
        //mViewModel.getHomeListData();
        //addItems();
    }



    private void addItems(List<String> courseDetailUrls) {
        if (isRefresh) {
            mItems.clear();
        }

        mItems.addAll(courseDetailUrls);

        setData();
    }


    public void setIntroImageList(List<String> courseDetailUrls) {
        addItems(courseDetailUrls);
    }
}
