package com.yuanin.fuliclub.minePart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;
import com.yuanin.fuliclub.minePart.bean.OrderDetailsInfoVo;
import com.yuanin.fuliclub.util.DensityUtil;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/26 16:23
 */
public class MyOrderListHolder extends AbsItemHolder<OrderDetailsInfoVo, MyOrderListHolder.ViewHolder> {

    public MyOrderListHolder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_my_order_list;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull OrderDetailsInfoVo orderDetailsInfoVo) {
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(DensityUtil.dip2px(mContext,8));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions
                .bitmapTransform(roundedCorners)
                .override(300, 300)
                .placeholder(R.mipmap.item_course);

        Glide.with(mContext).load(orderDetailsInfoVo.getImageurl())
                .apply(options)
                .into(holder.ivCourseImage);

        holder.tvCourseName.setText(orderDetailsInfoVo.getProductName());
        holder.tvOrderNo.setText("订单编号：" + orderDetailsInfoVo.getOrderNo());
        holder.tvOrderPrice.setText("已支付：" + orderDetailsInfoVo.getPrice() + "元");
        holder.tvOrderStatus.setText("购买成功");

    }


    static class ViewHolder extends AbsHolder {

        TextView tvOrderNo, tvCourseName, tvOrderPrice, tvOrderStatus;
        ImageView ivCourseImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderNo = itemView.findViewById(R.id.tvOrderNo);
            ivCourseImage = itemView.findViewById(R.id.ivCourseImage);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);

        }
    }
}
