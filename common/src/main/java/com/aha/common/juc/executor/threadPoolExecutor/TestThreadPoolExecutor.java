package com.aha.common.juc.executor.threadPoolExecutor;

import java.util.concurrent.*;

/**
 * 1. 对于参数：execute 只能传入 runnable，submit 可以传入 runnable 或者 callable
 *
 * 因为 submit 提交会将传入的参数 包装成 futureTask 所以会有以下不同
 * 2. 对于返回值：execute 执行完没有返回值，submit 有返回值
 * 3. 对于异常处理方式不同：execute() 方法中如果任务执行过程中发生了异常，则异常会被传递到任务提交的地方，
 * 并由任务提交的线程来处理。
 * 而submit()方法中，如果任务执行过程中发生了异常，异常将被封装在Future对象中，
 * 直到调用Future.get()方法时才会将异常抛出。
 *
 * 4. submit()方法返回的Future对象可以用来取消任务，而execute()方法没有提供取消任务的方法。
 *
 *
 */
public class TestThreadPoolExecutor {

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtils.getThreadPoolExecutor();

        Future<?> submit = threadPoolExecutor.submit(() -> {

            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " submit 提交 callable running");

            /**
             * submit 提交任务因为是要有返回值的，所以提交任务是通过 futureTask 来提交的，
             * 而 futureTask 的 run 方法中会将异常捕获，赋值给一个叫 outcome 的属性，
             * java.util.concurrent.FutureTask.run()
             * outcome = t;
             * 在执行 submit 的时候控制台不会直接打印异常栈信息
             * 在执行 futureTask.get() 方法时，当抛出相应的异常 源码如下
             * private V report(int s) throws ExecutionException {
             *         Object x = outcome;
             *         if (s == NORMAL)
             *             return (V)x;
             *         if (s >= CANCELLED)
             *             throw new CancellationException();
             *         throw new ExecutionException((Throwable)x);
             *     }
             */
//            int i = 1 / 0;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(threadName + " submit 提交 callable is completed");
//            countDownLatch.countDown();

        });

//        try {
//            Object o = submit.get();
//            System.out.println(o);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "等待");
            countDownLatch.await();
            System.out.println(threadName + "执行完成");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
