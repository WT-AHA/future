package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 * 斐波那契数列
 * @author WT
 * @date 2022/01/19 10:21:19
 */
public class Offer10_1 {

    /**
     * 经典的斐波那契数列 - 第一印象 使用递归 - 结果要对 1000000007 进行取模
     * 1000000007
     * 2147483648
     * 1000000007 没有超过 int 的范围
     *
     * 直接使用递归的话就超出了时间限制
     * @param n f(n)
     * @return 结果
     */
    public int fib2 (int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return (fib2(n - 1) + fib2(n - 2)) % 1000000007;
    }


    /**
     * 因为题目给出 0 <= n <= 100 所以创建数组的时候，创建为 101 的数组，这样写速度很快，但是空间复杂度相对较高
     */
    int[] tempArray = new int[101];
    /**
     * 因为使用递归超出了时间限制，我们采用空间换时间的思路，将每次计算得出的函数值给存放起来
     *
     * @param n f(n)
     * @return 结果
     */
    public int fib1 (int n) {

        if (tempArray[n] != 0) {
            return tempArray[n];
        }

        if (n == 0 || n == 1) {
            return n;
        }
        int tempResult = (fib1(n - 1) + fib1(n - 2)) % 1000000007;
        tempArray[n] = tempResult;
        return tempResult;

    }

    /**
     * 使用递归相当于从大数 -> 小数 推进，这样当我们要计算 f(10) 的时候 f(3) 不知道计算了多少次，属于重复计算
     * 而我们的缓存法，就相当于将每次计算过的值不用重复计算了，直接从数组中获取，这样呢，空间复杂度就变成了 O(n) 级别的，
     * 这个时候我们想，如果我们从小数 -> 大数进行累加 直到累加到我们需要的数字，不就不涉及小数重新计算的问题了，
     * 这种方法被称为动态规划的方法
     * 如果对步骤理解的不太清晰的话，可以参考
     * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/solution/fei-bo-na-qi-shu-lie-by-leetcode-solutio-hbss/
     *
     * @param n f(n)
     * @return 结果
     */
    public int fib (int n) {

        final int mod = 1000000007;

        if (n < 2) {
            return n;
        }

        int left, right = 0, result = 1;
        for (int i = 2; i <= n; ++i) {
            left = right;
            right = result;
            result = (left + right) % mod;
        }
        return result;
    }

}
