package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * 替换字符串中的空格
 *
 * @author WT
 * @date 2022/01/10 17:53:49
 */
public class Offer05 {

    /**
     * 此方法不满足，直接使用下面的方法即可，没有那么多弯弯绕绕
     * 将字符串中的空格转化为 %20，直接按照 空格 进行字符串的切割，然后在拼一下返回即可，如果整个字符串都是 空格，或者 空格在第一个字符或者最后一个字符这种写法就不满足
     * @param s 输入字符串
     * @return 替换之后的字符串
     */
    public String replaceSpace1(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = s.split(" ");
        for (int i=0; i<strArr.length; i++) {
            stringBuffer.append(strArr[i]);
            if (i != strArr.length-1) {
                stringBuffer.append("%20");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 那就直接将字符串转换为 字符数组，遍历字符数据如果是空则进行相应的处理
     * @param s 输入字符串
     * @return 替换之后的字符串
     */
    public String replaceSpace(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == ' ') {
                stringBuffer.append("%20");
            } else {
                stringBuffer.append(s.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

}
