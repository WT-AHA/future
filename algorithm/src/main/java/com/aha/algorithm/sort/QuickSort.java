package com.aha.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    char c;

    public void testC () {
        System.out.println(c);
    }

    public static void main(String[] args) {

        int[] a = new int[]{8, 6, 9, 4, 5, 8, 1, 1, 2, 34, 9};
        quickSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        QuickSort quickSort = new QuickSort();
        quickSort.testC();
    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        // 核心算法部分：分别介绍 双边指针（交换法）
        int pivotIndex = doublePointerSwap(arr, startIndex, endIndex);

        // 用分界值下标区分出左右区间，进行递归调用
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 双边指针（交换法）
     * 思路：
     * 记录分界值 pivot，创建左右指针（记录下标）。
     * （分界值选择方式有：首元素，随机选取，三数取中法）这边选择使用的是首元素
     *
     * 首先从右向左找出比pivot小的数据，
     * 然后从左向右找出比pivot大的数据，
     * 左右指针数据交换，进入下次循环。
     *
     * 结束循环后将当前指针数据与分界值互换，
     * 返回当前指针下标（即分界值下标）
     */
    private static int doublePointerSwap(int[] arr, int startIndex, int endIndex) {

        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {

            // 从右向左找出比pivot小的数据(右边找出比边界值小的数据)
            while (leftPoint < rightPoint && arr[rightPoint] >= pivot) {
                rightPoint--;
            }
            // 从左向右找出比pivot大的数据(从左边找出比边界值大的数据)
            while (leftPoint < rightPoint && arr[leftPoint] <= pivot) {
                leftPoint++;
            }

            // 没有过界则交换，符合条件的左右两边的数据进行交换
            if (leftPoint < rightPoint) {
                int temp = arr[leftPoint];
                arr[leftPoint] = arr[rightPoint];
                arr[rightPoint] = temp;
            }

        }

        // 最终将分界值与当前指针数据交换 一般到这一步骤就是 right和left 相同
        arr[startIndex] = arr[rightPoint];
        arr[rightPoint] = pivot;

        // 返回分界值所在下标 这边就是 right 和 left 是相同的
        return rightPoint;

    }



}
