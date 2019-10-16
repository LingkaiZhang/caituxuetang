package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.yuanin.fuliclub.R;


/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class CourseIntroImageItemHolder extends AbsItemHolder<String, CourseIntroImageItemHolder.ViewHolder> {


    public CourseIntroImageItemHolder(Context context) {
        super(context);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_course_intro_image_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final String string) {

        Glide.with(mContext).load(string).placeholder(R.mipmap.ic_launcher).into(holder.ivIntroImage);
//        Glide.with(mContext).load(courseListBean.userinfo.avatar).transform(new GlideCircleTransform(mContext)).into(holder.mUserIcon);
    }


    static class ViewHolder extends AbsHolder {

        private ImageView ivIntroImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIntroImage = itemView.findViewById(R.id.ivIntroImage);
        }
    }

}
