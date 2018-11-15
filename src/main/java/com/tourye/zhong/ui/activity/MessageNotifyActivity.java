package com.tourye.zhong.ui.activity;

import android.view.View;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
/**
 * MessageNotifyActivity
 * author:along
 * 2018/10/10 上午11:10
 *
 * 描述:消息通知页面
 */

public class MessageNotifyActivity extends BaseActivity {

    @Override
    public void initView() {
        mTvTitle.setText("消息通知");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public int getRootView() {
        return R.layout.activity_message_notify;
    }
}
