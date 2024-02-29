package com.aha.common.juc.printABC;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量
 * Semaphore 是 Java 并发包中的一个类，它是一种计数信号量，用于控制同时访问某个资源的线程数量。
 * Semaphore 维护了一个许可的计数，线程可以通过 acquire() 方法获取许可，也可以通过 release() 方法释放许可。
 *
 * 构造方法：
 *
 * Semaphore(int permits): 创建具有给定许可数的 Semaphore 对象， permits 为许可的数量。
 * Semaphore(int permits, boolean fair): 创建具有给定许可数和公平性设置的 Semaphore 对象，
 * fair 为 true 表示公平性，即等待时间最长的线程会优先获得许可。
 *
 * 常用方法：
 *
 * acquire(): 获取一个许可，如果没有可用的许可则阻塞直到有可用的许可为止。
 * acquire(int permits): 获取指定数量的许可，如果没有足够的可用许可则阻塞直到有足够的可用许可为止。
 * tryAcquire(): 尝试获取一个许可，如果获取成功返回 true，否则返回 false，不会阻塞。
 * tryAcquire(long timeout, TimeUnit unit): 尝试在指定的时间内获取一个许可，
 * 如果在指定时间内获取成功返回 true，否则返回 false。
 * release(): 释放一个许可。
 * release(int permits): 释放指定数量的许可。
 * availablePermits(): 返回当前可用的许可数量。
 * getQueueLength(): 返回正在等待获取许可的线程数量。
 * hasQueuedThreads(): 查询是否有线程在等待获取许可。
 * tryAcquireUninterruptibly(): 与 tryAcquire() 类似，但不响应中断。
 * tryAcquire(int permits, long timeout, TimeUnit unit):
 * 与 tryAcquire(long timeout, TimeUnit unit) 类似，但可以获取指定数量的许可。
 *
 */
public class PrintABCBySemaphore {

    // permits 信号量个数，为 0 的时候 ； acquire 方法获取不到 permits 会阻塞
    // tryAcquire 不会阻塞
    private static final Semaphore semaphoreA = new Semaphore(0);
    private static final Semaphore semaphoreB = new Semaphore(0);
    private static final Semaphore semaphoreC = new Semaphore(0);

    static class PrintThread extends Thread {

        private final String message;
        private final Semaphore currentSemaphore;
        private final Semaphore nextSemaphore;

        public PrintThread(String message, Semaphore currentSemaphore, Semaphore nextSemaphore) {
            this.message = message;
            this.currentSemaphore = currentSemaphore;
            this.nextSemaphore = nextSemaphore;
        }

        @Override
        public void run() {
            try {
                // 控制打印次数，可以根据需要修改
                for (int i = 0; i < 10; i++) {
//                    Thread.sleep(100);
                    currentSemaphore.acquire();
                    System.out.print(message);
                    nextSemaphore.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        PrintThread threadA = new PrintThread("A", semaphoreA, semaphoreB);
        PrintThread threadB = new PrintThread("B", semaphoreB, semaphoreC);
        PrintThread threadC = new PrintThread("C", semaphoreC, semaphoreA);

        threadA.start();
        threadB.start();
        threadC.start();

        // 释放一个permit，开始执行线程A
        semaphoreA.release();

    }
}

