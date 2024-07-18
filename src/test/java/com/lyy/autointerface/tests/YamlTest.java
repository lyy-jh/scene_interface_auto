package com.lyy.autointerface.tests;

import com.lyy.autointerface.utils.YamlUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
@Slf4j
public class YamlTest {
    @Test
    public void test(){
        Object[][] objs = YamlUtil.getYamlValue(System.getProperty("user.dir")+"/src/test/java/com/lyy/autointerface/data/userData.yml");
        for(Object[] obj: objs){
            log.info("一维数组"+obj.toString());
            for(Object b: obj){
                log.info(b.toString());
            }
        }
    }

    @Test
    public void test2(){
        Object[][] objs = new Object[][]{{"/api/users","jh","tester"},{"/api/users","lyy","programmer"}};
        for(Object[] obj:objs){
            log.info(obj.toString());
            for(Object b:obj){
               log.info(b.toString());
            }
        }
    }
}
