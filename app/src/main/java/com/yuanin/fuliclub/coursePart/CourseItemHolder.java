package com.yuanin.fuliclub.coursePart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.util.AppUtils;
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

        holder.ivItemCourseName.setText(courseListBean.getCourseName());
        holder.tvItemCourseSlogan.setText(courseListBean.getCourseTitle());

        SpannableString span = new SpannableString(courseListBean.getBoughtNum() + " 人已买");
        span.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.login_text_color_9b)),span.length()-3, span.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        holder.tvItemCourseBought.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tvItemCourseBought.setHighlightColor(mContext.getResources().getColor(R.color.coursr_item_buyed_num));
        holder.tvItemCourseBought.setText(span);


        holder.tvitemCoursePrice.setText(courseListBean.getRulingPrice() + "");


        //课程类型标记
        holder.llTips.removeAllViews();
        for (int j = 0; j < courseListBean.getCourseLabels().size(); j++) {
            TextView textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setTextColor(mContext.getResources().getColor(R.color.text_orang_ff6500));
            textView.setTextSize(11);
            textView.setBackground(mContext.getDrawable(R.drawable.shape_order_price_bg));
            textView.setPadding( DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 0), DensityUtil.dip2px(mContext, 5), 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(DensityUtil.dip2px(mContext, 8),0,0,0);//4个参数按顺序分别是左上右下
            textView.setLayoutParams(layoutParams);

            textView.setText(courseListBean.getCourseLabels().get(j).trim());

            holder.llTips.addView(textView);
        }

    }


    static class ViewHolder extends AbsHolder {


        ImageView ivItemCourseImage;
        TextView ivItemCourseName;
        TextView tvItemCourseSlogan;
        TextView tvItemCourseBought;
        TextView tvitemCoursePrice;

        LinearLayout llTips;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivItemCourseImage = itemView.findViewById(R.id.ivItemCourseImage);
            ivItemCourseName = itemView.findViewById(R.id.ivItemCourseName);
            tvItemCourseSlogan = itemView.findViewById(R.id.tvItemCourseSlogan);
            tvItemCourseBought = itemView.findViewById(R.id.tvItemCourseBought);
            tvitemCoursePrice = itemView.findViewById(R.id.tvitemCoursePrice);

            llTips = itemView.findViewById(R.id.llTips);

        }
    }

}
