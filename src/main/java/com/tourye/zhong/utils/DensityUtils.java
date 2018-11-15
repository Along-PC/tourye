package com.tourye.zhong.utils;

import android.content.Context;

/**
 * Created by longlongren on 2018/8/14.
 * <p>
 * introduce:像素单位转换工具
 */

public class DensityUtils {

    /**
     * dp值转化成px
     * @param dp
     * @return
     */
    public static int dp2px(Context context,int dp){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5f);
    }

    /**
     * sp值转化成px
     * @param sp
     * @return
     */
    public static int sp2px(Context context,int sp){
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp*density+0.5f);
    }
}
