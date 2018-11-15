package com.tourye.zhong.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.ActivityListBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.HotGameAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/9/21.
 * <p>
 * introduce:热门赛事
 */

public class HotGameFragment extends BaseFragment {
    private RecyclerView mRecyclerFragmentHotGame;

    @Override
    public void initView(View view) {
        mRecyclerFragmentHotGame = (RecyclerView) view.findViewById(R.id.recycler_fragment_hot_game);
    }

    @Override
    public void initData() {

        Bundle arguments = getArguments();
        String road_id = arguments.getString("road_id");
        Map<String,String> map=new HashMap<>();
        map.put("road_id",road_id);
        HttpUtils.getInstance().get(Constants.ACTIVITY_LIST, map, new HttpCallback<ActivityListBean>() {
            @Override
            public void onSuccessExecute(ActivityListBean activityListBean) {
                if (activityListBean.getStatus()==0) {
                    List<ActivityListBean.DataBean> data = activityListBean.getData();
                    if (data!=null && data.size()>0) {
                        HotGameAdapter hotGameAdapter = new HotGameAdapter(mActivity);
                        hotGameAdapter.setDataBeans(data);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
                        mRecyclerFragmentHotGame.setLayoutManager(linearLayoutManager);
                        mRecyclerFragmentHotGame.setAdapter(hotGameAdapter);
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
        return R.layout.fragment_hot_game;
    }
}
