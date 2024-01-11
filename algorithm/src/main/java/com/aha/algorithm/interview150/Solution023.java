package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 28. 找出字符串中第一个匹配项的下标
 * 简单
 * 相关标签
 * 相关企业
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 *
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 *
 *
 * 提示：
 *
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class Solution023 {

    // 不使用 indexOf 题目大概率是不让使用此类 api的
    // 直观的进行比较
    public int strStr(String haystack, String needle) {


        int haystackLength = haystack.length();
        int needleLength = needle.length();
        if (needleLength > haystackLength) {
            return -1;
        }

        for (int i=0; i<haystackLength; i++) {

            int j=0;

            while (j < needleLength) {

                if (i+j < haystackLength && haystack.charAt(i+j) == needle.charAt(j)) {
                    if (j == needleLength-1) {
                        return i;
                    }
                    j ++;
                } else {
                    break;
                }

            }

        }

        return -1;

    }


    // 直接使用 indexOf
    public int strStr1(String haystack, String needle) {

        return haystack.indexOf(needle);

    }

}
