package com.lyy.autointerface.utils;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
@Slf4j
public class YamlUtil {
    public static Object[][] getYamlValue(String fileName){
        Object[][] data = new Object[0][];
        InputStream is = null;
        try {
            File file = new File(fileName);
            is = new BufferedInputStream(new FileInputStream(file));
            Yaml yaml = new Yaml();
            data = yaml.loadAs(is,Object[][].class);

        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
