package com.yuanin.fuliclub.learnPart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.homePart.banner.BannerListVo;
import com.yuanin.fuliclub.homePart.itemView.BannerItemView;
import com.yuanin.fuliclub.homePart.itemView.BannerView;

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

    }


    static class ViewHolder extends AbsHolder {

        private BannerView mBannerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
}