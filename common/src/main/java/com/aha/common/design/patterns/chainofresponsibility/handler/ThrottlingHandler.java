package com.aha.common.design.patterns.chainofresponsibility.handler;

import com.aha.common.design.patterns.chainofresponsibility.Handler;
import com.aha.common.design.patterns.chainofresponsibility.RequestSource;
import lombok.extern.slf4j.Slf4j;

/**
 * 限流处理器
 *
 * @author WT
 * date 2021/11/16
 */
@Slf4j
public class ThrottlingHandler extends Handler {

    // 请求数量
    private Integer requestCount;
    // 请求数量阈值：每分钟最大可以请求的数量
    private final Integer requestThreshold;
    // 计算请求数量的起始时间点
    private long currentTime;

    public ThrottlingHandler (Integer requestThreshold) {
        this.requestCount = 0;
        this.requestThreshold = requestThreshold;
        // 创建对象时,取系统当前时间为计数起始时间点
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * 判断一分钟的请求量是否超过阈值
     *
     * @param requestSource 请求时需要携带的资源，用于处理链验证和流转
     * @return 执行下一个处理器或停止处理链
     */
    @Override
    public Boolean doHandler(RequestSource requestSource) {

        // 每隔 60000ms 重置一下 请求数量和时间点
        if (System.currentTimeMillis() > currentTime + 60000) {
            requestCount = 0;
            currentTime = System.currentTimeMillis();
        }

        requestCount ++;

        if (requestCount > requestThreshold) {
            log.error("一分钟内的请求量已经超过了阈值，阈值为：{}，当前请求次数为：{}", requestThreshold, requestCount);
            log.error("Request limit exceeded!");
            // 只是标记当前线程的中断状态但是没有实际中断
            // Thread.currentThread().interrupt();
            // log.error("只是标记当前线程的中断状态但是没有实际中断：{}", Thread.currentThread().isInterrupted());
            // stop() 可以停止当前线程 但是是废弃方法了 不建议使用
            // Thread.currentThread().stop();
            return false;
        }

        // 进入下一个处理器链
        return handleNext(requestSource);

    }

}
