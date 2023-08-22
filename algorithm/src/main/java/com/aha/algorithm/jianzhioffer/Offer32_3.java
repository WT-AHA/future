package com.aha.algorithm.jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 * 从上到下打印二叉树 - 3
 *
 * @author WT
 * @date 2022/01/17 11:11:47
 */
public class Offer32_3 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 是在 32_2 的基础上进行演化的，第一反应就是添加一个标志位进行判断，结合 linkedList 的 addFirst 和 addLast 来实现
     * 第一行从左到右，第二行从右到左，第三行从左到右以此类推
     * @param root 根节点
     * @return list 集合
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }
        // 是不是从左边进行循环
        boolean left = false;
        Queue<TreeNode> queue = new LinkedList<>();
        // 构建完成的 result
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode == null) {
                    continue;
                }
                // 使用 linkedList 的 addFirst 和 addLast 来实现
                if (left) {
                    levelList.addFirst(currentNode.val);
                } else {
                    levelList.addLast(currentNode.val);
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }

            }
            left = !left;
            result.add(levelList);
        }
        return result;
    }


}
