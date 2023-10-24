package com.aha.common.designpatterns.observer.subscriber;

import java.io.File;

/**
 * 收到通知后发送邮件
 */
public class EmailNotificationListener implements EventListener {

    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void handle(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}
