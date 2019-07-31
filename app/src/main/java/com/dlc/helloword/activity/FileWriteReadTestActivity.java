package com.dlc.helloword.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dlc.helloword.R;
import com.dlc.helloword.util.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class FileWriteReadTestActivity extends BaseActivity{

    private EditText mInputEdit;
    private TextView mShowText;

    public static final String FILE_NAME = "test_write_read.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_file_write_read);
        mInputEdit = findViewById(R.id.et_inputtext);
        mShowText = findViewById(R.id.tv_showtext);
    }

    private void writeFile(String message){
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(message.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1){
                baos.write(buffer, 0, length);
            }
            return baos.toString("UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveClick(View view) {
        String inputData = mInputEdit.getText().toString().trim();
        if(TextUtils.isEmpty(inputData)){
            ToastUtil.showToast(this, "请填写内容");
            return;
        }
        writeFile(inputData);
        ToastUtil.showToast(this, "已保存");
    }

    public void showClick(View view) {
        String showData = readFile();
        if (TextUtils.isEmpty(showData)) {
            ToastUtil.showToast(this, "无数据可显示");
            return;
        }
        mShowText.setText(showData);
    }
}
