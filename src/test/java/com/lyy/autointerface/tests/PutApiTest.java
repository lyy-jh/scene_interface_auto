package com.lyy.autointerface.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyy.autointerface.base.TestBase;
import com.lyy.autointerface.parameters.User;
import com.lyy.autointerface.restclient.RestClient;
import com.lyy.autointerface.utils.JsonUtil;

public class PutApiTest extends TestBase{
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("server.http.host");
        url = host+"/api/users/2";
    }

    @Test
    public void putApiTest() throws ClientProtocolException, IOException {
        restClient  = new RestClient();

        //设置请求头
        Map<String,String> headermap  =new HashMap<>();
        headermap.put("Content-Type","application/json");
        //请求参数
        User u = new User("jh","tester");
        String entityString = JSON.toJSONString(u);

        httpResponse = restClient.put(url, entityString, headermap);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "status code is not 200");

        String responseString = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        JSONObject responseJson =JSON.parseObject(responseString);

        String name = JsonUtil.getValueByJpath(responseJson, "name");
        Assert.assertEquals(name, "jh","name is not jh");

    }

}
