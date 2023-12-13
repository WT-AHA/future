package com.aha.algorithm.interview150;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution007 {

    // 第一印象没有想出什么好的办法，暴力破解一下先，
    // 提交的时候直接超出了时间限制
    public int maxProfit(int[] prices) {

        int length = prices.length;
        int max = 0;
        for (int i=0; i<length; i++) {
            int j=i+1;
            while (j<length) {
                max = Math.max(max, prices[j] - prices[i]);
                j++;
            }
        }

        return max;
    }

    // 直接找出 i 之后最大的 然后与 i 相减 结果还是超出时间限制，所以得转变思路了
    public int maxProfit1(int[] prices) {

        int length = prices.length;
        int maxResult = 0;
        for (int i=0; i<length; i++) {
            int j=i+1;
            // 找出 i 之后最大的
            int max = 0;
            while (j<length) {
                max = Math.max(max, prices[j]);
                j++;
            }
            maxResult = Math.max(max-prices[i], maxResult);
        }

        return maxResult;
    }

    /**
     * 当天可以获得的最大的利润一定是当天的成本 减去 当天前的历史最低点
     * 对于数组来说, 当天的价格就是 prices[i], 那我们只需要记录 历史最低点就可以算出当前卖出的利润
     * 维护一个变量为最大利润，当前的利润与最大利润进行比较几颗
     */
    public int maxProfit2(int[] prices) {

        // 最大利润
        int maxProfit = 0;
        // 历史低点（历史就是当天之前的最低价格）
        int minPrice = prices[0];

        for (int i=1; i<prices.length; i++) {

            // 当时最小值的时候，不用计算当前的利润，因为当天是最小值，利润肯定是 负数
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
//            else {
//                // 不是最小值的时候计算利润
//                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
//            }
            // 发现 直接判断比 Math.max() 函数执行的快
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }

        }

        return maxProfit;
    }

}
