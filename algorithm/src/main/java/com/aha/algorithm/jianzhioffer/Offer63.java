package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 * 股票的最大利益
 *
 * @author WT
 * @date 2022/01/20 12:05:13
 */
public class Offer63 {

    /**
     * 这道题的核心在买入的点，买入的点应该是数组中较小的数，只有出现更小的数，才可能出现利润更大的情况
     * 所以我们首先要做的就是记录买入的点，当没出现更小值的时候记录当前的利益，如果出现更小值的时候应该更改买入点的为这个最小值
     * 然后比较后面的利润和前面记录的利润进行比较，利润更大的话就更新利润值
     *
     * @param prices 股票的价格表
     * @return 利润值
     */
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            // cost 应该是当前最小的值
            cost = Math.min(cost, price);
            // 利润应该是 price - cost 然后取较大的那个
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

}
