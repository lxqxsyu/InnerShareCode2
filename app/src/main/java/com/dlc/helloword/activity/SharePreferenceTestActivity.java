package com.dlc.helloword.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dlc.helloword.R;
import com.dlc.helloword.util.ToastUtil;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class SharePreferenceTestActivity extends BaseActivity {


    private static final String SP_TEST = "sp_test";
    private static final String KEY_INPUT_DATA = "input_data";

    private SharedPreferences mSharedPreference;

    private EditText mInputEdit;
    private TextView mShowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sharepreference);
        mInputEdit = findViewById(R.id.et_inputtext);
        mShowText = findViewById(R.id.tv_showtext);
        mSharedPreference = getSharedPreferences(SP_TEST, Context.MODE_PRIVATE);
    }

    private void save(String key, String data){
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(key, data);
        editor.commit(); // 或者 editor.apply();
    }

    private String get(String key){
        return mSharedPreference.getString(key, null);
    }

    public void saveClick(View view) {
        String inputData = mInputEdit.getText().toString().trim();
        if(TextUtils.isEmpty(inputData)){
            ToastUtil.showToast(this, "请填写内容");
            return;
        }
        save(KEY_INPUT_DATA, inputData);
        ToastUtil.showToast(this, "已保存");
    }

    public void showClick(View view) {
        String showData = get(KEY_INPUT_DATA);
        if (TextUtils.isEmpty(showData)) {
            ToastUtil.showToast(this, "无数据可显示");
            return;
        }
        mShowText.setText(showData);
    }
}
