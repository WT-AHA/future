package com.aha.algorithm.interview150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 3. 无重复字符的最长子串
 *
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 *
 * abcabcbb
 * abc
 * bca
 *
 */
public class Solution031 {

    public static int lengthOfLongestSubstring(String s) {

        int length = s.length();
        // 边界值判断
        if (length == 0 || length ==1) {
            return length;
        }
        Map<Character, Integer> indexMap = new HashMap<>();
        char[] charArray = s.toCharArray();
        int i=0;
        int result = 1;
        while (i < length) {

            if (indexMap.containsKey(charArray[i])) {
                result = Math.max(indexMap.size(), result);
                Integer index = indexMap.get(charArray[i]);
                i = index + 1;
                indexMap.clear();
            } else {
                indexMap.put(charArray[i], i++);
            }

        }

        return Math.max(indexMap.size(), result);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

}
