package com.tourye.zhong.ui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.ui.activity.CreateDynamicActivity;
import com.tourye.zhong.ui.adapter.FindVpAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * FindFragment
 * author:along
 * 2018/9/19 下午2:32
 *
 * 描述:发现页面
 */

public class FindFragment extends BaseFragment{
    private TabLayout mTabFragmentFind;
    private ViewPager mVpFragmentFind;
    private Fragment mFindCommunity;
    private Fragment mFindArticle;
    private Fragment mFindVideo;
    private Fragment mFindPhotos;
    private List<Fragment> mFragments=new ArrayList<>();
    private FragmentManager mFragmentManager;
    private FindVpAdapter mFindVpAdapter;
    private List<String> mTitles=new ArrayList<>();

    @Override
    public void initView(View view) {
        mTvTitle.setText("发现");
        mTabFragmentFind = (TabLayout) view.findViewById(R.id.tab_fragment_find);
        mVpFragmentFind = (ViewPager) view.findViewById(R.id.vp_fragment_find);
        mImgCertain.setVisibility(View.VISIBLE);
        mImgCertain.setBackgroundResource(R.drawable.icon_find_create);

        mImgCertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, CreateDynamicActivity.class));
            }
        });

    }

    /**
     * 设置tablayout下划线长度
     * @param tabs
     * @param leftMargin
     * @param rightMargin
     */
    public void setTabLine(TabLayout tabs,int leftMargin,int rightMargin){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            //通过反射得到tablayout的下划线的Field
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            //得到承载下划线的LinearLayout   //源码可以看到SlidingTabStrip继承得到承载下划线的LinearLayout
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftMargin, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightMargin, Resources.getSystem().getDisplayMetrics());
        //循环设置下划线的左边距和右边距
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void initData() {
        mTitles.clear();
        mFragments.clear();
        mTitles.add("社区");
        mTitles.add("文章");
        mTitles.add("视频");
        mTitles.add("相册");
        mFindCommunity=new FindCommunityFragment();
        mFindArticle=new FindArticleFragment();
        mFindVideo=new FindVideoFragment();
        mFindPhotos=new FindPhotosFragment();
        mFragments.add(mFindCommunity);
        mFragments.add(mFindArticle);
        mFragments.add(mFindVideo);
        mFragments.add(mFindPhotos);
        mFragmentManager = this.getChildFragmentManager();
        mFindVpAdapter = new FindVpAdapter(mFragmentManager);
        mFindVpAdapter.setFragments(mFragments);
        mFindVpAdapter.setTitles(mTitles);
        mVpFragmentFind.setAdapter(mFindVpAdapter);
        mVpFragmentFind.setOffscreenPageLimit(3);
        mTabFragmentFind.setupWithViewPager(mVpFragmentFind);

        setTabLine(mTabFragmentFind,20,20);

        mVpFragmentFind.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position!=0) {
                    mImgCertain.setVisibility(View.GONE);
//                    mImgCertain.setBackgroundResource(R.drawable.icon_find_create);
                }else{
                    mImgCertain.setVisibility(View.VISIBLE);
                    mImgCertain.setBackgroundResource(R.drawable.icon_find_create);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public int getRootView() {
        return R.layout.fragment_find;
    }
}
