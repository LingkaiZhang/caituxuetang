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
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.event.NoteSaveSuccess;
import com.yuanin.fuliclub.event.PaySuccessEvent;
import com.yuanin.fuliclub.homePart.WebViewActivity;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.loginRegister.LoginActivity;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseDetailNoteListFragment extends BaseListFragment<CourseViewModel> implements OnItemClickListener {

    private String courseId;

    public static CourseDetailNoteListFragment newInstance() {

        Bundle args = new Bundle();

        CourseDetailNoteListFragment fragment = new CourseDetailNoteListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(Bundle state) {
        super.initView(state);

        EventBus.getDefault().register(this);

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
        return AdapterPool.newInstance().getCourseDetailNoteListFragmentAdapter(getActivity())
                .setOnItemClickListener(this)
                .build();
    }

    @Override
    protected void getRemoteData() {

    }


    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(CourseRepository.EVENT_KEY_COURSE_NOTE_LIST, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    List<CourseKnobbleNoteInfoVo> courseKnobbleNoteInfoVos = (List<CourseKnobbleNoteInfoVo>) returnResult.getData();
                    if (courseKnobbleNoteInfoVos.size() > 0) {


                        addItems();
                        mItems.clear();

                        mItems.addAll(courseKnobbleNoteInfoVos);
                        //mItems.add(new BottomBackgroundVo());

                    } else {
                        addItems();
                        mItems.clear();
                        mItems.add(new EmptyNoteListVo());
                    }

                    setData();

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
        if (o instanceof CourseKnobbleNoteInfoVo) {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra(ParamsKeys.TYPE, ParamsValues.NOTE);
            intent.putExtra(ParamsKeys.KNOBBLE_MLID, String.valueOf(((CourseKnobbleNoteInfoVo) o).getChildId()));
            startActivity(intent);
        }
    }

    public void setDatas(String courseId) {
        this.courseId = courseId;
        setQuestData();
    }

    public void setQuestData(){

        mViewModel.getCoursrKonbbleNoteList(courseId);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mViewModel != null) {
                setQuestData();
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setNoteSaveSuccess(NoteSaveSuccess noteSaveSuccess){
       setQuestData();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
