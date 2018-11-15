package com.tourye.zhong.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tourye.zhong.R;
import com.tourye.zhong.ui.adapter.CommunityThumbAdapter;

import java.util.ArrayList;

/**
 * Created by longlongren on 2018/10/15.
 * <p>
 * introduce:社区详情点赞页面
 */

public class CommunityThumbFragment extends BaseCommunityFragment {
    private RecyclerView mListFragmentCommunityThumb;

    private CommunityThumbAdapter mCommunityThumbAdapter;
    @Override
    public void initView(View view) {
        mListFragmentCommunityThumb = (RecyclerView) view.findViewById(R.id.list_fragment_community_thumb);
    }

    /**
     * 初始化点赞数据
     */
    private void initThumbData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("along");
        strings.add("along");
        strings.add("along");
        mCommunityThumbAdapter = new CommunityThumbAdapter(mContext, strings);
        mListFragmentCommunityThumb.setLayoutManager(new LinearLayoutManager(mActivity));
        mListFragmentCommunityThumb.setAdapter(mCommunityThumbAdapter);
    }

    @Override
    public void initData() {
        initThumbData();
    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_community_thumb;
    }
}
