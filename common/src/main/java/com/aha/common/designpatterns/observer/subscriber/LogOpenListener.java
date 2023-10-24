package com.aha.common.designpatterns.observer.subscriber;

import java.io.File;

/**
 * 收到通知后在日志中记录一条消息
 */
public class LogOpenListener implements EventListener {

    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void handle(String eventType, File file) {
        System.out.println("Save log to" + log + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}
