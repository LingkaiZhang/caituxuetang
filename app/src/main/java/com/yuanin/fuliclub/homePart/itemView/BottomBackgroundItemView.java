package com.yuanin.fuliclub.homePart.itemView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;


/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/28 15:33
 */
public class BottomBackgroundItemView extends AbsItemHolder<BottomBackgroundVo, BottomBackgroundItemView.ViewHolder> {

    public BottomBackgroundItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_item_backgroung;
    }

    @Override
    public BottomBackgroundItemView.ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull BottomBackgroundItemView.ViewHolder holder, @NonNull BottomBackgroundVo item) {

    }

    static class ViewHolder extends AbsHolder {


        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
