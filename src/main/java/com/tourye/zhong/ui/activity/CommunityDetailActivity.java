package com.tourye.zhong.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.CommonBean;
import com.tourye.zhong.beans.DynamicListBean;
import com.tourye.zhong.beans.ThumbUpBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.CommunityDetailImageAdapter;
import com.tourye.zhong.ui.adapter.CommunityDetailVpAdapter;
import com.tourye.zhong.ui.fragment.BaseCommunityFragment;
import com.tourye.zhong.ui.fragment.CommunityCommentFragment;
import com.tourye.zhong.ui.fragment.FindCommunityFragment;
import com.tourye.zhong.utils.DateFormatUtils;
import com.tourye.zhong.utils.GlideCircleTransform;
import com.tourye.zhong.utils.SaveUtil;
import com.tourye.zhong.views.MeasureGridView;
import com.tourye.zhong.views.dialogs.CommentDialogFragment;
import com.tourye.zhong.views.dialogs.CommentDialogFragment.DialogFragmentDataCallback;
import com.tourye.zhong.views.dialogs.DeleteDynamicDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommunityDetailActivity
 * author:along
 * 2018/10/8 上午10:24
 * <p>
 * 描述:社区详情页面
 */

public class CommunityDetailActivity extends BaseActivity implements View.OnClickListener, DialogFragmentDataCallback {

    private AppBarLayout mAppbarActivityCommunityDetail;//顶部toolbar父控件
    private CollapsingToolbarLayout mCollapsingToolbar;
    private ImageView mImgCommunityDetailHead;//头像
    private TextView mTvCommunityDetailName;//昵称
    private TextView mTvCommunityDetailContent;//动态的文字
    private MeasureGridView mGridCommunityDetail;//动态的图片列表
    private RelativeLayout mRlCommunityDetailShare;//分享模块
    private TextView mTvItemFindCommunityShare;
    private RelativeLayout mRlCommunityDetailComment;//评论模块
    private TextView mTvItemFindCommunityComment;
    private RelativeLayout mRlCommunityDetailThumb;//点赞模块
    private TextView mTvFindCommunityThumb;
    private TabLayout mTabCommunityDetail;//导航栏
    private ViewPager mVpCommunityDetail;
    private TextView mTvCommunityDetailComment;
    private TextView mTvCommunityDetailTime;
    private ImageView mImgCommunityDetailThumb;
    private TextView mTvCommunityDetailDelete;//删除动态


    private static final String TAG = "CommunityDetailActivity";
    private CommunityDetailImageAdapter mCommunityDetailImageAdapter;

    private BaseCommunityFragment mCommunityThumbFragment;//点赞页面
    private BaseCommunityFragment mCommunityCommentFragment;//评论页面
    private List<BaseCommunityFragment> mBaseCommunityFragments = new ArrayList<>();
    private FragmentManager mSupportFragmentManager;
    private CommunityDetailVpAdapter mCommunityDetailVpAdapter;

    private int id;//动态的id
    private TabLayout.Tab mTabComment;
    private int mComment_count;//评论的数量
    private DynamicListBean.DataBean mDataBean;//数据实体


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        mTvTitle.setText("动态详情");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAppbarActivityCommunityDetail = (AppBarLayout) findViewById(R.id.appbar_activity_community_detail);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mImgCommunityDetailHead = (ImageView) findViewById(R.id.img_community_detail_head);
        mTvCommunityDetailName = (TextView) findViewById(R.id.tv_community_detail_name);
        mTvCommunityDetailContent = (TextView) findViewById(R.id.tv_community_detail_content);
        mGridCommunityDetail = (MeasureGridView) findViewById(R.id.grid_community_detail);
        mRlCommunityDetailShare = (RelativeLayout) findViewById(R.id.rl_community_detail_share);
        mTvItemFindCommunityShare = (TextView) findViewById(R.id.tv_item_find_community_share);
        mRlCommunityDetailComment = (RelativeLayout) findViewById(R.id.rl_community_detail_comment);
        mTvItemFindCommunityComment = (TextView) findViewById(R.id.tv_item_find_community_comment);
        mRlCommunityDetailThumb = (RelativeLayout) findViewById(R.id.rl_community_detail_thumb);
        mTvFindCommunityThumb = (TextView) findViewById(R.id.tv_item_find_community_thumb);
        mTabCommunityDetail = (TabLayout) findViewById(R.id.tab_community_detail);
        mVpCommunityDetail = (ViewPager) findViewById(R.id.vp_community_detail);
        mTvCommunityDetailComment = (TextView) findViewById(R.id.tv_community_detail_comment);
        mTvCommunityDetailTime = (TextView) findViewById(R.id.tv_community_detail_time);
        mImgCommunityDetailThumb = (ImageView) findViewById(R.id.img_community_detail_thumb);
        mTvCommunityDetailDelete = (TextView) findViewById(R.id.tv_community_detail_delete);

