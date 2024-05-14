package com.aha.common.newfeature.java21.vt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class createTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(4);

        // == 1、通过 Thread.ofVirtual() 创建
        Runnable fn = () -> {
            // your code here
            System.out.println(Thread.currentThread().getName() + "1");
            countDownLatch.countDown();
        };

        Thread thread1 = Thread.ofVirtual().start(fn);

        // == 2、通过 Thread.startVirtualThread() 、创建
        Thread thread2 = Thread.startVirtualThread(() -> {
            // your code here
            System.out.println(Thread.currentThread().getName() + "2");
            countDownLatch.countDown();
        });

        // 3、通过 Executors.newVirtualThreadPerTaskExecutor() 创建
        var executorService = Executors.newVirtualThreadPerTaskExecutor();

        executorService.submit(() -> {
            // your code here
            System.out.println(Thread.currentThread().getName() + "3");
            countDownLatch.countDown();
        });


        //4、通过 ThreadFactory 创建
        class CustomThread implements Runnable {
            @Override
            public void run() {
                // your code here
                System.out.println(Thread.currentThread().getName() + "4");
                countDownLatch.countDown();
            }
        }

        CustomThread customThread = new CustomThread();
        // 获取线程工厂类
        ThreadFactory factory = Thread.ofVirtual().factory();
        // 创建虚拟线程
        Thread thread4 = factory.newThread(customThread);
        // 启动线程
        thread4.start();

        try {
            countDownLatch.await();
            System.out.println("虚拟线程全部启动完毕");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
