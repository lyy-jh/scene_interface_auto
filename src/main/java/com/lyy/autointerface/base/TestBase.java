package com.lyy.autointerface.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * 所有接口测试都需要继承此类
 * @author liyingying
 *
 */
public class TestBase {

    public Properties prop;
    //方便测试用例调用去断言
    public static int RESPONSE_STATUS_CODE_200=200;
    public static int RESPONSE_STATUS_CODE_201=201;
    public static int RESPONSE_STATUS_CODE_204=204;
    public static int RESPONSE_STATUS_CODE_400=400;
    public static int RESPONSE_STATUS_CODE_404=404;
    public static int RESPONSE_STATUS_CODE_500=500;

    final static Logger logger = Logger.getLogger(TestBase.class);

    /**
     * 构造方法，加载读取properties文件
     * @throws
     */
    public TestBase() {
        logger.info("正在读取配置文件");
        prop = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/lyy/autointerface/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
