package com.aha.common;

import com.aha.common.spring.ioc.beanFactory.SpringDemoApplication;
import com.aha.common.spring.ioc.beanFactory.UserRegisteredEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class CommonApplication {

    public static void main(String[] args) {

        SpringApplication.run(CommonApplication.class, args);

    }

}
