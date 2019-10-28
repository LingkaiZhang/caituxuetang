package com.yuanin.fuliclub.minePart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.util.DateUtil;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/26 16:23
 */
public class MyMessageListHolder extends AbsItemHolder<MyMessageVo, MyMessageListHolder.ViewHolder> {

    public MyMessageListHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_my_message_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull MyMessageVo bookAd) {
        holder.tvMessageTitle.setText(bookAd.getTitle());
        holder.tvMessageTime.setText(DateUtil.timeStamp2Date(String.valueOf(bookAd.getCreateDate()/1000),null));
        holder.tvMessageAbstract.setText(bookAd.getContent());
        if (bookAd.getStatus() == 0 ){
            holder.ivUnReadMessage.setVisibility(View.VISIBLE);
        } else if (bookAd.getStatus() == 1) {
            holder.ivUnReadMessage.setVisibility(View.GONE);
        }

    }


    static class ViewHolder extends AbsHolder {

        private TextView tvMessageTitle, tvMessageTime, tvMessageAbstract;
        private ImageView ivUnReadMessage;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMessageTitle = getViewById(R.id.tvMessageTitle);
            tvMessageTime = getViewById(R.id.tvMessageTime);
            tvMessageAbstract = getViewById(R.id.tvMessageAbstract);
            ivUnReadMessage = getViewById(R.id.ivUnReadMessage);


        }
    }
}
