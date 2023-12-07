package com.aha.algorithm.interview150;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution001 {

    /**
     * 利用数组非递减的特性
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        // 边界值判断
        // nums1 没有数据直接返回 nums2 的数据
        if (m == 0) {
//            直接这样赋值不行
//            nums1 = nums2;
//            得使用 for 循环赋值 也可以使用 java 提供的 arraycopy 方法 是个 native 方法
            for (int i=0; i<nums2.length; i++) {
                nums1[i] = nums2[i];
            }
        }

        // nums2 没有数据直接返回 nums1 的数据
        if (n == 0) {
            return;
        }

        // 需要合并的数组
        // 使用新的数组来承载
        int[] nums3 = new int[m+n];
        int nums1Index = 0;
        int nums2Index = 0;
        int nums3Index = 0;
        while (nums3Index < nums1.length) {

            // nums1 没有数据了 直接使用 nums2 的数据
            if (nums1Index >= m) {
                nums3[nums3Index] = nums2[nums2Index];
                nums2Index ++;
                nums3Index ++;
                continue;
            }

            // nums2 没有数据了 直接使用 nums1 的数据
            if (nums2Index >= n) {
                nums3[nums3Index] = nums1[nums1Index];
                nums1Index ++;
                nums3Index ++;
                continue;
            }

            // 当 nums1 的数据 > nums2 的数据时，应该使用 nums2 的数据，因为数组是非递减的 (递增有相等的情况)
            if (nums1[nums1Index] > nums2[nums2Index]) {
                nums3[nums3Index] = nums2[nums2Index];
                nums2Index ++;
                nums3Index ++;
            } else {
                nums3[nums3Index] = nums1[nums1Index];
                nums1Index ++;
                nums3Index ++;
            }


        }

//        System.out.println(Arrays.toString(nums3));
//        // 直接这样赋值不行
//        nums1 = nums3;
        // 得使用 for 循环赋值 也可以使用 java 提供的 arraycopy 方法 是个 native 方法
//        System.arraycopy(nums3, 0, nums1, 0, nums1.length);
        for (int i=0; i<nums1.length; i++) {
            nums1[i] = nums3[i];
        }


    }

    /**
     * 如果不利用数组非递减的特性的话，可以直接将 nums2 数组直接当入到 nums1 数组中，
     * 然后直接对 nums1 数组进行排序即可
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {

        // 将 nums2 添加到 nums1 中
        for (int i=0; i<nums2.length; i++) {
            nums1[m+i] = nums2[i];
        }

        Arrays.sort(nums1);

    }


    public static void main(String[] args) {
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        merge(nums1, 1, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }

}
