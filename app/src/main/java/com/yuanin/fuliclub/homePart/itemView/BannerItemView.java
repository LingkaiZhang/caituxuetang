package com.yuanin.fuliclub.homePart.itemView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.homePart.WebViewActivity;
import com.yuanin.fuliclub.homePart.banner.BannerListVo;
import com.yuanin.fuliclub.homePart.banner.BannerVo;
import com.yuanin.fuliclub.learnPart.CourseDetailsActivity;
import com.yuanin.fuliclub.learnPart.CourseDetailsLoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang on 18/6/21 18:00
 */
public class BannerItemView extends AbsItemHolder<BannerListVo, BannerItemView.ViewHolder> {

    private XBanner.XBannerAdapter xBannerAdapter;

    public BannerItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.common_banner_view;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull BannerItemView.ViewHolder holder, @NonNull final BannerListVo bannerAdListVo) {

        //刷新数据之后，需要重新设置是否支持自动轮播
        holder.mBannerView.setAutoPlayAble(bannerAdListVo.data.size() > 1);
        holder.mBannerView.setIsClipChildrenMode(true);
        List<BannerVo> data = bannerAdListVo.data;
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            strings.add(data.get(i).getPicture());
        }

        holder.mBannerView.setData(data, null);

        //设置图片圆角角度
        xBannerAdapter = new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //设置图片圆角角度
                RoundedCorners roundedCorners = new RoundedCorners(16);

                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(mContext).load(data.get(position).getPicture()).apply(options).into((ImageView) view);
            }
        };

        holder.mBannerView.loadImage(xBannerAdapter);

        holder.mBannerView.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                if (bannerAdListVo.data.get(position).getType() == 0) {
                    if (bannerAdListVo.data.get(position).getLink() != null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra(ParamsKeys.TYPE, ParamsValues.BANNER);
                        intent.putExtra("banner_url", bannerAdListVo.data.get(position).getLink());
                        mContext.startActivity(intent);
                    }
                } else if (bannerAdListVo.data.get(position).getType() == 1){

                    if (bannerAdListVo.data.get(position).getIsBuy()== 0) {
                        Intent intent = new Intent(mContext, CourseDetailsActivity.class);
                        intent.putExtra("courseId", String.valueOf(bannerAdListVo.data.get(position).getCourseId()));
                        mContext.startActivity(intent);
                    } else if (bannerAdListVo.data.get(position).getIsBuy()== 1) {
                        Intent intent = new Intent(mContext, CourseDetailsLoginActivity.class);
                        intent.putExtra("courseId", String.valueOf(bannerAdListVo.data.get(position).getCourseId()));
                        mContext.startActivity(intent);
                    }
                }


            }
        });



//        holder.mBannerView.delayTime(5).setBannerView(() -> {
//            List<ImageView> imageViewList = new ArrayList<>();
//            for (int i = 0; i < bannerAdListVo.data.size(); i++) {
//                ImageView mImageView = new ImageView(mContext);
//                mImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                Glide.with(mContext).load(bannerAdListVo.data.get(i).getPicture() == null ? bannerAdListVo.data.get(i).getPicture() : bannerAdListVo.data.get(i).getPicture()).centerCrop().into(mImageView);
//                imageViewList.add(mImageView);
//            }
//            return imageViewList;
//        }).build(bannerAdListVo.data);
//
//
//
//        holder.mBannerView.setOnBannerItemClickListener(new BannerView.onBannerItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//                if (bannerAdListVo.data.get(position).getType() == 0) {
//                    if (bannerAdListVo.data.get(position).getLink() != null) {
//                        Intent intent = new Intent(mContext, WebViewActivity.class);
//                        intent.putExtra(ParamsKeys.TYPE, ParamsValues.BANNER);
//                        intent.putExtra("banner_url", bannerAdListVo.data.get(position).getLink());
//                        mContext.startActivity(intent);
//                    }
//                } else if (bannerAdListVo.data.get(position).getType() == 1){
//
//                    if (bannerAdListVo.data.get(position).getIsBuy()== 0) {
//                        Intent intent = new Intent(mContext, CourseDetailsActivity.class);
//                        intent.putExtra("courseId", String.valueOf(bannerAdListVo.data.get(position).getCourseId()));
//                        mContext.startActivity(intent);
//                    } else if (bannerAdListVo.data.get(position).getIsBuy()== 1) {
//                        Intent intent = new Intent(mContext, CourseDetailsLoginActivity.class);
//                        intent.putExtra("courseId", String.valueOf(bannerAdListVo.data.get(position).getCourseId()));
//                        mContext.startActivity(intent);
//                    }
//                }
//
//            }
//        });
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    static class ViewHolder extends AbsHolder {

        private XBanner mBannerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBannerView = getViewById(R.id.banner);
        }

    }

}
