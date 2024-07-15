package com.lyy.autointerface.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyy.autointerface.base.TestBase;
import com.lyy.autointerface.restclient.RestClient;
import com.lyy.autointerface.utils.JsonUtil;

public class DeleteApiTest extends TestBase{
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
    public void deleteApiTest() throws ClientProtocolException, IOException {
        restClient = new RestClient();
        httpResponse = restClient.delete(url);

        //获取响应状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        //断言状态码是不是200
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204,"response status code is not 204");
        Reporter.log("状态码："+statusCode,true);
        Reporter.log("接口地址： "+url);
    }
}

