package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * 查找旋转数组中的最小数字，旋转数组是 原本一个有序的升序数组，将最开始的若干个数字搬到了数组的尾部，形成的数组
 * 例如： [1,2,3,4,5] 旋转成 [3,4,5,1,2]
 * 题目要求 查出此数组中最小的数字，ps: 数组中可能存在重复数字
 *
 * @author WT
 * @date 2022/01/13 10:51:10
 */
public class Offer11 {

    /**
     * 暴力解法： 直接遍历每次比较 numbers[i], 时间复杂度是 O(n),空间复杂度是 O(1) 的
     * @param numbers 需要查找数据的数组
     * @return 数组中最小的数字
     */
    public int minArray1(int[] numbers) {
        for (int i=0; i<numbers.length-1; i++) {
            if (numbers[i] > numbers[i+1]) {
                return numbers[i+1];
            }
        }
        return numbers[0];
    }

    /**
     * 使用二分法：二分法时间复杂度是 logN ，空间复杂度是 O(1)
     * 因为旋转数组是由一个升序的数组旋转得到的，所以旋转之后的数组是由两个升序的数组 组成的，我们将旋转点定义为 x，旋转点一定是右数组中的第一个数
     * 使用二分法 比较的值为 nums[middle] 与 nums[j] 进行对比
     *  1. nums[middle] > nums[j] 的时候，说明 middle 一定在左数组中，说明 x 一定在 middle 的右边 所以 i = middle + 1
     *  2. nums[middle] < num[j] 的时候，说明 middle 一定在右数组中，说明 x 一定是 middle 或者 middle 的左边 所以 j = middle
     *  3. nums[middle] == nums[j], 没有办法确定 x 在一个范围内，但是因为二者相同所以可以去掉 j 所以执行 j --
     * 当 i = j 的时候就可以认为 i和j便是 旋转点 x
     * @param numbers 需要查找数据的数组
     * @return 数组中最小的数字
     */
    public int minArray(int[] numbers) {
        int i = 0, j=numbers.length-1;
        while (i < j) {
            int middle = i + (j-i)/2;
            if (numbers[middle] > numbers[j]) {
                i = middle + 1;
            } else if (numbers[middle] < numbers[j]) {
                j = middle;
            } else {
                // 去重
                j --;
            }
        }
        return numbers[j];
    }

}
