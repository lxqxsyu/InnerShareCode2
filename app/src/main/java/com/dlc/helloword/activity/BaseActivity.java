package com.dlc.helloword.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class BaseActivity extends AppCompatActivity {

    protected void turnToPage(Class<? extends BaseActivity> toClass){
        Intent intent = new Intent(this, toClass);
        startActivity(intent);
    }
}
