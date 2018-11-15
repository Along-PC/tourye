package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tourye.zhong.BuildConfig;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.AlbumPhotosBean;
import com.tourye.zhong.beans.PhotoBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.AlbumDetailAdapter;
import com.tourye.zhong.utils.DensityUtils;
import com.tourye.zhong.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AlbumDetailActivity
 * author:along
 * 2018/9/26 下午12:00
 * <p>
 * 描述:相册详情页面
 */

public class AlbumDetailActivity extends BaseActivity {
    private RecyclerView mRecyclerActivityAlbumDetail;
    private AlbumDetailAdapter mAlbumDetailAdapter;
    private String mData;
    private SmartRefreshLayout mRefreshLayoutActivityAlbumDetail;

    private int count = 10;//每次请求多少数据
    private int offset = 0;//从第多少条数据开始

    private List<PhotoBean> mPhotoBeans = new ArrayList<>();
    private Map<Integer, PhotoBean> mPhotoBeanMap = new HashMap<>();

    private boolean mIsFirst = false;
    private boolean mIsRefresh = false;
    private int size = 0;//当前应该有多少图片
    private int imageIndex=0;//当前第几张图片
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10086:
//                    Set<Integer> integers = mPhotoBeanMap.keySet();
//                    Iterator<Integer> iterator = integers.iterator();
//                    while (iterator.hasNext()) {
//                        Integer next = iterator.next();
//                        mPhotoBeans.add(next, mPhotoBeanMap.get(next.intValue()));
//                    }
                    mPhotoBeans=new ArrayList<>();
                    for (int i = 0; i < imageIndex; i++) {
                        mPhotoBeans.add(mPhotoBeanMap.get(i));
                    }
                    if (mIsFirst) {
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        mRecyclerActivityAlbumDetail.setLayoutManager(staggeredGridLayoutManager);
                        mRecyclerActivityAlbumDetail.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(BaseApplication.mApplicationContext,5)));
                        mAlbumDetailAdapter = new AlbumDetailAdapter(mActivity, mPhotoBeans);
                        mAlbumDetailAdapter.setHasStableIds(true);
                        mRecyclerActivityAlbumDetail.setAdapter(mAlbumDetailAdapter);
                    } else {
                        if (mIsRefresh) {
                            mAlbumDetailAdapter.setPhotoBeans(mPhotoBeans);
                        }else{
                            mAlbumDetailAdapter.addPhotoBeans(mPhotoBeans);

                        }
                    }

                    break;
            }
        }
    };

    @Override
    public void initView() {
        mTvTitle.setText("发现");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRecyclerActivityAlbumDetail = (RecyclerView) findViewById(R.id.recycler_activity_album_detail);
        mRefreshLayoutActivityAlbumDetail = (SmartRefreshLayout) findViewById(R.id.refreshLayout_activity_album_detail);

    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        mData = intent.getStringExtra("data");
        getPhotos(true, true);

        mRefreshLayoutActivityAlbumDetail.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                offset = 0;
                getPhotos(true, false);
            }
        });
        mRefreshLayoutActivityAlbumDetail.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                offset += count;
                getPhotos(false, false);
            }
        });

    }

    /**
     * 获取照片
     *
     * @param isRefresh 刷新操作
     * @param isFirst   第一次请求
     */
    public void getPhotos(final boolean isRefresh, final boolean isFirst) {
        if (TextUtils.isEmpty(mData)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("album_id", mData);
        map.put("count", count + "");
        map.put("offset", offset + "");
        HttpUtils.getInstance().get(Constants.ALBUM_PHOTOS, map, new HttpCallback<AlbumPhotosBean>() {
            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutActivityAlbumDetail.finishRefresh();
                } else {
                    mRefreshLayoutActivityAlbumDetail.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutActivityAlbumDetail.finishRefresh();
                } else {
                    mRefreshLayoutActivityAlbumDetail.finishLoadMore();
                }
            }

            @Override
            public void onSuccessExecute(AlbumPhotosBean albumPhotosBean) {
                if (albumPhotosBean.getStatus() == 0) {
                    AlbumPhotosBean.DataBean data = albumPhotosBean.getData();
                    if (data != null) {
                        List<String> photos = data.getPhotos();
                        if (photos != null && photos.size() > 0) {
                            mIsFirst = isFirst;
                            mIsRefresh = isRefresh;
                            size = photos.size();
                            createData(photos);

                        }
                    }
                }

            }
        });
    }

    public void createData(final List<String> photos) {
        imageIndex=0;
        mPhotoBeanMap=new HashMap<>();
//        mPhotoBeans=new ArrayList<>();
        for (int i = 0; i < photos.size(); i++) {
            //获取图片真正的宽高
            final int finalI = i;
            final int finalI1 = i;
            Glide.with(BaseApplication.mApplicationContext)
                    .asBitmap()
                    .load(photos.get(i))
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            synchronized (String.class) {
                                int height = resource.getHeight();
                                int width = resource.getWidth();

                                int widthPixels = getResources().getDisplayMetrics().widthPixels;
                                widthPixels-=DensityUtils.dp2px(BaseApplication.mApplicationContext,30);
                                int imageWidth = widthPixels / 2;
                                int imageheight = 0;
                                imageheight = (int) ((height * 1.0 / width) * imageWidth);
                                if (BuildConfig.DEBUG)
                                    Log.e("AlbumDetailActivity", "imageheight:" + imageheight);
                                PhotoBean photoBean = new PhotoBean();
                                photoBean.setUrl(photos.get(finalI));
                                photoBean.setHeight(imageheight);
                                photoBean.setWidth(imageWidth);
//                                mPhotoBeans.add(photoBean);
                                mPhotoBeanMap.put(finalI1,photoBean);
                                imageIndex++;
                                if (mPhotoBeanMap.size() == size) {
                                    mHandler.sendEmptyMessage(10086);
                                }
                            }
                        }
                    });
        }
    }


    @Override
    public int getRootView() {
        return R.layout.activity_album_detail;
    }
}
