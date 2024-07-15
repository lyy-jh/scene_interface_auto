package com.lyy.autointerface.restclient;

import java.io.IOException;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestClient {
    final static Logger logger = Logger.getLogger(RestClient.class);

    //1.get请求方法
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
        //创建一个可关闭的httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个HttpGet的请求对象
        HttpGet httpGet = new HttpGet(url);
        //执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
        logger.info("开始发送get请求...");
        CloseableHttpResponse httpResponse =  httpClient.execute(httpGet);
        logger.info("发送请求成功，开始得到响应对象...");

        return httpResponse;
    }

    //2.带请求头信息的get请求方法
    public CloseableHttpResponse get(String url,Map<String,String> headermap) throws ClientProtocolException, IOException {
        //创建一个可关闭的CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个get请求对象
        HttpGet httpGet = new HttpGet(url);

        //加载请求头到httpGet对象
        for(Map.Entry<String, String> entry: headermap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        //执行请求,相当于postman上点击发送按钮，httpResponse对象接收
        logger.info("开始发送带请求头的get请求...");
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        logger.info("发送请求成功，开始得到响应对象...");

        return httpResponse;
    }

    //3.带请求头信息的post方法
    /**
     *
     * @param url
     * @param entityString  这个主要是设置payload,一般来说就是json串
     * @param headermap  带请求的头信息，格式是键值对，所以这里使用hashmap
     * @return  返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse post(String url,String entityString,Map<String,String> headermap) throws ClientProtocolException, IOException {
        //创建一个可关闭的CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post请求对象
        HttpPost httpPost = new HttpPost(url);

        //设置payLoad
        httpPost.setEntity(new StringEntity(entityString));

        //加载请求头到httpPost对象
        for(Map.Entry<String, String> entry:headermap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        //发送请求，相当于postman中点击发送按钮
        logger.info("开始发送post请求...");
        CloseableHttpResponse httpResponse  =httpClient.execute(httpPost);
        logger.info("发送请求成功，开始得到响应对象...");
        return httpResponse;

    }

    //4.带请求头信息的put方法
    public CloseableHttpResponse put(String url,String entityString,Map<String,String> headermap) throws ClientProtocolException, IOException {
        //创建一个可关闭的CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建put请求对象
        HttpPut httpPut = new HttpPut(url);

        //设置payLoad
        httpPut.setEntity(new StringEntity(entityString));

        //加载请求头到httpPut对象
        for(Map.Entry<String, String> entry:headermap.entrySet()) {
            httpPut.addHeader(entry.getKey(), entry.getValue());
        }
        //发送请求，相当于postman中点击发送按钮
        logger.info("开始发送put请求...");
        CloseableHttpResponse httpResponse  =httpClient.execute(httpPut);
        logger.info("发送请求成功，开始得到响应对象...");
        return httpResponse;

    }

    //5.delete方法
    public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {
        //创建一个可关闭的httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个HttpGet的请求对象
        HttpDelete httpDelete = new HttpDelete(url);

        //执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
        logger.info("开始发送delete请求...");
        CloseableHttpResponse httpResponse =  httpClient.execute(httpDelete);
        logger.info("发送请求成功");
        return httpResponse;
    }
    /**
     * 获取响应状态码，常用来和TestBase中定义的状态码常量去测试断言使用
     * @param httpResponse
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public int getStatusCode(CloseableHttpResponse httpResponse) throws ParseException, IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        logger.info("解析，得到响应状态码"+statusCode);
        return statusCode;

    }
    /**
     *
     * @param httpResponse
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public JSONObject getResponseJson(CloseableHttpResponse httpResponse) throws ParseException, IOException {
        String responseString = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        logger.info("得到响应对象的string格式"+responseString);
        JSONObject responseJson = JSON.parseObject(responseString);
        logger.info("返回响应内容的JSON格式"+responseJson);
        return responseJson;

    }
}

