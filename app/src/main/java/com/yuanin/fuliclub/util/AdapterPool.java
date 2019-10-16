package com.yuanin.fuliclub.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.adapter.adapter.DelegateAdapter;
import com.yuanin.fuliclub.coursePart.CourseInfoVo;
import com.yuanin.fuliclub.coursePart.CourseItemHolder;
import com.yuanin.fuliclub.homePart.banner.BannerListVo;
import com.yuanin.fuliclub.homePart.banner.BottomBackgroundVo;
import com.yuanin.fuliclub.homePart.banner.CategoryVo;
import com.yuanin.fuliclub.homePart.banner.TypeVo;
import com.yuanin.fuliclub.homePart.itemView.BannerItemView;
import com.yuanin.fuliclub.homePart.itemView.BottomBackgroundItemView;
import com.yuanin.fuliclub.homePart.itemView.CategoryItemView;
import com.yuanin.fuliclub.homePart.itemView.TypeItemView;
import com.yuanin.fuliclub.homePart.itemView.TypeItemView2;
import com.yuanin.fuliclub.learnPart.CourseIntroImageItemHolder;
import com.yuanin.fuliclub.learnPart.CourseKnobbleInfoVo;
import com.yuanin.fuliclub.learnPart.CourseKnobbleItemHolder;
import com.yuanin.fuliclub.learnPart.CourseLearnLogItemHolder;
import com.yuanin.fuliclub.learnPart.LastLearnItemView;
import com.yuanin.fuliclub.learnPart.LastLearnVo;
import com.yuanin.fuliclub.minePart.MyMessageListHolder;
import com.yuanin.fuliclub.minePart.bean.MyMessageVo;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/26 16:09
 */
public class AdapterPool {

    private static AdapterPool adapterPool;

    public static AdapterPool newInstance() {
        if (adapterPool == null) {
            synchronized (AdapterPool.class) {
                if (adapterPool == null) {
                    adapterPool = new AdapterPool();
                }
            }
        }

        return adapterPool;
    }

    public DelegateAdapter.Builder getMyMessageAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(MyMessageVo.class, new MyMessageListHolder(context));
    }

    public DelegateAdapter.Builder getCourseListAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(TypeVo.class, new TypeItemView2(context))
                .bind(CourseInfoVo.class, new CourseItemHolder(context))
                .bind(BottomBackgroundVo.class, new BottomBackgroundItemView(context));
    }

    public DelegateAdapter.Builder getHomeAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(BannerListVo.class, new BannerItemView(context))
                .bind(TypeVo.class, new TypeItemView(context))
                .bind(CategoryVo.class, new CategoryItemView(context))
                .bind(CourseInfoVo.class, new CourseItemHolder(context))
                .bind(BottomBackgroundVo.class, new BottomBackgroundItemView(context))
//                .bind(MatreialSubjectVo.class, new HomeMaterialItemView(context))
                ;
    }

    public DelegateAdapter.Builder getLearnFragmentAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(LastLearnVo.class, new LastLearnItemView(context))
                .bind(TypeVo.class, new TypeItemView2(context))
                .bind(CourseInfoVo.class, new CourseLearnLogItemHolder(context))
                .bind(BottomBackgroundVo.class, new BottomBackgroundItemView(context))
                ;

    }

    public DelegateAdapter.Builder getCourseDetailListFragmentAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(CourseKnobbleInfoVo.class, new CourseKnobbleItemHolder(context))
                .bind(BottomBackgroundVo.class, new BottomBackgroundItemView(context))
                ;

    }

    public DelegateAdapter.Builder getCourseIntroImageListFragmentAdapter(Context context) {
        return new DelegateAdapter.Builder<>()
                .bind(String.class, new CourseIntroImageItemHolder(context))
                ;

    }
}
