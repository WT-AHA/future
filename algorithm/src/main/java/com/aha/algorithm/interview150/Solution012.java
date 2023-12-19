package com.aha.algorithm.interview150;

import java.util.*;

/**
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution012 {

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    class RandomizedSet {

        // 先维护一个长度符合题意的数组
        // 根据下标 获取 是 O1 的；插入的话，队尾插入也是 O1 级别的；删除的话，删除队尾的也是 O1 级别的，如果删除的不是队尾的数据就将队尾的数据，放到被删除的数据的位置也可以实现 O1 级别的
        int[] nums = new int[200010];
        // 根据数组 长度，随机生成 数组下标，可以实现随机访问数据
        Random random = new Random();
        // 判断是否存在，使用 hashMap 的 key, value 维护数组的下标
        Map<Integer, Integer> map = new HashMap<>();
        // 数组下标
        int index = -1;

        public boolean insert(int val) {

            // 包含返回 false
            if (map.containsKey(val)) {
                return false;
            }

            // 不包含插入
            // 插入到数组，采用尾插法
            nums[++index] = val;
            // 插入到 map 中
            map.put(val, index);
            return true;

        }

        public boolean remove(int val) {

            // 不包含返回 false
            if (!map.containsKey(val)) {
                return false;
            }

            // 包含取出对应的数据，将对应的数据删除
            Integer removeIndex = map.remove(val);
            if (removeIndex != index) {
                // 删除的不是队尾的数据，将队尾的数据 覆盖到 要删除的下标，然后更新 map 中数组下标和数组值的对应关系
                // 将队尾的数据 覆盖到 要删除的下标
                nums[removeIndex] = nums[index];
                // 将队尾的数据 覆盖到 要删除的下标之后 要更新 map 的对应关系
                map.put(nums[removeIndex], removeIndex);
            }
            index --;

            return true;

        }

        public int getRandom() {
            return nums[random.nextInt(index+1)];
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(1));
        System.out.println(random.nextInt(1));
        System.out.println(random.nextInt(1));
        System.out.println(random.nextInt(1));

    }


}
