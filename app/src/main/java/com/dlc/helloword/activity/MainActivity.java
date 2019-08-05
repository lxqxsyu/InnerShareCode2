package com.dlc.helloword.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckedTextView;

import com.dlc.helloword.R;
import com.dlc.helloword.reciver.NetworkReceiver;

public class MainActivity extends BaseActivity {

    private NetworkReceiver mNetworkReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void registNetworkChange(){
        //注册网络状态监听广播
        if(mNetworkReceiver == null) {
            mNetworkReceiver = new NetworkReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkReceiver, filter);
    }

    private void unRegistNetworkChange(){
        if(mNetworkReceiver == null) return;
        unregisterReceiver(mNetworkReceiver);
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

    public void testHttpsURLConnection(View view) {
        turnToPage(HttpsURLConnectionTestActivity.class);
    }

    public void testNetworkChange(View view) {
        CheckedTextView checkedTextView = (CheckedTextView) view;
        checkedTextView.toggle();
        if(checkedTextView.isChecked()){
            registNetworkChange();
        }else{
            unRegistNetworkChange();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegistNetworkChange();
    }
}
