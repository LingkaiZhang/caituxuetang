package com.yuanin.fuliclub.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.event.PayUnusualeEvent;
import com.yuanin.fuliclub.event.WechatPayUnusualeEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import top.androidman.SuperButton;

/**
 * description ： PopupWindow弹窗工具类
 * author : lingkai
 * date : 2019/9/29 09:54
 */
public class PopupWindowUtils {

    public static PopupWindow createPayAbnormal(View popView, final Context context){
        final PopupWindow mPop = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPop.setAnimationStyle(R.style.SignPopupWindowAnimation);
        //mPop.setFocusable(false);

        mPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPop.setBackgroundDrawable(dw);

        SuperButton btnCancel = popView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });

        SuperButton btnGoPay = popView.findViewById(R.id.btnGoPay);
        btnGoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转去支付页面
                //发送支付异常消息
                EventBus.getDefault().post(new PayUnusualeEvent());
                mPop.dismiss();
            }
        });

        return mPop;
    }

    public static PopupWindow createClassWaiting(View popView, final Context context){
        final PopupWindow mPop = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPop.setAnimationStyle(R.style.SignPopupWindowAnimation);
        //mPop.setFocusable(false);

        mPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPop.setBackgroundDrawable(dw);

        return mPop;
    }



    public static PopupWindow createClassnfoPop(View popView, final Context context, String className, String classWecahtNo, String QRcode){
        final PopupWindow mPop = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPop.setAnimationStyle(R.style.SignPopupWindowAnimation);
        //mPop.setFocusable(false);

        mPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPop.setBackgroundDrawable(dw);

        TextView tvCopy = popView.findViewById(R.id.tvCopy);
        TextView tvClassWeChatNo = popView.findViewById(R.id.tvClassWeChatNo);
        tvClassWeChatNo.setText(classWecahtNo);

        TextView tvClassName = popView.findViewById(R.id.tvClassName);
        tvClassName.setText(className);

        ImageView rlQRcode = popView.findViewById(R.id.rlQRcode);

        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dip2px(context, 8));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions
                .bitmapTransform(roundedCorners)
                .override(300, 300)
                .placeholder(R.mipmap.item_course);

        Glide.with(context).load(QRcode)
                .apply(options)
                .into(rlQRcode);

        LinearLayout llKefu = popView.findViewById(R.id.llKefu);
        tvCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String KefuWeChatNo = tvClassWeChatNo.getText().toString().trim();
                ClipboardHelper.getInstance(context).copyText("class_wechat",KefuWeChatNo);
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


    public static PopupWindow createContactUsPop(View popView, final Context context, String name, String wxNumber, String wxUrl) {
        final PopupWindow mPop = new PopupWindow(popView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        mPop.setAnimationStyle(R.style.SignPopupWindowAnimation);
        //mPop.setFocusable(false);

        mPop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPop.setBackgroundDrawable(dw);

        TextView tvCopy = popView.findViewById(R.id.tvCopy);
        TextView tvKefuWeChatNo = popView.findViewById(R.id.tvKefuWeChatNo);
        tvKefuWeChatNo.setText(wxNumber);
        ImageView rlQRcode = popView.findViewById(R.id.rlQRcode);


        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dip2px(context, 8));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions
                .bitmapTransform(roundedCorners)
                .override(300, 300)
                .placeholder(R.mipmap.item_course);

        Glide.with(context).load(wxUrl)
                .apply(options)
                .into(rlQRcode);

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

        File destFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath().concat("/caituxutang"),System.currentTimeMillis() + "share.jpg");
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
