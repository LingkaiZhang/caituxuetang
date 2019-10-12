package com.yuanin.fuliclub.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yuanin.fuliclub.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>悬浮播放控制器</p>
 *
 * @author lingkai  星期六 2019/10/12
 * @version :
 * @name :
 */
public class MusicPlayControlView extends LinearLayout {
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.iv_play_control)
    ImageView ivPlayControl;
    @BindView(R.id.tvKnobbleName)
    TextView tvKnobbleName;
    @BindView(R.id.tvCourseName)
    TextView tvCourseName;
    @BindView(R.id.ll_play_small_container)
    LinearLayout llPlaySmallContainer;

    public MusicPlayControlView(Context context) {
        super(context);
        initView(context);
    }

    public MusicPlayControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MusicPlayControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.music_paly_control, this, true);
    }

    public void setCourseName(String courseName) {
        if (!TextUtils.isEmpty(courseName)) {
            tvCourseName.setText(courseName);
        }
    }

    public void setKnobbleName(String knobbleName) {
        if (!TextUtils.isEmpty(knobbleName)) {
            tvKnobbleName.setText(knobbleName);
        }
    }

    public void setStartTime(String startTime) {
        if (!TextUtils.isEmpty(startTime)) {
            tvStartTime.setText(startTime);
        }
    }

    public void setEndTime(String endTime) {
        if (!TextUtils.isEmpty(endTime)) {
            tvEndTime.setText(endTime);
        }
    }

    public void setSbProgress(int Progress) {
        sbProgress.setProgress(Progress);
    }

    public void setIvPlayControlListener(OnClickListener onClickListener) {
        ivPlayControl.setOnClickListener(onClickListener);
    }

}
