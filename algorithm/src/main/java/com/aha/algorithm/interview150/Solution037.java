package com.aha.algorithm.interview150;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 383. 赎金信
 *
 * 简单
 * 相关标签
 * 相关企业
 *
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * 示例 1：
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 *
 * 示例 2：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 *
 * 示例 3：
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 *
 */
public class Solution037 {

    public boolean canConstruct2(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // 存储 26 个小写英文字母
        int[] cnt = new int[122];
        for (char c : magazine.toCharArray()) {
            cnt[c]++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c]--;
            if(cnt[c] < 0) {
                return false;
            }
        }
        return true;

    }

    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // 存储 26 个小写英文字母
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;

    }

    public boolean canConstruct1(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (int i=0; i<magazine.length(); i++) {
            char key = magazine.charAt(i);
            if (characterIntegerHashMap.containsKey(key)) {
                characterIntegerHashMap.put(key, characterIntegerHashMap.get(key) + 1);
            } else {
                characterIntegerHashMap.put(key, 1);
            }
        }

        for (int i=0; i<ransomNote.length(); i++) {
            char key = ransomNote.charAt(i);
            if (!characterIntegerHashMap.containsKey(key)) {
                return false;
            } else {
                Integer count = characterIntegerHashMap.get(key);
                if (count <= 0) {
                    return false;
                } else {
                    characterIntegerHashMap.put(key, --count);
                }
            }
        }

        return true;

    }

    public static void main(String[] args) {
        System.out.println('a'-0);
    }

}
