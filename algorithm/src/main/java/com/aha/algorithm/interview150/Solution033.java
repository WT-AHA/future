package com.aha.algorithm.interview150;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/spiral-matrix/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 54. 螺旋矩阵
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class Solution033 {

    // 看到这题的第一印象是直接遍历, 每次到边界变化方向
    // 直接干掉 100% 的人
    public static List<Integer> spiralOrder(int[][] matrix) {

        // 二维数组的两个指针
        int rowIndex=0;
        int columnIndex=0;
        // 四个方向的边界值
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        int leftLength = 0;
        int upLength = 0;
        // 数组中 树的数量
        int numCount = rowLength * columnLength;
        List<Integer> resultList = new ArrayList<>();
        // 遍历数组的方向
        String direction = "right";

        while (resultList.size() < numCount) {

            switch (direction) {

                case "right": {

                    while (columnIndex < columnLength) {
                        resultList.add(matrix[rowIndex][columnIndex]);
                        columnIndex++;
                    }
                    columnIndex--;
                    columnLength--;
                    rowIndex++;
                    direction = "down";
                    break;

                }

                case "left": {

                    while (columnIndex >= leftLength) {
                        resultList.add(matrix[rowIndex][columnIndex]);
                        columnIndex--;
                    }
                    columnIndex++;
                    leftLength++;
                    rowIndex--;
                    direction = "up";
                    break;

                }

                case "down": {

                    while (rowIndex < rowLength) {
                        resultList.add(matrix[rowIndex][columnIndex]);
                        rowIndex++;
                    }
                    rowIndex--;
                    rowLength--;
                    columnIndex--;
                    direction = "left";
                    break;

                }

                case "up": {

                    while (rowIndex > upLength) {
                        resultList.add(matrix[rowIndex][columnIndex]);
                        rowIndex--;
                    }
                    rowIndex++;
                    upLength++;
                    columnIndex++;
                    direction = "right";
                    break;

                }

                default: {
                    break;
                }

            }

        }

        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

}
