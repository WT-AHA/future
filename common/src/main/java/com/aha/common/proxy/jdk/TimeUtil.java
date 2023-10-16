package com.aha.common.proxy.jdk;

/**
 * 用于增强交班费这个方法
 *
 * @author WT
 * @date 2022/04/21 17:30:11
 */
public class TimeUtil {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void start() {
        threadLocal.set(System.currentTimeMillis());
    }

    // 结束时打印耗时
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + "方法耗时: " + (finishTime - threadLocal.get()) + "ms");
    }

}
