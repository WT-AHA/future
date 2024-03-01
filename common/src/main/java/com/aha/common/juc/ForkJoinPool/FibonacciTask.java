package com.aha.common.juc.ForkJoinPool;

import org.springframework.util.StopWatch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 使用 ForkJoinPool 来计算斐波那契数列
 *
 * ForkJoinPool 与 ThreadPoolExecutor 的主要区别是
 * ForkJoinPool 的线程池大小是动态调整的，他还支持任务分割和合并的线程池实现，能够自动地处理任务的拆分和合并
 * ForkJoinPool 是基于工作窃取（Work-Stealing）算法实现的线程池，ForkJoinPool 中每个线程都有自己的工作队列，用于存储待执行的任务。
 * 当一个线程执行完自己的任务之后，会从其他线程的工作队列中窃取任务执行，以此来实现任务的动态均衡和线程的利用率最大化。
 *
 * ThreadPoolExecutor 是基于任务分配（Task-Assignment）算法实现的线程池，ThreadPoolExecutor 中线程池中有一个共享的工作队列，
 * 所有任务都将提交到这个队列中。线程池中的线程会从队列中获取任务执行，如果队列为空，则线程会等待，直到队列中有任务为止。
 *
 */
public class FibonacciTask extends RecursiveTask<Integer> {

    private final int n;

    public FibonacciTask (int n) {
        this.n = n;
    }

    // 相当于是 runnable 的 run  callable 的 call
    @Override
    protected Integer compute() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());

        if (n<=1) {
            return n;
        } else {
            // 还没有计算完，创建两个子任务取计算 n-1 和 n-2
            FibonacciTask ft1 = new FibonacciTask(n - 1);
            // ft1 异步执行时候需要先 fork() 后面要结果的时候 在 join()
            ft1.fork();
//            Integer ft1Result = ft1.compute();
            FibonacciTask ft2 = new FibonacciTask(n - 2);
            // ft2 同步执行
//            Integer ft2Result = ft2.compute();
            ft2.fork();
//
            // 这边同步异步执行其实不大， 主要是看 forkJoinPool.invoke(fibonacciTask) 怎么调度的，切分合并任务的
//            return ft1.join() + ft2Result;
//            return ft1Result + ft2Result;
            return ft1.join() + ft2.join();

        }

    }

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FibonacciTask fibonacciTask = new FibonacciTask(10);
        Integer result = forkJoinPool.invoke(fibonacciTask);
        System.out.println(result);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());

    }


}
