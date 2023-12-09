package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution002 {

    public int removeElement(int[] nums, int val) {

        // 边界值判断
        if (nums.length == 0) {
            return 0;
        }

        // 见到这道题的第一反应是将 等于 val 的指 放到 数组最后面的位置，但是需要 for 循环数据，感觉复杂到达到了 O(n) 的级别
        int rightIndex = nums.length - 1;
        int leftIndex = 0;

        while (leftIndex <= rightIndex) {

            if (nums[leftIndex] == val) {

                // 将等于 val 的值的数据，和最后面的数据的值交换，就是 rightIndex 指向的位置
                if (nums[rightIndex] == val) {
                    // rightIndex 指向的值也等于 val rightIndex 左移
                    rightIndex --;
                } else {
                    // leftIndex 与 rightIndex 数据进行交换
                    int temp = nums[leftIndex];
                    nums[leftIndex] = nums[rightIndex];
                    nums[rightIndex] = temp;
                    leftIndex ++;
                    rightIndex --;
                }

            } else {
                leftIndex ++;
            }

        }

        return leftIndex;

    }

}
