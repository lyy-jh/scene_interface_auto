package com.lyy.autointerface.test;

import com.alibaba.fastjson.JSONObject;
import com.lyy.autointerface.base.TestBase;
import com.lyy.autointerface.restclient.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.*;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lyy.autointerface.utils.JsonUtil;

@Slf4j
public class Request extends TestBase {
    static TestBase testBase;
    static String host;
    static RestClient restClient;
    static CloseableHttpResponse httpResponse;
    static Map<String,String> headermap;
    static String entityString;
    static Map<String,String> map = new HashMap<>();
    //excel路径
//    String testCaseExcel;

//        @BeforeClass
//        public void setUp() {
//            testBase = new TestBase();
//
//            host = prop.getProperty("server.http.host");
//            logger.info("测试服务器地址为："+ host.toString());
//
//            url = host+"/api/users?page=2";
//            logger.info("当前测试接口的完整地址为："+url.toString());
//
//        }
    @Test
    public static void postApiTest(String url,String params,String header,String assertData) throws IOException {
        log.info("开始执行用例...");
        restClient = new RestClient();

        //准备请求头信息
        if(header!=null){
            JSONObject headerJson = JSONObject.parseObject(header);
        }
        headermap = new HashMap<>();
        headermap.put("Content-Type", "application/json"); //设置请求类型

        //准备请求参数信息，对象转换成json字符串
        entityString = params;
        log.info("参数："+entityString);
        log.info("请求头："+headermap);

        httpResponse = restClient.post(url, entityString, headermap);

        log.info("用例执行结束");


        // 判断状态码是否正确
        int statusCode = restClient.getStatusCode(httpResponse);
//        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201, "status code is not 201");
//
//        //判断返回信息是否正确
        JSONObject responseJson = restClient.getResponseJson(httpResponse);
//
//        //解析json
//        String username = JsonUtil.getValueByJpath(responseJson, "name");
//        Assert.assertEquals(username, "lyy", "name is not lyy");
//
//        String userjob = JsonUtil.getValueByJpath(responseJson, "job");
//        Assert.assertEquals(userjob, "programer", "job is not tester");
//
        String userId = String.valueOf(JsonUtil.getValueByJpath(responseJson, "id"));
        log.info("新增id为："+userId);
        log.info("以下为断言数据");
        org.json.JSONObject json = new org.json.JSONObject(assertData);
        log.info("code:"+json.get("code"));
        Assert.assertEquals(String.valueOf(statusCode),json.get("code"), "status code is not 201");
        for(String key: json.keySet()){
            log.info(key);
            if(!key.equals("code")){
                String actual = JsonUtil.getValueByJpath(responseJson, key);
                log.info((String) json.get(key));
                Assert.assertEquals(actual, json.get(key), "name is not lyy");
            }

        }
        map.put("id",userId);
        for(String str :map.keySet()){
            log.info("map中的数据为"+map.get(str));
        }


    }

    @Test
    public static void putApiTest(String url,String params,String header,String assertData) throws IOException {
        if(url.contains("{{") && url.contains("}}")){
            String subStr = "";
            Pattern pattern = Pattern.compile("\\{\\{.*?\\}\\}");
            Matcher matcher = pattern.matcher(url);
            while(matcher.find()){
                subStr = matcher.group(0);
            }
            int len = subStr.length();
            subStr = subStr.substring(2,len-2);
            log.info("subStr:"+subStr);
            String resultStr = "";
            if(map.containsKey(subStr)){
                resultStr = map.get(subStr);
            }
            log.info("resultStr:"+resultStr);
             url = url.replaceAll("\\{\\{.*?\\}\\}",resultStr);
             log.info("url:"+url);
        }
        log.info("开始执行用例...");
        restClient = new RestClient();

        //准备请求头信息
        if(header!=null){
            JSONObject headerJson = JSONObject.parseObject(header);
        }
        headermap = new HashMap<>();
        headermap.put("Content-Type", "application/json"); //设置请求类型

        //准备请求参数信息，对象转换成json字符串
        entityString = params;
        log.info("参数："+entityString);
        log.info("请求头："+headermap);

        httpResponse = restClient.put(url, entityString, headermap);

        log.info("用例执行结束");

        // 判断状态码是否正确
        int statusCode = restClient.getStatusCode(httpResponse);
        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200, "status code is not 200");

        //判断返回信息是否正确
        JSONObject responseJson = restClient.getResponseJson(httpResponse);

        //解析json
        String username = JsonUtil.getValueByJpath(responseJson, "name");
        Assert.assertEquals(username, "jh", "name is not lyy");

        String userjob = JsonUtil.getValueByJpath(responseJson, "job");
        Assert.assertEquals(userjob, "tester", "job is not tester");

        //断言
        org.json.JSONObject json = new org.json.JSONObject(assertData);
        log.info("code:"+json.get("code"));
        Assert.assertEquals(String.valueOf(statusCode),json.get("code"), "status code is not 201");
        for(String key: json.keySet()){
            log.info(key);
            if(!key.equals("code")){
                String actual = JsonUtil.getValueByJpath(responseJson, key);
                log.info((String) json.get(key));
                String expect = (String) json.get(key);
                if(expect.contains("{{") && expect.contains("}}")){
//                    String subStr = "";
//                    Pattern pattern = Pattern.compile("\\{\\{.*?\\}\\}");
//                    Matcher matcher = pattern.matcher(url);
//                    while(matcher.find()){
//                        subStr = matcher.group(0);
//                    }
//                    int len = subStr.length();
//                    subStr = subStr.substring(2,len-2);
//                    log.info("subStr:"+subStr);
//                    String resultStr = "";
//                    if(map.containsKey(subStr)){
//                        resultStr = map.get(subStr);
//                    }
//                    log.info("resultStr:"+resultStr);
                    expect = expect.replaceAll("\\{\\{.*?\\}\\}","jh");
                    log.info("expect:"+expect);
                }
                Assert.assertEquals(actual, expect, "name is not lyy");
            }

        }

    }

//    @AfterSuite
//    public void generateReport() {
//        XmlSuite suite = new XmlSuite();
//        suite.setName("TestReport");
//
//        XmlTest test = new XmlTest(suite);
//        test.setName("TestReport");
//
//        // 添加当前类到测试
//        XmlClass xmlClass = new XmlClass("com.lyy.autointerface.test.Request");
//        test.getClasses().add(xmlClass);
//
//        // 启动TestNG并运行测试
//        TestNG testNG = new TestNG();
//        testNG.setXmlSuites(Collections.singletonList(suite));
//        testNG.run();
//    }
}
