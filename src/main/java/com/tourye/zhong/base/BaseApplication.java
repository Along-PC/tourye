package com.tourye.zhong.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by meridian on 2018/2/2.
 */

public class BaseApplication extends MultiDexApplication {


    public static Context mApplicationContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //日志工具
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(L) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("wake")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        mApplicationContext = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        //mob_share_sdk三方分享
        MobSDK.init(this);

    }


}
