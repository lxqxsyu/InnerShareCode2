package com.dlc.helloword.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dlc.helloword.App;
import com.dlc.helloword.R;
import com.dlc.helloword.entry.MyBoxTable;
import com.dlc.helloword.entry.MyBoxTable_;
import com.dlc.helloword.entry.MyTableContract;
import com.dlc.helloword.util.ToastUtil;

import java.util.List;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class ObjectBoxTestActivity extends BaseActivity {

    private EditText mEditTitle;
    private EditText mEditSubTitle;
    private EditText mEditSearchKey;

    private TextView mTextTitle;
    private TextView mTextSubTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bojectbox);

        mEditTitle = findViewById(R.id.et_input_title);
        mEditSubTitle = findViewById(R.id.et_input_subtitle);
        mEditSearchKey = findViewById(R.id.et_input_searchkey);

        mTextTitle = findViewById(R.id.tv_showtitle);
        mTextSubTitle = findViewById(R.id.tv_showsubtitle);
    }


    public void saveClick(View view) {
        String title = mEditTitle.getText().toString().trim();
        String subtitle = mEditSubTitle.getText().toString().trim();

        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(subtitle)){
            ToastUtil.showToast(this, "请填写title和subtitle");
            return;
        }

        App.getInstance().getMyTableBox().put(new MyBoxTable(title, subtitle));

        ToastUtil.showToast(this, "保存成功");
    }

    public void searchClick(View view) {
        String key = mEditSearchKey.getText().toString().trim();

        if(TextUtils.isEmpty(key)){
            ToastUtil.showToast(this, "请填写 key");
            return;
        }

        List<MyBoxTable> entrys = App.getInstance().getMyTableBox().query().equal(MyBoxTable_.title, key).build().find();

        if(entrys.isEmpty()) {
            ToastUtil.showToast(this, "没有查询到数据");
            return;
        }
        for(MyBoxTable entry : entrys){
            mTextTitle.append(entry.title);
            mTextTitle.append("/");
            mTextSubTitle.append(entry.subtitle);
            mTextSubTitle.append("/");
        }
    }
}
