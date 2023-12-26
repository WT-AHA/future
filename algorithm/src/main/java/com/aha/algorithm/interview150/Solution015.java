package com.aha.algorithm.interview150;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/candy/?envType=study-plan-v2&envId=top-interview-150
 *
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 * 提示：
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 *
 */
public class Solution015 {

    // 第一眼看到这个题没啥想说的，先给数组每个位置都赋值成 1 然后 从前往后遍历
    // 困难的题  直接没看题解  打败95.8  哈哈
    public static int candy(int[] ratings) {

        int length = ratings.length;
        int[] result = new int[length];

        if (length == 1) {
            return 1;
        }

        // 结果数据都初始化成 1
        Arrays.fill(result, 1);

        // 1. 从前往后遍历，使数组满足 后一个大的时候 分的糖 比前一个多
        for (int i=1; i<length; i++) {

            if (ratings[i] > ratings[i-1]) {
                result[i] = result[i-1] + 1;
            }

        }

        // 2. 从后往前遍历，使数组满足，前一个大的时候 分的糖 比后一个多
        // 3. 计算总数
        int resultNum = 0;
        for (int i=length-2; i>=0; i--) {

            if (ratings[i] > ratings[i+1] && result[i] <= result[i+1]) {
                // 前一个比后一个大 且 前一个没有后一个分的糖多的情况 就 重新赋值
                result[i] = result[i+1] + 1;
            }
            resultNum = resultNum + result[i];

        }
        resultNum = resultNum + result[length-1];

        return resultNum;

    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,4,5,2};
        System.out.println(candy(array));
    }

}
