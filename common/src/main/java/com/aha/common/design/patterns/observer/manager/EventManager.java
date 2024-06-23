package com.aha.common.design.patterns.observer.manager;


import com.aha.common.design.patterns.observer.subscriber.EventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息管理者
 * 维护 事件和其订阅者列表(订阅者要统一实现一个 接口，接口提供一个处理事件的方法，这样事件管理者在被时间发布者调用 通知方法的时候，就可以遍历
 * 订阅者列表，然后执行他们的 handle 方法，就通知到了每一个时间的订阅者)
 * 提供 订阅方法，将订阅者放到对应的订阅者列表中
 * 提供 事件通知方法，当事件发布者有新的事件的时候，调用此方法，通知所有的订阅者执行相应的处理逻辑
 */
public class EventManager {

    // 维护事件类型和监听者列表的对应关系
    Map<String, List<EventListener>> listeners = new HashMap<>();

    /**
     * 构造器 - 用于初始化 时间管理器
     * @param operations 事件类型
     */
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, File file) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.handle(eventType, file);
        }
    }

}
