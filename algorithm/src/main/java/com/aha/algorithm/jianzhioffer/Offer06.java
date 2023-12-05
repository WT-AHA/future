package com.aha.algorithm.jianzhioffer;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * 从尾到头打印链表, 反向打印链表
 *
 * @author WT
 * @date 2022/01/10 09:11:10
 */
public class Offer06 {

    /**
     * @date 2023/12/05 没啥好说的就是确认链表的长度 然后在遍历一次进行反转即可
     */
    public int[] reverseBookList(ListNode head) {

        // 边界值判断
        if (head == null) {
            return new int[0];
        }

        // 返回的是一个 int[] 所以只要知道链表的长度就好了
        int size = 0;

        ListNode current = head;
        while (current != null) {
            size ++;
            current = current.next;
        }

        int[] result = new int[size];
        current = head;
        while (current != null) {
            result[size-1] = current.val;
            size --;
            current = current.next;
        }

        return result;

    }

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    /**
     * 自己刚开始的思路 - 使用 LinkedList 保证有序进行存储 然后在遍历 list 放到数组中
     * 直接放到 linkedList 是为了确定链表的长度，并且进行转换
     * 优化思路 - 如果不使用 linkedList 直接进行长度的累加 也可以确定数组的长度来进行翻转
     * @param head 头节点
     * @return 要求的数组
     */
    public int[] reversePrint1(ListNode head) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        while (head != null) {
            linkedList.add(head.val);
            head = head.next;
        }

        int[] result = new int[linkedList.size()];
        int j = linkedList.size() - 1;
        for (Integer temp : linkedList) {
            result[j] = temp;
            j --;
        }

        return result;
    }

    /**
     * 这种方法速度是快了，但是没有节省空间，相当于是存了两个链表的信息，上面的那种方法只存了一次链表的信息加上链表的value
     * @param head 头结点
     * @return 要求的数组
     */
    public int[] reversePrint(ListNode head) {

        // 确定链表的长度
        int count = 0;
        ListNode node = head;
        while (node != null) {
            count ++;
            node = node.next;
        }

        int[] result = new int[count];
        while (head != null) {
            result[count-1] = head.val;
            head = head.next;
            count --;
        }

        return result;
    }

}
