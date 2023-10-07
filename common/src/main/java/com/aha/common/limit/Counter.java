package com.aha.common.limit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器方式实现限流
 *
 * 假设在 00:01 时发生一个请求，在 00:01-00:58 之间不在发送请求，在 00:59 时发送剩下的所有请求 n-1 (n 为限流请求数量)，在下一分钟的 00:01 发送 n 个请求，这样在 2 秒钟内请求到达了 2n - 1 个。
 * 设每分钟请求数量为 60 个，每秒可以处理 1 个请求，用户在 00:59 发送 60 个请求，在 01:00 发送 60 个请求 此时 2 秒钟有 120 个请求(每秒 60 个请求)，远远大于了每秒钟处理数量的阈值。
 */
public class Counter {

    /**
     * 最大访问数量
     */
    private final int limit = 10;
    /**
     * 访问时间差
     */
    private final long timeout = 1000;
    /**
     * 请求时间
     */
    private long time;
    /**
     * 当前计数器
     */
    private AtomicInteger reqCount = new AtomicInteger(0);

    public boolean limit() {
        long now = System.currentTimeMillis();

        // 单位时间内，直接比较请求数和 limit 的大小
        if (now < time + timeout) {
            // 单位时间内
            reqCount.addAndGet(1);
            return reqCount.get() <= limit;
        } else {
            // 超出单位时间， 将计数器设置为 0，将时间设置为当前时间内
            time = now;
            reqCount = new AtomicInteger(0);
            return true;
        }
    }

    public static void main(String[] args) {
        int a = -2;
        int b = a >> 1;
        int c = a >>> 1;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        // -2 是 1000 0010 右移 1 位 结果是 1000 0001
        System.out.println(b);
        System.out.println(Integer.toBinaryString(b));
        // -2 是 11111111111111111111111111111110 无符号右移 1 位 (无符号右移，符号位直接补 0) 结果是 01111111111111111111111111111111 正数的补码还是自己
        // 所以 c 为 2147483647
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));

    }

}
