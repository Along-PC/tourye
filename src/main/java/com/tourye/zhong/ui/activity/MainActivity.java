package com.tourye.zhong.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.UserBasicBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.MainVpAdapter;
import com.tourye.zhong.ui.fragment.CrowdfundingFragment;
import com.tourye.zhong.ui.fragment.EquipmentFragment;
import com.tourye.zhong.ui.fragment.FindFragment;
import com.tourye.zhong.ui.fragment.MineFragment;
import com.tourye.zhong.ui.fragment.SignUpFragment;
import com.tourye.zhong.utils.SaveUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mVpActivityMain;
    private RelativeLayout mRlActivityMainSignup;//报名
    private RelativeLayout mRlActivityMainCrowdfunding;//众筹
    private RelativeLayout mRlActivityMainFind;//发现
    private RelativeLayout mRlActivityMainEquipment;//装备
    private RelativeLayout mRlActivityMainMine;//我的
    private TextView mTvActivityMainSignup;//报名页面导航
    private TextView mTvActivityMainCrowdfunding;//众筹页面导航
    private TextView mTvActivityMainFind;//发现页面导航
    private TextView mTvActivityMainEquipment;//装备页面导航
    private TextView mTvActivityMainMine;//我的页面导航
    private TextView mTvActivityCurrent;//当前页面导航

    private List<BaseFragment> mFragments=new ArrayList<>();
    private BaseFragment mFragmentSignup;//报名页面
    private BaseFragment mFragmentCrowdfunding;//众筹页面
    private BaseFragment mFragmentFind;//发现页面
    private BaseFragment mFragmentEquipment;//装备页面
    private BaseFragment mFragmentMine;//我的页面

    private FragmentManager mSupportFragmentManager;
    private MainVpAdapter mMainVpAdapter;


    @Override
    public void initView() {
        mVpActivityMain = (ViewPager) findViewById(R.id.vp_activity_main);

        mRlActivityMainSignup = (RelativeLayout) findViewById(R.id.rl_activity_main_signup);
        mRlActivityMainCrowdfunding = (RelativeLayout) findViewById(R.id.rl_activity_main_crowdfunding);
        mRlActivityMainFind = (RelativeLayout) findViewById(R.id.rl_activity_main_find);
        mRlActivityMainEquipment = (RelativeLayout) findViewById(R.id.rl_activity_main_equipment);
        mRlActivityMainMine = (RelativeLayout) findViewById(R.id.rl_activity_main_mine);

        mTvActivityMainSignup = (TextView) findViewById(R.id.tv_activity_main_signup);
        mTvActivityMainCrowdfunding = (TextView) findViewById(R.id.tv_activity_main_crowdfunding);
        mTvActivityMainFind = (TextView) findViewById(R.id.tv_activity_main_find);
        mTvActivityMainEquipment = (TextView) findViewById(R.id.tv_activity_main_equipment);
        mTvActivityMainMine = (TextView) findViewById(R.id.tv_activity_main_mine);

        mTvActivityMainSignup.setSelected(true);
        mTvActivityCurrent=mTvActivityMainSignup;

        mRlActivityMainSignup.setOnClickListener(this);
        mRlActivityMainCrowdfunding.setOnClickListener(this);
        mRlActivityMainFind.setOnClickListener(this);
        mRlActivityMainEquipment.setOnClickListener(this);
        mRlActivityMainMine.setOnClickListener(this);


    }

    @Override
    public void initData() {

        mFragmentSignup=new SignUpFragment();
        mFragmentCrowdfunding=new CrowdfundingFragment();
        mFragmentFind=new FindFragment();
        mFragmentEquipment=new EquipmentFragment();
        mFragmentMine=new MineFragment();
        mSupportFragmentManager = this.getSupportFragmentManager();
        mFragments.add(mFragmentSignup);
        mFragments.add(mFragmentCrowdfunding);
        mFragments.add(mFragmentFind);
        mFragments.add(mFragmentEquipment);
        mFragments.add(mFragmentMine);
        mMainVpAdapter = new MainVpAdapter(mSupportFragmentManager);
        mMainVpAdapter.setFragments(mFragments);
        mVpActivityMain.setAdapter(mMainVpAdapter);
        mVpActivityMain.setCurrentItem(0);
        mVpActivityMain.setOffscreenPageLimit(4);

        mVpActivityMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvActivityCurrent.setSelected(false);
                        mTvActivityMainSignup.setSelected(true);
                        mTvActivityCurrent=mTvActivityMainSignup;
                        break;
                    case 1:
                        mTvActivityCurrent.setSelected(false);
                        mTvActivityMainCrowdfunding.setSelected(true);
                        mTvActivityCurrent=mTvActivityMainCrowdfunding;
                        break;
                    case 2:
                        mTvActivityCurrent.setSelected(false);
                        mTvActivityMainFind.setSelected(true);
                        mTvActivityCurrent=mTvActivityMainFind;
                        break;
                    case 3:
                        mTvActivityCurrent.setSelected(false);
                        mTvActivityMainEquipment.setSelected(true);
                        mTvActivityCurrent=mTvActivityMainEquipment;
                        break;
                    case 4:
                        mTvActivityCurrent.setSelected(false);
                        mTvActivityMainMine.setSelected(true);
                        mTvActivityCurrent=mTvActivityMainMine;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getUserBasic();
    }

    /**
     * 获取用户基本信息
     */
    private void getUserBasic() {
        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.USER_BASIC_INFO, map, new HttpCallback<UserBasicBean>() {
            @Override
            public void onSuccessExecute(UserBasicBean userBasicBean) {
                if (userBasicBean.getStatus()==0) {
                    UserBasicBean.DataBean data = userBasicBean.getData();
                    if (data!=null) {
                        SaveUtil.putInt("user_id",data.getId());
                        SaveUtil.putString("user_name",data.getNickname());
                    }
                }
            }
        });
    }


    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mFragmentSignup.onBackPressed()) {
            return;
        }
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_activity_main_signup:
                mTvActivityCurrent.setSelected(false);
                mTvActivityMainSignup.setSelected(true);
                mTvActivityCurrent=mTvActivityMainSignup;
                mVpActivityMain.setCurrentItem(0,false);
                break;
            case R.id.rl_activity_main_crowdfunding:
                mTvActivityCurrent.setSelected(false);
                mTvActivityMainCrowdfunding.setSelected(true);
                mTvActivityCurrent=mTvActivityMainCrowdfunding;
                mVpActivityMain.setCurrentItem(1,false);
                break;
            case R.id.rl_activity_main_find:
                mTvActivityCurrent.setSelected(false);
                mTvActivityMainFind.setSelected(true);
                mTvActivityCurrent=mTvActivityMainFind;
                mVpActivityMain.setCurrentItem(2,false);
                break;
            case R.id.rl_activity_main_equipment:
                mTvActivityCurrent.setSelected(false);
                mTvActivityMainEquipment.setSelected(true);
                mTvActivityCurrent=mTvActivityMainEquipment;
                mVpActivityMain.setCurrentItem(3,false);
                break;
            case R.id.rl_activity_main_mine:
                mTvActivityCurrent.setSelected(false);
                mTvActivityMainMine.setSelected(true);
                mTvActivityCurrent=mTvActivityMainMine;
                mVpActivityMain.setCurrentItem(4,false);
                break;
        }

    }


}
