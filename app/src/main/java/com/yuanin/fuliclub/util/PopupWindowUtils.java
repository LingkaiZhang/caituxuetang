package com.yuanin.fuliclub.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.config.StaticMembers;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/29 09:54
 */
public class PopupWindowUtils {

    public static PopupWindow createHKPop(View popView, final Context context) {
        final PopupWindow mPop = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPop.setAnimationStyle(R.style.SignPopupWindowAnimation);
        mPop.setFocusable(false);

        return mPop;
    }
}
