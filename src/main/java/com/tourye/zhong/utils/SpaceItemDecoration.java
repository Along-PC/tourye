package com.tourye.zhong.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by longlongren on 2018/9/26.
 * <p>
 * introduce:瀑布流间隔工具类
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int spanCount;
    private int space;
    private boolean includeEdge;


    public SpaceItemDecoration( int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        outRect.left = space;
        outRect.right = space;
        outRect.bottom=space*2;


    }
}
