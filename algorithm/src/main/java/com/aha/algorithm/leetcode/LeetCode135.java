package com.aha.algorithm.leetcode;

import java.util.Arrays;

/**
 * 135. 分发糖果
 * https://leetcode.cn/problems/candy/
 */
public class LeetCode135 {

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,0,2}));
    }

    /**
     * 基本的解题思路分为 3 步
     * 1. 先给每个学生分一个糖果
     * 2. 从左向右遍历第 i+1 个孩子如果分数高于 i 那么 给 i+1 个孩子多分一个糖果 不高于则不变
     * 3. 从右向左遍历第 j-1 个孩子如果分数高于 j 那么 给 j-1 个孩子多跟一个糖果 不高于则不变
     * 4. 取 两次遍历糖果的较大的值
     */
    public static int candy(int[] ratings) {

        int length = ratings.length;
        int[] left = new int[length];
        Arrays.fill(left, 1);
        int[] right = new int[length];
        Arrays.fill(right, 1);
        int result = 0;

        for (int i=0; i< length - 1; i++) {
            if (ratings[i+1] > ratings[i]) {
                left[i+1] = left[i] + 1;
            }
        }

        for (int j=length - 1; j > 0; j--) {
            if (ratings[j-1] > ratings[j]) {
                right[j-1] = right[j] + 1;
            }
            result += Math.max(left[j], right[j]);
        }
        result += Math.max(left[0], right[0]);

        return result;
    }

}
