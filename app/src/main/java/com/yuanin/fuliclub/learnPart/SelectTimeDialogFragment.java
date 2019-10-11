package com.yuanin.fuliclub.learnPart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.BottomSheetDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期五 2019/10/11
 * @version :
 * @name :
 */
public class SelectTimeDialogFragment extends BottomSheetDialogFragment {


    @BindView(R.id.tvCourseName)
    TextView tvCourseName;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    Unbinder unbinder;


    private List<String> data;
    private OnSelectTimeListener onSelectTimeListener;
    //选中的时间
    private String time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_select_time, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();

    }

    private void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        TimeListAdapter timeListAdapter = new TimeListAdapter(getActivity(), data);
        recyclerView.setAdapter(timeListAdapter);
        timeListAdapter.setOnItemClickListener(new TimeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                view.setBackground(getResources().getDrawable(R.drawable.shape_select_time_bg));
                time = data.get(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public static void show(FragmentManager fragmentManager, List data, OnSelectTimeListener onSelectTimeListener) {
        SelectTimeDialogFragment.show(fragmentManager, data, false, onSelectTimeListener);
    }

    public static void show(FragmentManager fragmentManager, List data, boolean isShowDelete, OnSelectTimeListener onSelectTimeListener) {
        SelectTimeDialogFragment selectTimeDialogFragment = new SelectTimeDialogFragment();
        selectTimeDialogFragment.setData(data);
        selectTimeDialogFragment.setListener(onSelectTimeListener);
        selectTimeDialogFragment.show(fragmentManager, "SortDialogFragment");
    }

    private void setListener(OnSelectTimeListener onSelectTimeListener) {
        this.onSelectTimeListener = onSelectTimeListener;
    }

    private void setData(List data) {
        this.data = data;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivClose, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                this.dismiss();
                break;
            case R.id.btnConfirm:
                if (onSelectTimeListener != null){

                    onSelectTimeListener.selectTime(time);
                }
                this.dismiss();
                break;
        }
    }

    public interface OnSelectTimeListener {
        void selectTime(String time);
    }
}
