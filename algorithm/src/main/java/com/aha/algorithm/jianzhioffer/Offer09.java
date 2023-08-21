package com.aha.algorithm.jianzhioffer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 剑指 offer 09题：
 * 原题地址：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * <p>
 * 题目描述：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * 示例 1：输入 ["CQueue","appendTail","deleteHead","deleteHead"], [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * <p>
 * 实例 2：
 * 输入：["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]，[[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * <p>
 * 题目分析：读题的时候可能有点懵，因为题目过于简短，其实这道题的意思就是要求你用两个栈，来实现一个队列的功能，主要是 appendTail 和 deleteHead
 * 这两个功能；然后来分析一下示例，第一个输入参数为 ["CQueue","appendTail","deleteHead","deleteHead"] 对应的是操作类型，第二个参数列表
 * 是操作类型对应的输入参数 [[],[3],[],[]]；整体就是取第一个参数的 CQueue 和第二个参数的 [] ，指的是 new CQueue 然传入的参数为 [] 就是不需要参数
 * 然后便是 appendTail(3), deleteHead(), deleteHead(), 然后这四步对应的输出便是 [null,null,3,-1]
 *
 * @author WT
 * @date 2022/01/07 11:08:34
 */
public class Offer09 {

    LinkedList<Integer> stack1;

    public Offer09() {
        stack1 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.add(value);
    }

    public int deleteHead() {
        if (stack1.isEmpty()) {
            return -1;
        }
        Integer first = stack1.getFirst();
        stack1.remove();
        return first;
    }

}

/**
 * 第一种写法是使用一个 list 实现的，可以实现功能，但是不太符合题意
 * 使用两个栈来实现一个队列的功能，栈是先进后出，队列是先进先出，将压到一个栈中的数据弹出来，压入到另外一个栈，对于输入来说就实现了先进先出
 * 有点类似于 负负得正的感觉
 *
 * @author WT
 * @date 2022/01/07 11:52:57
 */
class Offer09_2 {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public Offer09_2() {
        // stack1 负责压入数据
        stack1 = new Stack<>();
        // stack2 负责弹出数据
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        // 无脑压入 stack1 中
        stack1.push(value);
    }

    public int deleteHead() {

        // 判断 stack2 是否为空
        if (stack2.isEmpty()) {
            // stack2 为空的话 判断 stack1 是否为空
            if (stack1.isEmpty()) {
                // 都为空 说明没有数据 返回 -1
                return -1;
            }
            // stack1 不为空 要将stack1 中数据存放到stack2 中 已实现队列的先进后出
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // 弹出 stack2 的数据即可
        return stack2.pop();

    }

}
