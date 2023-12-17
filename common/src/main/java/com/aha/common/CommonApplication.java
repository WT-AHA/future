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

        ConfigurableApplicationContext context = SpringApplication.run(CommonApplication.class, args);

        // 国际化 需要 resources 中的配置文件支持   hi 为 key
        System.out.println("\n国际化示例--------------------------------------");
        System.out.println(context.getMessage("hi", null, Locale.CHINA));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));
        System.out.println("----------------------------------------------\n");


        //资源访问
        System.out.println("资源访问示例-------------------------------------");
        Resource[] resources = new Resource[0];
        try {
            resources = context.getResources("classpath*:META-INF/spring.factories");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Resource resource : resources) {
            System.out.println("获取到所有的spring.factories文件为---"+resource);
        }
        System.out.println("----------------------------------------------\n");

        //事件发布
        context.publishEvent(new UserRegisteredEvent(context));



        //环境参数
        System.out.println("获取环境参数示例----------------------------------------");
        ConfigurableEnvironment environment = context.getEnvironment();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        systemEnvironment.entrySet().forEach(x->
                System.out.println("环境参数的KEY为----"+x.getKey()+"----环境参数的value为----"+x.getValue())
        );
        System.out.println("------------------------------------------------------\n");
    }

}
