package com.aha.algorithm.jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 * 从上到下打印二叉树 - 2
 * 这 2 与 1 不同的点就是 每一层要返回一个单独的 list, 利用 for 循环 queue 的长度来实现
 *
 * @author WT
 * @date 2022/01/17 09:35:28
 */
public class Offer32_2 {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每次 while 循环就是一层，新创建一个 list
            ArrayList<Integer> temp = new ArrayList<>();
            // 每次 for 循环的次数为 queue 的长度，达到循环一层的效果，因为每次 for 循环的时候会把那一层所有的 左子树以及右子树添加进去，
            // 所以每次 for 循环，queue 的长度就是应该循环的次数
            // 注意：我们在 for 循环中改变了 queue 的长度，所以我们不能直接在 for 循环的条件中用 i<queue.size,应该先获取在使用
            // 不然每次都会重新判断 queue 的长度
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode tempNode = queue.poll();
                if (tempNode == null) {
                    break;
                }
                temp.add(tempNode.val);
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
            }
            result.add(temp);

        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        List<List<Integer>> lists = new Offer32_2().levelOrder(root);
        System.out.println(lists);
    }

}
