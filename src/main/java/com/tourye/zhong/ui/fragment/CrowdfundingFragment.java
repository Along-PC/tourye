package com.tourye.zhong.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourye.zhong.BuildConfig;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;

/**
 * CrowdfundingFragment
 * author:along
 * 2018/9/19 下午2:32
 *
 * 描述:众筹页面
 */

public class CrowdfundingFragment extends BaseFragment{
    private TextView mTvTest;


    private static final String TAG = "CrowdfundingFragment";
    @Override
    public void initView(View view) {
        mTvTest = (TextView) view.findViewById(R.id.tv_test);
        mTvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.get(mActivity).clearMemory();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (BuildConfig.DEBUG) Log.e(TAG, "isVisibleToUser:" + isVisibleToUser);
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_crowdfunding;
    }
}
