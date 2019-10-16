package com.yuanin.fuliclub.homePart.itemView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.adapter.holder.AbsHolder;
import com.adapter.holder.AbsItemHolder;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.adapter.HomeCategoryAdapter;
import com.yuanin.fuliclub.coursePart.AdvanceCourseListActivity;
import com.yuanin.fuliclub.coursePart.RookieCourseListActivity;
import com.yuanin.fuliclub.homePart.banner.CatagoryInfoVo;
import com.yuanin.fuliclub.homePart.banner.CategoryVo;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.view.bamtoast.etoast.EToast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：tqzhang on 18/6/20 13:41
 */
public class CategoryItemView extends AbsItemHolder<CategoryVo, CategoryItemView.ViewHolder> {
    private String[] tvNames;
    private int[] tvIcons;
    private List<CatagoryInfoVo> list = new ArrayList<>();
    private HomeCategoryAdapter adapter;

    public CategoryItemView(Context context) {
        super(context);
        tvNames = new String[]{"小白", "进阶", "直播", "FM"};
        tvIcons = new int[]{R.drawable.xiaobai, R.drawable.jinjie, R.drawable.zhibo, R.drawable.fm};
        initData();
        adapter = new HomeCategoryAdapter(mContext, list, 0);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_category;
    }

    @Override
    public ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull CategoryVo categoryTop) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener((v, position) -> {
            if (list.get(position).title.equals("小白")) {
                Intent intent = new Intent(mContext, RookieCourseListActivity.class);
                mContext.startActivity(intent);

            } else if (list.get(position).title.equals("进阶")) {
                Intent intent = new Intent(mContext, AdvanceCourseListActivity.class);
                mContext.startActivity(intent);

            } else if (list.get(position).title.equals("直播")) {
                EToast.makeText(mContext, "敬请期待...", false).show();

            } else if (list.get(position).title.equals("FM")) {
                EToast.makeText(mContext, "敬请期待...", false).show();
            }
        });
    }

    @Override
    protected void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        RecyclerView.LayoutParams clp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (clp instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) clp).setFullSpan(true);
        }
    }

    public static class ViewHolder extends AbsHolder {

        private RecyclerView recyclerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = getViewById(R.id.recycler_view);
        }
    }

    private void initData() {
        list.clear();
        for (int i = 0; i < tvNames.length; i++) {
            list.add(new CatagoryInfoVo(tvNames[i], tvIcons[i]));
        }
    }

}
