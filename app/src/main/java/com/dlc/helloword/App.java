package com.dlc.helloword;

import android.app.Application;

import com.dlc.helloword.entry.MyBoxTable;
import com.dlc.helloword.entry.MyObjectBox;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class App extends Application {

    private static App mInstance;
    private BoxStore mBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public static App getInstance(){
        return mInstance;
    }

    public Box<MyBoxTable> getMyTableBox(){
        return mBoxStore.boxFor(MyBoxTable.class);
    }
}
