package com.aha.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class MyInsertSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertSort(new int[]{8, 6, 9, 4, 5, 8, 1, 1, 2, 34, 9})));
    }

    /**
     * 插入排序
     * 就是任务前面的元素是有序的，然后往里面进行数据的插入，被插入节点都需要往后面移动
     */
    public static int[] insertSort(int[] array) {

        // i 之前的数据默认是有序的数组
        for (int i=1; i<array.length; i++) {
            // i 所在的位置就是要插入的值
            int insertValue = array[i];
            // 从有序数组的最后一位开始比较
            int insertIndex = i-1;
            while (insertIndex >= 0 && array[insertIndex] > insertValue) {
                // 有序数组的值大于 要插入的值 insertValue 需要将其往后移动一位
                array[insertIndex + 1] = array[insertIndex];
                insertIndex --;
            }
            // 移动之后, insertIndex + 1 便是 insertValue 要插入的位置
            array[insertIndex + 1] = insertValue;
        }

        return array;
    }

}
