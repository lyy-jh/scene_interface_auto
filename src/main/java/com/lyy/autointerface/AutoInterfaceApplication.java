package com.lyy.autointerface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lyy.autointerface.mapper")
public class AutoInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoInterfaceApplication.class, args);
    }

}
