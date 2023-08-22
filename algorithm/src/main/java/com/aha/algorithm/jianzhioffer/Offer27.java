package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 * 二叉树的镜像
 * 输入一个二叉树，输出一个左右节点调换的二叉树镜像
 *
 * @author WT
 * @date 2022/01/18 11:06:14
 */
public class Offer27 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 第一反应是使用 先序遍历(递归) 然后调转左右子节点
     * @param root 根节点
     * @return 镜像后的根节点
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode result = new TreeNode(root.val);
        handle(result, root);
        return result;
    }

    /**
     * 递归 - 处理所有涉及的节点
     * @param fresh 新的节点
     * @param old 原本的节点
     */
    private void handle (TreeNode fresh, TreeNode old) {
        // 如果原本的节点为 null 的话说明已经构建完了，直接 return
        if (old == null) {
            return;
        }
        // 将原本的右节点变成新节点的左节点, 并且进行递归操作
        if (old.right != null) {
            fresh.left = new TreeNode(old.right.val);
            handle(fresh.left, old.right);
        }
        // 将原本的左节点变成新节点的右节点，并且进行递归操作
        if (old.left != null) {
            fresh.right = new TreeNode(old.left.val);
            handle(fresh.right, old.left);
        }
    }

}
