package com.aha.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class LeetCode17Myself {

    public static void main(String[] args) {
        System.out.println(letterCombinationList("24"));
    }

    public static List<String> letterCombinationList(String digitStr) {

        // 结果集
        List<String> combinationList = new ArrayList<>();
        if (digitStr.length() == 0) {
            return combinationList;
        }

        // 电话号码 和 字符串的映射关系
        Map<Character, String> digitStringMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        constructResult(0, digitStr, new StringBuffer(), combinationList, digitStringMap);

        return combinationList;

    }

    /**
     * 递归回溯方法
     *
     * @param index 当前回溯的游标，标记回溯到哪一个电话号码了
     * @param digitStr 输入的电话号码字符串
     * @param combinationStr 此次回溯构建的字符串
     * @param combinationStrList 所有的回溯结果的字符串集合
     * @param digitStringMap 电话号码 和 其代表的字符串 的映射关系
     */
    public static void constructResult (int index, String digitStr, StringBuffer combinationStr,
                                        List<String> combinationStrList, Map<Character, String> digitStringMap) {

        // 当前回溯的下标 和 输入的数字长度 相等 说明已经符合条件了
        if (index == digitStr.length()) {
            combinationStrList.add(combinationStr.toString());
            return;
        }

        // 获取当前 index 对应的 电话号码
        char c = digitStr.charAt(index);
        // 获取当前电话号码对应的 String
        String str = digitStringMap.get(c);

        for (int i=0; i<str.length(); i++) {
            combinationStr.append(str.charAt(i));
            // 回溯，构建后面数字对应的字符
            constructResult(index + 1, digitStr, combinationStr, combinationStrList, digitStringMap);
            // 当前列已经回溯完成，将当前列去掉继续下一列的回溯
            combinationStr.deleteCharAt(index);
        }

    }


}
