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
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                commonWidth, (int) (0.56 * commonWidth));
//        holder.mVideoImage.setLayoutParams(params);
//        holder.mVideoImage.setScaleType(ImageView.ScaleType.FIT_XY);
//        Glide.with(mContext).load(courseListBean.thumb_url).placeholder(R.color.black_e8e8e8).into(holder.mVideoImage);
//        Glide.with(mContext).load(courseListBean.userinfo.avatar).transform(new GlideCircleTransform(mContext)).into(holder.mUserIcon);
//        holder.mUserName.setText(courseListBean.userinfo.sname);
//        holder.mVideoTitle.setText(courseListBean.title);
//        holder.mLookNum.setText(new StringBuilder(String.valueOf(courseListBean.hits)).append("人看过"));
    }


    static class ViewHolder extends AbsHolder {

        private ImageView mVideoImage, mUserIcon;
        private TextView mLookNum, mVideoTitle, mUserName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
