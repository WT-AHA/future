package com.aha.algorithm.interview150;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 205. 同构字符串
 *
 * 简单
 * 相关标签
 * 相关企业
 *
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 *
 * 示例 3：
 * 输入：s = "paper", t = "title"
 * 输出：true
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 *
 */
public class Solution038 {

    // 将 s 映射到 t，使用 hashMap 来进行映射
    public boolean isIsomorphic(String s, String t) {

        int sLength = s.length();
        int tLength = t.length();

        if (sLength != tLength) {
            return false;
        }

        HashMap<Character, Character> characterCharacterHashMap = new HashMap<>();
        for (int i=0; i<sLength; i++) {
            char key = s.charAt(i);
            char value = t.charAt(i);
            if (characterCharacterHashMap.containsKey(key)) {

                if (value != characterCharacterHashMap.get(key)) {
                    return false;
                }

            } else {

                // 当不包含 key 但是包含 value 的时候说明一个 value 对应多个 key 了也是有问题的，返回 false
                if (characterCharacterHashMap.containsValue(value)) {
                    return false;
                }

                characterCharacterHashMap.put(key, value);
            }
        }

        return true;

    }

}
