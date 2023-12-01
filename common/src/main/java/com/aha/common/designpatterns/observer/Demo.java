package com.aha.common.designpatterns.observer;


import com.aha.common.designpatterns.observer.publisher.Publisher;
import com.aha.common.designpatterns.observer.subscriber.EmailNotificationListener;
import com.aha.common.designpatterns.observer.subscriber.LogOpenListener;

/**
 * 观察者：测试案例
 */
public class Demo {

    public static void main(String[] args) {

        // 创建事件发布者
        Publisher publisher = new Publisher();
        // 创建事件监听者，并维护好与订阅时间的关系
        publisher.eventManager.subscribe("open", new LogOpenListener("/path/to/log/log.txt"));

        publisher.eventManager.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            // 发布对应的事件，通知对应的事件订阅者
            publisher.openFile("test.txt");
            publisher.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
