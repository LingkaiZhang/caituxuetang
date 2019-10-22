package com.yuanin.fuliclub.coursePart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.learnPart.CourseKnobbleDetailsActivity;

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
        Glide.with(mContext).load(childDetailList.get(i)).placeholder(R.mipmap.ic_launcher).into(myViewHolder.ivIntroImage);
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
