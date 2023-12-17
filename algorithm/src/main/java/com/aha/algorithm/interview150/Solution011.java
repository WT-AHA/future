package com.aha.algorithm.interview150;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/h-index/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution011 {

    // 最优解为，空间换时间
    // 另外 new 一个数组，数组第一个位置记录大于0的有几个，第二个位置记录大于1的有几个
    // h 最大的值为 citations.length 要记录 大于等于 citations.length 有几个 新数组的长度就有 length+1
    public static int hIndex1(int[] citations) {

        int length = citations.length;
        int[] newArray = new int[length+1];
        for (int i=0; i<length; i++) {
            // 大于等于length
            if (citations[i] >= length) {
                // length 的位置计数 ++
                newArray[length] ++;
            } else {
                // citations[i] = citations[i] 让 newArray 的 citations[i] 位置 ++
                newArray[citations[i]] ++;
            }
        }

        // 维护好了计数数组，从后往前进行遍历
        int h = 0;
        for (int n=length; n>0; n--) {
            // 有多少 > n 的
            h += newArray[n];
            if (h >= n) {
                // 例如在第三个位置 一共有 3 个 >= 3 的就符合 h 的条件，直接返回
                return n;
            }
        }

        return 0;

    }

    // 第一眼看见的反应是先进行数组的排序，然后从后往前来进行计数
    public int hIndex(int[] citations) {

        Arrays.sort(citations);
        int length = citations.length;
        int h = length;
        while (h>0) {

            if (citations[length-h] >= h) {
                return h;
            }
            h --;

        }

        return h;

    }

    public static void main(String[] args) {
        int[] array = {3, 0, 6, 1, 5};
        hIndex1(array);
    }

}
