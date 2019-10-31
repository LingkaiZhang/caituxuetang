package com.yuanin.fuliclub.coursePart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.learnPart.CourseKnobbleDetailsActivity;
import com.yuanin.fuliclub.util.BigView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期二 2019/10/22
 * @version :
 * @name :
 */
public class KnobbleDetailsListAdapter extends RecyclerView.Adapter<KnobbleDetailsListAdapter.MyViewHolder> {

    private List<String> childDetailList;
    private Context mContext;

    public KnobbleDetailsListAdapter(Context mContext, List<String> childDetailList) {
        this.mContext = mContext;
        this.childDetailList = childDetailList;

    }

    @NonNull
    @Override
    public KnobbleDetailsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_course_intro_image_list, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KnobbleDetailsListAdapter.MyViewHolder myViewHolder, int i) {

        Glide.with(mContext).load(childDetailList.get(i))
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                        Bitmap bitmap = BitmapFactory.decodeFile(resource.getAbsolutePath(),getBitmapOption(2));
                        // 显示处理好的Bitmap图片
                        myViewHolder.ivIntroImage.setImageBitmap(bitmap);
                    }

                });

    }


    private BitmapFactory.Options getBitmapOption(int inSampleSize) {
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        return options;
    }

    @Override
    public int getItemCount() {
        return childDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIntroImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivIntroImage = itemView.findViewById(R.id.ivIntroImage);
        }
    }
}
