package com.dlc.helloword.entry;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 日期：2019/8/5
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class RequestParam {

    private String url;
    private Map<String, String> parms = new HashMap<>();

    public RequestParam(String url) {
        this.url = url;
    }

    public RequestParam setParam(String key, String value){
        parms.put(key, value);
        return this;
    }

    public RequestParam setParam(String key, int value){
        parms.put(key, String.valueOf(value));
        return this;
    }

    public URL getURL(){
        URL uRL = null;
        if(url == null || url.length() == 0) return uRL;
        StringBuilder sbUrl = new StringBuilder(url);
        try {
            boolean isfist = true;
            for(Map.Entry<String, String> entry : parms.entrySet()){
                if(isfist){
                    isfist = false;
                    sbUrl.append("?");
                }else {
                    sbUrl.append("&");
                }
                sbUrl.append(entry.getKey()).append("=");
                String value = entry.getValue();
                if(!TextUtils.isEmpty(value)){
                    sbUrl.append(URLEncoder.encode(value, "utf-8"));
                }
            }
            uRL = new URL(sbUrl.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uRL;
    }
}
