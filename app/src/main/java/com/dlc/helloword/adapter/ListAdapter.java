package com.dlc.helloword.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dlc.helloword.R;
import com.dlc.helloword.entry.ListEntry;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class ListAdapter extends BaseQuickAdapter<ListEntry, BaseViewHolder> {

    public ListAdapter() {
        super(R.layout.item_adapter_list_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListEntry item) {
        helper.setText(R.id.item_title, item.title);
    }
}
