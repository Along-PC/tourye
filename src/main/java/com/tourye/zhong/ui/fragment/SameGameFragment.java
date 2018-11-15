package com.tourye.zhong.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.ui.adapter.SameRoadAdapter;

import java.util.ArrayList;

/**
 * Created by longlongren on 2018/9/21.
 * <p>
 * introduce:相似路线
 */

public class SameGameFragment extends BaseFragment {
    private RecyclerView mRecyclerFragmentSameGame;



    @Override
    public void initView(View view) {
        mRecyclerFragmentSameGame = (RecyclerView) view.findViewById(R.id.recycler_fragment_same_game);

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("along");
        strings.add("along");
        strings.add("along");
        strings.add("along");
        strings.add("along");
        strings.add("along");
        strings.add("along");
        strings.add("along");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerFragmentSameGame.setLayoutManager(linearLayoutManager);
        SameRoadAdapter sameRoadAdapter = new SameRoadAdapter(mActivity);
        sameRoadAdapter.setStrings(strings);
        mRecyclerFragmentSameGame.setAdapter(sameRoadAdapter);

    }

    @Override
    public int getRootView() {
        return R.layout.fragment_same_game;
    }
}
