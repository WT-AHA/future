package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution004 {

    public int removeDuplicates(int[] nums) {

        // 边界值判断
        if (nums.length == 1) {
            return 1;
        }

        // 当首尾相同，长度也不是 1 的时候返回长度 2
        if (nums[0] == nums[nums.length - 1]) {
            return 2;
        }

        // 从数组下标为 1 开始因为可以重复两次
        int effectiveIndex = 1;
        int currentIndex = 2;

        while (currentIndex < nums.length) {

            if (nums[currentIndex] == nums[effectiveIndex] && nums[currentIndex] ==  nums[effectiveIndex-1]) {

                // 说明已经有两个重复的了，单纯的 currentIndex++;
                currentIndex++;

            } else {
                // 还可以存放相同的数据  或者
                // 不相同的时候，直接将 currentIndex 放到 effectiveIndex + 1
                nums[effectiveIndex+1] = nums[currentIndex];
                effectiveIndex++;
                currentIndex ++;
            }

        }

        return effectiveIndex+1;

    }

}
