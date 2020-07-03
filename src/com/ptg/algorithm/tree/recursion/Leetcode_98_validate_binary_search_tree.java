package com.ptg.algorithm.tree.recursion;

import com.ptg.algorithm.tree.TreeNode;

import java.util.Stack;

public class Leetcode_98_validate_binary_search_tree {

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        long inorder = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;

        }

        return true;

    }

    public boolean isValidBST_1(TreeNode root) {
        return isValid(root, null, null);

    }
    private boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        int val = root.val;
        if (min != null && val <= min) return false;
        if (max != null && val >= max) return false;

        if (! isValid(root.right, val, max)) return false;
        if (! isValid(root.left, min, val)) return false;
        return true;
    }
}
