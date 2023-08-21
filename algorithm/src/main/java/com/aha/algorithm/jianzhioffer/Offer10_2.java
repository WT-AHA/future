package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * 青蛙 🐸 跳台阶问题
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 像这种题目都是类似于 斐波那契数列的 青蛙的最后一步只有两种情况，跳上一级台阶 或者 两级台阶
 *  如果它最后一步跳一节台阶的话，那么 n 阶的情况就是 f(n) = f(n-1) 就是 n-1 台阶有多少种情况就有多少种情况
 *  如果它最后一步跳二节台阶的话，那么 n 阶的情况就是 f(n) = f(n-2) 就是 n-2 台阶有多少种情况就有多少种情况
 * 所以一次跳 n 台阶的情况就是 这两种的和 那么就有 f(n) = f(n-1) + f(n-2) 不就是 斐波那契数列的 问题
 * 那么根据 斐波那契数列的解法，我们应该计算 f(0)[这个是因为题目给了示例 n=0 输出 1]， f(1) = 1 和 f(2) = 2
 * 为了符合 f(n) = f(n-1) + f(n-2)
 * 那么分析到这里，就跟 菲波那切数列的解法是一致的了
 *  1. 直接递归
 *  2. 递归加缓存
 *  3. 动态规划
 *
 * @author WT
 * @date 2022/01/20 11:09:14
 */
public class Offer10_2 {

    int[] tempResult = new int[101];

    /**
     * 缓存递归的方法
     * @param n 台阶的数目
     * @return 一共多少种跳法
     */
    public int numWays1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (tempResult[n] != 0) {
            return tempResult[n];
        }
        int temp = (numWays1(n - 1) + numWays1(n - 2)) % 1000000007;
        tempResult[n] = temp;

        return temp;
    }

    /**
     * 使用动态规划的方法，从小往大进行递推
     * @param n 台阶的数目
     * @return 一共多少种跳法
     */
    public int numWays(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        int f0=1, f1=1, sum;

        for (int i=0; i<n; i++) {
            sum = (f0+f1) % 1000000007;
            f0 = f1;
            f1 = sum;
        }

        return f0;

    }

}
