package com.aha.algorithm.jianzhioffer;

/**
 * 题目原地址：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 * 题目描述：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 示例：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 * @author WT
 * @date 2022/01/07 16:49:42
 */
public class Offer30 {

    /**
     * 头节点
     */
    private Node head;

    private static class Node {
        int value;
        // 为了保证 min 方法是 O(1) 的复杂度，在每一个节点上都存储一个 min 字段
        int min;
        // 因为栈是先进后出，所以定义 next 节点
        Node next;

        public Node(int value, int min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }

    public Offer30() {
    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
        } else {
            head = new Node(x, Math.min(x, head.min) , head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int min() {
        return head.min;
    }

    public static void main(String[] args) {
        Offer30 stack = new Offer30();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.min());
    }
    
}
