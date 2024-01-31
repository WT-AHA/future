package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150
 *
 * 209. 长度最小的子数组
 *
 * 中等
 * 相关标签
 * 相关企业
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 */
public class Solution030 {

    // 典型的滑动窗口的问题，主要是联系的子数组，可以使用滑动窗口，大于就左指针移动，小于就右指针移动
    public static int minSubArrayLen(int target, int[] nums) {

        int result = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int total = nums[0];
        while (left <= right) {

            if (total < target) {
                right ++;
                if (right >= nums.length) {
                    return result == Integer.MAX_VALUE ? 0 : result;
                }
                total += nums[right];

            } else {
                result = Math.min(result, right - left + 1);
                total -= nums[left ++];
            }

        }

        return result == Integer.MAX_VALUE ? 0 : result;

    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(11, new int[]{1,2,3,4,5}));
    }

}
