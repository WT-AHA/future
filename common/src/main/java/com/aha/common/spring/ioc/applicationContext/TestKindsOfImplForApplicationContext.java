package com.aha.common.spring.ioc.applicationContext;


import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

public class TestKindsOfImplForApplicationContext {

    static class Bean1 {

        public Bean1() {
            System.out.println("构造bean1");
        }

        private Bean2 bean2;

        public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }

        public Bean2 getBean2() {
            return bean2;
        }

    }

    static class Bean2 {

        public Bean2() {
            System.out.println("构造bean2");
        }
    }

    @Configuration
    static class Config {

        @Bean
        public Bean1 bean1(Bean2 bean2) {
            Bean1 bean1 = new Bean1();
            bean1.setBean2(bean2);
            return bean1;
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    // web 环境配置类如下，实现 springBoot 的基础功能
    @Configuration
    static class WebConfig {

        // 创建 servlet Web容器
        @Bean
        public ServletWebServerFactory servletWebServerFactory(){
            return new TomcatServletWebServerFactory();
        }

        // 创建 Mvc 的 dispatcherServlet
        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

        // 注册 mvc 的 dispatcherServlet 指定拦截路径
        @Bean
        public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet) {
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }

        // 编写一个 controller
        @Bean("/hello")
        public Controller controller1() {
            return (request, response) -> {
                response.getWriter().print("hello");
                return null;
            };
        }
    }

    public static void main(String[] args) {

        // 1. ClassPathXmlApplicationContext 就是使用类路径去查找 xml 配置的路径 基于 classPath 的路径
        System.out.println("=========     ClassPathXmlApplicationContext 就是使用类路径去查找 xml 配置的路径 基于 classPath 的路径      ============");
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("spring/ioc/applicationContext/testKindsOfImplForApplicationContext.xml");

        Bean1 bean1 = classPathXmlApplicationContext.getBean(Bean1.class);

        for (String name : classPathXmlApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println(bean1);
        System.out.println(bean1.getBean2());

        // 2. FileSystemXmlApplicationContext 就是使用文件系统路径去查找 xml 配置的路径 基于 工程的 future 的路径
        System.out.println("=========     FileSystemXmlApplicationContext 就是使用文件系统路径去查找 xml 配置的路径 基于 工程的 future 的路径      ============");
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext =
                new FileSystemXmlApplicationContext("common/src/main/resources/spring/ioc/applicationContext/testKindsOfImplForApplicationContext.xml");

        Bean1 bean11 = fileSystemXmlApplicationContext.getBean(Bean1.class);

        for (String name : fileSystemXmlApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println(bean11);
        System.out.println(bean11.getBean2());

        // 3. annotationConfigApplicationContext, 基于 java 配置类来创建
        System.out.println("=========     annotationConfigApplicationContext, 基于 java 配置类来创建      ============");
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(Config.class);

        for (String name : annotationConfigApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        System.out.println(annotationConfigApplicationContext.getBean(Bean1.class).getBean2());

        // 4. annotationConfigServletWebServerApplicationContext, 基于 web 环境的，springBoot 内嵌 tomcat 应该使用的就是这种方式
        System.out.println("=========     annotationConfigApplicationContext, 基于 java 配置类来创建      ============");
        AnnotationConfigServletWebServerApplicationContext annotationConfigServletWebServerApplicationContext =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        for (String name : annotationConfigServletWebServerApplicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }

    }

}
