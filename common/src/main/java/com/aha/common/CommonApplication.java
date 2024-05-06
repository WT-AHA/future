package com.aha.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 暂时不扫描 com.aha.common.spring 里面会有重复的 bean
@SpringBootApplication(scanBasePackages = "com.aha.common.spring.event")
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

}
