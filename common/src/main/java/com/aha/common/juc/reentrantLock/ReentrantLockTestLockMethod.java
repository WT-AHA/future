package com.aha.common.juc.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 ReentrantLock 的 lock 与 unLock 方法
 * 需求：
 *   打印 AAA 的线程先执行 10 次
 *   然后
 *   打印 BBB 的线程在执行 10 次
 */
public class ReentrantLockTestLockMethod {

    // 锁对象
    private static final Lock LOCK_A = new ReentrantLock();

    private static void printString(Lock currentLock, String message, int printCount) {

        currentLock.lock();
        for (int i=0; i<printCount; i ++) {
            System.out.println(message);
        }
        currentLock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> printString(LOCK_A, "aaa", 10)).start();
        new Thread(() -> printString(LOCK_A, "bbb", 10)).start();

        Thread.sleep(100 * 1000);

    }

}
