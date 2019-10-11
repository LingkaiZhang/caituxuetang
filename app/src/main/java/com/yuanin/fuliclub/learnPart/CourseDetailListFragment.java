package com.yuanin.fuliclub.learnPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.yuanin.fuliclub.base.BaseListFragment;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.util.AdapterPool;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseDetailListFragment extends BaseListFragment<CourseViewModel> implements OnItemClickListener {

    public static CourseDetailListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CourseDetailListFragment fragment = new CourseDetailListFragment();
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
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getCourseDetailListFragmentAdapter(getActivity())
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

        for (int i = 0; i < 10; i++) {
            mItems.add(new CourseKnobbleInfoVo());
        }
        mItems.add(new BottomBackgroundVo());

        setData();
    }

    @Override
    public void onItemClick(View view, int position, Object o) {
        if (o instanceof CourseKnobbleInfoVo) {
            startActivity(new Intent(getActivity(), CourseKnobbleDetailsActivity.class));
        }
    }
}
