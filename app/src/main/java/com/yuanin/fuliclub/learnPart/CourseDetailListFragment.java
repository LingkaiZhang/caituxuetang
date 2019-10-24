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
import com.yuanin.fuliclub.base.ReturnResult2;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.coursePart.bean.CourseDetailsVo;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.loginRegister.LoginActivity;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.ToastUtils;


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
    private boolean courseIsBuy;

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

        registerSubscriber(CourseRepository.EVENT_KEY_COURSE_KNOBBLE_LIST, ReturnResult2.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    List<CourseKnobbleInfoVo> knobbleInfoVoList = (List<CourseKnobbleInfoVo>) returnResult.getData();


                    if (knobbleInfoVoList.size() > 0) {

                        for (int i = 0; i < knobbleInfoVoList.size(); i++) {
                            knobbleInfoVoList.get(i).setBuyed(courseIsBuy);
                        }

                        addItems();
                        mItems.clear();

                        mItems.addAll(knobbleInfoVoList);
                        mItems.add(new BottomBackgroundVo());

                        setData();
                    }

                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });

        registerSubscriber(CourseRepository.EVENT_KEY_COURSE_KNOBBLE_LIST_LOGIN, ReturnResult2.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    List<CourseKnobbleInfoVo> knobbleInfoVoList = (List<CourseKnobbleInfoVo>) returnResult.getData();
                    if (knobbleInfoVoList.size() > 0) {

                        for (int i = 0; i < knobbleInfoVoList.size(); i++) {
                            knobbleInfoVoList.get(i).setBuyed(courseIsBuy);
                        }

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

            if (StaticMembers.IS_NEED_LOGIN) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            } else {
                if (((CourseKnobbleInfoVo) o).getTryOut() == 0) {
                    mViewModel.updateLearnLastLog(courseId);

                    Intent intent = new Intent(getActivity(), CourseKnobbleDetailsActivity.class);
                    intent.putExtra("courseKnobbleId", ((CourseKnobbleInfoVo) o).getId());
                    startActivity(intent);
                } else if(courseIsBuy){
                    Intent intent = new Intent(getActivity(), CourseKnobbleDetailsActivity.class);
                    intent.putExtra("courseKnobbleId", ((CourseKnobbleInfoVo) o).getId());
                    startActivity(intent);
                } else {
                    ToastUtils.showToast("您还没有权限阅读该内容。");
                }
            }


        }
    }

    public void setDatas(String courseId, boolean courseIsBuy) {
        this.courseId = courseId;
        this.courseIsBuy = courseIsBuy;
        setQuestData();
    }

    public void setQuestData(){

        if (StaticMembers.IS_NEED_LOGIN) {
            mViewModel.getCoursrKonbbleList(courseId);
        } else {
            mViewModel.getCoursrKonbbleListLogin(courseId);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }
}
