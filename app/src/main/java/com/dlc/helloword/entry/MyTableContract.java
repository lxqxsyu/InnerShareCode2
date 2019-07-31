package com.dlc.helloword.entry;

import android.provider.BaseColumns;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class MyTableContract {

    private MyTableContract() {}

    public static class MyTableEntry implements BaseColumns {

        public static final String TABLE_NAME = "mytable";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

        public MyTableEntry(){

        }

        public MyTableEntry(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
        }

        public String title;
        public String subtitle;
    }
}
