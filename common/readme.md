1. com.aha.common.juc.thread.TestThread 几种常规创建线程的方式和它们的区别，
   还有 futureTask 的一些特性，还有是否可以看到方法体中抛出的异常信息

2. com.aha.common.java8.stream.MapAndFlatMap stream流中 map 和 flatMap 的区别

3. com.aha.common.juc.reentrantLock.ReentrantLockTestLockMethod 
   测试 ReentrantLock 的 lock 与 unLock 方法

4. JUC 的一个小问题：
   三个线程，一个线程打印 a，一个线程打印 b，一个线程打印 c，同时启动这三个线程，让它们循环以 abc 的方式进行打印 10 次
   com.aha.common.juc.printABC.PrintABCBySemaphore 使用信号量(Semaphore)的方式来实现

5. com.aha.common.juc.ForkJoinPool.FibonacciTask 使用 ForkJoinPool 来计算斐波那契数列 
   简单介绍 ForkJoinPool 与 ThreadPoolExecutor 的主要区别
   com.aha.common.juc.ForkJoinPool.MyRecursiveTask 对 ForkJoinPool 的基础概念和使用方法做了演示
