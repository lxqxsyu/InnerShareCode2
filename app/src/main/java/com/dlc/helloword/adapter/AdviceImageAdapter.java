package com.dlc.helloword.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dlc.helloword.R;
import com.dlc.helloword.entry.AdviceImageBean;

/**
 * 描述：
 * 日期：2019/8/5
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class AdviceImageAdapter extends BaseQuickAdapter<AdviceImageBean, BaseViewHolder> {

    public AdviceImageAdapter() {
        super(R.layout.item_advice_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdviceImageBean item) {
        Glide.with(mContext).load(item.img).into((ImageView) helper.getView(R.id.item_imageview));
        helper.setText(R.id.item_time, item.time);
    }
}
