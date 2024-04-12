package com.aha.common.spring.ioc.beanLifeCycle.test01;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestBeanLifeCycle01SpringBootApplication {

    public static void main(String[] args) {

        // 启动 springBoot
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(TestBeanLifeCycle01SpringBootApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 停止 springBoot 测试 bean 生命周期的销毁阶段
        applicationContext.close();

    }

}
