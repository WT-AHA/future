1. com.aha.common.juc.thread.TestThread 几种常规创建线程的方式和它们的区别，
   还有 futureTask 的一些特性，还有是否可以看到方法体中抛出的异常信息，不打印异常信息的原因是因为提交的时 FutureTask

2. com.aha.common.java8.stream.MapAndFlatMap stream流中 map 和 flatMap 的区别

3. com.aha.common.juc.reentrantLock.ReentrantLockTestLockMethod 
   测试 ReentrantLock 的 lock 与 unLock 方法

4. JUC 的一个小问题：
   三个线程，一个线程打印 a，一个线程打印 b，一个线程打印 c，同时启动这三个线程，让它们循环以 abc 的方式进行打印 10 次
   com.aha.common.juc.printABC.PrintABCBySemaphore 使用信号量(Semaphore)的方式来实现
   com.aha.common.juc.printABC.PrintABCByNotifyAndWait 使用 wait 与 notify 来实现，
   简单介绍 notify 与 notifyAll 的区别

5. com.aha.common.juc.ForkJoinPool.FibonacciTask 使用 ForkJoinPool 来计算斐波那契数列 
   简单介绍 ForkJoinPool 与 ThreadPoolExecutor 的主要区别
   com.aha.common.juc.ForkJoinPool.MyRecursiveTask 对 ForkJoinPool 的基础概念和使用方法做了演示

6. com.aha.common.juc.executor.threadPoolExecutor.ThreadPoolExecutorUtils 提供一个自定义线程池的工具类
   使用双重校验锁的方式来实现 ThreadPoolExecutor 单例对象的获取
   com.aha.common.juc.executor.threadPoolExecutor.TestThreadPoolExecutor 简单使用了下 CountDownLatch
   再次探求了下 FutureTask 执行 run 方法体时不打印异常栈的问题；说明 submit 和 execute 提交任务的区别

7. com.aha.common.newfeature 是新特性的目录
   7.1 java8.stream stream 相关的
      7.1.1 com.aha.common.newfeature.java8.stream.MapAndFlatMap stream 的 .map 和 .flatMap 区别
      7.1.2 com.aha.common.newfeature.java8.stream.MyFunctionInterfaceTest 自定义函数式接口，使用 lambda 表达式来调用参数为函数式接口的方法

8. 
