package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * 左旋转字符串
 *
 * @author WT
 * @date 2022/01/10 18:09:53
 */
public class Offer58 {

    /**
     * 刚看到题的基本思路是 以 n 为界限 直接往 StringBuffer 中放数据即可 - 但是效率太低
     * @param s 输入的字符串
     * @param n 需要反转的字符
     * @return 反转过后的字符串
     */
    public String reverseLeftWords1(String s, int n) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=n; i<s.length(); i++) {
            stringBuffer.append(s.charAt(i));
        }
        for (int i=0; i<n; i++) {
            stringBuffer.append(s.charAt(i));
        }
        return stringBuffer.toString();
    }

    /**
     * 以 n 为界限分割成两个字符串，使用 substring()
     * 或者直接使用下面这种方法
     * @param s 输入的字符串
     * @param n 旋转的位置
     * @return 返回处理好的字符串
     */
    public String reverseLeftWords(String s, int n) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s, n, s.length());
        stringBuffer.append(s, 0, n);
        return stringBuffer.toString();
    }

}
