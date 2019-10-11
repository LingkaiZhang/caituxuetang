package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuanin.fuliclub.R;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期五 2019/10/11
 * @version :
 * @name :
 */
public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    //时间选中的条目
    private String time;

    public TimeListAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public TimeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_select_time_list, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeListAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tvTime.setText(mList.get(i));
        if (mList.get(i).equals(time)){
            myViewHolder.ivSelectLogo.setVisibility(View.VISIBLE);
            myViewHolder.tvTime.setBackground(mContext.getResources().getDrawable(R.drawable.shape_select_time_bg));
        }else {
            myViewHolder.ivSelectLogo.setVisibility(View.INVISIBLE);
            myViewHolder.tvTime.setBackground(mContext.getResources().getDrawable(R.drawable.shape_unselect_time_bg));
        }
        if (mOnItemClickListener != null) {
            myViewHolder.tvTime.setOnClickListener(v -> {
                mOnItemClickListener.onItemClick(myViewHolder.tvTime, i);
                myViewHolder.ivSelectLogo.setVisibility(View.VISIBLE);
                time = mList.get(i);
                notifyDataSetChanged();
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener =mOnItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;
        ImageView ivSelectLogo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.tvTime);
            ivSelectLogo = itemView.findViewById(R.id.ivSelectLogo);
        }
    }
}
