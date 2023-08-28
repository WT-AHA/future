package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 * 礼物的最大价值：
 * 具体的解析参考：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/solution/mian-shi-ti-47-li-wu-de-zui-da-jie-zhi-dong-tai-gu/
 * 简单的说明一下思路，直接在矩阵上修改，从上往下一行一行的进行修改，将原本的值变成达到那个位置最大的值
 * 先修改第一行的因为可以控制一个变量可以很好的计算出最大的也就是最优解
 * 主要分为以下四种情况：
 *  i j 同时为 0 为一个固定的值就是自己本身的值
 *  i j 有一个为 0 只自己左边或者上面的值加上自己本身的值
 *  i j 都不为 0 应该是自己上面或者左边较大的值加上自己本身的值
 *
 * @author WT
 * @date 2022/01/21 11:32:02
 */
public class Offer47 {

    public int maxValue(int[][] grid) {
        int rows = grid.length;
        int column = grid[0].length;
        // 行
        for (int i=0; i<rows; i++) {
            // 列
            for (int j=0; j<column; j++) {
                if (i ==0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] += grid[i][j-1];
                } else if (j == 0) {
                    grid[i][j] += grid[i-1][j];
                } else {
                    grid[i][j] = grid[i][j] + Math.max(grid[i -1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[rows-1][column-1];
    }

}
