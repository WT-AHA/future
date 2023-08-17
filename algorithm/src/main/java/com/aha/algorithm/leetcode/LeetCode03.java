package com.aha.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 3. 无重复字符的最长子串
 *
 * 一般看到子串的问题都要想到滑动窗口
 */
public class LeetCode03 {

    public static void main(String[] args) {
        System.out.println(maxLen("qwjssurrw"));
        System.out.println(maxLen("bbbbbb"));
//        System.out.println(maxLen("aau"));
    }

    public static int maxLen (String s) {

        // 子串的最大长度
        int maxLen = 1;
        // 标记窗口的最左边
        int left = 0;
        // 存储出现过的字符，以及字符下标的位置,遇到重复的字符会重新更新下标的位置
        Map<Character, Integer> map = new HashMap<>();
        if (s == null || s.length() == 0) {
            return 0;
        }

        for (int i=0; i<s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // 滑动窗口一旦往前滑动不会往后推，随要判断 left 和 上次出现此字符的后一位(map.get(s.charAt(i))  + 1)哪一个大 取大的
                left = Math.max(left, map.get(s.charAt(i))  + 1);
            }
            maxLen = Math.max(maxLen, i-left+1);
            map.put(s.charAt(i), i);
        }


        return maxLen;
    }

}
