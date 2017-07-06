package com.example.shichengxinag.monitorsystem;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.shichengxinag.monitorsystem.utils.Log;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * Created by shichengxinag on 2017/7/3.
 */

public class MyApp extends Application {

    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent agent = PushAgent.getInstance(this);
        agent.setDebugMode(false);
        handler = new Handler();
        //sdk开启通知声音
//        agent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        // sdk关闭通知声音
//		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // 通知声音由服务端控制
//		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

//		mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
//		mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        Log.d("开始注册");
        agent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Log.d("注册成功");
                Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("注册失败");
                Toast.makeText(getApplicationContext(),"注册失败！",Toast.LENGTH_SHORT).show();
            }
        });
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage uMessage) {
                super.dealWithCustomMessage(context, uMessage);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(uMessage);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(uMessage);
                        }
                        Toast.makeText(context, uMessage.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void dealWithNotificationMessage(Context context, UMessage uMessage) {
                super.dealWithNotificationMessage(context, uMessage);
            }

            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                switch (uMessage.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, uMessage.title);
                        myNotificationView.setTextViewText(R.id.notification_text, uMessage.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, uMessage));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, uMessage));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, uMessage))
                                .setTicker(uMessage.ticker)
                                .setAutoCancel(true);

                        return builder.build();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, uMessage);
                }
            }
        };
        agent.setMessageHandler(messageHandler);
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
        agent.setNotificationClickHandler(notificationClickHandler);

    }
}
