package com.dlc.helloword.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.dlc.helloword.App;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class ToastUtil {

    /**
     * 显示 Toast
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        if(context == null) return;
        if(TextUtils.isEmpty(message)) return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String message){
        if(TextUtils.isEmpty(message)) return;
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
