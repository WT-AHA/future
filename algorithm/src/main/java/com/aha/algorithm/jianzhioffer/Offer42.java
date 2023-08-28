package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * 连续子数组的最大和
 *
 * @author WT
 * @date 2022/01/21 09:13:47
 */
public class Offer42 {

    /**
     * 要求 时间复杂度是 O(n) 级别的所以这边不能使用暴力破解法
     * 这边使用动态规划的方法
     * 主要的思路：以 nums[i] 元素为结尾的连续子数组的最大和记为 dp[i]，dp[i] 的前一个元素设为 dp[i-1]，当 dp[i] 前只有一个元素的时候，那么
     * dp[i-1] 就是 nums[i-1] 要考虑的就是如果 nums[i-1] 大于 0,就将这个数累加上，如果小于 0 就会舍去，以dp[i]为重新累加的起点，这样 dp[i-1]
     * 永远可以保证是前面连续的子数组中最大的和，知道遍历到数组的最后一个数就能得出 连续子数组的最大和
     * @param nums 输入的数组
     * @return 数组的最大值
     */
    public int maxSubArray(int[] nums) {

        // 题目描述 已经 限制了数组的长度 1 <= arr.length <= 10^5， 所以这边不需要判空
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }

        // 初始化最大的值便是 nums[0]
        int max = nums[0];
        // 用于记录 dp[i-1] 的值，对于 dp[0] 而言，其前面的 dp[-1]=0
        int previous = 0;
        // 用于记录 dp[i] 的值, dp[i] 只是当前的值，不是最大的值
        int current;
        for(int num : nums){
            current = num;
            if(previous > 0) {
                // 如果 previous 大于 0，dp[i] 应该是 previous + num 但是dp[i] 不一定比 dp[i-1] 大，因为 num 可能为负数，所以下面要判断
                current += previous;
            }
            if(current > max) {
                max = current;
            }
            // 如果 previous 大于 0， previous = num + previous；如果小于 0，等于 num，重新开始计数
            previous = current;
        }
        return max;
    }

    public int maxSubArray1(int[] nums) {
        int result = nums[0], previous = 0, current;
        for (int num : nums) {
            current = num;
            if (previous > 0) {
                // 如果 previous > 0, dp[i] 应该等于 current + num
                current = current + previous;
            }
            previous = current;
            if (current > result) {
                result = current;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        int i = new Offer42().maxSubArray(arr);
        System.out.println(i);
    }

}