        mTvCommunityDetailComment.setOnClickListener(this);
        mRlCommunityDetailComment.setOnClickListener(this);
        mTvCommunityDetailDelete.setOnClickListener(this);


    }

    /**
     * 初始化消息图片的数据
     */
    public void initGridData(ArrayList<String> images) {
        mCommunityDetailImageAdapter = new CommunityDetailImageAdapter(mActivity, images);
        int size = images.size();
        switch (size) {
            case 1:
                mGridCommunityDetail.setNumColumns(1);
                break;
            case 2:
            case 3:
            case 4:
                mGridCommunityDetail.setNumColumns(2);
                break;
            default:
                mGridCommunityDetail.setNumColumns(3);
                break;

        }

        mGridCommunityDetail.setAdapter(mCommunityDetailImageAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册event-bus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //接触注册event-bus
        EventBus.getDefault().unregister(this);
    }

    //更新评论数量
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateCommentCount(FindCommunityFragment.CommentCountBean commentCountBean){
        mComment_count=mComment_count+commentCountBean.getUpdate_comment_count();
        mTabComment.setText("评论("+mComment_count+")");
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        mDataBean = (DynamicListBean.DataBean) intent.getSerializableExtra("data");
        if (mDataBean == null) {
            return;
        }
        //头像
        RequestOptions requestOptions = new RequestOptions().transform(new GlideCircleTransform(BaseApplication.mApplicationContext));
        Glide.with(BaseApplication.mApplicationContext).load(mDataBean.getAvatar()).apply(requestOptions).into(mImgCommunityDetailHead);
        //姓名
        mTvCommunityDetailName.setText(mDataBean.getNickname());
        //文字内容
        String content = mDataBean.getContent();
        if (TextUtils.isEmpty(content)) {
            mTvCommunityDetailContent.setVisibility(View.GONE);
        } else {
            mTvCommunityDetailContent.setText(content);
        }
        id = mDataBean.getId();
        //发表时间
        String create_time = mDataBean.getCreate_time();
        if (!TextUtils.isEmpty(create_time)) {
            mTvCommunityDetailTime.setText(DateFormatUtils.format(create_time));
        }
        //点赞状态
        mImgCommunityDetailThumb.setSelected(mDataBean.isAlready_thumb_up());
        mTvFindCommunityThumb.setText(mDataBean.getThumb_up_count() + "");
        //顶部图片数据
        ArrayList<String> images = mDataBean.getImages();
        if (images != null && images.size() > 0) {
            initGridData(mDataBean.getImages());
        }
        //是否是自己的动态，是的话可以删除
        int user_id = mDataBean.getUser_id();
        int userId = SaveUtil.getInt("user_id", -998);
        if (user_id==userId) {
            mTvCommunityDetailDelete.setVisibility(View.VISIBLE);
        }else{
            mTvCommunityDetailDelete.setVisibility(View.GONE);
        }

        mComment_count = mDataBean.getComment_count();//评论数
        initTabData(mDataBean.getComment_count());
        initVp();

        mImgCommunityDetailThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImgCommunityDetailThumb.setOnClickListener(null);
                if (mImgCommunityDetailThumb.isSelected()) {
                    cancel_thumb_up(mDataBean);
                } else {
                    thumb_up(mDataBean);
                }
            }
        });
    }

    //取消点赞
    public void cancel_thumb_up(final DynamicListBean.DataBean dataBean) {
        Map<String, String> map = new HashMap<>();
        map.put("id", dataBean.getId() + "");
        HttpUtils.getInstance().post(Constants.THUMB_UP_CANCEL, map, new HttpCallback<ThumbUpBean>() {
            @Override
            public void onSuccessExecute(ThumbUpBean thumbUpBean) {
                if (thumbUpBean.getStatus() == 0) {
                    if (thumbUpBean.isData()) {
                        mImgCommunityDetailThumb.setSelected(false);
                        dataBean.setThumb_up_count(dataBean.getThumb_up_count() - 1);
                        dataBean.setAlready_thumb_up(false);
                        //发送状态更改事件，刷新列表～～
                        FindCommunityFragment.Msg msg = new FindCommunityFragment.Msg();
                        msg.setIs_already_thumb(false);
                        EventBus.getDefault().post(msg);
                        //拿到网络请求结果，重新添加监听
                        mTvFindCommunityThumb.setText(dataBean.getThumb_up_count() + "");
                        mImgCommunityDetailThumb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mImgCommunityDetailThumb.setOnClickListener(null);
                                if (mImgCommunityDetailThumb.isSelected()) {
                                    cancel_thumb_up(dataBean);
                                } else {
                                    thumb_up(dataBean);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    //点赞
    public void thumb_up(final DynamicListBean.DataBean dataBean) {
        Map<String, String> map = new HashMap<>();
        map.put("id", dataBean.getId() + "");
        HttpUtils.getInstance().post(Constants.THUMB_UP, map, new HttpCallback<ThumbUpBean>() {
            @Override
            public void onSuccessExecute(ThumbUpBean thumbUpBean) {
                if (thumbUpBean.getStatus() == 0) {
                    if (thumbUpBean.isData()) {
                        mImgCommunityDetailThumb.setSelected(true);
                        dataBean.setAlready_thumb_up(true);
                        int thumb_up_count = dataBean.getThumb_up_count();
                        dataBean.setThumb_up_count(thumb_up_count + 1);
                        //发送状态更改事件，刷新列表
                        FindCommunityFragment.Msg msg = new FindCommunityFragment.Msg();
                        msg.setIs_already_thumb(true);
                        EventBus.getDefault().post(msg);
                        //拿到网络请求结果，重新添加监听
                        mTvFindCommunityThumb.setText(dataBean.getThumb_up_count() + "");
                        mImgCommunityDetailThumb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mImgCommunityDetailThumb.setOnClickListener(null);
                                if (mImgCommunityDetailThumb.isSelected()) {
                                    cancel_thumb_up(dataBean);
                                } else {
                                    thumb_up(dataBean);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * 初始化viewpager
     */
    private void initVp() {
        mCommunityCommentFragment = new CommunityCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("data", id);
        mCommunityCommentFragment.setArguments(bundle);
//        mCommunityThumbFragment = new CommunityThumbFragment();
//        mBaseCommunityFragments.add(mCommunityThumbFragment);
        mBaseCommunityFragments.add(mCommunityCommentFragment);
        mSupportFragmentManager = getSupportFragmentManager();
        mCommunityDetailVpAdapter = new CommunityDetailVpAdapter(mSupportFragmentManager);
        mCommunityDetailVpAdapter.setFragments(mBaseCommunityFragments);
        mVpCommunityDetail.setAdapter(mCommunityDetailVpAdapter);
        mVpCommunityDetail.setCurrentItem(0);
        mVpCommunityDetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabCommunityDetail.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化导航栏
     * @param num  评论数
     */
    private void initTabData(int num) {
//        tabLayout.addTab(tabLayout.newTab().setText("赞(300)"));
        mTabComment = mTabCommunityDetail.newTab();
        mTabComment.setText("评论("+num+")");
        mTabCommunityDetail.addTab(mTabComment);
        mTabCommunityDetail.getTabAt(0).select();
        mTabCommunityDetail.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    mVpCommunityDetail.setCurrentItem(0);
                } else {
                    mVpCommunityDetail.setCurrentItem(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public int getRootView() {
        return R.layout.activity_community_detail;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_community_detail_comment:
            case R.id.rl_community_detail_comment:
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
                commentDialogFragment.show(getFragmentManager(), "CommentDialogFragment");
                break;
            case R.id.tv_community_detail_delete:
                DeleteDynamicDialog deleteDynamicDialog = new DeleteDynamicDialog(mActivity);
                deleteDynamicDialog.setOnDeleteInterface(new DeleteDynamicDialog.OnDeleteInterface() {
                    @Override
                    public void delete() {
                        deleteDynamic();
                    }
                });
                deleteDynamicDialog.show();

                break;
        }
    }

    /**
     * 删除动态
     */
    private void deleteDynamic() {
        Map<String,String> map=new HashMap<>();
        map.put("id",mDataBean.getId()+"");
        HttpUtils.getInstance().post(Constants.DELETE_DYNAMIC, map, new HttpCallback<CommonBean>() {
            @Override
            public void onSuccessExecute(CommonBean commonBean) {
                if (commonBean.getStatus()==0) {
                    Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(mActivity, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取输入框文字
     *
     * @return
     */
    @Override
    public String getCommentText() {
        return mTvCommunityDetailComment.getText().toString();
    }

    /**
     * 更新输入框文字
     *
     * @param commentTextTemp
     */
    @Override
    public void setCommentText(String commentTextTemp) {
        mCommunityCommentFragment.addComment(commentTextTemp);
    }

}
