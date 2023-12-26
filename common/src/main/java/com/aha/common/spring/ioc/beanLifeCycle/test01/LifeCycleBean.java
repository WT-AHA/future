package com.aha.common.spring.ioc.beanLifeCycle.test01;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 2023-12-26T15:24:17.066+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.MyBeanPostProcessor   : <<<<<< 1. 实例化之前执行, InstantiationAwareBeanPostProcessor 的 postProcessBeforeInstantiation, 这里返回的对象会替换掉原本的 bean
 * 2023-12-26T15:24:17.067+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.LifeCycleBean         : 2. 执行构造方法进行实例化
 * 2023-12-26T15:24:17.068+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.MyBeanPostProcessor   : <<<<<< 3. 实例化之后执行, InstantiationAwareBeanPostProcessor 的 postProcessAfterInstantiation 这里如果返回 false 会跳过依赖注入阶段
 * 2023-12-26T15:24:17.068+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.MyBeanPostProcessor   : <<<<<< 4. 依赖注入阶段执行, InstantiationAwareBeanPostProcessor 的 postProcessProperties 如 @Autowired、@Value、@Resource
 * 2023-12-26T15:24:17.069+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.LifeCycleBean         : 5. 依赖注入: wangtong, 执行 @Autowired
 * 2023-12-26T15:24:17.069+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.MyBeanPostProcessor   : <<<<<< 6. 初始化之前执行, BeanPostProcessor 的 postProcessBeforeInitialization 这里返回的对象会替换掉原本的 bean, 如 @PostConstruct、@ConfigurationProperties
 * 2023-12-26T15:24:17.069+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.LifeCycleBean         : 7. 初始化，执行 @PostConstruct
 * 2023-12-26T15:24:17.069+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.MyBeanPostProcessor   : <<<<<< 8. 初始化之后执行, BeanPostProcessor 的 postProcessAfterInitialization 这里返回的对象会替换掉原本的 bean, 如代理增强
 * 2023-12-26T15:24:17.177+08:00  INFO 70708 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
 * 2023-12-26T15:24:17.181+08:00  INFO 70708 --- [           main] TestBeanLifeCycle01SpringBootApplication : Started TestBeanLifeCycle01SpringBootApplication in 0.682 seconds (process running for 0.899)
 * 2023-12-26T15:24:17.187+08:00  INFO 70708 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
 * 2023-12-26T15:24:17.192+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.MyBeanPostProcessor   : <<<<<< 9. 销毁之前执行, DestructionAwareBeanPostProcessor 的 postProcessBeforeDestruction 如 @PreDestroy
 * 2023-12-26T15:24:17.193+08:00 DEBUG 70708 --- [           main] c.a.c.s.i.b.test01.LifeCycleBean         : 10. 销毁之前，执行 @PreDestroy
 */
@Component
public class LifeCycleBean {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        log.debug("2. 执行构造方法进行实例化");
    }

    @Autowired
    public void autowire(@Value("${USER}") String user) {
        log.debug("5. 依赖注入: {}, 执行 @Autowired", user);
    }

    @PostConstruct
    public void init() {
        log.debug("7. 初始化，执行 @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        log.debug("10. 销毁之前，执行 @PreDestroy");
    }

}
