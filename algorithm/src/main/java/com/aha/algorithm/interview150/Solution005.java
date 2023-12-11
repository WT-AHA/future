package com.aha.algorithm.interview150;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150
 * 1. 直接遍历所有的数据，记录数据的个数, 使用 hashMap
 * 2. 对数组进行排序，中间那个数一定是众数
 * 3. 摩尔投票法，寻找众数（超过一半的书），众数与非众数进行相互抵消 或者 非众数与非众数相互抵消 当票数为 0
 * 的时候重新标记新的书最后标记的那个数就是众数
 */
public class Solution005 {


    // 1. 直接遍历所有的数据，记录数据的个数, 使用 hashMap
    // 时间复杂度 On
    public int majorityElement(int[] nums) {

        HashMap<Integer, Integer> keyMap = new HashMap<>();

        int length = nums.length;
        int halfLength = length/2;
        for (int i=0; i<length; i++) {

            int key = nums[i];
            if (keyMap.containsKey(key)) {
                Integer count = keyMap.get(key);
                count++;
                if (count > halfLength) {
                    return key;
                }
                keyMap.put(key, count);
            } else {
                keyMap.put(key, 1);
            }

        }

        for (Integer key : keyMap.keySet()) {

            if (keyMap.get(key) > halfLength) {
                return key;
            }

        }

        return -1;

    }

    // 2. 对数组进行排序，中间那个数一定是众数
    public int majorityElement1(int[] nums) {

        Arrays.sort(nums);
        return nums[nums.length/2];

    }

    // 3. 摩尔投票法，寻找众数（超过一半的书），众数与非众数进行相互抵消 或者 非众数与非众数相互抵消 当票数为 0
    // 的时候重新标记新的书最后标记的那个数就是众数
    public int majorityElement2(int[] nums) {

        int result = nums[0];
        int vote = 0;
        for (int i=0; i<nums.length; i++) {

            if (nums[i] == result) {
                vote++;
            } else {
                vote--;
                if (vote == 0) {
                    result = nums[i+1];
                }
            }

        }

        return result;

    }

}
