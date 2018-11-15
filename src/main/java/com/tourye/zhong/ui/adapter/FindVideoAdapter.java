package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.tourye.zhong.R;
import com.tourye.zhong.beans.VideoListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/26.
 * <p>
 * introduce:发现--视频播放适配器
 */

public class FindVideoAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public static final String TAG = "FindVideoAdapter";

    private List<VideoListBean.DataBean> mDataBeans=new ArrayList<>();

    public FindVideoAdapter(Context context,List<VideoListBean.DataBean> dataBeans) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mDataBeans=dataBeans;
    }

    public void setDataBeans(List<VideoListBean.DataBean> dataBeans) {
        mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    public void addDataBeans(List<VideoListBean.DataBean> dataBeans) {
        mDataBeans.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FindVideoHolder findVideoHolder=null;
        if (view==null) {
            view=mLayoutInflater.inflate(R.layout.item_fragment_find_video,viewGroup,false);
            findVideoHolder=new FindVideoHolder(view);
            view.setTag(findVideoHolder);
        }else{
            findVideoHolder= (FindVideoHolder) view.getTag();
        }
        VideoListBean.DataBean dataBean = mDataBeans.get(i);
        findVideoHolder.mTvItemFragmentFindVideo.setText(dataBean.getTitle());
        initVideo(findVideoHolder.mVideoItemFragmentFindVideo,i,dataBean.getFrame_url());
        return view;
    }

    /**
     * 初始化视频播放
     */
    private void initVideo(final StandardGSYVideoPlayer standardGSYVideoPlayer,int position,String url) {
//        String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";

        standardGSYVideoPlayer.setUpLazy(url, true, null, null, "这是title");
        //增加title
        standardGSYVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        standardGSYVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        standardGSYVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardGSYVideoPlayer.startWindowFullscreen(mContext, false, true);
            }
        });
//        防止错位设置
        standardGSYVideoPlayer.setPlayTag(TAG);
        standardGSYVideoPlayer.setPlayPosition(position);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        standardGSYVideoPlayer.setAutoFullWithSize(true);
        //音频焦点冲突时是否释放
        standardGSYVideoPlayer.setReleaseWhenLossAudio(false);
        //全屏动画
        standardGSYVideoPlayer.setShowFullAnimation(true);
        //小屏时不触摸滑动
        standardGSYVideoPlayer.setIsTouchWiget(false);
    }

    public class FindVideoHolder{
        private StandardGSYVideoPlayer mVideoItemFragmentFindVideo;
        private TextView mTvItemFragmentFindVideo;

        public FindVideoHolder(View view){
            mVideoItemFragmentFindVideo = (StandardGSYVideoPlayer) view.findViewById(R.id.video_item_fragment_find_video);
            mTvItemFragmentFindVideo = (TextView) view.findViewById(R.id.tv_item_fragment_find_video);

        }
    }
}
