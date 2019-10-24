package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;


/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class CourseKnobbleItemHolder extends AbsItemHolder<CourseKnobbleInfoVo, CourseKnobbleItemHolder.ViewHolder> {


    public CourseKnobbleItemHolder(Context context) {
        super(context);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_course_knobble_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final CourseKnobbleInfoVo courseListBean) {

        holder.tvKnobbleName.setText(courseListBean.getClassHourName());
        if (courseListBean.getTryOut() == 0) {

            if (courseListBean.isBuyed()) {
                holder.tvTryLearn.setVisibility(View.GONE);
            } else {
                holder.tvTryLearn.setVisibility(View.VISIBLE);
            }
            holder.ivPlay.setVisibility(View.VISIBLE);
            holder.ivLock.setVisibility(View.GONE);
        } else if (courseListBean.getTryOut() == 1) {
            holder.tvTryLearn.setVisibility(View.GONE);
            holder.ivPlay.setVisibility(View.GONE);
            holder.ivLock.setVisibility(View.VISIBLE);
        }

    }


    static class ViewHolder extends AbsHolder {

        private ImageView ivPlay, ivLock;
        private TextView tvKnobbleName, tvTryLearn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKnobbleName = itemView.findViewById(R.id.tvKnobbleName);
            tvTryLearn = itemView.findViewById(R.id.tvTryLearn);
            ivPlay = itemView.findViewById(R.id.ivPlay);
            ivLock = itemView.findViewById(R.id.ivLock);

        }
    }

}
