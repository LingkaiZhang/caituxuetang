package com.yuanin.fuliclub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.listener.OnItemClickListener;
import com.mvvm.base.BaseFragment;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.BaseListFragment;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.coursePart.bean.MyCourseListVo;
import com.yuanin.fuliclub.event.OnClickKefuEvent;
import com.yuanin.fuliclub.homePart.HomeRepository;
import com.yuanin.fuliclub.homePart.HomeViewModel;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.learnPart.CourseDetailsActivity;
import com.yuanin.fuliclub.learnPart.LastLearnVo;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.PopupWindowUtils;
import com.yuanin.fuliclub.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * description ： 首页
 * author : lingkai
 * date : 2019/8/20 14:51
 */
public class BuyedFragment extends BaseListFragment<HomeViewModel> implements OnItemClickListener {

    @BindView(R.id.clMain)
    LinearLayout clMain;


    private View popupWindowContactUs;

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

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logoutSuccessEvent(OnClickKefuEvent event) {
        PopupWindow ContactUsPop = PopupWindowUtils.createContactUsPop(popupWindowContactUs, getActivity());
        ContactUsPop.showAtLocation(clMain, Gravity.CENTER, 0, 0);
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(HomeRepository.EVENT_KEY_COURSE_LAST_LEARN, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    LastLearnVo lastLearnVo = (LastLearnVo) returnResult.getData();
                    addItems();
                    mItems.add(0,lastLearnVo);

                    mViewModel.getMyCourseList("1","15");

                }else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });

        registerSubscriber(HomeRepository.EVENT_KEY_COURSE_MY_LIST, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    MyCourseListVo myCourseListVo = (MyCourseListVo) returnResult.getData();
                    List<MyCourseListVo.MyCourseInfoVo> list = myCourseListVo.getList();
                    if (list != null && list.size() > 0){
                        mItems.add(new TypeVo("我的课程"));
                        mItems.addAll(list);
                        mItems.add(new BottomBackgroundVo());
                    }else {
                        mItems.add(new TypeVo("我的课程"));
                    }
                    setData();

                }else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });
    }

    @Override
    protected void onStateRefresh() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        popupWindowContactUs = inflater.inflate(R.layout.popupwindow_contact_us, container, false);
        return super.onCreateView(inflater, container, state);
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

        if (StaticMembers.IS_NEED_LOGIN) {
            addItems();
            mItems.add(0,new LastLearnVo());
            setData();

        } else {
            mViewModel.getLastLearnInfo();
        }


    }

    private void addItems() {
        if (isRefresh) {
            mItems.clear();
        }

//        mItems.add(new TypeVo("我的课程"));
//        mItems.add(new MyCourseListVo());
//        mItems.add(new MyCourseListVo());
//        mItems.add(new BottomBackgroundVo());

        //setData();
    }

    @Override
    public void onItemClick(View view, int position, Object o) {
        if (o instanceof MyCourseListVo.MyCourseInfoVo) {
            Intent intent = new Intent(getActivity(), CourseDetailsActivity.class);
            intent.putExtra("courseId", String.valueOf(((MyCourseListVo.MyCourseInfoVo) o).getId()));
            startActivity(intent);
        }
    }
}
