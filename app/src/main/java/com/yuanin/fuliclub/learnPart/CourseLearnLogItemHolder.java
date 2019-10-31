package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.icu.util.TimeUnit;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.coursePart.bean.MyCourseListVo;
import com.yuanin.fuliclub.util.DateUtil;
import com.yuanin.fuliclub.util.DensityUtil;


/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class CourseLearnLogItemHolder extends AbsItemHolder<MyCourseListVo.MyCourseInfoVo, CourseLearnLogItemHolder.ViewHolder> {


    public CourseLearnLogItemHolder(Context context) {
        super(context);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_learn_log_course;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final MyCourseListVo.MyCourseInfoVo courseListBean) {
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(DensityUtil.dip2px(mContext,8));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions
                .bitmapTransform(roundedCorners)
                .override(300, 300)
                .placeholder(R.mipmap.item_course);

        Glide.with(mContext).load(courseListBean.getSmallPicture())
                .apply(options)
                .into(holder.ivItemCourseImage);

        holder.ivItemCourseName.setText(courseListBean.getCourseName());
        holder.tvItemCourseSlogan.setText(courseListBean.getCourseTitle());

        SpannableString span = new SpannableString("已完成 " + courseListBean.getFinished() + "/" + courseListBean.getSum() );
        span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.coursr_item_buyed_num)),0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        holder.tvItemCourseProgress.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tvItemCourseProgress.setHighlightColor(mContext.getResources().getColor(R.color.color_ffca00));
        holder.tvItemCourseProgress.setText(span);


        String endDate = DateUtil.timeStamp2Date(String.valueOf(courseListBean.getEndDate()/1000), "yyyy-MM-dd");
        String startDate = DateUtil.timeStamp2Date(String.valueOf(courseListBean.getStartDate()/1000), "yyyy-MM-dd");
        holder.tvitemCourseFinishTime.setText("训练营有效期：" + startDate + " - " + endDate);
    }


    static class ViewHolder extends AbsHolder {

        ImageView ivItemCourseImage;
        TextView ivItemCourseName, tvItemCourseSlogan, tvItemCourseProgress, tvitemCourseFinishTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivItemCourseImage = itemView.findViewById(R.id.ivItemCourseImage);
            ivItemCourseName = itemView.findViewById(R.id.ivItemCourseName);
            tvItemCourseSlogan = itemView.findViewById(R.id.tvItemCourseSlogan);
            tvItemCourseProgress = itemView.findViewById(R.id.tvItemCourseProgress);
            tvitemCourseFinishTime = itemView.findViewById(R.id.tvitemCourseFinishTime);

        }
    }

}
