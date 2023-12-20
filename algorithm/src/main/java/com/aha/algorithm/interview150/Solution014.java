package com.aha.algorithm.interview150;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/gas-station/?envType=study-plan-v2&envId=top-interview-150
 *
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 * 示例 1:
 *
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 *
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * 提示:
 *
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 105
 * 0 <= gas[i], cost[i] <= 104
 *
 */
public class Solution014 {

    // 看过题解之后: 总结出两句话比较重要，
    // 1. 从 0 开始计算 minRemainGas, minRemainGas 一直 >= 0 (没有亏空) 那么 0 就可以走到终点
    // 2. 当 minRemainGas 有 < 0 的 情况，那么亏空最厉害的那个点必须最后一次走，因为此题只有一个解法，这样才能保证前面攒的油能走完亏空最严重的那个节点
    // 所以起点是亏空最大的后一个点，这样亏空最大的点是最后走的
    public static int canCompleteCircuit1(int[] gas, int[] cost) {

        int remainGas = 0;
        int minRemainGas = 0;
        int minRemainIndex = 0;
        int length = gas.length;
        for (int i=0; i<length; i++) {
            remainGas = remainGas + gas[i] - cost[i];
            if (remainGas < minRemainGas) {
                minRemainGas = remainGas;
                minRemainIndex = i;
            }
        }

        // 因为从第一个节点计算最小 minRemainGas 没有油不够的情况，那么就应该从第一个节点出发
        if (minRemainGas >= 0) {
            return 0;
        } else if (remainGas >= 0) {
            return (minRemainIndex+1) % length;
        } else {
            return -1;
        }

    }

    // 看到这题没有什么特殊思路，因为题目给出了只有唯一题解，认为可以根据题意顺序遍历判断
    // 没啥问题就是复杂度偏高，直接超时了
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int length = gas.length;
        int beginIndex = 0;
        while (beginIndex < length) {

            int i = beginIndex;
            int canReachCount = 0;
            int totalGas = 0;
            while (canReachCount < length && i != -1) {

                // 一般情况下算法题，尽量不使用 加减乘除 多用取余，取反等操作
                i = i%length;

                totalGas = totalGas + gas[i] - cost[i];

                if (totalGas < 0) {
                    // 说明 i 不能继续走了，退出第二个 while 循环
                    i = -1;
                } else {
                    // 说明 可以继续往后走
                    canReachCount ++;
                    i ++;
                }

            }

            if (canReachCount >= length) {
                return beginIndex;
            } else {
                beginIndex = beginIndex + canReachCount + 1;
            }

        }

        return -1;

    }

    public static void main(String[] args) {
        int[] array1 = new int[]{3,1,1};
        int[] array2 = new int[]{1,2,2};
        canCompleteCircuit1(array1, array2);
    }

}
