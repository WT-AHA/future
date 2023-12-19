package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/?envType=study-plan-v2&envId=top-interview-150
 *
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * 提示：
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 *
 */
public class Solution013 {

    // 题解很精妙，需要一个图来说明 题解连接如下
    // https://leetcode.cn/problems/product-of-array-except-self/solutions/11472/product-of-array-except-self-shang-san-jiao-xia-sa/?envType=study-plan-v2&envId=top-interview-150
    // 类似两个 9 * 9 乘法表 一个倒着的 一个正的
    public int[] productExceptSelf1(int[] nums) {

        // 定义两个数组  一个存档正三角  一个存放倒三角
        int length = nums.length;
        int[] result = new int[length];
        int[] resultTwo = new int[length];

        // 构建正三角
        result[0] = 1;
        for (int i = 1; i<length; i++) {
            result[i] = nums[i-1] * result[i-1];
        }

        // 构建倒三角
        resultTwo[length - 1] = 1;
        for (int i = length - 2; i>=0; i--) {
            resultTwo[i] = nums[i+1] * resultTwo[i+1];
        }

        // 结果为 正三角 * 倒三角
        for (int i=0; i<length; i++) {
            result[i] = result[i] * resultTwo[i];
        }

        return result;

    }

    // 看到这个题的第一反应是，全部想乘，然后除以当前的游标(但是题目要求不能使用除法),
    // 如果数组中有 0 的话 标记 0 的数量，数量 > 1 结果全部都是 0 , = 1 除了 = 0 的那个节点 其余都是 0
    // 用了除法的情况下 效率还是很低
    public int[] productExceptSelf(int[] nums) {

        int total = 1;
        int zeroCount = 0;
        for (int num : nums) {
            if (num != 0) {
                total = total * num;
            } else {
                zeroCount ++;
            }
        }

        int[] result = new int[nums.length];
        for (int i=0; i<nums.length; i++) {

            if (zeroCount > 1) {
                result[i] = 0;
            } else if (zeroCount == 1) {
                if (nums[i] == 0) {
                    result[i] = total;
                } else {
                    result[i] = 0;
                }
            } else {
                result[i] = total/nums[i];
            }
        }

        return result;

    }

}
