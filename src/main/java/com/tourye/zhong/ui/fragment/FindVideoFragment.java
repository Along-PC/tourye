package com.tourye.zhong.ui.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.VideoListBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.FindVideoAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/9/25.
 * <p>
 * introduce:发现--视频
 */

public class FindVideoFragment extends BaseFragment {
    private SmartRefreshLayout mRefreshLayoutFragmentFindVideo;
    private ListView mListFragmentFindVideo;
    private FindVideoAdapter mFindVideoAdapter;

    private int count=1;//一次请求多少条数据
    private int offset=0;//从第多少条开始

    @Override
    public void initView(View view) {
        mRefreshLayoutFragmentFindVideo = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout_fragment_find_video);
        mListFragmentFindVideo = (ListView) view.findViewById(R.id.list_fragment_find_video);

    }

    private boolean mExcuteResume=true;//是否执行onresume
    private boolean mFirstVisible=true;//是否第一次执行setuservisiblehint
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && !mFirstVisible) {

            mExcuteResume=false;
        }else{
//            GSYVideoManager.releaseAllVideos();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mExcuteResume) {

            mFirstVisible=false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mExcuteResume=true;
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void initData() {

        getVideoList(true,true);

        mRefreshLayoutFragmentFindVideo.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                offset=0;
                getVideoList(true,false);
            }
        });

        mRefreshLayoutFragmentFindVideo.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                offset+=count;
                getVideoList(false,false);
            }
        });

        mListFragmentFindVideo.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int i2) {
                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(FindVideoAdapter.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        if(GSYVideoManager.isFullState(mActivity)) {
                            return;
                        }
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos();
                        mFindVideoAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    /**
     * 获取视频列表数据
     * @param isRefersh 下拉刷新
     * @param isFirst 第一次加载数据
     */
    public void getVideoList(final boolean isRefersh, final boolean isFirst){
        Map<String,String> map=new HashMap<>();
        map.put("count",count+"");
        map.put("offset",offset+"");
        HttpUtils.getInstance().get(Constants.FIND_VIDEO_LIST, map, new HttpCallback<VideoListBean>() {

            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isFirst) {
                    return;
                }
                if (isRefersh) {
                    mRefreshLayoutFragmentFindVideo.finishRefresh();
                }else{
                    mRefreshLayoutFragmentFindVideo.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isFirst) {
                    return;
                }
                if (isRefersh) {
                    mRefreshLayoutFragmentFindVideo.finishRefresh();
                }else{
                    mRefreshLayoutFragmentFindVideo.finishLoadMore();
                }
            }

            @Override
            public void onSuccessExecute(VideoListBean videoListBean) {
                if (videoListBean.getStatus()==0) {
                    List<VideoListBean.DataBean> data = videoListBean.getData();
                    if (data!=null && data.size()>0) {
                        if (isFirst) {
                            mFindVideoAdapter = new FindVideoAdapter(mActivity, data);
                            mListFragmentFindVideo.setAdapter(mFindVideoAdapter);
                        }else{
                            if (isRefersh) {
                                mFindVideoAdapter.setDataBeans(data);
                            }else{
                                mFindVideoAdapter.addDataBeans(data);
                            }
                        }
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
        return R.layout.fragment_find_video;
    }
}
