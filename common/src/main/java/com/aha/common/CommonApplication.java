package com.aha.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 暂时不扫描 com.aha.common.spring 里面会有重复的 bean
@SpringBootApplication(scanBasePackages = "com.aha.common.open.source.easyexcel",
        exclude = {DataSourceAutoConfiguration.class})
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

}
