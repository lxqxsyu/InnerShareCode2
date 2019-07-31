package com.dlc.helloword.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dlc.helloword.R;
import com.dlc.helloword.entry.TestParcelableObj;
import com.dlc.helloword.entry.TestSerializObj;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestTurnActivity extends BaseActivity{

    private TextView mTitleText;
    private TextView mSubTitleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_turn);

        mTitleText = findViewById(R.id.tv_turn_title);
        mSubTitleText = findViewById(R.id.tv_turn_subtitle);

        String title = "";
        String subtitle = "";
        switch (getIntent().getIntExtra("type", 0)){
            case 0:
                title = getIntent().getStringExtra("title");
                subtitle = getIntent().getStringExtra("subtitle");
                break;
            case 1:
                TestSerializObj tso = (TestSerializObj) getIntent().getSerializableExtra("serialObject");
                title = tso.title;
                subtitle = tso.subtitle;
                break;
            case 2:
                TestParcelableObj tpo = getIntent().getParcelableExtra("parcelableObject");
                title = tpo.title;
                subtitle = tpo.subtitle;
                break;
        }

        mTitleText.setText(title);
        mSubTitleText.setText(subtitle);
    }

    public void turnbackClick(View view) {
        TestTurnActivity.this.finish();
    }
}
