package com.ptg.algorithm.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
public class Leetcode_46_permutations {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;

        if (len == 0) return result;
        boolean[] used = new boolean[len];
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, len, 0, path, used);
        return result;
    }

    private void dfs(int[] nums, int len, int depth,
                     LinkedList<Integer> path, boolean[] used) {

        // 递归终止的条件
        if (depth == len) {
            result.add(new LinkedList<>(path));
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used);
                used[i] = false;
                path.removeLast();
            }
        }

    }
}
