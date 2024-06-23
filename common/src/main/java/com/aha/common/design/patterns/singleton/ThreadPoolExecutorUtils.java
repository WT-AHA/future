package com.aha.common.design.patterns.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 对应语雀 Treasure/JAVA并发/线程池 和 Treasure/JAVA并发/理论基础/Synchronize Volatile ThreadLocal 关键字
 * 双重校验锁 ThreadPoolExecutorUtils 单例模式 主要时在没有单例对象时，防止多个线程同时创建对象的问题
 */
public class ThreadPoolExecutorUtils {

    // 核心线程数，正常情况下，允许同时执行的线程数量。
    private static final int CORE_POOL_SIZE = 3;
    /**
     * 最大线程数，当 workQueue （阻塞队列）中存放的线程数达到设定的值后，
     * 当前可以同时运行的线程数量会从核心线程数 corePoolSize 变为最大线程数 maximumPoolSize。
     * 注意：
     *  虽然可以同时运行的最大线程数变成 maximumPoolSize 但还是会优先去 workQueue 中排队，
     *  当排不下的时候才会一起运行。
     *
     * 举个例子：corePoolSize = 2，maximumPoolSize = 6，workQueue= 2
     *  我们同时开 7 个线程，这个时候同时运行的线程不是 6 而是 7 - 2 为 5。
     *  虽然可以运行的数量变成了 6 但是优先会去队列排队。 如果开8个线程的话就会同时运行6个线程。
     */
    private static final int MAX_POOL_SIZE = 6;
    /**
     * 阻塞队列，当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，新任务就会被存放在队列中。
     */
    private static final int QUEUE_CAPACITY = 2;
    /**
     * 当线程池中的线程数量大于 corePoolSize 的时候，如果这时没有新的任务提交，
     * 核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了 keepAliveTime 才会被回收销毁；
     */
    private static final Long KEEP_ALIVE_TIME = 10L;
    /**
     * 另外，需要注意 threadPoolExecutor 采用 volatile 关键字修饰也是很有必要。
     *  threadPoolExecutor = new ThreadPoolExecutor(); 这段代码其实是分为三步执行：
     * 1. 为 threadPoolExecutor 分配内存空间
     * 2. 初始化 threadPoolExecutor
     * 3. 将 threadPoolExecutor 指向分配的内存地址
     * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。
     * 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
     * 例如：线程 T1 执行了 1 和 3，此时 T2 调用 getThreadPoolExecutor() 因为已经分配的内存空间
     * 所以 threadPoolExecutor 不为空，但实际返回 threadPoolExecutor 还未被初始化。
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     */
    private volatile static ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolExecutorUtils() {}

    public static ThreadPoolExecutor getThreadPoolExecutor () {

        // 主要时提高效率，当有实例对象时，直接返回实例对象
        if (threadPoolExecutor == null) {
            // 当没有单例对象的时，创建对象代码块应该是同步代码块才行
            synchronized (ThreadPoolExecutorUtils.class) {
                // 在同步代码块中判断是否已经有其他线程创建了单例对象，没有才去创建单例对象
                // 主要是因为在高并发的情况下，会有很多想成被阻塞到 synchronized 外，而只需要其中之一创建实力就ok，这边不判断就会创建多个对象
                // 所以 1锁 2判 3更新(操作) 是必须的，在锁之前判断是为了提交效率，因为同步代码块或者同步方法会降低效率
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(
                            CORE_POOL_SIZE,
                            MAX_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }

        return threadPoolExecutor;

    }

}
