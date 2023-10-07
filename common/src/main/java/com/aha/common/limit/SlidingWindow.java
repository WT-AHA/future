package com.aha.common.limit;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/**
 * 滑动窗口进行计数，然后进行限流
 */
public class SlidingWindow {

    /**
     * 间隔秒数
     */
    private final int seconds;

    /**
     * 最大限流
     */
    private final int max;

    private final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<Long>();

    public SlidingWindow(int max, int seconds) {

        this.seconds = seconds;
        this.max = max;

        /*
         * 永续线程执行清理queue 任务
         */
        new Thread(() -> {
            while (true) {
                try {
                    // 等待 间隔秒数-1 执行清理操作
                    Thread.sleep((seconds - 1) * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clean();
            }
        }).start();

    }

    /**
     * 获取令牌，并且添加时间
     */
    public void take() {

        long start = System.currentTimeMillis();

        int size = sizeOfValid();
        if (size > max) {
            System.err.println("超限");

        }
        synchronized (queue) {
            if (sizeOfValid() > max) {
                System.err.println("超限");
                System.err.println("queue中有 " + queue.size() + " 最大数量 " + max);
            }
            this.queue.offer(System.currentTimeMillis());
        }
        System.out.println("queue中有 " + queue.size() + " 最大数量 " + max);


    }

    public int sizeOfValid() {
        Iterator<Long> it = queue.iterator();
        long ms = System.currentTimeMillis() - seconds * 1000L;
        int count = 0;
        while (it.hasNext()) {
            long t = it.next();
            if (t > ms) {
                // 在当前的统计时间范围内
                count ++;
            }
        }

        return count;
    }

    /**
     * 清理过期的时间
     */
    public void clean() {

        // 当前时间减去间隔描述
        long c = System.currentTimeMillis() - seconds * 1000L;

        Long tl = null;
        // 清理线程的时候会判断 队列中的时间是否是已过期的任务 是已过期的任务才会进行清理， 是否过期是根据时间来判断的
        while ((tl = queue.peek()) != null && tl < c) {
            System.out.println("清理数据");
            queue.poll();
        }
    }

    public static void main(String[] args) throws Exception {

        final SlidingWindow timeWindow = new SlidingWindow(10, 1);

        // 测试3个线程
        IntStream.range(0, 3).forEach((i) -> {
            new Thread(() -> {

                while (true) {

                    try {
                        Thread.sleep(new Random().nextInt(20) * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeWindow.take();
                }

            }).start();

        });

    }

}
