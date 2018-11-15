package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.tourye.zhong.BuildConfig;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.RoadsListBean;
import com.tourye.zhong.ui.adapter.GameListVpAdapter;
import com.tourye.zhong.ui.fragment.HotGameFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * GameListActivity
 * author:along
 * 2018/9/21 下午1:42
 *
 * 描述:赛事列表页面
 */
public class GameListActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mVpActivityGameList;
    private GameListVpAdapter mGameListVpAdapter;
    private android.support.v4.app.FragmentManager mSupportFragmentManager;
    private Fragment mHotGameFragment;
    private List<Fragment> mFragments=new ArrayList<>();
    private static final String TAG = "GameListActivity";
    private AppBarLayout mAppbarActivityGameList;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private Toolbar mToolbarActivityGameList;
    private ImageView mIvBackActivityGameList;
    private ButtonBarLayout mButtonBarLayoutActivityGameList;
    private TextView mTvTitleActivityGameList;
    private ImageView mIvDateActivityGameList;
    boolean isblack = false;//状态栏字体是否是黑色
    boolean iswhite = true;//状态栏字体是否是亮色
    private ImageView mImgGameListTop;
    private TextView mTvGameListTitle;
    private TextView mTvActivityGameListIntro;
    private TextView mTvActivityGameListUnderstand;
    private ImageView mImgGameList;

    @Override
    public void initView() {
        mVpActivityGameList = (ViewPager) findViewById(R.id.vp_activity_game_list);
        mAppbarActivityGameList = (AppBarLayout) findViewById(R.id.appbar_activity_game_list);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mToolbarActivityGameList = (Toolbar) findViewById(R.id.toolbar_activity_game_list);
        mIvBackActivityGameList = (ImageView) findViewById(R.id.iv_back_activity_game_list);
        mButtonBarLayoutActivityGameList = (ButtonBarLayout) findViewById(R.id.buttonBarLayout_activity_game_list);
        mTvTitleActivityGameList = (TextView) findViewById(R.id.tv_title_activity_game_list);
        mIvDateActivityGameList = (ImageView) findViewById(R.id.iv_date_activity_game_list);
        mImgGameListTop = (ImageView) findViewById(R.id.img_game_list_top);
        mTvGameListTitle = (TextView) findViewById(R.id.tv_game_list_title);
        mTvActivityGameListIntro = (TextView) findViewById(R.id.tv_activity_game_list_intro);
        mTvActivityGameListUnderstand = (TextView) findViewById(R.id.tv_activity_game_list_understand);
        mImgGameList = (ImageView) findViewById(R.id.img_game_list);

        mAppbarActivityGameList.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                mImgGameListTop.setTranslationY(verticalOffset);
                //200是appbar的高度
                int abs = Math.abs(verticalOffset);
                if (BuildConfig.DEBUG) Log.e("along", "abs:" + abs);
                int i = DensityUtil.dp2px(220) - mToolbarActivityGameList.getHeight();
                if (BuildConfig.DEBUG) Log.e("along", "固定值i:" + i);
                if (Math.abs(verticalOffset) > DensityUtil.dp2px(220) - mToolbarActivityGameList.getHeight()) {//关闭
                    if (iswhite) {//变黑
                        isblack = true;
                        iswhite = false;
                        //修改状态栏颜色
                        if (Build.VERSION.SDK_INT >= 21) {
                            View decorView = getWindow().getDecorView();
                            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                            decorView.setSystemUiVisibility(option);
                            getWindow().setStatusBarColor(Color.WHITE);
                        }
                        //android6.0以后可以对状态栏文字颜色和图标进行修改
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        }
                    }
                    mButtonBarLayoutActivityGameList.setVisibility(View.VISIBLE);
                    mCollapsingToolbar.setContentScrimResource(R.color.white);
                    mIvBackActivityGameList.setBackgroundResource(R.drawable.icon_return);
                    mIvDateActivityGameList.setBackgroundResource(R.drawable.icon_share);
//                    toolbar.setVisibility(View.VISIBLE);
                } else {  //展开
                    if (isblack) { //变白
                        isblack = false;
                        iswhite = true;
                        if (Build.VERSION.SDK_INT >= 21) {
                            View decorView = getWindow().getDecorView();
                            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                            decorView.setSystemUiVisibility(option);
                            getWindow().setStatusBarColor(Color.TRANSPARENT);
                        }
                        //android6.0以后可以对状态栏文字颜色和图标进行修改
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        }
                    }
                    mButtonBarLayoutActivityGameList.setVisibility(View.INVISIBLE);
                    mCollapsingToolbar.setContentScrimResource(R.color.transparent);
                    mIvBackActivityGameList.setBackgroundResource(R.drawable.icon_return);
                    mIvDateActivityGameList.setBackgroundResource(R.drawable.icon_share);
//                    toolbar.setVisibility(View.INVISIBLE);
                }
            }
        });
        mIvBackActivityGameList.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //获取前一个页面传递过来的数据
        Intent intent = getIntent();
        final RoadsListBean.DataBean dataBean= (RoadsListBean.DataBean) intent.getSerializableExtra("data");
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.holder);
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getImage()).apply(requestOptions).into(mImgGameListTop);
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getLogo()).apply(requestOptions).into(mImgGameList);
        mTvGameListTitle.setText(dataBean.getName());
        mTvTitleActivityGameList.setText(dataBean.getName());
        mTvActivityGameListIntro.setText(dataBean.getAbstractX());
        mTvActivityGameListUnderstand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moreIntent = new Intent(mActivity, GameMoreActivity.class);
                moreIntent.putExtra("data",dataBean.getContent());
                startActivity(moreIntent);
            }
        });

        mSupportFragmentManager = this.getSupportFragmentManager();
        mGameListVpAdapter = new GameListVpAdapter(mSupportFragmentManager);
        mHotGameFragment = new HotGameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("road_id",dataBean.getId()+"");
        mHotGameFragment.setArguments(bundle);
        mFragments.add(mHotGameFragment);
        mGameListVpAdapter.setFragments(mFragments);
        mVpActivityGameList.setAdapter(mGameListVpAdapter);

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.activity_game_list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_activity_game_list:
                finish();
                break;
        }
    }
}
