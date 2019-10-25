package com.yuanin.fuliclub.minePart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.adapter.adapter.DelegateAdapter;
import com.adapter.adapter.ItemData;
import com.adapter.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.bean.MyCourseListVo;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.minePart.bean.MyOrderListVo;
import com.yuanin.fuliclub.minePart.bean.OrderDetailsInfoVo;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.RefreshHelper;
import com.yuanin.fuliclub.util.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends AbsLifecycleActivity<MyViewModel> implements RefreshHelper.OnHelperRefreshListener, RefreshHelper.OnHelperLoadMoreListener, OnItemClickListener {


    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_empty_order)
    LinearLayout ll_empty_order;

    private WeakReference<OrderListActivity> weakReference;
    private ItemData mItems;
    private RefreshHelper refreshHelper;
    protected DelegateAdapter adapter;
    private Context activity = OrderListActivity.this;

    protected boolean isLoadMore = false;

    protected boolean isRefresh = false;

    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

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

        mViewModel.getMyOrderList("1", "15");

    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(MyRepository.EVENT_KEY_MY_ORDER_LIST, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {

                    loadManager.showSuccess();
                    MyOrderListVo myOrderListVo = (MyOrderListVo) returnResult.getData();
                    if (myOrderListVo != null) {
                        List<OrderDetailsInfoVo> orderDetailsInfoVos = myOrderListVo.getList();

                        setUiData(orderDetailsInfoVos);
                    }

                    ToastUtils.showToast(returnResult.getMessage());
                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }

            }
        });

    }

    protected void setUiData(Collection<?> data) {
        if (!isLoadMore) {
            mItems.clear();
            isLoadMore = false;
            mItems.addAll(data);
            setData();
        } else {
            mItems.addAll(data);
            setMoreData();
        }
    }


    protected void setData() {
        if (mItems.size() > 0){
            refreshLayout.setVisibility(View.VISIBLE);
            ll_empty_order.setVisibility(View.GONE);
        } else {
            refreshLayout.setVisibility(View.GONE);
            ll_empty_order.setVisibility(View.VISIBLE);
        }

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
        return AdapterPool.newInstance().getMyOrderListAdapter(activity).setOnItemClickListener(this).build();
    }


    @Override
    public void onRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
        mViewModel.getMyOrderList("1", "15");
    }

    @Override
    public void onLoadMore(boolean isLoadMore, int pageIndex) {
        this.isLoadMore = isLoadMore;
        mViewModel.getMyOrderList(String.valueOf(pageIndex), "15");
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
            if (object instanceof OrderDetailsInfoVo) {
                Intent intent = new Intent(this, OrderDetaailsActivity.class);
                intent.putExtra("orderDetails", (OrderDetailsInfoVo) object);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
