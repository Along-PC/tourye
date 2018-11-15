package com.tourye.zhong.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.ui.adapter.FamillyGameAdapter;

import java.util.ArrayList;

/**
 * Created by longlongren on 2018/9/21.
 * <p>
 * introduce:亲子赛事
 */

public class FamillyGameFragment extends BaseFragment {
    private RecyclerView mRecyclerFragmentFamillyGame;



    @Override
    public void initView(View view) {
        mRecyclerFragmentFamillyGame = (RecyclerView) view.findViewById(R.id.recycler_fragment_familly_game);

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
        strings.add("along");
        strings.add("along");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRecyclerFragmentFamillyGame.setLayoutManager(linearLayoutManager);
        FamillyGameAdapter famillyGameAdapter = new FamillyGameAdapter(mActivity);
        famillyGameAdapter.setStrings(strings);
        mRecyclerFragmentFamillyGame.setAdapter(famillyGameAdapter);

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_familly_game;
    }
}
