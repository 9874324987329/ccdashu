package com.sp.util;


import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpClientUtil {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(HttpClientUtil.class);
    
    private HttpClientUtil(){}

    /**
     * 模拟post提交表单
     *
     * @param map 表单中的键值对
     * @param url 提交的地址
     */
    public static String post_form(Map<String, String> map, String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        ClientConnectionManager mgr = httpclient.getConnectionManager();
        HttpParams params = httpclient.getParams();
        httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, mgr.getSchemeRegistry()), params);

        //设置是否请求重发
        httpclient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<String> mapKey = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (Iterator<String> iter = mapKey.iterator(); iter.hasNext(); ) {
            String key = iter.next();
            formparams.add(new BasicNameValuePair(key, map.get(key)));
            sb.append(key + "=" + map.get(key) + "&");
        }
        String getUrl = url + "?" + sb.toString();
        if (Logger.isInfoEnabled()) {
//            Logger.info(getUrl);
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            Logger.error(e1.getMessage(), e1);
        }
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
        String content = null;
        try {
            if (null != response) {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    content = convertStreamToString(httpEntity.getContent());
                    httpEntity.consumeContent();
                }
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
//        Logger.info(content);
        return content;
    }

    public static String get_form(List<NameValuePair> params, String url) {
        String body = null;
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            // Get请求
            HttpGet httpget = new HttpGet(url);
            // 设置参数
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
            httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 发送请求
            HttpResponse httpresponse = httpclient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity);
            if (entity != null) {
                entity.consumeContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.error(e.getMessage(), e);
        }
        return body;
    }

    private static String convertStreamToString(InputStream is)
            throws Exception {
        InputStreamReader isReader = null;
        try {
            isReader = new InputStreamReader(is, "UTF-8");
            BufferedReader reader = new BufferedReader(isReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(isReader);
            IOUtils.closeQuietly(is);
        }
    }

    public static String executeGet(String url) throws Exception{
        BufferedReader in = null;
        InputStreamReader isReader = null;
        HttpClient client = null;
        try {
            // 定义HttpClient
            client = new DefaultHttpClient();
            
            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            isReader = new InputStreamReader(response.getEntity().getContent());
            in = new BufferedReader(isReader);
            StringBuilder sb = new StringBuilder("");
            String line;
            String nl = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line).append(nl);
            }
            return sb.toString();
        } catch(Exception e) {
            throw new Exception(e);
        } finally {
            HttpClientUtils.closeQuietly(client);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(isReader);
        }
    }

    public static void main(String[] args) throws Exception {
//        String url = "https://zixun.mmall.com/fitment/1758.html";
//        String url = "https://zixun.mmall.com/news/1758.html";
        String url = "https://zixun.mklmall.com/zhinan/29.html";
//        Map<String,String> paramMap = new HashedMap();
        List<NameValuePair> paramMap = new ArrayList<>();
       String html = get_form(paramMap,url);
       //<a href="http://sh.to8to.com/zs/274279/" target="_blank" class="zgscl_name" >

        System.out.println("html:"+html);
    }

}
