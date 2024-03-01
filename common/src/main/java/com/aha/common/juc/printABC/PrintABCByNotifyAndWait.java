package com.aha.common.juc.printABC;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * notify() 和 notifyAll() 都是用于在多线程编程中实现线程间通信的方法，它们都属于 Object 类中的方法。
 * 它们的主要区别在于唤醒等待中的线程的方式不同。
 *
 * notify():
 *
 * notify() 方法用于唤醒在当前对象上调用 wait() 方法进入等待状态的单个线程。
 * 如果有多个线程等待在该对象上，但只有一个线程能够被唤醒，并且是由 JVM 决定唤醒哪个线程。
 * 通常情况下，使用 notify() 方法时，选择唤醒哪个线程是不确定的，因此需要谨慎使用，以避免发生竞态条件或死锁等问题。
 *
 * notifyAll():
 *
 * notifyAll() 方法用于唤醒在当前对象上调用 wait() 方法进入等待状态的所有线程。
 * 当有多个线程等待在该对象上时，notifyAll() 方法会将它们全部唤醒，使它们都有机会竞争执行。
 * notifyAll() 方法的优点是可以更灵活地唤醒所有等待线程，避免了使用 notify() 时可能导致的一些问题。
 *
 * 总的来说，notify() 用于单个线程唤醒，而 notifyAll() 用于唤醒所有等待线程。
 * 在大多数情况下，notifyAll() 更安全、更可靠，因为它可以避免竞态条件，并且不会导致某些线程长时间等待而没有被唤醒的问题。
 */
public class PrintABCByNotifyAndWait {

    // 声明锁对象
    private static final Object LOCK = new Object();
    // a 是否已经执行
    private static final AtomicBoolean isExecuteA = new AtomicBoolean(false);
    // b 是否已经执行
    private static final AtomicBoolean isExecuteB = new AtomicBoolean(false);
    // c 是否已经执行
    private static final AtomicBoolean isExecuteC = new AtomicBoolean(true);

    private static void printMessage (int printCount, String message,
                                AtomicBoolean preCondition) {

        for (int i=0; i<printCount; i++) {
            synchronized (LOCK) {
                while (!preCondition.get()) {
                    // 如果 c 没有打印的话就等待，直到 c 打印之后
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(message);
                if ("A".equals(message)) {
                    isExecuteA.compareAndSet(false, true);
                    isExecuteB.compareAndSet(true, false);
                    isExecuteC.compareAndSet(true, false);
                } else if ("B".equals(message)) {
                    isExecuteA.compareAndSet(true, false);
                    isExecuteB.compareAndSet(false, true);
                    isExecuteC.compareAndSet(true, false);
                } else {
                    isExecuteA.compareAndSet(true, false);
                    isExecuteB.compareAndSet(true, false);
                    isExecuteC.compareAndSet(false, true);

                }
                // 唤醒所有的线程争抢锁
                LOCK.notifyAll();

            }
        }

    }

    public static void main(String[] args) {

        new Thread(() -> printMessage(10, "A", isExecuteC), "A").start();
        new Thread(() -> printMessage(10, "B", isExecuteA), "A").start();
        new Thread(() -> printMessage(10, "C", isExecuteB), "A").start();

        try {
            Thread.sleep(100 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
