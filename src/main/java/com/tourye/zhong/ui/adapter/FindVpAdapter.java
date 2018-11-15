package com.tourye.zhong.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/25.
 * <p>
 * introduce:发现模块适配器
 */

public class FindVpAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments=new ArrayList<>();
    private List<String> mTitles=new ArrayList<>();

    public FindVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public void setTitles(List<String> titles) {
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
