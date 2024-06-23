package com.aha.common.design.patterns.observer.subscriber;

import java.io.File;

/**
 * 通用观察者接口
 */
public interface EventListener {

    void handle(String eventType, File file);

}
