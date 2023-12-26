package com.aha.common.spring.ioc.beanLifeCycle.test02;

import java.util.ArrayList;
import java.util.List;

/**
 * spring ioc 是怎么实现对 bean 做了这么多扩展点的呢?
 * spring 使用的模板方法的设计模式，将 bean 的增强逻辑都封装到 beanPostProcessor 中，代码如下
 */
public class TestMyBeanFactory {

    interface BeanPostProcessor {

        // 对依赖注入阶段的扩展
        void inject(Object bean);

    }

    // 模板方法  Template Method Pattern
    static class MyBeanFactory {

        // 存储需要执行的 BeanPostProcessor
        private List<BeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(BeanPostProcessor processor) {
            processors.add(processor);
        }

        public Object createBean () {
            Object bean = new Object();

            // 模板方法的形式来执行 BeanPostProcessor 来对 bean 进行增强
            for (BeanPostProcessor processor : processors) {
                processor.inject(bean);
            }
            System.out.println("初始化 " + bean);
            return bean;
        }

    }

    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        // 添加需要增强的逻辑  对应的  BeanPostProcessor; 当然 spring 是直接从 容器 中来获取的，
        // 然后 for 循环添加的 这边也会被改造成 模板方法的形式
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Autowired"));
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Resource"));
        beanFactory.createBean();
    }

}
