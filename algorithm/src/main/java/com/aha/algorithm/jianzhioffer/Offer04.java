package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 * 有序二维数据中查找对应的数据
 *
 * @author WT
 * @date 2022/01/13 09:15:49
 */
public class Offer04 {

    /**
     *
     * 发现了一个规律，在算法题中很少有使用 for 循环的，一般都是使用 while 循环，在符合条件的时候才会使用 i++ 或者 j++
     * 看不懂这个看下下面的解析
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {

        // 行数和列数边界判断
        int row = plants.length;
        if (row == 0) {
            return false;
        }
        // 列数边界和最大值,最小值判断
        int column = plants[0].length;
        if (column == 0 || target > plants[row-1][column-1] || target < plants[0][0]) {
            return false;
        }

        // 基本思路，得着一个切入点，一遍比这个数大，一遍比这个数小，这样才可以判断 大于往哪里移动
        // 小于往哪里移动，所以咱们找 a[0][column] 这个节点作为起点
        // 就是寻找一个符合二分搜索树特点的节点作为根节点，就转化为 二分搜索树的问题了
        int i = 0;
        int j = column - 1;
        while (i<row && j>=0) {

            if (plants[i][j] == target) {
                return true;
            }

            if (plants[i][j] > target) {
                // 往左移动
                j--;
                continue;
            }

            if (plants[i][j] < target) {
                // 往下移动
                i++;
                continue;
            }

        }
        return false;

    }

    /**
     * 使用线性查找的方法，这道题的核心在选择遍历的根节点上，可以选择左下角或者右上角作为遍历的根节点
     * 因为左上角是最小的数据，右下角是最大的数据，当 target 与它们比较的时候没有办法缩小 target 的搜索范围，
     * 比如左上角 为 2 target 为 4，4 > 2, 这个时候不管左移还是右移都会使这个数变大所以没有办法缩小范围
     * 如果选则右上角的作为 根节点 右上角是 7 ， target 是 4， 4 < 7 所以 target 一定在 7 的左边应该执行 j --，反之执行 i ++
     *
     * date: 2023/05/04 14:59:00 重新看这个题的补充，第一眼看到这个题直观的感受是直接通过左上角将它变成一棵树，后来发现不符合二叉搜索树的特点
     * 就是 1 不能是根节点，因为题目中给的二维数据没有比 1 小的，所以要换个位置当做根节点，左下角和右上角都可以，因为他们都符合二叉搜索树的特点
     * 就是左节点小于根节点小于右节点，这样咱们在比较数据的时候就可以明确的减少数据的范围，
     * 对于左下角来说，如果 target 比它大，那么就往右移动 j++ ，如果比它小，就往上移动 i--，这样就可以减少数据的范围,直到找到相应的数据
     *
     * @param matrix 输入的二维数组 矩阵
     * @param target 要查找值
     * @return 是否包含此数据
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        // 首先对矩阵进行校验
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // 注意矩阵的行和列的获取方法
        // int rows = matrix.length - 1;
        // int column = matrix[0].length;

        // 选则 右上角的元素作为 根节点记性遍历
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >=0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                // 左移
                j --;
            } else {
                // 下移
                i ++;
            }
        }
        return false;
    }

}
