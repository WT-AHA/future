package com.aha.common.spring.event;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring event 使用很简单
 * 1. 自定义事件 继承 extends ApplicationEvent
 * 2. 自定义事件监听者
 *  2.1 指定监听的事件    @EventListener(RegisterSuccessEvent.class)
 *  2.2 可以指定异步监听  @Async
 * 3. 发布事件           applicationContext.publishEvent(new RegisterSuccessEvent(register));
 */
@RestController
public class TestSpringEventController {

    @Resource
    private PublishEventService publishEventService;

    @GetMapping("/test/spring/event")
    public void testSpringEvent () {
        publishEventService.publishEvent();
    }

}
