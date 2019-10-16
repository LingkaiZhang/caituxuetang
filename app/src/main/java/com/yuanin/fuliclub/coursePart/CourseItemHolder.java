package com.yuanin.fuliclub.coursePart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.util.DensityUtil;
import com.yuanin.fuliclub.util.DisplayUtil;


/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class CourseItemHolder extends AbsItemHolder<CourseInfoVo, CourseItemHolder.ViewHolder> {


    Context mContext;

    public CourseItemHolder(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    public int getLayoutResId() {
        return R.layout.home_remmend_course;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final CourseInfoVo courseListBean) {
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

        holder.ivItemCourseName.setText(courseListBean.getCourseTitle());
        holder.tvItemCourseSlogan.setText(courseListBean.getCourseName());
        holder.tvItemCourseBought.setText(courseListBean.getBoughtNum() + "已购买");
        holder.tvitemCoursePrice.setText("￥" + courseListBean.getCostPrice());

    }


    static class ViewHolder extends AbsHolder {


        ImageView ivItemCourseImage;
        TextView ivItemCourseName;
        TextView tvItemCourseSlogan;
        TextView tvItemCourseBought;
        TextView tvitemCoursePrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivItemCourseImage = itemView.findViewById(R.id.ivItemCourseImage);
            ivItemCourseName = itemView.findViewById(R.id.ivItemCourseName);
            tvItemCourseSlogan = itemView.findViewById(R.id.tvItemCourseSlogan);
            tvItemCourseBought = itemView.findViewById(R.id.tvItemCourseBought);
            tvitemCoursePrice = itemView.findViewById(R.id.tvitemCoursePrice);

        }
    }

}
