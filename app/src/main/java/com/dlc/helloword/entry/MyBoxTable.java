package com.dlc.helloword.entry;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
@Entity
public class MyBoxTable {

    public MyBoxTable(){

    }

    public MyBoxTable(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Id
    public long id;
    public String title;
    public String subtitle;
}
