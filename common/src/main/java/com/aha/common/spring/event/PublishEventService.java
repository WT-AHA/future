package com.aha.common.spring.event;

import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * 发布事件
 * 直接使用 ApplicationContext 来发布
 * applicationContext.publishEvent
 */
@Service
public class PublishEventService {

    @Resource
    private ApplicationContext applicationContext;

    public void publishEvent () {

        RegisterInfo register = new RegisterInfo().setUsername("王二哈");
        applicationContext.publishEvent(new RegisterSuccessEvent(register));

    }

}
