package com.aha.common.designpatterns.observer.publisher;


import com.aha.common.designpatterns.observer.manager.EventManager;

import java.io.File;

/**
 * 消息发布者
 */
public class Publisher {

    // 事件管理者，存放 事件和订阅者的对应关系
    public EventManager eventManager;
    private File file;

    public Publisher() {
        // 生成两种类型的事件可供订阅
        this.eventManager = new EventManager("open", "save");
    }

    // ================   观察者模式的核心就是消息发布者在发布消息的时候通过时间管理器通知消息订阅者(其实就是调用订阅者的处理接口)    ==================== //

    // 当消息发布者触发对应事件的时候，通知消息订阅者
    public void openFile(String filePath) {
        this.file = new File(filePath);
        eventManager.notify("open", file);
    }

    // 当消息发布者触发对应事件的时候，通知消息订阅者
    public void saveFile() throws Exception {
        if (this.file != null) {
            eventManager.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }

}
