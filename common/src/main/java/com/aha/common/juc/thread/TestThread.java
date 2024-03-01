package com.aha.common.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 结论：
 *  Thread.start 方法不会主线程阻塞线程
 *
 *  futureTask.run,callable.call,runnable.run,thread.run 方法会阻塞线程，因为它们没有新开线程,
 *  直接使用主线程调用对应的方法
 *
 *  所以 这些方法应该作为新开线程被调用的方法，这种都不会阻塞
 *  new Thread(runnable).start() 可以传入 runnable
 *  new Thread(futureTask).start()  可以传入 futureTask
 *  new Thread(new FutureTask(new Callable)).start()
 *
 *  注意：
 *  callable 是可以有返回值的，call 方法可以抛出异常
 *  如果使用 futureTask 包装 callable 方到 Thread 中的话
 *  要获取返回值需要调用 futureTask.get() 这个方法会阻塞主线程 但是他也是开了新线程的  这个阻塞是为了执行结果
 *  不管 futureTask 包装 callable 还是 runnable 只要调用 futureTask.get() 就会阻塞主线程
 *  futureTask 还有取消运行操作
 *
 *  对于多线程中 方法体(callable 的 call ；runnable 的 run ；futureTask 的 run)中发生异常，在主线程却不打印的情况
 *  只有 catch 了，才不会抛出异常
 *  单纯使用 new Thread(runnable).start() 是可以看到 runnable run 的异常的
 *  但是  new Thread(futureTask).start() 不行
 *  是因为 futureTask 的 run 方法 将异常捕获了，没有抛出来，源码如下
 *  public void run() {
 *         if (state != NEW ||
 *             !RUNNER.compareAndSet(this, null, Thread.currentThread()))
 *             return;
 *         try {
 *             Callable<V> c = callable;
 *             if (c != null && state == NEW) {
 *                 V result;
 *                 boolean ran;
 *                 try {
 *                     result = c.call();
 *                     ran = true;
 *                 } catch (Throwable ex) {
 *                     // 将异常捕获了，没有抛出所以在控制台，不会打印相关的异常信息
 *                     result = null;
 *                     ran = false;
 *                     setException(ex);
 *                 }
 *                 if (ran)
 *                     set(result);
 *             }
 *         } finally {
 *             // runner must be non-null until state is settled to
 *             // prevent concurrent calls to run()
 *             runner = null;
 *             // state must be re-read after nulling runner to prevent
 *             // leaked interrupts
 *             int s = state;
 *             if (s >= INTERRUPTING)
 *                 handlePossibleCancellationInterrupt(s);
 *         }
 *     }
 *
 */
public class TestThread {

    public static void main(String[] args) {


        // 1. 匿名内部类 实现 runnable 来创建线程 不会阻塞主线程
        Thread runnableThread = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " running");
                int i = 1/0;
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("5a");
        });

        class MyThread extends Thread {

            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("6b");
            }

        }

        // 2. 继承 Thread 类 不会阻塞主线程
        MyThread myThread = new MyThread();

        // 3. 实现 callable 范型是返回值的类型
        Callable<String> callable = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " running");
                int i = 1/0;
                System.out.println(Thread.currentThread().getName() + " run completed");
                return "c";
            }

        };

        // lambda 简化写法
//        Callable<String> callableLambda = () -> "c";

        Callable<String> callableLambda = () -> {
            System.out.println(Thread.currentThread().getName() + " running");
            int i = 1/0;
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " run completed");
            return "1c";
        };



        // 4. 或者使用 FutureTask 然后传入 callable 或者 runnable 接口
        FutureTask<String> futureTaskCallable = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " running");
                Thread.sleep(2000);
                System.out.println("2d");
                return "2d";
            }
        });



        FutureTask<String> futureTaskRunnable = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    int i = 1/0;
                    Thread.sleep(30000);
                    System.out.println("3e");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "result");


        try {

            // 5a 可以抛出异常
//            runnableThread.start();
//            System.out.println("runnableThread.start()");
//            // 6b
//            myThread.run();
//            System.out.println("myThread.start()");
            // 1e futureTask.run
//            new Thread(futureTaskRunnable).start();
//            System.out.println("futureTaskRunnable.run()");
//            // 会阻塞主线程 1c
//            String result = callableLambda.call();
            FutureTask<String> futureTask = new FutureTask<>(callable);
            new Thread(futureTask).start();
//            new Thread(runnableThread).start();
//            Thread.sleep(1000);
//            // 取消执行，已经在执行的话；传入 true 仍然中断，false 不进行中断了
//            futureTask.cancel(true);
//            System.out.println("futureTask isCancelled : " + futureTask.isCancelled());
//            System.out.println(futureTask.get());

//            // 执行完之后的返回结果
//            System.out.println(result);
//            System.out.println("callableLambda.call()");
//            // 会阻塞主线程 2d
//            futureTaskCallable.run();
//            System.out.println("futureTaskCallable.run()");


//            System.out.println("到了主线程的末尾");
//            Thread.sleep(100 * 1000);

//            int i = 1/0;

        }
        catch (Exception e) {
            throw new RuntimeException();
//            System.out.println(222);
        }
        finally {
            System.out.println(111);
        }


    }

}
