package com.yuanin.fuliclub.learnPart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.yuanin.fuliclub.base.BaseListFragment;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.coursePart.bean.CourseDetailsVo;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.view.StaticMembers;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseDetailListFragment extends BaseListFragment<CourseViewModel> implements OnItemClickListener {

    private String courseId;

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
        refreshHelper.setEnableRefresh(false);
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

    }


    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(CourseRepository.EVENT_KEY_COURSE_KNOBBLE_LIST, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    List<CourseKnobbleInfoVo> knobbleInfoVoList = (List<CourseKnobbleInfoVo>) returnResult.getData();
                    if (knobbleInfoVoList.size() > 0) {
                        addItems();
                        mItems.addAll(knobbleInfoVoList);
                        mItems.add(new BottomBackgroundVo());

                        setData();
                    }

                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });
    }

    private void addItems() {
        if (isRefresh) {
            mItems.clear();
        }
    }

    @Override
    public void onItemClick(View view, int position, Object o) {
        if (o instanceof CourseKnobbleInfoVo) {
            startActivity(new Intent(getActivity(), CourseKnobbleDetailsActivity.class));
        }
    }

    public void setDatas(String courseId) {
        this.courseId = courseId;
        if (StaticMembers.IS_NEED_LOGIN) {
            mViewModel.getCoursrKonbbleList(courseId);
        } else {
            mViewModel.getCoursrKonbbleListLogin(courseId);
        }
    }
}
