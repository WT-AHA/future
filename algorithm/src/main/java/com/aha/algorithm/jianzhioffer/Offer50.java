package com.aha.algorithm.jianzhioffer;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * @author WT
 * @date 2022/01/14 09:27:57
 */
public class Offer50 {

    /**
     * 看到这道题的第一个想法是使用 hash 表来记录出现的次数，然后遍历 hash 表，看看有没有 出现一次的
     * 因为题目要求第一次出现一次的，所以在遍历 hashMap 的时候，我们的 key 选择从 字符串从前往后获取 key，因为 hashMap 是无序的
     * 也可以使用 linkedHashMap
     *
     *
     * @param s 输入的字符串
     * @return 没有返回单个空格，有的话返回出现一次的数字
     */
    public char firstUniqChar1(String s) {

        if (s == null || s.isEmpty()) {
            return ' ';
        }

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i=0; i<s.length(); i++) {
            char temp = s.charAt(i);
            if (hashMap.containsKey(temp)) {
                hashMap.put(temp, hashMap.get(temp) + 1);
            } else {
                hashMap.put(temp, 1);
            }
        }

        for (int i=0; i<s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }

        return ' ';

    }

    /**
     * 对方法一的一点优化， 使用 linkedHashMap 会影响速度
     *
     * @param s 输入的字符串
     * @return 没有返回单个空格，有的话返回出现一次的数字
     */
    public char firstUniqChar2(String s) {

        if (s == null || s.isEmpty()) {
            return ' ';
        }

        HashMap<Character, Boolean> hashMap = new HashMap<>();

        for (int i=0; i<s.length(); i++) {
            char temp = s.charAt(i);
            hashMap.put(temp, !hashMap.containsKey(temp));
        }

        for (int i=0; i<s.length(); i++) {
            if (hashMap.get(s.charAt(i))) {
                return s.charAt(i);
            }
        }

        return ' ';

    }

    /**
     * 一直没有用到官方给的一个都是小写的条件，时间复杂度都是一样就是数组指定所以的获取和修改比 hashMap 快
     *
     * @param s 输入的字符串
     * @return 没有返回单个空格，有的话返回出现一次的数字
     */
    public char firstUniqChar(String s) {

        if ("".equals(s)) {
            return ' ';
        }

        // 创建‘a'-'z'的字典
        int[] target = new int[26];
        // 第一次遍历，将字符统计到字典数组
        for (int i = 0; i < s.length(); i++) {
            target[s.charAt(i) - 'a']++;
        }

        // 第二次遍历，从字典数组获取次数
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }

        return ' ';

    }

}
