package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.event.OnClickKefuEvent;
import com.yuanin.fuliclub.homePart.banner.BannerListVo;
import com.yuanin.fuliclub.homePart.itemView.BannerItemView;
import com.yuanin.fuliclub.homePart.itemView.BannerView;
import com.yuanin.fuliclub.minePart.MyMessageActivity;
import com.yuanin.fuliclub.util.DensityUtil;
import com.yuanin.fuliclub.util.PopupWindowUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期日 2019/9/29
 * @version :
 * @name :
 */
public class LastLearnItemView extends AbsItemHolder<LastLearnVo, LastLearnItemView.ViewHolder> {

    public LastLearnItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_last_learn;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LastLearnVo item) {

        if (item.getCourseName() == null) {
            holder.ivWrite.setVisibility(View.VISIBLE);
            holder.btnGotoStudy.setVisibility(View.GONE);
        } else {
            holder.ivWrite.setVisibility(View.GONE);
            holder.btnGotoStudy.setVisibility(View.VISIBLE);
            holder.ivItemCourseImage.setVisibility(View.VISIBLE);
            //设置图片圆角角度
            RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dip2px(mContext, 8));
            //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
            RequestOptions options = RequestOptions
                    .bitmapTransform(roundedCorners)
                    .override(300, 300)
                    .placeholder(R.mipmap.item_course);

            Glide.with(mContext).load(item.getSmallPicture())
                    .apply(options)
                    .into(holder.ivItemCourseImage);

            holder.ivItemCourseName.setText(item.getCourseName());
            holder.tvItemCourseSlogan.setText(item.getCourseTitle());
        }


        holder.ivKefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击客服,发送消息
                EventBus.getDefault().post(new OnClickKefuEvent());
            }
        });

        holder.ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyMessageActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.btnGotoStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getIsBuy() == 0) {
                    Intent intent = new Intent(mContext, CourseDetailsActivity.class);
                    intent.putExtra("courseId", String.valueOf(item.getCourseId()));
                    mContext.startActivity(intent);
                } else if (item.getIsBuy() == 1) {
                    Intent intent = new Intent(mContext, CourseDetailsLoginActivity.class);
                    intent.putExtra("courseId", String.valueOf(item.getCourseId()));
                    mContext.startActivity(intent);
                }


            }
        });

    }


    static class ViewHolder extends AbsHolder {

        private ImageView ivMessage;
        private ImageView ivKefu;
        private ImageView ivItemCourseImage;
        private ImageView ivWrite;
        private View unReadMessage;
        private TextView ivItemCourseName;
        private TextView tvItemCourseSlogan;
        private Button btnGotoStudy;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMessage = itemView.findViewById(R.id.ivMessage);
            ivKefu = itemView.findViewById(R.id.ivKefu);
            unReadMessage = itemView.findViewById(R.id.unReadMessage);
            ivItemCourseImage = itemView.findViewById(R.id.ivItemCourseImage);
            ivWrite = itemView.findViewById(R.id.ivWrite);
            ivItemCourseName = itemView.findViewById(R.id.ivItemCourseName);
            tvItemCourseSlogan = itemView.findViewById(R.id.tvItemCourseSlogan);
            btnGotoStudy = itemView.findViewById(R.id.btnGotoStudy);

        }

    }
}