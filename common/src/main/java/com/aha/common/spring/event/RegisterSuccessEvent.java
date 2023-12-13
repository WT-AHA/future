package com.aha.common.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 注册成功之后，发送一个事件（比如说给用户发送一个欢迎短信）
 * 自定义事件 RegisterSuccessEvent 继承 ApplicationEvent
 */
public class RegisterSuccessEvent extends ApplicationEvent {

    public RegisterSuccessEvent(RegisterInfo registerInfo) {
        super(registerInfo);
    }

}
