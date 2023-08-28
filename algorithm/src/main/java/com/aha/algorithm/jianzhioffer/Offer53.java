package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * 在排序数组中查找某个数字的出现次数
 *
 * @author WT
 * @date 2022/01/12 09:33:55
 */
public class Offer53 {

    /**
     * 对于有序数组的问题，首先想到的解法便应该是二分查找的方法
     * 这个问题查找一个数字出现的次数，我们应该确定左边界和右边界，确定了左边界和右边界之后，有边界 - 左边界 - 1 便是此数字出现的次数
     * 确定边界的时候使用二分查找，i 为左边界，j 为有边界，m 为中点，确定边界的步骤如下：
     *  1. 循环 - 确定右边界
     *    循环的条件为 i  <= j,当 i > j 的时候，退出循环，退出循环的时候，i 只可能比 j 大 1，这是时候 i 应该是右边界，nums[j] 应该等于 target，
     *    如果不等于，说明数组中不包含 target
     *    a. 当 nums[m] >= target 的时候，说明右边界在 [i, m-1] 区间中，所以 j = m-1
     *    b. 当 nums[m] < target 的时候，说明右边界在 [m+1, j] 区间中, 所以 i = m+1
     * 2. 判断 nums[j] == target 吗，不等于直接返回结果为 0
     * 3. 初始化 i=0，j 不变 即为(right-1)就是比原本的i小1； 循环 - 确定左边界
     *    循环的条件和第一个循环是完全相同的，左边界应该等于 j
     * 4. 返回结果为 j - i - 1
     *
     * @param nums 有序数组
     * @param target 要查找的数字
     * @return 数字出现的次数
     */
    public int search(int[] nums, int target) {

        // 初始化 i，j 双指针
        int i=0, j=nums.length-1;
        // 确定右边界
        while (i <= j) {
            // 每次二分查找的中点位置
            int middle = (i+j)/2;
            if (nums[middle] > target) {
                // 说明右边界在 [i, m-1] 设置指针 j 的值
                j = middle - 1;
            } else {
                // 说明右边界在 [m+1, j] 设置指针 i 的值
                i = middle + 1;
            }
        }
        // 退出循环之后，右边界应该为 i
        int right = i;
        // 这时候的 j 应该是比 i 小 1 的而且因为 i 是右边界，所以如果存在 target 的话，右边界的左边第一个数一定是 target
        if (j >= 0 && nums[j] != target) {
            // 说明不存在 target 直接返回 不用在进行左边界的查找了
            return 0;
        }

        // 初始化 i, j 指针，j 指针不用变了，因为 j 现在的值为 right - 1，左边界最大的值也只可能为 right -1
        i = 0;
        // 确定左边界 与 确定右边界类似 但是要注意在等于逻辑的处理上是有差异的
        while (i <= j) {
            // 每次二分查找的中点位置
            int middle = (i+j)/2;
            if (nums[middle] >= target) {
                // 说明右边界在 [i, m-1] 设置指针 j 的值
                j = middle - 1;
            } else {
                // 说明右边界在 [m+1, j] 设置指针 i 的值
                i = middle + 1;
            }
        }
        int left = j;
        return right - left - 1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,7,7,8,8,10};
        int search = new Offer53().search(array, 8);
        System.out.println(search);
    }

}
