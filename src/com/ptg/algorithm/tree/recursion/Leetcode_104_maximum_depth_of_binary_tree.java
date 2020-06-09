package com.ptg.algorithm.tree.recursion;

import com.ptg.algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Leetcode_104_maximum_depth_of_binary_tree<depth> {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int depth = 0, currentLevelElemCnt = 0;

        TreeNode tmp = null;
        while (!queue.isEmpty()) {
            currentLevelElemCnt = queue.size();
            while (currentLevelElemCnt > 0) {
                tmp = queue.poll();
                if (tmp.left != null) queue.add(tmp.left);
                if (tmp.right != null) queue.add(tmp.right);
                currentLevelElemCnt--;
            }
            depth++;

        }
        return depth;
    }


}
