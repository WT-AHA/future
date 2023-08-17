package com.aha.algorithm.leetcode;

import java.util.*;

/**
 * n 皇后问题，看完题解自己做
 * https://leetcode.cn/problems/n-queens/
 */
public class LeetCode51MySelf {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {

        // 使用一个数组来标记哪一行的哪一列应该防止皇后, 数组下标就是行号, 下标对应的值就是列号, 所以数组的长度应该等于 n
        // 因为题目描述 1 <= n <= 9 索引咱们使用 byte 类型即可
        int[] flagArray = new int[n];
        // 使用一个 Set 集合来标记列数
        Set<Integer> columnSet = new HashSet<>();
        // 使用一个 Set 集合来标记 斜线1 斜线一的计算方法就是 行下标-列下标相同 \ 的方向
        Set<Integer> diagonalOneSet = new HashSet<>();
        // 使用一个 Set 集合来标记 斜线2 斜线二的计算方法就是 行下标+列下标相同 / 的方向
        Set<Integer> diagonalTwoSet = new HashSet<>();
        // 最终的结果集
        List<List<String>> resultList = new ArrayList<>();

        // 回溯 - 递归 构建结果
        constructFlagArray(n, 0, flagArray, columnSet, diagonalOneSet, diagonalTwoSet, resultList);

        return resultList;

    }

    /**
     * @param n 要构建 几乘几的 n 皇后问题
     * @param rowNum 当前构建的行号
     * @param flagArray 标记哪一行的那一个位置是 皇后的标记数组
     * @param columnSet 已经存在的列
     * @param diagonalOneSet 已经存在的斜线 1 行下标-列下标
     * @param diagonalTwoSet 已经存在的斜线 2 行下标+列下标
     */
    public static void constructFlagArray(int n, int rowNum, int[] flagArray, Set<Integer> columnSet,
                                       Set<Integer> diagonalOneSet, Set<Integer> diagonalTwoSet, List<List<String>> resultList) {

        // 已经构建到最后一行数据了 可以进行返回了， 这边可能出现多个结果，所以这边要讲构建好，满足条件的 flagArray 转化为结果，保存到 resultList 中
        if (n == rowNum) {
            // 根据 flagArray 构建出结果，放入到 resultList
            resultList.add(constructResult(flagArray));
            return;
        }

        for (int i=0; i<n; i++) {

            // 三个条件判断 当前列是否满足条件
            if (columnSet.contains(i) || diagonalOneSet.contains(rowNum - i) || diagonalTwoSet.contains(rowNum + i)) {
                continue;
            }

            // 满足条件，将此行此列的数据添加到筛选条件中
            columnSet.add(i);
            diagonalOneSet.add(rowNum - i);
            diagonalTwoSet.add(rowNum + i);
            flagArray[rowNum] = i;

            // 递归构建后序的列，直到完成
            constructFlagArray(n, rowNum + 1, flagArray, columnSet, diagonalOneSet, diagonalTwoSet, resultList);

            // 此列回溯完成，符合的结果已经在 if 中构建了，移除当前的判断条件，换一列进行回溯
            columnSet.remove(i);
            diagonalOneSet.remove(rowNum - i);
            diagonalTwoSet.remove(rowNum + i);
            flagArray[rowNum] = -1;

        }

    }


    /**
     * 根据标志位构建结果
     * @param flagArray 标志为数组
     * @return 构建好的一个结果集
     */
    public static List<String> constructResult (int[] flagArray) {

        List<String> stringList = new ArrayList<>();
        int length = flagArray.length;
        char[] charArray = new char[length];
        for (int i=0; i<flagArray.length; i++) {
            Arrays.fill(charArray, '.');
            // 第 i 行 的 flagArray[i] 列 是 皇后
            charArray[flagArray[i]] = 'Q';
            stringList.add(new String(charArray));
        }

        return stringList;

    }

}
