package com.aha.algorithm.interview150;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/zigzag-conversion/?envType=study-plan-v2&envId=top-interview-150
 *
 * 6. Z 字形变换
 * 中等
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P0      I6      N12
 * A1   L5 S7  I11 G13
 * Y2 A4   H8 R10
 * P3      I9
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 */
public class Solution022 {

    // 0   4   8
    // 1 3 5 7 9
    // 2   6   10
    // i 的取值为 numRows -1, 主要的思路是 从 0 递增到 numRows-1 然后 从 numRows-1 递减到 0 这样循环
    // 所以 flag 每次变化的时候 是遇到 0 或者 numRows - 1 进行标志位的变化
    public String convert(String s, int numRows) {

        // 边界值判断
        if (numRows == 1) {
            return s;
        }

        // 构建行
        ArrayList<StringBuilder> rows = new ArrayList<>();
        for (int i=0; i<numRows; i++) {
            rows.add(new StringBuilder());
        }

        // 往行里面塞入数据的时候规则为，从 0 递增到 numRows-1 然后 从 numRows-1 递减到 0 这样循环
        int rowNum = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {

            // 塞入对应的行
            rows.get(rowNum).append(c);
            // 如果 rowNum == 0 || rowNum == numRows - 1, 需要从递增变成递减或者从递减变成递增
            if (rowNum == 0 || rowNum == numRows - 1) {
                flag = -flag;
            }
            rowNum += flag;

        }

        // 拼接结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();

    }

}
