package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/jump-game-ii/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution010 {

    // 感觉还是得使用贪心的思想，每次选出可以到达数组中最大的，看看 大于等于 nums.length - 1 吗
    // 首次没做过的题打败 99% 的人
    public static int jump(int[] nums) {

        int length = nums.length;

        // 边界值判断
        if (length == 1) {
            return 0;
        }

        if (nums[0] >= length-1) {
            return 1;
        }

        // 记录跳跃的次数
        int count = 0;
        // 记录每次跳跃应该跳到的位置，一定是下次可以跳到最远的位置，也就是 maxValueIndex + nums[maxValueIndex] 为最大
        int maxValueIndex = 0;

        // 记录可以达到的最远的位置，最远的位置 >= length - 1 就说明下一次跳跃可以到达终点
        while (maxValueIndex + nums[maxValueIndex] < length-1) {

            // 下次跳跃可以到达的位置为 maxValueIndex 到 nums[maxValueIndex] + maxValueIndex
            // 所以 beginIndex endIndex
            int beginIndex = maxValueIndex+1;
            int endIndex = nums[maxValueIndex] + maxValueIndex;
            // 循环 找可以跳到最远的位置
            while (beginIndex<=endIndex) {
                if (beginIndex + nums[beginIndex] > maxValueIndex + nums[maxValueIndex]) {
                    // 说明此位置调的远，记录此位置
                    maxValueIndex = beginIndex;
                }
                beginIndex++;
            }
            count ++;

        }

        // 因为是下一次跳跃可以到达终点所以返回 count + 1
        return count+1;

    }

    // 看过题解之后感觉豁然开朗
    // 所谓贪心算法就是理所当然的任务我可以达到最远的位置，当达到最远的位置的时候，才算跳一次
    public int jump1(int[] nums) {

        // 每次可以跳的最远的位置，当 for 循环到这个位置的时候就说明已经跳了一次
        int endIndex = 0;
        // 跳跃的次数
        int count = 0;
        // 跳跃的可以到达的最远的位置,题目中明确指出一定可以跳到末尾，所以不用判断 maxIndex 和 nums.length
        int maxIndex = 0;
        // 在遍历数组时我们不访问最后一个元素，因为在最后一个元素之前我们的 maxIndex 一定 >= nums.length - 1 可以到达最后一个元素
        for (int i=0; i<nums.length-1; i++) {

            // 更新最远可以到达的位置
            if (nums[i] + i > maxIndex) {
                maxIndex = nums[i] + i;
            }

            // i 循环到了最远的位置，说明必定跳了一次
            if (endIndex == i) {
                // 下次可以跳到最远的位置
                endIndex = maxIndex;
                count++;
            }


        }

        return count;

    }

    public static void main(String[] args) {
        int[] array = {2,3,1,1,4};
        System.out.println(jump(array));

    }

}
