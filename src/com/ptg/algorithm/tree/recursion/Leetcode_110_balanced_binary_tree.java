package com.ptg.algorithm.tree.recursion;

import com.ptg.algorithm.tree.TreeNode;

public class Leetcode_110_balanced_binary_tree {
    // https://leetcode-cn.com/problems/balanced-binary-tree/
    // https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/

    public boolean isBalanced2(TreeNode root) {
        if (root == null) return true;
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left); // left的大小表示以left为根节点的树的高度
        if (left == -1) return -1; //返回-1表示以root.left为根节点的树不是平衡的
        int right = recur(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) <= 1 ? Math.max(left, right) : -1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(height(root.right) - height(root.left)) > 1) return false;
        return isBalanced(root.right) && isBalanced(root.left);
    }

    public int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.right), height(root.left)) + 1;
    }

}
