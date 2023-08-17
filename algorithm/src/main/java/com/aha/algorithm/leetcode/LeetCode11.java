package com.aha.algorithm.leetcode;

/**
 * 盛最多水的容器
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class LeetCode11 {

    public static void main(String[] args) {
        int[] height = new int[] {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = 0;
        // 使用双指针来解决, 盛水的容量，应该是双指针中小的那个乘以间距，所以移动的时候应该优先移动数值小的那个指针
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right --;
            } else {
                left ++;
            }
        }
        return max;
    }

}
