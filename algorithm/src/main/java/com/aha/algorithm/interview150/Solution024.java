package com.aha.algorithm.interview150;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150
 *
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 注意:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 *
 * 示例 1:
 *
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 *
 * 提示:
 *
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] 由小写英文字母和符号组成
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 *
 */
public class Solution024 {

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();
        int right = 0;
        int length = words.length;

        while (true) {

            // left 为当前行的第一个单词在 words 的位置
            int left = right;
            // 统计这一行单词长度之和
            int sumLen = 0;
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            // right - left 单词之间至少有一个空格
            while (right < length && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // right == length 说明已经过了最后一个单词，回溯前一个单词进行组装，形成最后一行
            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == length) {
                StringBuffer sb = join(words, left, length, " ");
                sb.append(blank(maxWidth - sb.length()));
                result.add(sb.toString());
                return result;
            }

            // 当前行可以容纳的单词数
            int numWords = right - left;
            // 当前行剩余可以分配的空格数
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                // 空格全部放到单词的后面，只有一个单词的时候  right = left 所以不需要加单词之间的空格数，直接加上 numSpaces 即可
                sb.append(blank(numSpaces));
                result.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            // 每个单词之间先平均分摊空格数
            int avgSpaces = numSpaces / (numWords - 1);
            // 剩余的空格数据，从前往后分配，分配到 extraSpaces 的位置
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            // 从 left 到 left + extraSpaces + 1 的位置  每个单词中间有 avgSpaces + 1 个空格
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            // 从 left + extraSpaces + 1 到 right 填充 avgSpaces 个空格
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            result.add(sb.toString());

        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }


}
