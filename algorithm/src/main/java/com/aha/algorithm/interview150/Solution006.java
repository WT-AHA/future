package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-interview-150
 * 1. 直接拿一个新的数组来接结果数组, 根据题意来进行数据的变化
 * 2. 不使用额外的数据的话，就需要使用数据交换的方式来了
 */
public class Solution006 {

    /**
     * 1. 直接拿一个新的数组来接结果数组, 根据题意来进行数据的变化
     */
    public void rotate(int[] nums, int k) {

        int length = nums.length;
        k = k%length;

        // 边界值判断
        if (k == 0) {
            return;
        }


        int[] resultArray = new int[length];

        int i = length-k;
        int j = 0;
        while (j<k && i<length) {

            resultArray[j] = nums[i];
            i++;
            j++;

        }

        i=0;
        while (j<length && i<length-k) {

            resultArray[j] = nums[i];
            i++;
            j++;

        }

//        i=0;
//        while (i<length) {
//
//            nums[i] = resultArray[i];
//            i++;
//
//        }
//        可以替换为系统函数
        System.arraycopy(resultArray, 0, nums, 0, length);

    }

    /**
     * 2. 不使用额外的数据的话，就需要使用数据交换的方式来了
     * 2.1 先将整个数组反转
     * 2.2 将 [0,k-1] 和 [k, length-1] 分为两个数组， 分别进行翻转
     */
    public void rotate2(int[] nums, int k) {

        int length = nums.length;
        k = k%length;

        // 边界值判断
        if (k == 0) {
            return;
        }

        // 整个数组反转
        reverseArray(nums,0, length-1);
        // 0 到 k-1 反转
        reverseArray(nums,0, k-1);
        // k 到 length-1 反转
        reverseArray(nums, k, length-1);

    }

    public void reverseArray(int[] nums, int start, int end) {

        while (start<end) {

            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            end --;
            start ++;

        }

    }

    public static void main(String[] args) {
        System.out.println(3%4);
    }

}
