package com.aha.algorithm.jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 * 从上到下打印二叉树
 *
 * @author WT
 * @date 2022/01/14 10:44:19
 */
public class Offer32_1 {

    /**
     * 二叉树的数据结构
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 层序遍历 - 广度优先 BFS Breadth First Search - 借助队列
     * @param root 二叉树的根节点
     * @return 二叉树值组成的数组
     */
    public int[] levelOrder(TreeNode root) {

        if (root == null) {
            return new int[0];
        }

        // 注意：linkedList 是实现了 Deque 接口的， Deque 实现了 Queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 注意：arrayList 是有序的, 用来存储 value，可以通过它的长度来确定 数组的长度
        ArrayList<Integer> arrayList = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode currentTreeNode = queue.poll();
            arrayList.add(currentTreeNode.val);
            // 如果存在左子树或者右子树，将他们放入到队列中，借助队列先进先出的特性
            if (currentTreeNode.left != null) {
                queue.add(currentTreeNode.left);
            }
            if (currentTreeNode.right != null) {
                queue.add(currentTreeNode.right);
            }
        }

        int[] result = new int[arrayList.size()];
        for (int i=0; i<arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }

        return result;
    }

}
