package com.ptg.algorithm.tree.recursion;

//给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看
//做它自身的一棵子树。
//
// 示例 1:
//给定的树 s:
//
//
//     3
//    / \
//   4   5
//  / \
// 1   2
//
//
// 给定的树 t：
//
//
//   4
//  / \
// 1   2
//
//
// 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
//
// 示例 2:
//给定的树 s：
//
//
//     3
//    / \
//   4   5
//  / \
// 1   2
//    /
//   0
//
//
// 给定的树 t：
//
//
//   4
//  / \
// 1   2
//
//
// 返回 false。
// Related Topics 树

import com.ptg.algorithm.tree.TreeNode;

public class Leetcode_572_is_sub_tree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;

        return isSub(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);

    }

    /**
     * 判断以s为根节点的树 与 以t为根节点的树 是否一样
     * @param s
     * @param t
     * @return
     */
    private boolean isSub(TreeNode s, TreeNode t) {
        if (s == null && t != null) return false;
        if (s != null && t == null) return false;
        if (s == null && t == null) return true;
        if (s.val == t.val) {
            return isSub(s.left, t.left) && isSub(s.right, t.right);
        }
        return false;
    }
}
