package com.aha.common.spring.ioc.applicationContext;

import com.aha.common.spring.event.RegisterInfo;
import com.aha.common.spring.event.RegisterSuccessEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * 测试 ApplicationContext 相对于 beanFactory 的扩展功能
 * 因为监听程序写在 event 包下需要添加 scanBasePackages = {"com.aha.common"}
 */
@SpringBootApplication(scanBasePackages = {"com.aha.common"})
public class TestApplicationContextExtend {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(TestApplicationContextExtend.class, args);

        // 国际化 需要 resources 中的配置文件支持 hi 为 key
        System.out.println("\n国际化示例--------------------------------------");
        System.out.println(context.getMessage("hi", null, Locale.CHINA));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));
        System.out.println("----------------------------------------------\n");


        // 资源访问
        System.out.println("资源访问示例-------------------------------------");
        Resource[] resources = new Resource[0];
        try {
            // classpath 是只扫描 当前项目 resources 下，classpath* 就是包含所有 jar 包下的 resources
            resources = context.getResources("classpath*:META-INF/spring.factories");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Resource resource : resources) {
            System.out.println("获取到所有的spring.factories文件为---" + resource);
        }
        System.out.println("----------------------------------------------\n");

        // 事件发布
        System.out.println("事件发布示例-------------------------------------\n");
        context.publishEvent(new RegisterSuccessEvent(new RegisterInfo().setUsername("aha").setSecret("abc")));

        // 环境参数
        System.out.println("获取环境参数示例----------------------------------------");
        ConfigurableEnvironment environment = context.getEnvironment();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        systemEnvironment.forEach((key, value) -> System.out.println("环境参数的KEY为----" + key + "----环境参数的value为----" + value));
        System.out.println("------------------------------------------------------\n");

    }

}
