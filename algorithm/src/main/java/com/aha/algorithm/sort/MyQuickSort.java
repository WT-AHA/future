package com.aha.algorithm.sort;

import java.util.Arrays;

public class MyQuickSort {

    public static void main(String[] args) {
        int[] a = new int[]{8, 6, 9, 4, 5, 8, 1, 1, 2, 34, 9};
        quickSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
    }


    public static void quickSort(int[] array, int startIndex, int endIndex) {

        if (startIndex >= endIndex) {
            return;
        }

        // 第一次交换，确定以后得边界 index
        int i = doublePointerSwap(array, startIndex, endIndex);
        quickSort(array, startIndex, i-1);
        quickSort(array, i+1, endIndex);

    }

    public static int doublePointerSwap(int[] array, int startIndex, int endIndex) {

        // 设置首元素为边界值
        int boundary = array[startIndex];
        // 左指针
        int left = startIndex;
        // 右指针
        int right = endIndex;

        while (left < right) {

            // 右边元素与边界值进行比较，找比边界值小的元素
            while (left < right && array[right] > boundary) {
                // 说明比边界值大或者相等  不用进行交换 所以右指针左移
                right --;
            }

            while (left < right && array[left] <= boundary) {
                // 说明比边界值小或者相等  不用交换数据 所以左指针右移
                left ++;
            }

            // 如果指向的不是同一个元素的话 找到了可以交换的元素 进行左右指针的交换
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }

        }

        // 将边界值与指针指向的元素进行交换
        array[startIndex] = array[left];
        array[left] = boundary;
        return left;

    }

}
