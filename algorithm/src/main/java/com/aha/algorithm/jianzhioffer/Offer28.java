package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 * 对称的二叉树
 *
 * @author WT
 * @date 2022/01/19 09:26:22
 */
public class Offer28 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return judge(root.left, root.right);
    }

    private boolean judge (TreeNode left, TreeNode right) {
        // 说明遍历到叶子节点之后了
        if (left == null && right == null) {
            return true;
        }
        // left 或者 right 有一个单独为空，或者 left.val 不等于 right.val 说明不是镜像的
        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        // 递归进行镜像的判断
        return judge(left.left, right.right) && judge(left.right, right.left);
    }

}
