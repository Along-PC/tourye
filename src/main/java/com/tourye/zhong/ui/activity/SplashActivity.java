package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.utils.SaveUtil;

/**
 * SplashActivity
 * author:along
 * 2018/9/18 下午2:02
 *
 * 描述:启动页
 */


public class SplashActivity extends BaseActivity {

    private ConstraintLayout mRootActivitySplash;


    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10086:
                    String authorization = SaveUtil.getString("Authorization", "");
                    if (!TextUtils.isEmpty(authorization)) {
                        Intent registerIntent = new Intent(mActivity, MainActivity.class);
                        startActivity(registerIntent);
                        //必须手动释放图片内存
                        finish();
                    }else{
                        startActivity(new Intent(mActivity,LoginActivity.class));
                        //必须手动释放图片内存
                        finish();
                    }

                    break;
            }
        }
    };

    @Override
    public void initView() {
        mRootActivitySplash = (ConstraintLayout) findViewById(R.id.root_activity_splash);

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRootActivitySplash!=null) {
            mRootActivitySplash.setBackground(null);
        }
    }

    @Override
    public void initData() {

        mHandler.sendEmptyMessageDelayed(10086,200);

    }

    @Override
    public int getRootView() {
        return R.layout.activity_splash;
    }
}
