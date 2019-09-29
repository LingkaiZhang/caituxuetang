package com.yuanin.fuliclub.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.config.StaticMembers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;

/**
 * description ： PopupWindow弹窗工具类
 * author : lingkai
 * date : 2019/9/29 09:54
 */
public class PopupWindowUtils {

    public static PopupWindow createContactUsPop(View popView, final Context context) {
        final PopupWindow mPop = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPop.setAnimationStyle(R.style.SignPopupWindowAnimation);
        //mPop.setFocusable(false);

        mPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPop.setBackgroundDrawable(dw);

        TextView tvCopy = popView.findViewById(R.id.tvCopy);
        TextView tvKefuWeChatNo = popView.findViewById(R.id.tvKefuWeChatNo);
        ImageView rlQRcode = popView.findViewById(R.id.rlQRcode);
        LinearLayout llKefu = popView.findViewById(R.id.llKefu);
        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String KefuWeChatNo = tvKefuWeChatNo.getText().toString().trim();
                ClipboardHelper.getInstance(context).copyText("kefu_wechat",KefuWeChatNo);
                ToastUtils.showToast("已复制");
            }
        });
        rlQRcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                shareImage(llKefu,context);

                return false;
            }
        });
        return mPop;
    }

    private static void shareImage(View view,Context context) {
        Bitmap bitmap= ViewUtil.createBitmap(view);

        //一定要sd卡目陆，不然其他的应用不能读取

        File destFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat("/caituxutang"),"share.jpg");
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        BitmapUtil.saveToFile(bitmap,destFile);
        ToastUtils.showToast("图片保存成功");
        sendMediaChanged(destFile,context);
        //ShareUtil.shareImage(getActivity(),destFile.getAbsolutePath());
    }

    private static void sendMediaChanged(File destFile,Context context) {
        //try {
        //不用在插入图片了，因为我们已经保存到了图库中了
        //MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
        //        destFile.getAbsolutePath(), destFile.getName(), null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(destFile);
        intent.setData(uri);
        context.sendBroadcast(intent);
        //} catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //}

    }
}
