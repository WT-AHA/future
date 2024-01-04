package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/longest-common-prefix/?envType=study-plan-v2&envId=top-interview-150
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 */
public class Solution020 {

    // 简单的题一般直接按照要就就能解出来
    public String longestCommonPrefix(String[] strs) {

        int length = strs[0].length();

        if (length == 0) {
            return "";
        }

        for (int i=1; i<strs.length; i++) {

            length = Math.min(strs[i].length(), length);

            for (int j=0; j<length; j++) {

                if (strs[i-1].charAt(j) != strs[i].charAt(j)) {
                    length = j;
                    break;
                }

            }

            if (length == 0) {
                return "";
            }

        }

        return strs[0].substring(0, length);

    }

}
