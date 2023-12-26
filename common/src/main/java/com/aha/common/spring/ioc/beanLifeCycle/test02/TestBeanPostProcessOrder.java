package com.aha.common.spring.ioc.beanLifeCycle.test02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BeanPostProcessor 排序问题
 * 1. 实现了 PriorityOrdered 接口的优先级最高
 * 2. 实现了 Ordered 接口与加了 @Order 注解的平级, 按数字升序
 * 3. 其它的排在最后
 *
 * 16:37:30.926 [main] INFO com.aha.common.spring.ioc.beanLifeCycle.test02.TestBeanPostProcessOrder$P3 -- postProcessBeforeInitialization PriorityOrdered
 * 16:37:30.928 [main] INFO com.aha.common.spring.ioc.beanLifeCycle.test02.TestBeanPostProcessOrder$P4 -- postProcessBeforeInitialization Ordered
 * 16:37:30.928 [main] INFO com.aha.common.spring.ioc.beanLifeCycle.test02.TestBeanPostProcessOrder$P1 -- postProcessBeforeInitialization @Order(1)
 * 16:37:30.928 [main] INFO com.aha.common.spring.ioc.beanLifeCycle.test02.TestBeanPostProcessOrder$P2 -- postProcessBeforeInitialization @Order(2)
 * 16:37:30.928 [main] INFO com.aha.common.spring.ioc.beanLifeCycle.test02.TestBeanPostProcessOrder$P5 -- postProcessBeforeInitialization
 */
public class TestBeanPostProcessOrder {

    @Order(1)
    static class P1 implements BeanPostProcessor {
        private static final Logger log = LoggerFactory.getLogger(P1.class);

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessBeforeInitialization @Order(1)");
            return bean;
        }
    }

    @Order(2)
    static class P2 implements BeanPostProcessor {
        private static final Logger log = LoggerFactory.getLogger(P2.class);

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessBeforeInitialization @Order(2)");
            return bean;
        }

    }

    static class P3 implements BeanPostProcessor, PriorityOrdered {
        private static final Logger log = LoggerFactory.getLogger(P3.class);

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessBeforeInitialization PriorityOrdered");
            return bean;
        }

        @Override
        public int getOrder() {
            return 100;
        }
    }

    static class P4 implements BeanPostProcessor, Ordered {
        private static final Logger log = LoggerFactory.getLogger(P4.class);

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessBeforeInitialization Ordered");
            return bean;
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }

    static class P5 implements BeanPostProcessor {
        private static final Logger log = LoggerFactory.getLogger(P5.class);

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessBeforeInitialization");
            return bean;
        }
    }

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        List<BeanPostProcessor> list = new ArrayList<>(Arrays.asList(new P1(), new P2(), new P3(), new P4(), new P5()));
        list.sort(beanFactory.getDependencyComparator());

        list.forEach(processor->{
            processor.postProcessBeforeInitialization(new Object(), "");
        });

    }

}
