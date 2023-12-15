package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution009 {

    // 使用贪心的方式，维护好最远可以到达的位置，如果最远可以到达的位置 >= length - 1 说明可以到达最后一个下标
    // 维护最远的位置的方式为，maxLength = index + nums[index]
    public static boolean canJump1(int[] nums) {

        int maxLength = nums[0];
        int length = nums.length;
        for (int index=1; index<length; index++) {

            if (maxLength >= index) {
                // 说明目前可以到达 index
//                if (maxLength < index+nums[index]) {
//                    maxLength = index+nums[index];
//                }
                maxLength = Math.max(maxLength, index+nums[index]);
            }

        }

        return maxLength >= length - 1;

    }

    // 第一印象没有什么好的办法，对于动态规划的题不是很熟悉
    // 从 nums.length - 1 往前来遍历数组，一步一步求解减少数据范围
    // 使用递归的方式
    public static boolean canJump(int[] nums) {

        int length = nums.length;

        // 边界值判断
        if (length == 1) {
            return true;
        }

        for (int i=length-1; i>=0; i--) {

            // 判断 i 能到达的位置是否可以到达 length
            if (i+nums[i] >= length-1 && canJumpTemp(i, nums)) {
                // 可以到达
                return true;
            }

        }

        return false;

    }

    // 递归判断可以是都可以到达指定的位置，当指定的位置为0的时候，说明可以到达，返回true
    public static boolean canJumpTemp (int target, int[] nums) {

        if (target == 0) {
            return true;
        }

        for (int i=target-1; i>=0; i--) {
            // 判断 i 能到达的位置是否可以到达 length
            if (i+nums[i] >= target) {
                // 可以到达
                return canJumpTemp(i, nums);
            }
        }

        return false;

    }


    public static void main(String[] args) {
        int[] array = {2,0,0};
//        int[] array = {2,3,1,1,4};
//        int[] array = {3,2,1,0,4};
        System.out.println(canJump(array));
    }

}
