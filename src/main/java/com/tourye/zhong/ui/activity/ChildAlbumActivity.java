package com.tourye.zhong.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseActivity;
import com.tourye.zhong.beans.ChildAlbumBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.ChildAlbumAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChildAlbumActivity
 * author:along
 * 2018/9/26 上午10:44
 *
 * 描述:子相册
 */

public class ChildAlbumActivity extends BaseActivity {
    private SmartRefreshLayout mRefreshLayoutActivityChildAlbum;
    private ListView mListActivityChildAlbum;

    private ChildAlbumAdapter mChildAlbumAdapter;

    private int count=10;//每次请求多少条数据
    private int offset=0;//从第几条开始请求
    private String mData;


    @Override
    public void initView() {
        mTvTitle.setText("相册");
        mImgReturn.setBackgroundResource(R.drawable.icon_return);
        mImgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRefreshLayoutActivityChildAlbum = (SmartRefreshLayout) findViewById(R.id.refreshLayout_activity_child_album);
        mListActivityChildAlbum = (ListView) findViewById(R.id.list_Activity_child_album);

    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        mData = intent.getStringExtra("data");

        getChildAlbum(true,true);

        mRefreshLayoutActivityChildAlbum.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                offset=0;
                getChildAlbum(true,false);
            }
        });
        mRefreshLayoutActivityChildAlbum.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                offset+=count;
                getChildAlbum(false,false);
            }
        });

    }

    public void getChildAlbum(final boolean isRefresh, final boolean isFirst){
        if (TextUtils.isEmpty(mData)) {
            if (isFirst) {
                return;
            }
            if (isRefresh) {
                mRefreshLayoutActivityChildAlbum.finishRefresh();
            }else{
                mRefreshLayoutActivityChildAlbum.finishLoadMore();
            }
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("gallery_id",mData);
        map.put("count",count+"");
        map.put("offset",offset+"");
        HttpUtils.getInstance().get(Constants.CHILD_ALBUM_LIST, map, new HttpCallback<ChildAlbumBean>() {
            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutActivityChildAlbum.finishRefresh();
                }else{
                    mRefreshLayoutActivityChildAlbum.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutActivityChildAlbum.finishRefresh();
                }else{
                    mRefreshLayoutActivityChildAlbum.finishLoadMore();
                }
            }
            @Override
            public void onSuccessExecute(ChildAlbumBean childAlbumBean) {

                if (childAlbumBean.getStatus()==0) {
                    ChildAlbumBean.DataBean data = childAlbumBean.getData();
                    if (data!=null) {
                        List<ChildAlbumBean.DataBean.AlbumsBean> albums = data.getAlbums();
                        if (albums!=null && albums.size()>0) {
                            if (isFirst) {
                                mChildAlbumAdapter = new ChildAlbumAdapter(mActivity, albums);
                                mListActivityChildAlbum.setAdapter(mChildAlbumAdapter);
                            }else{
                                if (isRefresh) {
                                    mChildAlbumAdapter.setAlbumsBeans(albums);
                                }else{
                                    mChildAlbumAdapter.addAlbumsBeans(albums);
                                }
                            }

                        }
                    }
                }

            }
        });


    }

    @Override
    public int getRootView() {
        return R.layout.activity_child_album;
    }
}
