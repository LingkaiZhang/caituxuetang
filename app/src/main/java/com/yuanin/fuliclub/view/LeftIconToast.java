package com.yuanin.fuliclub.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanin.fuliclub.R;

/**
 * description ： 左边带图片的自定义Toast
 * author : lingkai
 * date : 2019/8/22 17:48
 */
public class LeftIconToast {

    private Toast toast;

    /**
     *
     * @param context
     * @param text
     */
    public LeftIconToast(Context context, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toast_left_icon, null);
        ImageView imageView=(ImageView)view.findViewById(R.id.toast_image);
        imageView.setBackgroundResource(R.mipmap.ic_launcher);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
    }

    public void show(){
        toast.show();
    }
}
