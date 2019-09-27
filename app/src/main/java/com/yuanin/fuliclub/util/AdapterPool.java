package com.yuanin.fuliclub.util;

import android.content.Context;

import com.adapter.adapter.DelegateAdapter;
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
}
