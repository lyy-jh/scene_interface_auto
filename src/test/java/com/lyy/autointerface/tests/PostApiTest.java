package com.lyy.autointerface.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyy.autointerface.base.TestBase;
import com.lyy.autointerface.parameters.User;
import com.lyy.autointerface.restclient.RestClient;
import com.lyy.autointerface.utils.JsonUtil;
import com.lyy.autointerface.utils.TestUtil;

public class PostApiTest extends TestBase{
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;
    Map<String,String> headermap;
    String entityString;
    //excel路径
    String testCaseExcel;

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("server.http.host");
        //url = host+"/api/users";
        //载入配置文件，post接口参数
        testCaseExcel = prop.getProperty("postdata");


    }

    @DataProvider(name="postData")
    public Object[][] post() throws IOException{
        return TestUtil.dtt("testCaseExcel", 0);

    }


    @Test(dataProvider="postData")
    public void postApiTest(String url,String name, String job) throws ClientProtocolException, IOException {

        restClient = new RestClient();

        //准备请求头信息
        headermap = new HashMap<>();
        headermap.put("Content-Type", "application/json"); //设置请求类型

        //准备请求参数信息，对象转换成json字符串
        User u = new User(name,job);

        entityString = JSON.toJSONString(u);

        httpResponse = restClient.post(host+url, entityString, headermap);

        //判断状态码是否正确
        int statusCode = restClient.getStatusCode(httpResponse);
        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201, "status code is not 201");

        //判断返回信息是否正确
        JSONObject responseJson = restClient.getResponseJson(httpResponse);

        //解析json
        String username = JsonUtil.getValueByJpath(responseJson, "name");
        Assert.assertEquals(username, "lyy", "name is not lyy");

        String userjob = JsonUtil.getValueByJpath(responseJson, "job");
        Assert.assertEquals(userjob, "tester", "job is not tester");


    }
}

