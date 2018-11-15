package com.tourye.zhong.ui.fragment;

import android.content.Intent;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tourye.zhong.BuildConfig;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.HomeLinkBean;
import com.tourye.zhong.beans.HomePhotosBean;
import com.tourye.zhong.beans.HomeVideoBean;
import com.tourye.zhong.beans.RoadsListBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.activity.GameListActivity;
import com.tourye.zhong.ui.adapter.HomeLinkAdapter;
import com.tourye.zhong.ui.adapter.MainImageAdapter;
import com.tourye.zhong.ui.adapter.SignupGameAdapter;
import com.tourye.zhong.utils.DensityUtils;
import com.tourye.zhong.views.MeasureListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SignUpFragment
 * author:along
 * 2018/9/19 下午2:30
 * <p>
 * 描述:报名页面
 */

public class SignUpFragment extends BaseFragment {
    private ImageView mImgFragmentSignupTop;//顶部gif背景
    private ImageView mImgFragmentSignupTopLogo;//logo图
    private MeasureListView mListFragmentSignup;//路线列表

    private RecyclerView mRecyclerFragmentSignupLink;//顶部选项

    //视频播放
    private StandardGSYVideoPlayer mVideoFragmentSignup;
    private static final String TAG = "SignUpFragment";

    //图片轮播
    private RecyclerView mRecylceFragmentSignupTop;
    private RecyclerView mRecylceFragmentSignupCenter;
    private RecyclerView mRecylceFragmentSignupBottom;

    private boolean mIsFirstLoop = true;//是否第一次滚动

    Runnable scrollRunnable;

    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    public void initView(View view) {
        mTvTitle.setText("首页");
        mImgFragmentSignupTop = (ImageView) view.findViewById(R.id.img_fragment_signup_top);
        mImgFragmentSignupTopLogo = (ImageView) view.findViewById(R.id.img_fragment_signup_top_logo);
        mListFragmentSignup = (MeasureListView) view.findViewById(R.id.list_fragment_signup);
        mListFragmentSignup.setFocusable(false);
        mRecylceFragmentSignupTop = (RecyclerView) view.findViewById(R.id.recylce_fragment_signup_top);
        mRecylceFragmentSignupCenter = (RecyclerView) view.findViewById(R.id.recylce_fragment_signup_center);
        mRecylceFragmentSignupBottom = (RecyclerView) view.findViewById(R.id.recylce_fragment_signup_bottom);
        mVideoFragmentSignup = (StandardGSYVideoPlayer) view.findViewById(R.id.video_fragment_signup);
        mRecyclerFragmentSignupLink = (RecyclerView) view.findViewById(R.id.recycler_fragment_signup_link);

    }

    @Override
    public void initData() {
        //顶部gif图片背景
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(BaseApplication.mApplicationContext).load(R.drawable.icon_main_road).into(mImgFragmentSignupTop);

        getRoadsList();

        getHomeLink();

        initVideo();

        initBanner();

    }

