package com.aha.algorithm.leetcode;

import java.util.*;

/**
 * n 皇后问题
 * https://leetcode.cn/problems/n-queens/
 */
public class LeetCode51 {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    /**
     * @param solutions 最终的集合
     * @param queens 保存每行的第几列是 Q，行号为 数据下标，列号为数据值
     * @param n 需要的行列数
     * @param row 当前的行数
     * @param columns 已经存在的列的集合
     * @param diagonals1 已经存在的斜线的集合
     * @param diagonals2 已经存在的斜线2的集合
     */
    public static void backtrack(List<List<String>> solutions, int[] queens,
                                 int n, int row, Set<Integer> columns,
                                 Set<Integer> diagonals1, Set<Integer> diagonals2) {

        if (row == n) {
            // 需要返回结果的时候进入此分支
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            // i 代表的是列数
            for (int i = 0; i < n; i++) {
                // 同一列
                if (columns.contains(i)) {
                    continue;
                }
                // 斜线 1
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                // 斜线 2
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }

                // 行号为数据下标，列号为数组的值(i 为 列号)
                queens[row] = i;
                // 标记此列已有数据
                columns.add(i);
                // 标记此斜线已经有数据
                diagonals1.add(diagonal1);
                // 标记斜线2有数据
                diagonals2.add(diagonal2);

                // 构建下一行的数据, 如果下一行的数据一直满足的话，就会走到 row == n ，然后返回结果；
                // 如果当前行的当前列 i 不满足的话 走 continue，行不换，换一列看看满足不满足，都不满足，跳过了这个递归
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);

                // 没有在上个递归中返回结果，说明: 当前行 row 当前列 i 放皇后，后序的行没有满足条件的答案，所以 当前行的当前列不能放皇后，列 ++ 往后移动，重新寻找答案
                queens[row] = -1;
                // 删除当前列,两个斜线的情况
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
