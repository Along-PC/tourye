package com.tourye.zhong.ui.fragment;

import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.AlbumListBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.FindPhotosAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/9/25.
 * <p>
 * introduce:发现---相册
 */

public class FindPhotosFragment extends BaseFragment {
    private SmartRefreshLayout mRefreshLayoutFragmentFindPhotos;
    private ListView mListFragmentFindPhotos;
    private FindPhotosAdapter mFindPhotosAdapter;

    private int count=10;//每次请求多少条数据
    private int offset=0;//从第几条数据开始

    @Override
    public void initView(View view) {
        mRefreshLayoutFragmentFindPhotos = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout_fragment_find_photos);
        mListFragmentFindPhotos = (ListView) view.findViewById(R.id.list_fragment_find_photos);

    }

    @Override
    public void initData() {
        getAlbumList(true,true);

        mRefreshLayoutFragmentFindPhotos.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                offset=0;
                getAlbumList(true,false);
            }
        });
        mRefreshLayoutFragmentFindPhotos.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                offset+=count;
                getAlbumList(false,false);
            }
        });
    }

    /**
     * 获取相册列表数据
     * @param isRefresh 是否刷新
     * @param isFirst  是否第一次执行
     */
    public void getAlbumList(final boolean isRefresh, final boolean isFirst){

        Map<String,String> map=new HashMap<>();
        map.put("count",count+"");
        map.put("offset",offset+"");
        HttpUtils.getInstance().get(Constants.FIND_ALBUM_LIST, map, new HttpCallback<AlbumListBean>() {
            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutFragmentFindPhotos.finishRefresh();
                }else{
                    mRefreshLayoutFragmentFindPhotos.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutFragmentFindPhotos.finishRefresh();
                }else{
                    mRefreshLayoutFragmentFindPhotos.finishLoadMore();
                }
            }
            @Override
            public void onSuccessExecute(AlbumListBean albumListBean) {
                if (albumListBean.getStatus()==0) {
                    List<AlbumListBean.DataBean> data = albumListBean.getData();
                    if (data!=null && data.size()>0) {
                        if (isFirst) {
                            mFindPhotosAdapter = new FindPhotosAdapter(mActivity, data);
                            mListFragmentFindPhotos.setAdapter(mFindPhotosAdapter);
                        }else{
                            if (isRefresh) {
                                mFindPhotosAdapter.setDataBeans(data);
                            }else{
                                mFindPhotosAdapter.addDataBeans(data);
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
        return R.layout.fragment_find_photos;
    }
}
