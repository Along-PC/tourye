package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tourye.zhong.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MainVpAdapter
 * author:along
 * 2018/9/19 下午3:33
 *
 * 描述:主页viewpager适配器
 */


public class MainVpAdapter extends FragmentPagerAdapter{

    private Context mContext;
    private List<BaseFragment> mFragments=new ArrayList<>();

    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<BaseFragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
