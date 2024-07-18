package com.lyy.autointerface.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.lyy.autointerface.utils.YamlUtil;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class PostApiTest extends TestBase{
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;
    Map<String,String> headermap;
    String entityString;
    //excel路径
//    String testCaseExcel;

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("server.http.host");
        //url = host+"/api/users";
        //载入配置文件，post接口参数
//        testCaseExcel = prop.getProperty("postdata");


    }

//    @DataProvider(name="postData")
//    public Object[][] post() throws IOException{
//        return TestUtil.dtt("testCaseExcel", 0);
//
//    }

    @DataProvider(name="postData")
    public Object[][] post() throws IOException{
       Object[][] objs = YamlUtil.getYamlValue(System.getProperty("user.dir")+"/src/test/java/com/lyy/autointerface/data/userData.yml");
        for(Object[] obj: objs){
            log.info("一维数组"+obj.toString());
            for(Object b: obj){
                log.info(b.toString());
            }
        }
       return objs;
    }


    @Test(dataProvider="postData")
    public void postApiTest(String url,String name, String job) throws ClientProtocolException, IOException {
        log.info("url:"+url+"name:"+name+"job:"+job);
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
        Assert.assertEquals(username, name, "name is not correct");

        String userjob = JsonUtil.getValueByJpath(responseJson, "job");
        Assert.assertEquals(userjob, job, "job is not correct");


    }
}

