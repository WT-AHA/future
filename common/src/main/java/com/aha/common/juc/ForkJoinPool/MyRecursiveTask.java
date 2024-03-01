package com.aha.common.juc.ForkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveTask 是 Java 并发包中用于实现可递归分解的任务的一个抽象类，
 * 通常用于实现分治算法。它是 ForkJoinTask 的子类，
 * ForkJoinTask 是一种可以在 ForkJoinPool 中执行的任务。
 * RecursiveTask 主要用于处理需要返回结果的任务，其内部通常包含了 fork() 和 compute() 方法。
 *
 * fork(): 用于异步执行当前任务的子任务。
 * 它会将当前任务拆分成更小的子任务，这些子任务会被提交到 ForkJoinPool 中执行。
 * fork() 方法会立即返回，不会阻塞当前线程。
 *
 * compute(): 用于执行当前任务的主要逻辑。
 * 在 compute() 方法中，你需要定义任务的具体执行过程。
 * 通常在 compute() 方法中，会根据问题的规模决定是否需要进一步分解成子任务，
 * 如果问题规模足够小，可以直接进行计算并返回结果；
 * 如果问题规模较大，则可以调用 fork() 方法将任务分解成更小的子任务，
 * 然后在适当的时候调用子任务的 join() 方法等待子任务的执行结果，并进行合并。
 *
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {
    private final int threshold;
    private final int[] array;
    private final int start;
    private final int end;

    public MyRecursiveTask(int[] array, int start, int end, int threshold) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Integer compute() {

        System.out.println(Thread.currentThread().getName());

        // 如果问题规模小于阈值，则直接计算结果
        if (end - start < threshold) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // 否则拆分成子任务执行
            int mid = (start + end) / 2;
            MyRecursiveTask leftTask = new MyRecursiveTask(array, start, mid, threshold);
            MyRecursiveTask rightTask = new MyRecursiveTask(array, mid, end, threshold);
            // 异步执行左子任务
            leftTask.fork();
            // 同步执行右子任务
//            int rightResult = rightTask.compute();
            int rightResult = rightTask.compute();
            // 获取异步执行左子任务的结果
            int leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length, 3);
        // 第一次的 compute 使用主线程
        System.out.println("Result: " + task.compute());
        // 全部放入到 ForkJoinPool 中新建线程计算
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        Integer invoke = forkJoinPool.invoke(task);
//        System.out.println("Result: " + invoke);
    }
}

