package com.aha.common.spring.ioc.beanFactory;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {

    public UserRegisteredEvent(Object source) {
        super(source);
        System.out.println("事件发布成功---------\n");
    }

}
