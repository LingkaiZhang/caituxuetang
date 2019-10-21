package com.yuanin.fuliclub.coursePart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.BottomSheetDialogFragment;
import com.yuanin.fuliclub.coursePart.bean.CourseStartTimeListVo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期一 2019/10/21
 * @version :
 * @name :
 */
public class SelectPlaySpeedDialogFragment extends BottomSheetDialogFragment {

    @BindView(R.id.tvSpeed_1)
    TextView tvSpeed1;
    @BindView(R.id.tvSpeed_2)
    TextView tvSpeed2;
    @BindView(R.id.tvSpeed_3)
    TextView tvSpeed3;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    Unbinder unbinder;
    private OnSelectPlaySpeedListener onSelectPlaySpeedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_select_play_speed, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public static void show(FragmentManager fragmentManager, OnSelectPlaySpeedListener onSelectPlaySpeedListener) {
        SelectPlaySpeedDialogFragment speedDialogFragment = new SelectPlaySpeedDialogFragment();
        speedDialogFragment.setListener(onSelectPlaySpeedListener);
        speedDialogFragment.show(fragmentManager, "SortDialogFragment");
    }

    private void setListener(OnSelectPlaySpeedListener onSelectPlaySpeedListener) {
        this.onSelectPlaySpeedListener = onSelectPlaySpeedListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvSpeed_1, R.id.tvSpeed_2, R.id.tvSpeed_3, R.id.tvCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSpeed_1:
                if (onSelectPlaySpeedListener != null) {
                    onSelectPlaySpeedListener.selectPlaySpeed("1倍");
                }
                this.dismiss();
                break;
            case R.id.tvSpeed_2:
                if (onSelectPlaySpeedListener != null) {
                    onSelectPlaySpeedListener.selectPlaySpeed("1.5倍");
                }
                this.dismiss();
                break;
            case R.id.tvSpeed_3:
                if (onSelectPlaySpeedListener != null) {
                    onSelectPlaySpeedListener.selectPlaySpeed("2倍");
                }
                this.dismiss();
                break;
            case R.id.tvCancel:
                this.dismiss();
                break;
        }
    }

    public interface OnSelectPlaySpeedListener {
        void selectPlaySpeed(String speed);
    }
}
