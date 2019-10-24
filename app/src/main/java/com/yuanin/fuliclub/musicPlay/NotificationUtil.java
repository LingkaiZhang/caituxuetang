package com.yuanin.fuliclub.musicPlay;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;

/**
 * <p>应用通知管理类</p>
 *
 * @author lingkai  星期三 2019/10/23
 * @version :
 * @name :
 */
public class NotificationUtil {

    private static NotificationManager notificationManager;

    private static final int NOTIFICATION_MUSIC_ID = 9999;


    /**
     * 显示播放音乐通知栏
     *
     * @param context
     * @param knobbleDetailsInfoVo
     * @param isPlaying
     */
    public static void showMusicNotification(final Context context, final KnobbleDetailsInfoVo knobbleDetailsInfoVo, final boolean isPlaying){
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        Glide.with(context).asBitmap().load(R.mipmap.ic_launcher).apply(options).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                //这个布局的根View的尺寸不能引用dimen文件，要写死
                //设置标准通知数据
                RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_music_play);
                //contentView.setImageViewBitmap(R.id.iv_icon, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
                contentView.setTextViewText(R.id.tv_title, knobbleDetailsInfoVo.getClassHourName());
                contentView.setTextViewText(R.id.tv_info, knobbleDetailsInfoVo.getCourseName());
                int playResId = isPlaying ? R.mipmap.ic_music_notification_pause : R.mipmap.ic_music_notification_play;
                contentView.setImageViewResource(R.id.iv_play, playResId);

                //设置标准通知，点击事件
                PendingIntent playPendingIntent = PendingIntent.getBroadcast(context, Consts.ACTION_PLAY.hashCode(), new Intent(Consts.ACTION_PLAY), PendingIntent.FLAG_UPDATE_CURRENT);
                contentView.setOnClickPendingIntent(R.id.iv_play, playPendingIntent);

                getNotificationManager(context);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setAutoCancel(false)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setCustomContentView(contentView)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("001", "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
//                    channel.enableLights(true);//是否在桌面icon右上角展示小红点
//                    channel.setLightColor(Color.GREEN);//小红点颜色
//                    channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
                    notificationManager.createNotificationChannel(channel);
                    builder.setChannelId("001");
                }

                NotificationUtil.notify(context, NOTIFICATION_MUSIC_ID, builder.build());


            }
        });
    }


    private static void notify(Context context, int id, Notification notification) {
        getNotificationManager(context);

        notificationManager.notify(id, notification);
    }

    private static void getNotificationManager(Context context) {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
    }
}
