package com.aha.common.spring.ioc.beanLifeCycle.test01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(MyBeanPostProcessor.class);

    // InstantiationAwareBeanPostProcessor
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            log.debug("<<<<<< 1. 实例化之前执行, InstantiationAwareBeanPostProcessor 的 postProcessBeforeInstantiation, 这里返回的对象会替换掉原本的 bean");
        return null;
    }

    // InstantiationAwareBeanPostProcessor
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.debug("<<<<<< 3. 实例化之后执行, InstantiationAwareBeanPostProcessor 的 postProcessAfterInstantiation 这里如果返回 false 会跳过依赖注入阶段");
//            return false;
        }
        return true;
    }

    // InstantiationAwareBeanPostProcessor
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            log.debug("<<<<<< 4. 依赖注入阶段执行, InstantiationAwareBeanPostProcessor 的 postProcessProperties 如 @Autowired、@Value、@Resource");
        return pvs;
    }

    // BeanPostProcessor
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            log.debug("<<<<<< 6. 初始化之前执行, BeanPostProcessor 的 postProcessBeforeInitialization 这里返回的对象会替换掉原本的 bean, 如 @PostConstruct、@ConfigurationProperties");
        return bean;
    }

    // BeanPostProcessor
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            log.debug("<<<<<< 8. 初始化之后执行, BeanPostProcessor 的 postProcessAfterInitialization 这里返回的对象会替换掉原本的 bean, 如代理增强");
        return bean;
    }

    // DestructionAwareBeanPostProcessor
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean"))
            log.debug("<<<<<< 9. 销毁之前执行, DestructionAwareBeanPostProcessor 的 postProcessBeforeDestruction 如 @PreDestroy");
    }



}