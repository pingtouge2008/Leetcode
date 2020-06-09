package com.ptg.algorithm.tree.recursion;

import com.ptg.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_113_path_sum_ii {

    // https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/

    private List<List<Integer>> list = new ArrayList<>();


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return list;

        pathSum(root, sum, new LinkedList<Integer>());

        return list;
    }


    public void pathSum(TreeNode root, int target, LinkedList<Integer> p) {
        if (root == null) return;
        target -= root.val;
        p.add(root.val);


        if (target == 0 && root.left == null && root.right == null) {
            list.add(new ArrayList(p));
        } else {
            pathSum(root.left, target, p);
            pathSum(root.right, target, p);
        }


        p.removeLast();
    }
}
