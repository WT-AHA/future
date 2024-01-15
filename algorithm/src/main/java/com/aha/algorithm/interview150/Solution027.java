package com.aha.algorithm.interview150;

/**
 * 167. 两数之和 II - 输入有序数组
 *
 * 中等
 * 相关标签
 * 相关企业
 *
 * 给你一个下标从 1 开始的整数数组 numbers ，
 * 该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 *
 * 示例 1：
 *
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * 示例 2：
 *
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 * 示例 3：
 *
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 *
 * 提示：
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class Solution027 {

    // 看到这道题的第一印象就是，要有两个移动的方向，一个方向是递增，一个方向是递减，这样当 与 target 进行比较的时候，
    // 大于 移动一个  小于移动另外一个  所以一个标记最小值  一个标记最大值
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int total = numbers[left] + numbers[right];
            if (total < target) {
                left ++;
            } else if (total == target) {
                break;
            } else {
                right --;
            }

        }

        return new int[] {left + 1, right + 1};

    }

}
