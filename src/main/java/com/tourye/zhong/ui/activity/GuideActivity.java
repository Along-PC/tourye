package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.utils.SaveUtil;

/**
 * GuideActivity
 * author:along
 * 2018/10/29 下午6:15
 *
 * 描述:引导页面
 */

public class GuideActivity extends BaseActivity {
    private ImageView mImgActivityGuide;
    private TextView mTvActivityGuide;


    @Override
    public void initView() {
        mTvTitle.setText("数据同步");
        mImgActivityGuide = (ImageView) findViewById(R.id.img_activity_guide);
        mTvActivityGuide = (TextView) findViewById(R.id.tv_activity_guide);
        mTvActivityGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void initData() {
        //更新状态，下次不再进入引导页
        SaveUtil.putBoolean("hasLogin", true);

    }

    @Override
    public int getRootView() {
        return R.layout.activity_guide;
    }
}
