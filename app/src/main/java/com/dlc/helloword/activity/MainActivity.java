package com.dlc.helloword.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dlc.helloword.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testSharePreference(View view) {
        turnToPage(SharePreferenceTestActivity.class);
    }

    public void testSQLite(View view) {
        turnToPage(SQLiteTestActivity.class);
    }

    public void testObjectBox(View view) {
        turnToPage(ObjectBoxTestActivity.class);
    }

    public void testWriteReadFile(View view) {
        turnToPage(FileWriteReadTestActivity.class);
    }

    public void testIntentBundle(View view) {
        turnToPage(IntentBundleTestAcivity.class);
    }

    public void testListAdapter(View view) {
        turnToPage(ListAdapterTestActivity.class);
    }

    public void testGridAdapter(View view) {
        turnToPage(GrideAdapterTestActivity.class);
    }

    public void testFileProvider(View view) {
        turnToPage(FileProviderTestActivity.class);
    }
}
