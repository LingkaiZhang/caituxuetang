package com.yuanin.fuliclub.homePart.itemView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.learnPart.EmptyNoteListVo;


/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/28 15:33
 */
public class EmptyNoteListItemView extends AbsItemHolder<EmptyNoteListVo, EmptyNoteListItemView.ViewHolder> {

    public EmptyNoteListItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.empty_note_list;
    }

    @Override
    public EmptyNoteListItemView.ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull EmptyNoteListItemView.ViewHolder holder, @NonNull EmptyNoteListVo item) {

    }

    static class ViewHolder extends AbsHolder {


        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }
}
