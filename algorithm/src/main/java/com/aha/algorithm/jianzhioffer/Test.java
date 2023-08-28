package com.aha.algorithm.jianzhioffer;

/**
 * 用于运行默写的代码
 */
public class Test {

    public static int numWays(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        int dp0 = 1, dp1 = 1, dpn = 0;

        for (int i=0; i<n; i++) {
            dpn = (dp0 + dp1) % 1000000007;
            dp0 = dp1;
            dp1 = dpn;
        }

        return dp0;

    }

    public static void main(String[] args) {
        System.out.println(numWays(1));
    }


}
