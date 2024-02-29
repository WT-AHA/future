package com.aha.common.juc.printABC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 练习题：
 *  启动三个线程，线程1 打印每隔 1s 打印一次 "a"
 *  线程2 打印每隔 1s 打印一次 "b"
 *  线程3 打印每隔 1s 打印一次 "c"
 *  解法1
 */
public class QuestionForPrintABCTest01 {

    // 锁对象
    private static final Object LOCK_OBJECT = new Object();
//    private static  volatile char = 'a';

    static class TwoLockPrinter implements Runnable {

        @Override
        public void run() {

        }

    }

    public static void main(String[] args) throws InterruptedException {

//        Thread threadA = new Thread(() -> printString("a"));
//        Thread threadB = new Thread(() -> printString("b"));
//        Thread threadC = new Thread(() -> printString("c"));

//        threadA.start();
//        threadA.join();
//
//        threadB.start();
//        threadB.join();
//
//        threadC.start();
//        threadC.join();

    }

}
