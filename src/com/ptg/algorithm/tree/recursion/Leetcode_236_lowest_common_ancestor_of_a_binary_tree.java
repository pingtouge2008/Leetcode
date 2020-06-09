package com.ptg.algorithm.tree.recursion;


import com.ptg.algorithm.tree.TreeNode;

public class Leetcode_236_lowest_common_ancestor_of_a_binary_tree {
    // https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*if (root.val == p.val || root.val == q.val) return root;

        if (root.left == null) return lowestCommonAncestor(root.right, p, q);
        if (root.right == null) return lowestCommonAncestor(root.left, p, q);


        if (isExist(root.left, p) && isExist(root.left, q)) return lowestCommonAncestor(root.left, p, q);
        if (isExist(root.right, p) && isExist(root.right, q)) return lowestCommonAncestor(root.right, p, q);*/

        if (root.val == p.val || root.val == q.val) return root;

        if ( (root.left == null) || (isExist(root.right, p) && isExist(root.right, q)) ) return lowestCommonAncestor(root.right, p, q);
        if ( (root.right == null) || (isExist(root.left, p) && isExist(root.left, q)) ) return lowestCommonAncestor(root.left, p, q);


        return root;

    }

    private boolean isExist(TreeNode root, TreeNode find) {
        if (root == null) return false;
        if (root.val == find.val) return true;
        return isExist(root.left, find) || isExist(root.right, find);

    }

}
