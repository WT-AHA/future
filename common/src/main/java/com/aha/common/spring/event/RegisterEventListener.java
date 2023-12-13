package com.aha.common.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听者
 *
 * 提供一个回调方法，当事件发布之后，执行回调方法
 * 底层逻辑就是 时间管理器 维护了 事件和对应的监听者的实例列表，当有事件发布之后，事件管理器遍历监听者列表
 * 执行他们的回调方法即可，是观察者模式，参考 designPattern 中的 observer 模式
 *
 * @EventListener(RegisterSuccessEvent.class) 指定要监听的事件
 */
@Slf4j
@Component
public class RegisterEventListener {

    // 指定异步，不加默认是同步，不太建议使用，一般异步建议使用自定义线程池
    @Async
    // 指定要监听的事件
    @EventListener(RegisterSuccessEvent.class)
    public void onApplicationEvent(RegisterSuccessEvent event) {
        RegisterInfo registerInfo = (RegisterInfo) event.getSource();
        log.info("监听到消息, {} 注册成功", registerInfo.getUsername());
        // 执行发送欢迎短信的逻辑
    }


}

