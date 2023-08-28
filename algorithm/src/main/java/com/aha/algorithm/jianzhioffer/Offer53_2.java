package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 * 0 ~ n-1 中缺失的数据
 * 一个长度为 n-1 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围 0～n-1 之内。
 * 在范围 0～n-1 内的 n 个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * @author WT
 * @date 2022/01/12 11:08:48
 */
public class Offer53_2 {

    /**
     * 看到这个题的第一影响就是暴力破解，就是一个 for 循环完事呗，这样的效率肯定是不行的。
     * 然后想到排序数组无脑二分法, 先尝试使用 offer53 双指针边界的方法
     * @param nums 输入数组
     * @return 丢失的那个数字
     */
    public int missingNumber(int[] nums) {
        int i=0, j=nums.length - 1;
        while (i <= j) {
            int middle = (i+j)/2;
            if (nums[middle] == middle) {
                // 说明缺失的数据在 [middle+1, j] 区间内
                i = middle + 1;
            } else {
                // 说明缺失的数据在 [i, middle-1]
                j = middle - 1;
            }
        }
        // 这个时候 i 比 j 大 1，这个 索引 i 就是缺失的数据
        return i;
    }

}
