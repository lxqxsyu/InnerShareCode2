package com.dlc.helloword.util;

import android.content.Context;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class ConvertUtil {


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
