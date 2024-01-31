package com.aha.algorithm.interview150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 15. 三数之和
 *
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 *
 * 提示
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 *  123 124 125 134 135 145
 *  234 235
 *  345
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 */
public class Solution029 {

    // 暴力破解不行，得用双指针，主要精髓是固定一个指针  另外两个指针 需要一个 循环即可，另外两个指针的要求是，一个移动变大，一个移动变小，逐步缩小范围
    // 判断重复，使用的是，当 等于 0 时候，相同的数据直接 跳过
    // leftIndex 的判断是  leftValue = nums[leftIndex - 1] 的话 直接将 leftIndex ++
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int leftIndex = 0;
        // 先进行数据的排序
        Arrays.sort(nums);

        while (leftIndex + 1 < nums.length - 1) {

            int leftValue = nums[leftIndex];
            if (leftValue > 0) {
                return result;
            }

            // 重复数据排除 left 和 前一个 left 相同   则 left 直接右移一下
            if (leftIndex > 0 && leftValue == nums[leftIndex - 1]) {
                leftIndex ++;
                continue;
            }


            // 主要精髓是固定一个指针  另外两个指针 需要一个 循环即可，另外两个指针的要求是，一个移动变大，一个移动变小，逐步缩小范围
            int middleIndex = leftIndex + 1;
            int rightIndex = nums.length - 1;
            while (middleIndex < rightIndex) {

                int rightValue = nums[rightIndex];

                if (rightValue < 0) {
                    break;
                }

                int tempTotal = leftValue + nums[middleIndex] + rightValue;
                if (tempTotal > 0) {
                    rightIndex --;
                } else if (tempTotal < 0) {
                    middleIndex ++;
                } else {

                    List<Integer> tempResult = Arrays.asList(leftValue, nums[middleIndex], rightValue);
                    result.add(tempResult);
                    // 重复数据的排除
                    while (middleIndex < rightIndex && nums[middleIndex] == nums[middleIndex+1]) {
                        middleIndex ++;
                    }

                    while (middleIndex < rightIndex && nums[rightIndex] == nums[rightIndex-1]) {
                        rightIndex --;
                    }

                    middleIndex ++;
                    rightIndex --;

                }

            }

            leftIndex ++;

        }

        return result;

    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(lists);
    }

}
