package com.aha.algorithm.interview150;

/**
 *
 * https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-interview-150
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 */
public class Solution016 {


    // 1. 这道题关键是看题干给的图片，有一个模糊的思路，就是分割成一块一块的，每次计算时的，高为数组的值，长为当前数组下标到下一个比他大的数组下标的差
    // 雨水的值是 1 步骤算出来的长方形面积的值 - 在这个范围内数组的value
    // 从左向右计算一遍，从右向左计算一遍，但是相等的情况只计算一遍即可
    public static int trap(int[] height) {

        // 总共的雨水
        int result = 0;
        // beginIndex 到 i 的 height 的 value 和
        int heightTotal = 0;

        int beginIndex = 0;
        int length = height.length;
        for (int i=1; i<length; i++) {
            heightTotal = heightTotal + height[i-1];
            if (height[i] >= height[beginIndex]) {
                result = result + ((i - beginIndex) * height[beginIndex]) - heightTotal;
                heightTotal = 0;
                beginIndex = i;
            }
        }

        heightTotal = 0;
        // i 到 beginIndex 的 height 的 value 和
        beginIndex = length-1;
        for (int i=length-2; i>0; i--) {
            heightTotal = heightTotal + height[i+1];
            if (height[i] > height[beginIndex]) {
                result = result + ((beginIndex-i) * height[beginIndex]) - heightTotal;
                heightTotal = 0;
                beginIndex = i;
            }
        }


        return result;

    }

    public static void main(String[] args) {
        int[] array = new int[]{2,0,2};
        System.out.println(trap(array));

    }

}
