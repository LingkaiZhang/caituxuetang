package com.yuanin.fuliclub.coursePart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.ItemData;
import com.adapter.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.homePart.HomeRepository;
import com.yuanin.fuliclub.homePart.HomeViewModel;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.CourseListVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.learnPart.CourseDetailsActivity;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.RefreshHelper;
import com.yuanin.fuliclub.util.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvanceCourseListActivity extends AbsLifecycleActivity<HomeViewModel> implements RefreshHelper.OnHelperRefreshListener, RefreshHelper.OnHelperLoadMoreListener, OnItemClickListener {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private WeakReference<AdvanceCourseListActivity> weakReference;
    private ItemData mItems;
    private RefreshHelper refreshHelper;
    protected DelegateAdapter adapter;
    private Context activity = AdvanceCourseListActivity.this;

    protected boolean isLoadMore = false;

    protected boolean isRefresh = false;
    private ArrayList<AdvanceCourseListActivity> myMessageVos;



    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_message;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        mItems = new ItemData();
        refreshHelper = new RefreshHelper.Builder()
                .setRefreshLayout(refreshLayout)
                .setOnRefreshListener(this)
                .setOnLoadMoreListener(this)
                .build();
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addOnScrollListener(onScrollListener);

        //模拟数据
        /*myMessageVos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            myMessageVos.add(new MyMessageVo());
        }
        setUiData(myMessageVos);*/
        mViewModel.getCourseListjinjie(2,1,5);


    }

    protected void setUiData(Collection<?> data) {
        if (!isLoadMore) {
            mItems.clear();
            isLoadMore = false;
            mItems.add(new TypeVo("进阶学习"));
            mItems.addAll(data);
            mItems.add(new BottomBackgroundVo());
            setData();
        } else {
            mItems.addAll(mItems.size() - 1,data);
            setMoreData();
        }
    }

    @Override
    protected void dataObserver() {
        registerSubscriber(HomeRepository.EVENT_KEY_COURSE_LIST_JINJIE, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    CourseListVo courseListVo = (CourseListVo) returnResult.getData();
                    List<CourseInfoVo> courseInfoVos = courseListVo.getList();

                    setUiData(courseInfoVos);

                }else {
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

    protected void setData() {
        adapter.setDatas(mItems);
        adapter.notifyDataSetChanged();
        if (isRefresh) {
            refreshHelper.refreshComplete();
        }
    }

    protected void setMoreData() {
        adapter.notifyDataSetChanged();
        if (isLoadMore) {
            refreshHelper.loadMoreComplete();
        }
        isLoadMore = false;
    }

    private RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    private DelegateAdapter createAdapter() {
        return AdapterPool.newInstance().getCourseListAdapter(activity).setOnItemClickListener(this).build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
        mViewModel.getCourseListjinjie(2,1,3);
    }

    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        this.isLoadMore = isLoadMore;
        mViewModel.getCourseListjinjie(2,pageIndex, 10);
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (activity != null) {
                    Glide.with(activity).resumeRequests();
                }
            } else {
                if (activity != null) {
                    Glide.with(activity).pauseRequests();
                }
            }
        }
    };

    @Override
    public void onItemClick(View view, int position, Object object) {
        if (object != null) {
            if (object instanceof CourseInfoVo) {
                Intent intent = new Intent(this, CourseDetailsActivity.class);
                intent.putExtra("courseId", String.valueOf(((CourseInfoVo) object).getId()));
                startActivity(intent);
            }
        }
    }
}
