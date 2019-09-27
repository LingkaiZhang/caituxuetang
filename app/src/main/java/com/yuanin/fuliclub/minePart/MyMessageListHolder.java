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
//        holder.bookName.setText(bookAd.title);
//        holder.pressName.setText(bookAd.publishing_name);
//        holder.price.setText("￥" + bookAd.price);
//        Glide.with(mContext).load(bookAd.img.l.url).placeholder(R.color.black_e8e8e8).into(holder.bookImg);
    }


    static class ViewHolder extends AbsHolder {

        private TextView bookName, pressName, price;
        private ImageView bookImg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            pressName = getViewById(R.id.press_name);
//            bookName = getViewById(R.id.book_name);
//            price = getViewById(R.id.price);
//            bookImg = getViewById(R.id.book_img);

        }
    }
}
