package com.dlc.helloword.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dlc.helloword.R;
import com.dlc.helloword.adapter.AdviceImageAdapter;
import com.dlc.helloword.entry.AdviceImageBean;
import com.dlc.helloword.entry.RequestParam;
import com.dlc.helloword.entry.RequestResultBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * 描述：
 * 日期：2019/8/5
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class HttpsURLConnectionTestActivity extends BaseActivity {

    public static final int PAGE_SIZE = 20;

    private RecyclerView mRecyclerView;
    private AdviceImageAdapter mAdapter;
    private int mCurrentPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_httpsurlconnection);
        mRecyclerView = findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAnimation(null);
        mAdapter = new AdviceImageAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.list_empty_view);
        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setPreLoadNumber(3);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mCurrentPage++;
                requestNewData();
            }
        }, mRecyclerView);
        initData();
    }

    private class RequestPostTask extends AsyncTask<RequestParam, Integer, List<AdviceImageBean>>{

        public static final int CODE_RESULT_OK = 200;
        private int currentPage;
        public RequestPostTask() {
            currentPage = mCurrentPage;
        }

        @Override
        protected void onPreExecute() {
            //TODO 检查网络连接状态
        }

        @Override
        protected List<AdviceImageBean> doInBackground(RequestParam... strings) {
            try {
                String result = getRemoteDataByURL(strings[0].getURL());
                Log.d("TEST", "result = " + result);
                RequestResultBean resultObj = new Gson().fromJson(result, RequestResultBean.class);
                if(resultObj.code == CODE_RESULT_OK){
                    return resultObj.result;
                }else{
                    mAdapter.loadMoreFail();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<AdviceImageBean> adviceImageBeans) {
            if(currentPage == 1) {
                mAdapter.setNewData(adviceImageBeans);
            }else{
                mAdapter.addData(adviceImageBeans);
            }
            mAdapter.loadMoreComplete();
        }
    }

    private void initData(){
        mCurrentPage = 1;
        requestNewData();
    }

    private void requestNewData(){
        String requestUrl = "https://api.apiopen.top/getImages";
        RequestParam requestParam = new RequestParam(requestUrl)
                .setParam("page", mCurrentPage)
                .setParam("count", PAGE_SIZE);
        new RequestPostTask().execute(requestParam);
    }

    /**
     * 网络请求（POST方式）
     * @param url
     * @return
     * @throws IOException
     */
    private String getRemoteDataByURL(URL url) throws IOException {
        HttpsURLConnection connection = null;
        InputStream stream = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(3000); //设置读取超时时间
            connection.setConnectTimeout(3000); //设置连接超时时间
            connection.setRequestMethod("POST");
            connection.setDoInput(true); //使用 URL 连接进行输入, 默认true
            connection.setDoOutput(true);  //使用 URL 连接进行输出，默认false
            connection.connect();

            int responseCode = connection.getResponseCode();
            if(responseCode != HttpsURLConnection.HTTP_OK){
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if(stream != null){
                result = readStream(stream, 1024 * 1024);
            }
        }finally {
            if(stream != null){
                stream.close();
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return result;
    }


    /**
     * 从流中读取数据
     * @param stream
     * @param maxReadSize
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private String readStream(InputStream stream, int maxReadSize)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        StringBuffer buffer = new StringBuffer();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }
}
