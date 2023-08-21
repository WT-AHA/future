package com.aha.algorithm.jianzhioffer;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * 找出数组中任意一个重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * @author WT
 * @date 2022/01/11 13:49:25
 */
public class Offer03 {

    /**
     * 看到这道题第一个想法就是双重 for 循环, 但是性能太差，直接排除，想到使用 hashSet 的 contains 方法，发现效果还是不太理想
     *
     * @param nums 输入数组
     * @return 任意一个相同的数字
     */
    public int findRepeatNumber1(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }

        // 没有找到重复的数据
        return -1;
    }

    /**
     * 使用原地交换的方法
     * 思路：数组的值为 0 ~ n-1 范围内，我们遍历数组，将数组的值放到与其索引相同的位置，
     * 例如，数组第一个值为 3 我们就将 3 放到 a[3] 中，将原本的 a[3] 先放到 a[0] 中，
     * 下次再次遇到 3 的时候我们先判断 a[3] 是不是 3 是三的话就是重复的 返回即可，不是三就交换，知道找到重复的数据
     *
     * @param nums 输入数组
     * @return 任意一个相同的数字
     */
    public static int findRepeatNumber(int[] nums) {

        int i = 0;
        while (i < nums.length) {

            // 不需要进行位置的交换，直接进行下一次的循环
            if (nums[i] == i) {
                // 关键只有当 nums[i] == i 的时候才进行 i++，换句话说要将i之前的数据放到正确的位置上，如果放到正确的位置上就 i++，会出现位置
                // 参考主函数的测试用例
                i++;
                continue;
            }

            // 需要进行交换的数据， nums[i] 不等于 i，按照规则应该将 nums[i] 放到 第 nums[i] 个位置上即 nums[nums[i]]，但是如果 现在 nums[i] == nums[nums[i]] 说明重复了
            if (nums[nums[i]] == nums[i]) {
                // 重复了，直接返回
                return nums[i];
            }
            // 没有重复，交换数据
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] intArr = new int[]{3, 4, 2, 0, 0, 1};
        int repeatNumber = findRepeatNumber(intArr);
        System.out.println(repeatNumber);
    }

}
