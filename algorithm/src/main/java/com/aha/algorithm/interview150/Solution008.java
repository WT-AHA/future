package com.aha.algorithm.interview150;


/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/?envType=study-plan-v2&envId=top-interview-150
 */
public class Solution008 {

    // 使用贪心算法的思路，利益最大化
    // 递增的数组，每天交易 (注: 递增的数据每天交易和  最低点买入，最高点卖出的收益是一致的)
    // 递减的数据，一笔也不交易
    public int maxProfit(int[] prices) {

        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {

            if (prices[i] - prices[i-1] > 0) {
                // 递增的数据每天交易
                maxProfit += prices[i] - prices[i-1];
            }

        }


        return maxProfit;

    }

}
