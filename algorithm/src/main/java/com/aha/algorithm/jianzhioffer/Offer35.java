package com.aha.algorithm.jianzhioffer;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 * 复制复杂链表
 *
 * @author WT
 * @date 2022/01/11 09:29:20
 */
public class Offer35 {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 简单链表的复制 - 复制节点的时候，每一个被添加到新链表中的节点都是新 new 出来的节点
     * 关键点是 前置节点和头结点，当前节点只是为了不改变传递过来的原本的 链表
     * 前置节点和头结点在初始化的时候都是初始化成 new Node(0) 所以在返回复制好的头结点 headNode 的时候应该是 headNode.next
     * 前置节点的作用是将 new 出来的节点放到链表中，头结点的作用是保存头结点的位置。
     *
     * @param head 头结点
     * @return 复制好的头结点
     */
    public Node copy(Node head) {

        if (head == null) {
            return null;
        }

        // 初始化前置节点和复制完成之后的头节点
        Node preNode = new Node(0);
        Node headNode = preNode;
        // 初始化当前节点 为了不改变原本的头结点
        Node currentNode = head;

        while (currentNode != null) {
            // 前置节点的下一个节点应该是当前节点为值构建的节点
            Node node = new Node(currentNode.val);
            preNode.next = node;
            // 前置节点后移一位
            preNode = node;
            // 当前节点后移一位
            currentNode = currentNode.next;
        }

        // 初始化 headNode 的时候是初始化了一个无关节点 new Node(0)，所以它的下一个节点才是真正的头结点
        return headNode.next;

    }

    /**
     * 复杂链表的复制 - 与简单链表复制不同的就是 复杂链表 多了一个 randomNode - 如果不遍历一遍整个链表的话是没有办法确定 random 的指向的
     * 解题思路： 将整个链表遍历一遍，将原有链表的各个节点作为 key ，将新的以原有节点值构建的新的节点作为 value 一一对应放入 hash 表中
     * 然后在遍历一遍 hash 表(在Java中使用 hashMap)，原有链表的每个节点的 next 和 random 都是构建好的，这个时候它是 key，新的链表的节点又是与之一一对应的所以
     * 使用原有链表的 next 进行 get 操作出来的应该就是新链表节点的 next；random 同理
     *
     * 这样需要遍历一遍链表，遍历一遍 hash 表，这样时间复杂度就是 O(n), 需要存储多余的一个新老链表意一一对应的 hash 表所以空间复杂度也是 O(n)
     * @param head 老链表的头结点
     * @return 新链表的头结点
     */
    public Node copyRandomList(Node head) {

        Node currentNode = head;
        HashMap<Node, Node> map = new HashMap<>();

        // 将新老链表的节点 一一对应的放入到 hash 表中
        while (currentNode != null) {
            map.put(currentNode, new Node(currentNode.val));
            currentNode = currentNode.next;
        }

        // 重置当前节点，遍历老链表，构建新链表的 next 和 random 指向问题
        currentNode = head;
        while (currentNode != null) {
            // 构建 next 节点
            map.get(currentNode).next = map.get(currentNode.next);
            // 构建 random 节点
            map.get(currentNode).random = map.get(currentNode.random);
            currentNode = currentNode.next;
        }

        // 返回新构建好的新链表的头结点
        return map.get(head);

    }

    /**
     * 使用 拼接+拆分 的方法来解决此问题 - 问题的核心仍然是如何找到新链表的 random 节点
     * 解决问题的核心 仍然是 根据老链表的 random 节点 来确定 新链表的 random 节点
     * 我们在 老链表的每一个节点后面都设置一个 新的节点；那么新链表的 random 节点，就是老链表 random 节点的 next 节点
     * @param head 老链表头节点
     * @return 新链表头结点
     */
    public Node copyRandomList1(Node head) {

        if (head == null) {
            return null;
        }

        Node currentNode = head;
        // 给原有的链表添加新链表的 node
        while (currentNode != null) {
            // 构建新链表的节点
            Node newNode = new Node(currentNode.val);
            // 设置新节点的下一个节点
            newNode.next = currentNode.next;
            // 当前节点的下一个节点应该是 新创建的节点
            currentNode.next = newNode;
            // 当前节点后移两位，因为当前节点的下一个节点是新链表的节点
            currentNode = currentNode.next.next;
        }

        // 重置 currentNode
        currentNode = head;
        // 构建 链表节点的 random
        while (currentNode != null) {

            if (currentNode.random != null) {
                // 不为 null 的时候：新节点的 random 为 当前节点的 random 的 next，为 null 的话新节点的 random 也应该为null 所以就不用设置了
                currentNode.next.random = currentNode.random.next;
            }
            // 当前节点后移两位
            currentNode = currentNode.next.next;
        }

        // 拆分链表 - 需要将原本的链表也还原一下
        // 重置 currentNode 直接从新链表的节点开始
        Node preNode =head;
        Node resultHeadNode = head.next;
        currentNode = head.next;
        while(currentNode.next != null) {
            preNode.next = preNode.next.next;
            currentNode.next = currentNode.next.next;
            preNode = preNode.next;
            currentNode = currentNode.next;
        }

        // 单独处理原链表尾节点
        preNode.next = null;
        return resultHeadNode;

    }

    public static void main(String[] args) {
        // ======= 测试复制普通链表 =========
        Node head = new Node(1);
        head.next = new Node(2);
        head.random = null;
        head.next.next = new Node(3);
        head.next.random = new Node(22);

        Node copy = new Offer35().copyRandomList(head);
        System.out.println(copy);
        // ======= 结束测试复制普通链表 =========
    }


}
