package com.aha.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubbleSort(new int[]{8, 6, 9, 4, 5, 8, 1, 1, 2, 34, 9})));
    }

    /**
     * 冒泡排序，就是当前元素和自己后面那个元素比较，当前元素比较大，二者就交换，一轮下来就是最后一个位置是最大的元素
     * 然后再走下一轮，走完即可
     * n 方的时间复杂度
     */
    public static int[] bubbleSort (int[] array) {

        for (int j=0; j<array.length; j++) {
            for (int i=0; i<array.length-j-1; i++) {
                if (array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
            }
        }

        return array;
    }


}
