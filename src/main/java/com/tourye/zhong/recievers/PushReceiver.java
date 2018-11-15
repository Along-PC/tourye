package com.tourye.zhong.recievers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.tac.messaging.TACMessagingReceiver;
import com.tencent.tac.messaging.TACMessagingText;
import com.tencent.tac.messaging.TACMessagingToken;
import com.tencent.tac.messaging.TACNotification;
import com.tencent.tac.messaging.type.NotificationActionType;
import com.tencent.tac.messaging.type.PushChannel;

/**
 * PushReceiver
 * author:along
 * 2018/8/27 下午2:53
 * <p>
 * 描述:用于接收推送信息的广播
 */

public class PushReceiver extends TACMessagingReceiver {

    // 启动 Messaging 服务后，会自动向 Messaging 后台注册，注册完成后会回调此接口。
    @Override
    public void onRegisterResult(Context context, int errorCode, TACMessagingToken token) {

//        Toast.makeText(context, "注册结果返回：" + token, Toast.LENGTH_SHORT).show();
        Log.i("messaging", "MyReceiver::OnRegisterResult : code is " + errorCode + ", token is " + token.getTokenString());
    }

    // 反注册后回调此接口。
    @Override
    public void onUnregisterResult(Context context, int code) {

//        Toast.makeText(context, "取消注册结果返回：" + code, Toast.LENGTH_SHORT).show();
        Log.i("messaging", "MyReceiver::onUnregisterResult : code is " + code);
    }

    // 收到透传消息后回调此接口。
    @Override
    public void onMessageArrived(Context context, TACMessagingText tacMessagingText, PushChannel channel) {

//        Toast.makeText(context, "收到透传消息：" + tacMessagingText, Toast.LENGTH_LONG).show();
        Log.i("messaging", "MyReceiver::OnTextMessage : message is " + tacMessagingText + " pushChannel " + channel);
    }

    // 收到通知栏消息后回调此接口。
    @Override
    public void onNotificationArrived(Context context, TACNotification tacNotification, PushChannel pushChannel) {
//        Toast.makeText(context, "收到通知消息：" + pushChannel, Toast.LENGTH_LONG).show();
        Log.i("messaging", "MyReceiver::onNotificationArrived : notification is " + tacNotification + " pushChannel " + pushChannel);

    }

    // 点击通知栏消息后回调此接口。
    @Override
    public void onNotificationClicked(Context context, TACNotification tacNotification, PushChannel pushChannel) {
//        NotificationActionType notificationActionType = tacNotification.getNotificationActionType();
//        if (notificationActionType != NotificationActionType.ACTION_OPEN_APPLICATION) {
//            Intent intent = new Intent(context, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Intent intentMsg = new Intent(context, PersonMessageActivity.class);
//            Intent[] intents = new Intent[]{intent, intentMsg};
//            context.startActivities(intents);
//            if (BuildConfig.DEBUG) Log.d("PushReceiver", "跳转页面");
//        } else {
//            if (BuildConfig.DEBUG) Log.d("PushReceiver", "打开app");
//        }
        NotificationActionType notificationActionType = tacNotification.getNotificationActionType();

    }

    // 删除通知栏消息后回调此接口
    @Override
    public void onNotificationDeleted(Context context, TACNotification tacNotification, PushChannel pushChannel) {

        Log.i("messaging", "MyReceiver::onNotificationDeleted : notification is " + tacNotification + " pushChannel " + pushChannel);

    }

    // 绑定标签回调
    @Override
    public void onBindTagResult(Context context, int code, String tag) {
        Toast.makeText(context, "绑定标签成功：tag = " + tag, Toast.LENGTH_LONG).show();
        Log.i("messaging", "MyReceiver::onBindTagResult : code is " + code + " tag " + tag);

    }

    // 解绑标签回调
    @Override
    public void onUnbindTagResult(Context context, int code, String tag) {
        Toast.makeText(context, "解绑标签成功：tag = " + tag, Toast.LENGTH_LONG).show();
        Log.i("messaging", "MyReceiver::onUnbindTagResult : code is " + code + " tag " + tag);
    }

}
