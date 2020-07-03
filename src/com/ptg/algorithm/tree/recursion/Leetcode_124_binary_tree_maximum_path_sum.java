package com.ptg.algorithm.tree.recursion;

import com.ptg.algorithm.tree.TreeNode;

public class Leetcode_124_binary_tree_maximum_path_sum {

    // https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getMax(root);
        return max;
    }

    /*
     * 返回以root为根节点的最大单边分支 即: max(root.val+root.left_max, root.val+root.right_max)
     */
    private int getMax(TreeNode root) {
        if (root == null) return 0;
        int leftMax = Math.max(0, getMax(root.left)); // 如果root左子树路径最大值< 0, 就不要它了
        int rightMax = Math.max(0, getMax(root.right));
        max = Math.max(max, root.val + leftMax + rightMax);
        return Math.max(leftMax, rightMax) + root.val;

    }

}
