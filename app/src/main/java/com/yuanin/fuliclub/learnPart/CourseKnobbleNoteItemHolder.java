package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.util.DateUtil;


/**
 * @author：tqzhang on 18/6/19 15:00
 */
public class CourseKnobbleNoteItemHolder extends AbsItemHolder<CourseKnobbleNoteInfoVo, CourseKnobbleNoteItemHolder.ViewHolder> {


    public CourseKnobbleNoteItemHolder(Context context) {
        super(context);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_course_knobble_note_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final CourseKnobbleNoteInfoVo noteInfoListBean) {
        holder.tvNoteTitle.setText(noteInfoListBean.getName());
        holder.tvNoteContact.setText(noteInfoListBean.getAnalyzes());
        holder.tvNoteTime.setText(noteInfoListBean.getUpdateTime());
    }


    static class ViewHolder extends AbsHolder {

        TextView tvNoteTitle, tvNoteContact, tvNoteTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNoteTitle = getViewById(R.id.tvNoteTitle);
            tvNoteContact = getViewById(R.id.tvNoteContact);
            tvNoteTime = getViewById(R.id.tvNoteTime);


        }
    }

}
