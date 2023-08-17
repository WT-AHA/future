package com.aha.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class LeetCode17 {

    public static void main(String[] args) {
        System.out.println(letterCombinations("24"));
    }

    public static List<String> letterCombinations(String digits) {

        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    /**
     *
     * @param combinations
     * @param phoneMap 数组和字符串的映射表
     * @param digits 输入的数字
     * @param index 处理第几个输入的数字
     * @param combination
     */
    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {

        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = phoneMap.get(digit);
        int lettersCount = letters.length();
        for (int i = 0; i < lettersCount; i++) {
            combination.append(letters.charAt(i));
            backtrack(combinations, phoneMap, digits, index + 1, combination);
            // 此列回溯完成，符合条件的已经放入到集合中了，删除当前 index 的字符，换一列进行回溯，想想一个树结构
            combination.deleteCharAt(index);
        }

    }

}
