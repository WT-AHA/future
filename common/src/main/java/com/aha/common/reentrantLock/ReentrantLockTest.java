package com.aha.common.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();


        for (int i=0; i<10; i++) {
            new Thread(() -> {
                reentrantLock.lock();
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                reentrantLock.unlock();
            }).start();
        }

    }


}
