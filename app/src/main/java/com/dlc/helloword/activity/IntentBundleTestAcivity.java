package com.dlc.helloword.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dlc.helloword.R;
import com.dlc.helloword.entry.TestParcelableObj;
import com.dlc.helloword.entry.TestSerializObj;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class IntentBundleTestAcivity extends BaseActivity {

    public static final String TITLE = "LI-XIAO-QIANG";
    public static final String SUB_TITLE = "SUB-LXQ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent_bundle);
    }

    public void nomalDataClick(View view) {
        Intent intent = new Intent(this, TestTurnActivity.class);

        intent.putExtra("title", TITLE);
        intent.putExtra("subtitle", SUB_TITLE);
        intent.putExtra("type", 0);

        /*Bundle bundle = intent.getExtras();
        bundle.putString("title", TITLE);
        bundle.putString("subtitle", SUB_TITLE);*/

        startActivity(intent);
    }

    public void serializableClick(View view) {
        TestSerializObj tso = new TestSerializObj();
        tso.title = TITLE;
        tso.subtitle = SUB_TITLE;
        Intent intent = new Intent(this, TestTurnActivity.class);
        intent.putExtra("serialObject", tso);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    public void parcelableClick(View view) {
        TestParcelableObj tpo = new TestParcelableObj();
        tpo.title = TITLE;
        tpo.subtitle = SUB_TITLE;
        Intent intent = new Intent(this, TestTurnActivity.class);
        intent.putExtra("parcelableObject", tpo);
        intent.putExtra("type", 2);
        startActivity(intent);
    }
}
