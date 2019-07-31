package com.dlc.helloword.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dlc.helloword.R;
import com.dlc.helloword.entry.MyTableContract.MyTableEntry;
import com.dlc.helloword.helper.TestDBHelper;
import com.dlc.helloword.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class SQLiteTestActivity extends BaseActivity {

    private TestDBHelper mDBHelper;

    private EditText mEditTitle;
    private EditText mEditSubTitle;
    private EditText mEditSearchKey;

    private TextView mTextTitle;
    private TextView mTextSubTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sqlite);
        mDBHelper = new TestDBHelper(this);

        mEditTitle = findViewById(R.id.et_input_title);
        mEditSubTitle = findViewById(R.id.et_input_subtitle);
        mEditSearchKey = findViewById(R.id.et_input_searchkey);

        mTextTitle = findViewById(R.id.tv_showtitle);
        mTextSubTitle = findViewById(R.id.tv_showsubtitle);
    }

    //增加数据
    private void saveData(MyTableEntry entry){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyTableEntry.COLUMN_NAME_TITLE, entry.title);
        values.put(MyTableEntry.COLUMN_NAME_SUBTITLE, entry.subtitle);
        long newRowId = db.insert(MyTableEntry.TABLE_NAME, null, values);
    }

    //根据 title 查询数据
    private List<MyTableEntry> getData(String title){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                MyTableEntry.COLUMN_NAME_TITLE,
                MyTableEntry.COLUMN_NAME_SUBTITLE
        };

        String selection = MyTableEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { title };

        String sortOrder =
                MyTableEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                MyTableEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<MyTableEntry> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            MyTableEntry entry = new MyTableEntry();
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MyTableEntry._ID));
            entry.title = cursor.getString(cursor.getColumnIndexOrThrow(MyTableEntry.COLUMN_NAME_TITLE));
            entry.subtitle = cursor.getString(cursor.getColumnIndexOrThrow(MyTableEntry.COLUMN_NAME_SUBTITLE));
            itemIds.add(entry);
        }
        cursor.close();
        return itemIds;
    }

    public void saveClick(View view) {
        String title = mEditTitle.getText().toString().trim();
        String subtitle = mEditSubTitle.getText().toString().trim();

        if(TextUtils.isEmpty(title) || TextUtils.isEmpty(subtitle)){
            ToastUtil.showToast(this, "请填写title和subtitle");
            return;
        }

        saveData(new MyTableEntry(title, subtitle));
        ToastUtil.showToast(this, "保存成功");
    }

    public void searchClick(View view) {
        String key = mEditSearchKey.getText().toString().trim();

        if(TextUtils.isEmpty(key)){
            ToastUtil.showToast(this, "请填写 key");
            return;
        }
        List<MyTableEntry> entrys = getData(key);
        if(entrys.isEmpty()) {
            ToastUtil.showToast(this, "没有查询到数据");
            return;
        }
        for(MyTableEntry entry : entrys){
            mTextTitle.append(entry.title);
            mTextTitle.append("/");
            mTextSubTitle.append(entry.subtitle);
            mTextSubTitle.append("/");
        }
    }

    @Override
    protected void onDestroy() {
        mDBHelper.close();
        super.onDestroy();
    }
}
