package com.aha.algorithm.interview150;

import java.util.HashMap;

/**
 *
 * https://leetcode.cn/problems/roman-to-integer/?envType=study-plan-v2&envId=top-interview-150
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。
 * 但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: s = "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: s = "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 *
 */
public class Solution017 {

    // 这种少量数据的  使用 switch 速度是比 hashMap 快的
    private static HashMap<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    // 这样判断有点愚蠢
    public int romanToInt(String s) {

        int result = 0;
        int length = s.length();
        for (int i = 0; i<length; i++) {

            char currentChar = s.charAt(i);
            if (currentChar == 'I' && i + 1 < length
                    && (s.charAt(i+1) == 'V' || s.charAt(i+1) == 'X')) {
                result = result - 1;
            } else if (currentChar == 'X' && i + 1 < length &&
                    (s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C')) {
                result = result - 10;
            }else if (currentChar == 'C' && i + 1 < length &&
                    (s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M')) {
                result = result - 100;
            } else {
                result = result + map.get(currentChar);
            }

        }


        return result;

    }

    // 总结下规律就是 当左边的数据没有右边的数据大的时候，应该减去左边的数据
    public static int romanToInt1(String s) {

        int result = 0;
        int length = s.length();
        int leftValue = 10000;
        for (int i = 0; i<length; i++) {

            Integer currentValue = getValue(s.charAt(i));
            if (leftValue < currentValue) {
                result = result - (leftValue * 2);
            }
            result = result + currentValue;
            leftValue = currentValue;

        }


        return result;

    }

    private static int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(romanToInt1("MCMXCIV"));
    }

}
