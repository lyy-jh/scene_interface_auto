package com.lyy.autointerface.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.lyy.autointerface.base.TestBase;
import com.lyy.autointerface.restclient.RestClient;
import com.lyy.autointerface.utils.JsonUtil;

public class GetApiTest extends TestBase{
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;

    final static Logger logger = Logger.getLogger(GetApiTest.class);

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();

        host = prop.getProperty("server.http.host");
        logger.info("测试服务器地址为："+ host.toString());

        url = host+"/api/users?page=2";
        logger.info("当前测试接口的完整地址为："+url.toString());

    }

    @Test
    public void getApiTest() throws ClientProtocolException, IOException {
        logger.info("开始执行用例...");
        restClient = new RestClient();
        httpResponse = restClient.get(url);

        //判断断言状态码是不是200
        logger.info("判断响应状态码");
        int statusCode = restClient.getStatusCode(httpResponse);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"response status code is not 200");
        Reporter.log("状态码："+statusCode,true);
        Reporter.log("接口地址： "+url);


        //获取响应的json对象
        JSONObject responseJson = restClient.getResponseJson(httpResponse);

        //json内容解析
//		String s =JsonUtil.getValueByJpath(responseJson, "data[1]/first_name");
//		logger.info("执行JSON解析，解析的内容是 " + s);
//		logger.info("接口内容响应断言");
//		Assert.assertEquals(s, "Lindsay", "first name is not Lindsay");
//
//
//		String total = JsonUtil.getValueByJpath(responseJson, "total");
//		logger.info("执行JSON解析，解析的内容是 " + total);
//		logger.info("接口内容响应断言");
//		Assert.assertEquals(total, "12", "total is not 12");
//
//
//		String data = JsonUtil.getValueByJpath(responseJson, "data[0]");
//		logger.info("执行JSON解析，解析的内容是 " + data);
//		logger.info("接口内容响应断言");
//		Assert.assertEquals(data, "{\"last_name\":\"Lawson\",\"id\":7,\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg\",\"first_name\":\"Michael\",\"email\":\"michael.lawson@reqres.in\"}", "data is not data[0]对象");
        logger.info("用例执行结束..");
    }
}

