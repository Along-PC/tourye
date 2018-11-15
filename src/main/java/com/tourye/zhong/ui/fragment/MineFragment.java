package com.tourye.zhong.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.UserAccountBean;
import com.tourye.zhong.beans.UserBasicBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.activity.SettingActivity;
import com.tourye.zhong.ui.activity.UpdateHeadActivity;
import com.tourye.zhong.utils.GlideCircleTransform;
import com.tourye.zhong.views.dialogs.ModifyNameDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * MineFragment
 * author:along
 * 2018/9/19 下午2:33
 *
 * 描述:我的页面
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mImgFragmentMineMessage;
    private ImageView mImgFragmentMineHead;
    private TextView mTvFragmentMineName;
    private TextView mTvFragmentMineId;
    private TextView mTvFragmentMineGoldCount;//金币
    private TextView mTvFragmentMineGold;
    private TextView mTvFragmentMineOverageCount;//余额
    private TextView mTvFragmentMineOverage;
    private TextView mTvFragmentMineVoucherCount;//代金劵
    private TextView mTvFragmentMineVoucher;
    private RelativeLayout mRlFragmentMineCrowdfunding;
    private RelativeLayout mRlFragmentMineOrder;
    private RelativeLayout mRlFragmentMineSupport;
    private RelativeLayout mRlFragmentMineRecord;
    private LinearLayout mLlFragmentMinePoster;
    private RelativeLayout mRlFragmentMineDynamic;
    private LinearLayout mLlFragmentMineRebate;
    private LinearLayout mLlFragmentMineSetting;
    private ImageView mImgFragmentMineModify;



    @Override
    public void initView(View view) {
        mTvTitle.setText("个人中心");
        mImgCertain.setVisibility(View.VISIBLE);
        mImgCertain.setBackgroundResource(R.drawable.icon_find_comment);

        mImgFragmentMineMessage = (ImageView) view.findViewById(R.id.img_fragment_mine_message);
        mImgFragmentMineHead = (ImageView) view.findViewById(R.id.img_fragment_mine_head);
        mTvFragmentMineName = (TextView) view.findViewById(R.id.tv_fragment_mine_name);
        mTvFragmentMineId = (TextView) view.findViewById(R.id.tv_fragment_mine_id);
        mTvFragmentMineGoldCount = (TextView) view.findViewById(R.id.tv_fragment_mine_gold_count);
        mTvFragmentMineGold = (TextView) view.findViewById(R.id.tv_fragment_mine_gold);
        mTvFragmentMineOverageCount = (TextView) view.findViewById(R.id.tv_fragment_mine_overage_count);
        mTvFragmentMineOverage = (TextView) view.findViewById(R.id.tv_fragment_mine_overage);
        mTvFragmentMineVoucherCount = (TextView) view.findViewById(R.id.tv_fragment_mine_voucher_count);
        mTvFragmentMineVoucher = (TextView) view.findViewById(R.id.tv_fragment_mine_voucher);
        mRlFragmentMineCrowdfunding = (RelativeLayout) view.findViewById(R.id.rl_fragment_mine_crowdfunding);
        mRlFragmentMineOrder = (RelativeLayout) view.findViewById(R.id.rl_fragment_mine_order);
        mRlFragmentMineSupport = (RelativeLayout) view.findViewById(R.id.rl_fragment_mine_support);
        mRlFragmentMineRecord = (RelativeLayout) view.findViewById(R.id.rl_fragment_mine_record);
        mLlFragmentMinePoster = (LinearLayout) view.findViewById(R.id.ll_fragment_mine_poster);
        mRlFragmentMineDynamic = (RelativeLayout) view.findViewById(R.id.rl_fragment_mine_dynamic);
        mLlFragmentMineRebate = (LinearLayout) view.findViewById(R.id.ll_fragment_mine_rebate);
        mLlFragmentMineSetting = (LinearLayout) view.findViewById(R.id.ll_fragment_mine_setting);
        mImgFragmentMineModify = (ImageView) view.findViewById(R.id.img_fragment_mine_modify);

        mImgFragmentMineHead.setOnClickListener(this);
        mImgFragmentMineModify.setOnClickListener(this);
        mLlFragmentMineSetting.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getPersonInfo();
        getUserAccount();
    }

    /**
     * 获取用户账户信息
     */
    public void getUserAccount(){
        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.USER_ACCOUNT, map, new HttpCallback<UserAccountBean>() {
            @Override
            public void onSuccessExecute(UserAccountBean userAccountBean) {
                if (userAccountBean.getStatus()==0) {
                    UserAccountBean.DataBean data = userAccountBean.getData();
                    if (data!=null) {
                        mTvFragmentMineGoldCount.setText(data.getCoin()+"");
                        mTvFragmentMineOverageCount.setText(data.getFund()+"");
                        mTvFragmentMineVoucherCount.setText(data.getCoupon()+"");
                    }
                }
            }
        });
    }

    /**
     * 获取用户基本信息
     */
    public void getPersonInfo(){
        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.USER_BASIC_INFO, map, new HttpCallback<UserBasicBean>() {
            @Override
            public void onSuccessExecute(UserBasicBean userBasicBean) {
                if (userBasicBean.getStatus()==0) {
                    UserBasicBean.DataBean data = userBasicBean.getData();
                    if (data!=null) {
                        mTvFragmentMineName.setText(data.getNickname());
                        RequestOptions requestOptions = new RequestOptions().transform(new GlideCircleTransform(BaseApplication.mApplicationContext));
                        Glide.with(BaseApplication.mApplicationContext).load(data.getAvatar()).apply(requestOptions).into(mImgFragmentMineHead);
                        mTvFragmentMineId.setText("id:"+data.getId());
                    }
                }
            }
        });
    }

    @Override
    public boolean isNeedTitle() {
        return true;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_fragment_mine_head:
                startActivity(new Intent(mActivity, UpdateHeadActivity.class));
                break;
            case R.id.img_fragment_mine_modify:
                ModifyNameDialog modifyNameDialog = new ModifyNameDialog(mActivity);
                modifyNameDialog.show();
                modifyNameDialog.setRenameCallback(new ModifyNameDialog.RenameCallback() {
                    @Override
                    public void rename(String name) {
                        mTvFragmentMineName.setText(name);
                    }
                });
                break;
            case R.id.ll_fragment_mine_setting:
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
        }

    }
}