    /**
     * 获取路线列表数据
     */
    private void getRoadsList() {
        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.ROADS_LIST, map, new HttpCallback<RoadsListBean>() {
            @Override
            public void onSuccessExecute(RoadsListBean roadsListBean) {
                final List<RoadsListBean.DataBean> data = roadsListBean.getData();
                if (data!=null && data.size()>0) {
                    SignupGameAdapter signupGameAdapter = new SignupGameAdapter(mActivity);
                    signupGameAdapter.setDataBeans(data);
                    mListFragmentSignup.setAdapter(signupGameAdapter);
                    mListFragmentSignup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(mActivity, GameListActivity.class);
                            intent.putExtra("data",data.get(i));
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    //获取首页顶部连接数据
    private void getHomeLink() {
        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.HOME_PAGE_LINK, map, new HttpCallback<HomeLinkBean>() {
            @Override
            public void onSuccessExecute(HomeLinkBean homeLinkBean) {
                if (homeLinkBean.getStatus()==0) {
                    List<HomeLinkBean.DataBean> data = homeLinkBean.getData();
                    if (data!=null && data.size()>0) {
                        HomeLinkAdapter homeLinkAdapter = new HomeLinkAdapter(mActivity, data);
                        mRecyclerFragmentSignupLink.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
                        mRecyclerFragmentSignupLink.setLayoutManager(new GridLayoutManager(mContext,4));
                        mRecyclerFragmentSignupLink.setAdapter(homeLinkAdapter);
                    }
                }
            }
        });

    }

    /**
     * 初始化视频播放
     */
    private void initVideo() {

        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.HOME_PAGE_VIDEO, map, new HttpCallback<HomeVideoBean>() {
            @Override
            public void onSuccessExecute(HomeVideoBean homeVideoBean) {
                if (homeVideoBean.getStatus()==0) {
                    List<HomeVideoBean.DataBean> data = homeVideoBean.getData();
                    if (data!=null && data.size()>0) {
                        HomeVideoBean.DataBean dataBean = data.get(0);
                        String url = dataBean.getUrl();

                        //设置封面
                        LayoutInflater from = LayoutInflater.from(mActivity);
                        View inflate = from.inflate(R.layout.image_fragment_signup_video, mVideoFragmentSignup, false);
                        ImageView image=inflate.findViewById(R.id.img_home_video_cover);
                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions.error(R.drawable.holder);
                        requestOptions.placeholder(R.drawable.holder);
                        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getCover()).apply(requestOptions).into(image);
                        mVideoFragmentSignup.setThumbImageView(inflate);
                        mVideoFragmentSignup.getThumbImageViewLayout().setVisibility(View.VISIBLE);
                        //加载视频
                        mVideoFragmentSignup.setUpLazy(url, true, null, null, "这是title");
                        //增加title
                        mVideoFragmentSignup.getTitleTextView().setVisibility(View.GONE);
                        //设置返回键
                        mVideoFragmentSignup.getBackButton().setVisibility(View.GONE);
                        //设置全屏按键功能
                        mVideoFragmentSignup.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mVideoFragmentSignup.startWindowFullscreen(mActivity, false, true);
                            }
                        });
                        //防止错位设置
                        //        mVideoPlayerTest.setPlayTag(TAG);
                        //        mVideoPlayerTest.setPlayPosition(position);
                        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
                        mVideoFragmentSignup.setAutoFullWithSize(true);
                        //音频焦点冲突时是否释放
                        mVideoFragmentSignup.setReleaseWhenLossAudio(false);
                        //全屏动画
                        mVideoFragmentSignup.setShowFullAnimation(true);
                        //小屏时不触摸滑动
                        mVideoFragmentSignup.setIsTouchWiget(false);
                    }
                }
            }
        });

    }

    /**
     * 初始化图片轮播
     */
    private void initBanner() {
        mRecylceFragmentSignupTop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    return true; // 禁止recyclerview滑动
                }
                return false;
            }
        });
        mRecylceFragmentSignupCenter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    return true; // 禁止recyclerview滑动
                }
                return false;
            }
        });
        mRecylceFragmentSignupBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    return true; // 禁止recyclerview滑动
                }
                return false;
            }
        });

        Map<String,String> map=new HashMap<>();
        HttpUtils.getInstance().get(Constants.HOME_PAGE_PHOTOS, map, new HttpCallback<HomePhotosBean>() {
            @Override
            public void onSuccessExecute(HomePhotosBean homePhotosBean) {
                if (homePhotosBean.getStatus()==0) {
                    List<String> data = homePhotosBean.getData();
                    if (data!=null && data.size()>0) {
                        LinearLayoutManager linearLayoutManagerTop = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
                        LinearLayoutManager linearLayoutManagerCenter = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
                        LinearLayoutManager linearLayoutManagerBottom = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
                        mRecylceFragmentSignupTop.setLayoutManager(linearLayoutManagerTop);
                        mRecylceFragmentSignupCenter.setLayoutManager(linearLayoutManagerCenter);
                        mRecylceFragmentSignupBottom.setLayoutManager(linearLayoutManagerBottom);

                        MainImageAdapter mainImageAdapter = new MainImageAdapter(mActivity, data);
                        MainImageAdapter mainImageAdapterCenter = new MainImageAdapter(mActivity, data);
                        mRecylceFragmentSignupTop.setAdapter(mainImageAdapter);
                        mRecylceFragmentSignupCenter.setAdapter(mainImageAdapterCenter);
                        mRecylceFragmentSignupBottom.setAdapter(mainImageAdapter);

                        scrollRunnable = new Runnable() {
                            @Override
                            public void run() {
                                mRecylceFragmentSignupTop.scrollBy(1, 0);
                                if (mIsFirstLoop) {
                                    mRecylceFragmentSignupCenter.scrollBy(DensityUtils.dp2px(BaseApplication.mApplicationContext, 82), 0);
                                    mIsFirstLoop = false;
                                } else {
                                    mRecylceFragmentSignupCenter.scrollBy(1, 0);
                                }
                                mRecylceFragmentSignupBottom.scrollBy(1, 0);
                                mHandler.post(scrollRunnable);
                            }
                        };
                        mHandler.post(scrollRunnable);
                    }
                }
            }
        });

    }

    /**
     * 每次界面可见
     */
    @Override
    public void lazyLoad() {
        super.lazyLoad();
        mHandler.removeCallbacks(scrollRunnable);
        mHandler.post(scrollRunnable);
    }

    /**
     * 当界面不可见
     */
    @Override
    public void onInvisible() {
        super.onInvisible();
        if (mVideoFragmentSignup!=null) {
            mVideoFragmentSignup.onVideoPause();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (BuildConfig.DEBUG) Log.e(TAG, "isVisibleToUser:" + isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVideoFragmentSignup.release();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 点击返回键
     * @return
     */
    @Override
    public boolean onBackPressed() {
        FragmentActivity activity = getActivity();
        if (activity!=null) {
            if (GSYVideoManager.backFromWindowFull(activity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_signup;
    }
}
