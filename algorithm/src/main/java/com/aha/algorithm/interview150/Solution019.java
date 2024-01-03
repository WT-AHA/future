package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/length-of-last-word/?envType=study-plan-v2&envId=top-interview-150
 *
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 *
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 * 示例 2：
 *
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为4。
 * 示例 3：
 *
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为6的“ joyboy”。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 *
 */
public class Solution019 {

    public static int lengthOfLastWord(String s) {

        int beginIndex = -1;
        // 从后往前遍历呗
        for (int i=s.length()-1; i>=0; i--) {
            if (s.charAt(i) != ' ' && beginIndex == -1) {
                beginIndex = i;
            } else if (s.charAt(i) == ' ' && beginIndex != -1) {
                return beginIndex - i;
            }

        }

        return beginIndex + 1;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }

}
