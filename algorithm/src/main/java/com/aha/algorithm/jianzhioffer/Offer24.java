package com.aha.algorithm.jianzhioffer;

import java.util.LinkedList;

/**
 * 反转链表：
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 *
 * @author WT
 * @date 2022/01/10 10:16:00
 */
public class Offer24 {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    /**
     * 最简单写法肯定是将 val 都拿出来存到 linkedList 中，然后在重新遍历一下遍历一下 LinkedList 重新生成一个链表
     * 执行效率和内存都比较低下 - 需要优化
     *
     * @param head 头节点
     * @return 反向链表的头结点
     */
    public ListNode reverseList1(ListNode head) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        while (head != null) {
            linkedList.add(head.val);
            head = head.next;
        }

        ListNode temp = null;
        // 记录头结点
        ListNode resultHead = null;
        for (int i=linkedList.size()-1; i>=0; i--) {
            if (temp == null) {
                temp = new ListNode(linkedList.get(i));
                // 存储头结点
                resultHead = temp;
            } else {
                // 构建每一个节点
                temp.next = new ListNode(linkedList.get(i));
                // 将节点后一位
                temp = temp.next;
            }
        }

        return resultHead;

    }

    /**
     * 优化思路- 尽量少的使用多余的变量(就是尽量少的使用 new 关键字，引用指来指去的是不怎么消耗空间的)，尽量减少遍历 链表的次数
     * 解题思路：还是从前往后进行链表的遍历，存储当前节点和下一个节点，将当前节点的 next 变成当前节点的前一个节点，如果当前节点为头结点
     * 那么当前节点的 next 应该是 null
     * 在 leetCode 中提交的时候将注释给去掉
     * @param head 头节点
     * @return 反转的头结点
     */
    public ListNode reverseList(ListNode head) {

        // 当前节点的前一个节点
        ListNode preNode = null;
        // 当前指向的节点
        ListNode currentNode = head;
        while (currentNode != null) {
            // 暂存下一个节点
            ListNode nextNode = currentNode.next;
            // 当前节点指向前一个节点
            currentNode.next = preNode;
            // 现在的前置节点应该是原本的当前节点
            preNode = currentNode;
            // 现在的当前节点应该是原本的下一个节点
            currentNode = nextNode;

        }

        return preNode;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode listNode = new Offer24().reverseList(head);
        System.out.println(listNode);
    }

}
