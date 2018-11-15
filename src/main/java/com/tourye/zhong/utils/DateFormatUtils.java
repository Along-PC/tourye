package com.tourye.zhong.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by longlongren on 2018/10/31.
 * <p>
 * introduce:日期格式化工具
 */

public class DateFormatUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(String text){
        try {
            Date parse = simpleDateFormat.parse(text);
            String format = sdf.format(parse);
            return format;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
