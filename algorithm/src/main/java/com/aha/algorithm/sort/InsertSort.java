package com.aha.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(insertSort(new int[]{8, 6, 9, 4, 5, 8, 1, 1, 2, 34, 9})));
    }

    /**
     * 插入排序
     * 就是任务前面的元素是有序的，然后往里面进行数据的插入，被插入节点都需要往后面移动
     */
    public static int[] insertSort(int[] array) {

        for (int current=1; current<array.length; current++) {

            // 要插入的值
            int insertValue = array[current];
            // 要插入的索引从要插入的值的前一个开始比较
            int insertIndex = current - 1;
            // 如果要插入的索引位置的值大于要插入的值，将索引位置的数据往要插入的值的位置移动一格
            while (insertIndex >= 0 && array[insertIndex] > insertValue) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            // 注意这边要 +1 才是他要插入的位置
            array[insertIndex + 1] = insertValue;
        }

        return array;
    }

}
