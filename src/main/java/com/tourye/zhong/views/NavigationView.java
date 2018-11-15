package com.tourye.zhong.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tourye.zhong.utils.DensityUtils;

import java.util.List;

/**
 * Created by longlongren on 2018/9/25.
 * <p>
 * introduce:
 */

public class NavigationView extends LinearLayout {

    private final Context mContext;

    public NavigationView(Context context) {
        super(context);
        mContext = context;
        setOrientation(VERTICAL);

    }

    public void setData(List<String> strings){
        LinearLayout linearLayout = new LinearLayout(mContext);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(mContext, 50));
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        for (int i = 0; i < strings.size(); i++) {
            TextView textView = new TextView(mContext);
            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            marginLayoutParams.setMargins(0,0,0,0);
            textView.setLayoutParams(marginLayoutParams);
            textView.setText("导航"+i);
            linearLayout.addView(textView);
        }
    }
}
