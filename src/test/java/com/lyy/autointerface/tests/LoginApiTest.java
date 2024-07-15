package com.lyy.autointerface.tests;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lyy.autointerface.base.Log;
import com.lyy.autointerface.base.Login;
import com.lyy.autointerface.base.TestBase;
import com.lyy.autointerface.restclient.RestClient;

public class LoginApiTest {

    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;
    Properties prop;

    final static Logger logger = Logger.getLogger(GetApiTest.class);

    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = prop.getProperty("xng.http.host");
        logger.info("测试服务器地址为："+ host.toString());

        url = host+"/api/users?page=2";
        logger.info("当前测试接口的完整地址为："+url.toString());

    }

    @Test(dataProvider="data")
    public void test_login(Login login) {
        CloseableHttpClient  client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        try {
            client.execute(post);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @DataProvider(name="data")
    public Object[][] dataProvider(){
        Log log = new Log();
        log.setBrand("iPhone");
        log.setApp_version("1.20.0");
        log.setChannel("ios_app_store");
        log.setDevice("iPhone XS");
        log.setIdfa("56615B01-385E-4FA4-B133-C5796E1799DA");
        log.setNet("wifi");
        log.setOs_version("14.1");
        log.setPage("loginPage");
        log.setPf("4");
        log.setProduct("xng_album_tool");
        log.setResolution("1125*2436");
        log.setSession_id("3500CD9A-CF0A-49E4-85FA-9B713CED86D7");
        Login login = new Login();
        login.setLog(log);
        login.setName("test");
        login.setPwd("098f6bcd4621d373cade4e832627b4f6");
        login.setToken("");
        return (Object[][]) new Object[] {login};
    }

}

