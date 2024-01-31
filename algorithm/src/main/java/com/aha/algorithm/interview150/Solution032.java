package com.aha.algorithm.interview150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * https://leetcode.cn/problems/valid-sudoku/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 36. 有效的数独
 *
 * 中等
 * 相关标签
 * 相关企业
 *
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 *
 *
 * 注意：
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 * 示例 2：
 *
 * 输入：board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 *
 * 提示：
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字（1-9）或者 '.'
 * 请问您在哪类招聘中遇到此题？
 * 1/5
 * 社招
 * 校招
 * 实习
 * 未遇到
 */
public class Solution032 {

    /**
     * 所谓的 set 就是 hash 表
     *
     * 遍历一遍数独矩阵，在遍历的过程中依次判断是否满足题目中给的三个条件
     * 1. 同一行只能出现一次，一行用一个 set 来判断是否重复出现
     * 2. 同一列只能出现一次，一列用一个 set
     * 3. 同一个矩阵中只能出现一次，一个矩阵用一个 set
     *  怎么判断某一个数字属于哪一个矩阵？
     *  从左右对矩阵进行编号，分别为 0 1 2
     *                          3 4 5
     *                          6 7 8
     *  matrixIndex = i / 3 * 3 + j / 3   注意 i / 3 * 3 不能简化成 i 因为 i/3 不是整数的时候会向下取整
     */
    public boolean isValidSudoku(char[][] board) {

        // 初始化用于判断是否重复的 hash 表
        List<Set<Character>> rowList = new ArrayList<>(9);
        List<Set<Character>> columnList = new ArrayList<>(9);
        List<Set<Character>> matrixList = new ArrayList<>(9);

        for (int i=0; i<9; i++) {
            rowList.add(i, new HashSet<>(9));
            columnList.add(i, new HashSet<>(9));
            matrixList.add(i, new HashSet<>(9));
        }

        // 遍历的矩阵
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int matrixIndex = i / 3 * 3 + j / 3;
                if (
                        rowList.get(i).contains(c)
                        || columnList.get(j).contains(c)
                        || matrixList.get(matrixIndex).contains(c)
                ) {
                    return false;
                }
                // 行中添加
                rowList.get(i).add(c);
                columnList.get(j).add(c);
                matrixList.get(matrixIndex).add(c);
            }
        }

        return true;
    }

}
