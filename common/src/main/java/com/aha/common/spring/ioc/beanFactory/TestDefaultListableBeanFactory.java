package com.aha.common.spring.ioc.beanFactory;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用 DefaultListableBeanFactory 来创建一些 bean 实例
 */
public class TestDefaultListableBeanFactory {

	interface Inter {

	}

	static class Bean1 {

		public Bean1() {
			System.out.println("构造bean1");
		}

		@Autowired
		private Bean2 bean2;

		public Bean2 getBean2() {
			return bean2;
		}

		// @Autowired 先按照类型确定唯一 bean，根据类型不能确定唯一，然后按照变量的名称确定唯一
		// 都确定不了的时候，就会根据变量类型找到多个就会报错
		// Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.aha.ioc.beanFactory.TestDefaultListableBeanFactory$Inter' available: expected single matching bean but found 2: bean3,bean4
		@Autowired
//      @Autowired 可以跟 Qualifier 组合使用 用 Qualifier 来指定 bean 名称 找对应的 bean 来确定唯一
//		@Qualifier("bean4")
		// 当 Resource 没有指定 name 属性的属性 使用变量名匹配 bean 指定了 按照 name 属性匹配 bean
		@Resource(name = "bean4")
		private Inter bean3;

		public Inter getInter() {
			return bean3;
		}
	}

	static class Bean2 {

		public Bean2() {
			System.out.println("构造bean2");
		}
	}

	static class Bean3 implements Inter {

	}

	static class Bean4 implements Inter {

	}

	@Configuration
	static class Config {

		@Bean
		public Bean1 bean1() {
			return new Bean1();
		}

		@Bean
		public Bean2 bean2() {
			return new Bean2();
		}

		@Bean
		public Bean3 bean3() {
			return new Bean3();
		}

		@Bean
		public Bean4 bean4() {
			return new Bean4();
		}

	}

	public static void main(String[] args) {


		// beanFactory 最主要实现
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 创建 Bean 定义信息，根据自己编写的 class 获取 对应 class 的 beanDefinition
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(Config.class)
				.setScope("singleton")
				.getBeanDefinition();

		// 向 bean 工厂中 注册 bean 定义信息
		beanFactory.registerBeanDefinition("config", beanDefinition);

		// 在这个时候打印 beanDefinitionName 的集合，发现只包含了 config 类的 beanDefinitionName 不包含 Bean1 -> Bean4
		// 说明现在的 Config 类中的 @Configuration 和 @Bean 是不生效的
		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			System.out.println("<<<<<<<<< beanFactory.registerBeanDefinition 之后包含的 beanDefinitionName :" + beanDefinitionName);
		}

		// 给 BeanFactory 添加一些常用的后置处理器 主要包含
		// org.springframework.context.annotation.internalConfigurationAnnotationProcessor
		// org.springframework.context.annotation.internalAutowiredAnnotationProcessor
		// org.springframework.context.annotation.internalCommonAnnotationProcessor
		// org.springframework.context.event.internalEventListenerProcessor
		// org.springframework.context.event.internalEventListenerFactory
		AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			System.out.println("<<<<<<<<<  AnnotationConfigUtils.registerAnnotationConfigProcessors 之后包含的 beanDefinitionName :" + beanDefinitionName);
		}

		// 执行 BeanFactoryPostProcessor， BeanFactoryPostProcessor 主要是对 BeanDefinition 进行增强
		// 这里主要执行的是 对应 internalConfigurationAnnotationProcessor 的 ConfigurationClassPostProcessor，来支撑 @Configuration 注解的功能
		// 还有 EventListenerMethodProcessor
		beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
			System.out.println("<<< 执行了" + beanFactoryPostProcessor);
			beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		});

		// 所以这边应该包含了 Bean1 -> Bean4 的 beanDefinition 了 因为可以解析 @Configuration 注解了
		for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
			System.out.println("<<<<<<<<< beanFactoryPostProcessor.postProcessBeanFactory 之后包含的 beanDefinitionName :" + beanDefinitionName);
		}

		// 代码块 1
		// 发现这边返回了 null, 是因为 现在的 beanFactory 还不支持 @Autowired @Resource 这些注解
//		Bean1 bean1 = beanFactory.getBean(Bean1.class);
//		System.out.println("<<<<<< 执行了 beanFactoryPostProcessor.postProcessBeanFactory 之后的 bean1.getBean2(): " + bean1.getBean2());

		// 执行 BeanPostProcessor, 针对 bean 的生命周期的各个阶段提供扩展, 例如 @Autowired @Resource ...
		// @Autowired 对应 AutowiredAnnotationBeanPostProcessor
		// @Resource 对应 CommonAnnotationBeanPostProcessor
		assert beanFactory.getDependencyComparator() != null;
		beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream()
				// 功能相似的 BeanPostProcessor 例如  AutowiredAnnotationBeanPostProcessor CommonAnnotationBeanPostProcessor
				// 加载的顺序就至关重要的，会根据 beanFactory 的  DependencyComparator 来进行排序， BeanPostProcessor 都维护了 order
				// 属性 AutowiredAnnotationBeanPostProcessor 为 Ordered.LOWEST_PRECEDENCE - 2
				// CommonAnnotationBeanPostProcessor 为 Ordered.LOWEST_PRECEDENCE - 3 越小优先级越高
				// 所以 common 先加载， autowired 后加载，所以在 @Autowired 和 @Resource 都存在的时候应该按照 @Resource 来注入
				.sorted(beanFactory.getDependencyComparator())
				.forEach(beanPostProcessor -> {
					System.out.println("<<< 执行beanFactory.addBeanPostProcessor: " + beanPostProcessor);
					beanFactory.addBeanPostProcessor(beanPostProcessor);
				});

		// 准备好所有单例, beanFactory 的单例是懒加载的, spring 的 bean 默认是单例的，应该提前加载好，执行下面这个方法
		beanFactory.preInstantiateSingletons();

		// 这边不能与 代码块1 同时出现，代码块1 在没有添加 BeanPostProcessor 之前就获取了 bean 这边在获取也不会执行 beanPostProcessor 了
		// 发现这边返回了 null, 是因为 现在的 beanFactory 还不支持 @Autowired @Resource 这些注解
		Bean1 bean1 = beanFactory.getBean(Bean1.class);
		System.out.println("<<<<<< 执行了 beanFactoryPostProcessor.postProcessBeanFactory 之后的 bean1.getBean2(): " + bean1.getBean2());

		// BeanPostProcessor 加载顺序问题
		System.out.println("<<<<<< 执行了 beanFactoryPostProcessor.postProcessBeanFactory 之后的 bean1.getBean2(): " + bean1.getInter());


//       学到了什么:
//       a. beanFactory 不会做的事
//          1. 不会主动调用 BeanFactory 后处理器
//          2. 不会主动添加 Bean 后处理器
//          3. 不会主动初始化单例
//          4. 不会解析beanFactory 还不会解析 ${ } 与 #{ }
//       b. bean 后处理器会有排序的逻辑

	}

}