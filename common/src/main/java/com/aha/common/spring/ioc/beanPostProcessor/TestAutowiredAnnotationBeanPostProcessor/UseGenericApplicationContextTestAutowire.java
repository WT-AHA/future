package com.aha.common.spring.ioc.beanPostProcessor.TestAutowiredAnnotationBeanPostProcessor;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 使用 GenericApplicationContext 来测试 AutowiredAnnotationBeanPostProcessor 和 CommonAnnotationBeanPostProcessor
 * 的作用
 * BeanFactory 要记住 DefaultListableBeanFactory
 * ApplicationContext 要记住 GenericApplicationContext
 *
 * 测试的目的
 * AutowiredAnnotationBeanPostProcessor 支撑 @Autowired @Value
 * CommonAnnotationBeanPostProcessor 支撑 @Resource @PostConstruct @PreDestroy
 *
 * 在 Bean1 中有 @Autowired + @Value 来注入 USER 环境变量的，不加 ContextAnnotationAutowireCandidateResolver 解析器 默认按照 Bean 来处理
 * 加了才能正常解析环境变量
 * context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
 */
public class UseGenericApplicationContextTestAutowire {

    public static void main(String[] args) {

        // ⬇️GenericApplicationContext 是一个【干净】的容器
        GenericApplicationContext context = new GenericApplicationContext();

        // ⬇️用原始方法注册三个 bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        // 在 Bean1 中有 @Autowired + @Value 来注入 USER 环境变量的，不加 ContextAnnotationAutowireCandidateResolver 解析器 默认按照 Bean 来处理
        // 加了才能正常解析环境变量
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // @Autowired @Value
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // @Resource @PostConstruct @PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());

        // ⬇️初始化容器
        // 执行beanFactory后处理器, 添加bean后处理器, 初始化所有单例
        context.refresh();

        System.out.println(context.getBean(Bean1.class));

        // ⬇️销毁容器
        context.close();

    }
}
