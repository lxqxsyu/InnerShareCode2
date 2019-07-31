package com.dlc.helloword.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dlc.helloword.R;
import com.dlc.helloword.adapter.ListAdapter;
import com.dlc.helloword.entry.ListEntry;
import com.dlc.helloword.util.ConvertUtil;
import com.dlc.helloword.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class GrideAdapterTestActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;

    private int mVerticalGap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_listadapter);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mVerticalGap = ConvertUtil.dip2px(this, 6);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                       @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = mVerticalGap / 2;
                outRect.left = mVerticalGap / 2;
                outRect.right = mVerticalGap / 2;
                outRect.top = mVerticalGap / 2;
            }
        });
        mListAdapter = new ListAdapter();
        mListAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ListEntry entry = (ListEntry) adapter.getData().get(position);
                ToastUtil.showToast(GrideAdapterTestActivity.this, "你点击了:" + entry.title);
            }
        });

        initData();
    }

    private void initData(){
        List<ListEntry> datas = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            ListEntry entry = new ListEntry();
            entry.title = "这个是列表item" + i;
            datas.add(entry);
        }
        mListAdapter.setNewData(datas);
    }
}
