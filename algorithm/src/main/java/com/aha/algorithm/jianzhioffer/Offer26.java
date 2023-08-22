package com.aha.algorithm.jianzhioffer;

/**
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 * 树的子结构
 *
 * @author WT
 * @date 2022/01/18 09:09:29
 */
public class Offer26 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 主要思路是使用递归
     * 这边使用的是将一个树进行先序遍历，拆成多个子树，每个子树都会进行判断看看是否包含 B
     *
     * @param A 树
     * @param B 子树
     * @return A 树是否包含 B 子树
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        // 判断当前的 A 节点是否包含 B 节点，或者 A 的左子树是否包含 B 节点，或者 A 的右子树是否包含 B 节点 满足一个即可
        return include(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 判断传递过来的 father 树，是否包含 son 树
     *
     * @param father 树
     * @param son 树
     * @return boolean father 是否包含 son 数
     */
    private boolean include (TreeNode father, TreeNode son) {
        if (son == null) {
            // 说明已经遍历到 son 的叶子节点之后的节点了，father 的节点都是包含 son 的节点，所以应该返回 true
            return true;
        }
        if (father == null || father.val != son.val) {
            // 说明已经遍历到了 father 的叶子节点之后的节点了，或者 father 的当前节点不等于 son 的当前节点的值，所以应该返回 false
            return false;
        }
        // 递归比较，直到 father 节点为空，或者 son 节点为空，或者找到 father 的当前节点不等于 son 当前节点的情况
        return include(father.left, son.left) && include(father.right, son.right);

    }

}
