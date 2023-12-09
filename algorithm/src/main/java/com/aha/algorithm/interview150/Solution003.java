package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution003 {

    public static int removeDuplicates(int[] nums) {

        // 边界值判断 nums.length == 1 或者 nums 里面都是相同的元素
        if (nums[0] == nums[nums.length - 1]) {
            return 1;
        }

        // 不重复的有效的节点
        int effectiveIndex = 0;
        // 当前指向的节点
        int currentIndex = 1;
        while (currentIndex < nums.length) {

            // 当 nums[effectiveIndex] == nums[currentIndex] 的时候， 没有找到不同的元素不需要进行赋值操作
            // currentIndex 后移
            if (nums[effectiveIndex] == nums[currentIndex]) {
                currentIndex ++;
            } else {

                // 当 nums[effectiveIndex] != nums[currentIndex] 的时候, 找到了不同的元素，将不同的元素放到 effectiveIndex + 1
                // 的位置，因为 effectiveIndex 是有效的数据
                nums[effectiveIndex + 1] = nums[currentIndex];
                // effectiveIndex + 1 也是有效的数据，effectiveIndex 后移，下一次比较的时候使用新的 effectiveIndex
                effectiveIndex ++;

            }

        }

        return effectiveIndex + 1;

    }

    public static void main(String[] args) {
        int[] array = {0,0,1,1,1,2,2,3,3,4};
        removeDuplicates(array);
    }

}
