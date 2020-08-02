package com.ptg.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


//给定一个可包含重复数字的序列，返回所有不重复的全排列。
//
// 示例:
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//]
// Related Topics 回溯算法
public class Leetcode_47_permutations_ii {

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (len == 0) return result;
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        LinkedList<Integer> path = new LinkedList<>();

        dfs(nums, len, 0, path, used);

        return result;
    }

    private void dfs(int[] nums, int len, int depth, LinkedList<Integer> path, boolean[] used) {
        if (depth == len) {
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Leetcode_47_permutations_ii solution = new Leetcode_47_permutations_ii();
        int[] nums = {1, 1, 1, 2};
        List<List<Integer>> res = solution.permuteUnique(nums);
        System.out.println(res);
    }

}
