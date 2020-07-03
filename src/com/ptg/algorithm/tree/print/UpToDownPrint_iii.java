package com.ptg.algorithm.tree.print;

import com.ptg.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UpToDownPrint_iii {
    // https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if(level % 2 == 1) {
                    list.add(node.val);
                } else {
                    list.addFirst(node.val);
                }

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
            result.add(list);
        }
        return result;
    }

}
